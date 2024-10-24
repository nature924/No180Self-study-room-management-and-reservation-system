
package com.controller;

import java.io.File;
import java.math.BigDecimal;
import java.net.URL;
import java.text.SimpleDateFormat;
import com.alibaba.fastjson.JSONObject;
import java.util.*;
import org.springframework.beans.BeanUtils;
import javax.servlet.http.HttpServletRequest;
import org.springframework.web.context.ContextLoader;
import javax.servlet.ServletContext;
import com.service.TokenService;
import com.utils.*;
import java.lang.reflect.InvocationTargetException;

import com.service.DictionaryService;
import org.apache.commons.lang3.StringUtils;
import com.annotation.IgnoreAuth;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.entity.*;
import com.entity.view.*;
import com.service.*;
import com.utils.PageUtils;
import com.utils.R;
import com.alibaba.fastjson.*;

/**
 * 自习室信息
 * 后端接口
 * @author
 * @email
*/
@RestController
@Controller
@RequestMapping("/zixishi")
public class ZixishiController {
    private static final Logger logger = LoggerFactory.getLogger(ZixishiController.class);

    private static final String TABLE_NAME = "zixishi";

    @Autowired
    private ZixishiService zixishiService;


    @Autowired
    private TokenService tokenService;

    @Autowired
    private ChatService chatService;//客服聊天
    @Autowired
    private DictionaryService dictionaryService;//字典表
    @Autowired
    private ForumService forumService;//论坛
    @Autowired
    private NewsService newsService;//通知公告
    @Autowired
    private YonghuService yonghuService;//用户
    @Autowired
    private ZixishiCollectionService zixishiCollectionService;//自习室收藏
    @Autowired
    private ZixishiLiuyanService zixishiLiuyanService;//自习室留言
    @Autowired
    private ZixishiOrderService zixishiOrderService;//订座订单
    @Autowired
    private UsersService usersService;//管理员


    /**
    * 后端列表
    */
    @RequestMapping("/page")
    public R page(@RequestParam Map<String, Object> params, HttpServletRequest request){
        logger.debug("page方法:,,Controller:{},,params:{}",this.getClass().getName(),JSONObject.toJSONString(params));
        String role = String.valueOf(request.getSession().getAttribute("role"));
        if(false)
            return R.error(511,"永不会进入");
        else if("用户".equals(role))
            params.put("yonghuId",request.getSession().getAttribute("userId"));
        params.put("zixishiDeleteStart",1);params.put("zixishiDeleteEnd",1);
        CommonUtil.checkMap(params);
        PageUtils page = zixishiService.queryPage(params);

        //字典表数据转换
        List<ZixishiView> list =(List<ZixishiView>)page.getList();
        for(ZixishiView c:list){
            //修改对应字典表字段
            dictionaryService.dictionaryConvert(c, request);
        }
        return R.ok().put("data", page);
    }

    /**
    * 后端详情
    */
    @RequestMapping("/info/{id}")
    public R info(@PathVariable("id") Long id, HttpServletRequest request){
        logger.debug("info方法:,,Controller:{},,id:{}",this.getClass().getName(),id);
        ZixishiEntity zixishi = zixishiService.selectById(id);
        if(zixishi !=null){
            //entity转view
            ZixishiView view = new ZixishiView();
            BeanUtils.copyProperties( zixishi , view );//把实体数据重构到view中
            //修改对应字典表字段
            dictionaryService.dictionaryConvert(view, request);
            return R.ok().put("data", view);
        }else {
            return R.error(511,"查不到数据");
        }

    }

    /**
    * 后端保存
    */
    @RequestMapping("/save")
    public R save(@RequestBody ZixishiEntity zixishi, HttpServletRequest request){
        logger.debug("save方法:,,Controller:{},,zixishi:{}",this.getClass().getName(),zixishi.toString());

        String role = String.valueOf(request.getSession().getAttribute("role"));
        if(false)
            return R.error(511,"永远不会进入");

        Wrapper<ZixishiEntity> queryWrapper = new EntityWrapper<ZixishiEntity>()
            .eq("zixishi_name", zixishi.getZixishiName())
            .eq("zixishi_types", zixishi.getZixishiTypes())
            .eq("zuowei_number", zixishi.getZuoweiNumber())
            .eq("zan_number", zixishi.getZanNumber())
            .eq("cai_number", zixishi.getCaiNumber())
            .eq("zixishi_delete", 1)
            ;

        logger.info("sql语句:"+queryWrapper.getSqlSegment());
        ZixishiEntity zixishiEntity = zixishiService.selectOne(queryWrapper);
        if(zixishiEntity==null){
            zixishi.setZixishiDelete(1);
            zixishi.setCreateTime(new Date());
            zixishiService.insert(zixishi);
            return R.ok();
        }else {
            return R.error(511,"表中有相同数据");
        }
    }

    /**
    * 后端修改
    */
    @RequestMapping("/update")
    public R update(@RequestBody ZixishiEntity zixishi, HttpServletRequest request) throws NoSuchFieldException, ClassNotFoundException, IllegalAccessException, InstantiationException {
        logger.debug("update方法:,,Controller:{},,zixishi:{}",this.getClass().getName(),zixishi.toString());
        ZixishiEntity oldZixishiEntity = zixishiService.selectById(zixishi.getId());//查询原先数据

        String role = String.valueOf(request.getSession().getAttribute("role"));
//        if(false)
//            return R.error(511,"永远不会进入");
        if("".equals(zixishi.getZixishiPhoto()) || "null".equals(zixishi.getZixishiPhoto())){
                zixishi.setZixishiPhoto(null);
        }

            zixishiService.updateById(zixishi);//根据id更新
            return R.ok();
    }



    /**
    * 删除
    */
    @RequestMapping("/delete")
    public R delete(@RequestBody Integer[] ids, HttpServletRequest request){
        logger.debug("delete:,,Controller:{},,ids:{}",this.getClass().getName(),ids.toString());
        List<ZixishiEntity> oldZixishiList =zixishiService.selectBatchIds(Arrays.asList(ids));//要删除的数据
        ArrayList<ZixishiEntity> list = new ArrayList<>();
        for(Integer id:ids){
            ZixishiEntity zixishiEntity = new ZixishiEntity();
            zixishiEntity.setId(id);
            zixishiEntity.setZixishiDelete(2);
            list.add(zixishiEntity);
        }
        if(list != null && list.size() >0){
            zixishiService.updateBatchById(list);
        }

        return R.ok();
    }


    /**
     * 批量上传
     */
    @RequestMapping("/batchInsert")
    public R save( String fileName, HttpServletRequest request){
        logger.debug("batchInsert方法:,,Controller:{},,fileName:{}",this.getClass().getName(),fileName);
        Integer yonghuId = Integer.valueOf(String.valueOf(request.getSession().getAttribute("userId")));
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            List<ZixishiEntity> zixishiList = new ArrayList<>();//上传的东西
            Map<String, List<String>> seachFields= new HashMap<>();//要查询的字段
            Date date = new Date();
            int lastIndexOf = fileName.lastIndexOf(".");
            if(lastIndexOf == -1){
                return R.error(511,"该文件没有后缀");
            }else{
                String suffix = fileName.substring(lastIndexOf);
                if(!".xls".equals(suffix)){
                    return R.error(511,"只支持后缀为xls的excel文件");
                }else{
                    URL resource = this.getClass().getClassLoader().getResource("static/upload/" + fileName);//获取文件路径
                    File file = new File(resource.getFile());
                    if(!file.exists()){
                        return R.error(511,"找不到上传文件，请联系管理员");
                    }else{
                        List<List<String>> dataList = PoiUtil.poiImport(file.getPath());//读取xls文件
                        dataList.remove(0);//删除第一行，因为第一行是提示
                        for(List<String> data:dataList){
                            //循环
                            ZixishiEntity zixishiEntity = new ZixishiEntity();
//                            zixishiEntity.setZixishiName(data.get(0));                    //自习室标题 要改的
//                            zixishiEntity.setZixishiPhoto("");//详情和图片
//                            zixishiEntity.setZixishiTypes(Integer.valueOf(data.get(0)));   //自习室类型 要改的
//                            zixishiEntity.setZuoweiNumber(Integer.valueOf(data.get(0)));   //座位 要改的
//                            zixishiEntity.setZanNumber(Integer.valueOf(data.get(0)));   //赞 要改的
//                            zixishiEntity.setCaiNumber(Integer.valueOf(data.get(0)));   //踩 要改的
//                            zixishiEntity.setZixishiDelete(1);//逻辑删除字段
//                            zixishiEntity.setZixishiContent("");//详情和图片
//                            zixishiEntity.setCreateTime(date);//时间
                            zixishiList.add(zixishiEntity);


                            //把要查询是否重复的字段放入map中
                        }

                        //查询是否重复
                        zixishiService.insertBatch(zixishiList);
                        return R.ok();
                    }
                }
            }
        }catch (Exception e){
            e.printStackTrace();
            return R.error(511,"批量插入数据异常，请联系管理员");
        }
    }



    /**
    * 个性推荐
    */
    @IgnoreAuth
    @RequestMapping("/gexingtuijian")
    public R gexingtuijian(@RequestParam Map<String, Object> params, HttpServletRequest request){
        logger.debug("gexingtuijian方法:,,Controller:{},,params:{}",this.getClass().getName(),JSONObject.toJSONString(params));
        CommonUtil.checkMap(params);
        List<ZixishiView> returnZixishiViewList = new ArrayList<>();

        //查询订单
        Map<String, Object> params1 = new HashMap<>(params);params1.put("sort","id");params1.put("yonghuId",request.getSession().getAttribute("userId"));
        PageUtils pageUtils = zixishiOrderService.queryPage(params1);
        List<ZixishiOrderView> orderViewsList =(List<ZixishiOrderView>)pageUtils.getList();
        Map<Integer,Integer> typeMap=new HashMap<>();//购买的类型list
        for(ZixishiOrderView orderView:orderViewsList){
            Integer zixishiTypes = orderView.getZixishiTypes();
            if(typeMap.containsKey(zixishiTypes)){
                typeMap.put(zixishiTypes,typeMap.get(zixishiTypes)+1);
            }else{
                typeMap.put(zixishiTypes,1);
            }
        }
        List<Integer> typeList = new ArrayList<>();//排序后的有序的类型 按最多到最少
        typeMap.entrySet().stream().sorted((o1, o2) -> o2.getValue() - o1.getValue()).forEach(e -> typeList.add(e.getKey()));//排序
        Integer limit = Integer.valueOf(String.valueOf(params.get("limit")));
        for(Integer type:typeList){
            Map<String, Object> params2 = new HashMap<>(params);params2.put("zixishiTypes",type);
            PageUtils pageUtils1 = zixishiService.queryPage(params2);
            List<ZixishiView> zixishiViewList =(List<ZixishiView>)pageUtils1.getList();
            returnZixishiViewList.addAll(zixishiViewList);
            if(returnZixishiViewList.size()>= limit) break;//返回的推荐数量大于要的数量 跳出循环
        }
        //正常查询出来商品,用于补全推荐缺少的数据
        PageUtils page = zixishiService.queryPage(params);
        if(returnZixishiViewList.size()<limit){//返回数量还是小于要求数量
            int toAddNum = limit - returnZixishiViewList.size();//要添加的数量
            List<ZixishiView> zixishiViewList =(List<ZixishiView>)page.getList();
            for(ZixishiView zixishiView:zixishiViewList){
                Boolean addFlag = true;
                for(ZixishiView returnZixishiView:returnZixishiViewList){
                    if(returnZixishiView.getId().intValue() ==zixishiView.getId().intValue()) addFlag=false;//返回的数据中已存在此商品
                }
                if(addFlag){
                    toAddNum=toAddNum-1;
                    returnZixishiViewList.add(zixishiView);
                    if(toAddNum==0) break;//够数量了
                }
            }
        }else {
            returnZixishiViewList = returnZixishiViewList.subList(0, limit);
        }

        for(ZixishiView c:returnZixishiViewList)
            dictionaryService.dictionaryConvert(c, request);
        page.setList(returnZixishiViewList);
        return R.ok().put("data", page);
    }

    /**
    * 前端列表
    */
    @IgnoreAuth
    @RequestMapping("/list")
    public R list(@RequestParam Map<String, Object> params, HttpServletRequest request){
        logger.debug("list方法:,,Controller:{},,params:{}",this.getClass().getName(),JSONObject.toJSONString(params));

        CommonUtil.checkMap(params);
        PageUtils page = zixishiService.queryPage(params);

        //字典表数据转换
        List<ZixishiView> list =(List<ZixishiView>)page.getList();
        for(ZixishiView c:list)
            dictionaryService.dictionaryConvert(c, request); //修改对应字典表字段

        return R.ok().put("data", page);
    }

    /**
    * 前端详情
    */
    @RequestMapping("/detail/{id}")
    public R detail(@PathVariable("id") Long id, HttpServletRequest request){
        logger.debug("detail方法:,,Controller:{},,id:{}",this.getClass().getName(),id);
        ZixishiEntity zixishi = zixishiService.selectById(id);
            if(zixishi !=null){


                //entity转view
                ZixishiView view = new ZixishiView();
                BeanUtils.copyProperties( zixishi , view );//把实体数据重构到view中

                //修改对应字典表字段
                dictionaryService.dictionaryConvert(view, request);
                return R.ok().put("data", view);
            }else {
                return R.error(511,"查不到数据");
            }
    }


    /**
    * 前端保存
    */
    @RequestMapping("/add")
    public R add(@RequestBody ZixishiEntity zixishi, HttpServletRequest request){
        logger.debug("add方法:,,Controller:{},,zixishi:{}",this.getClass().getName(),zixishi.toString());
        Wrapper<ZixishiEntity> queryWrapper = new EntityWrapper<ZixishiEntity>()
            .eq("zixishi_name", zixishi.getZixishiName())
            .eq("zixishi_types", zixishi.getZixishiTypes())
            .eq("zuowei_number", zixishi.getZuoweiNumber())
            .eq("zan_number", zixishi.getZanNumber())
            .eq("cai_number", zixishi.getCaiNumber())
            .eq("zixishi_delete", zixishi.getZixishiDelete())
//            .notIn("zixishi_types", new Integer[]{102})
            ;
        logger.info("sql语句:"+queryWrapper.getSqlSegment());
        ZixishiEntity zixishiEntity = zixishiService.selectOne(queryWrapper);
        if(zixishiEntity==null){
                zixishi.setZanNumber(1);
                zixishi.setCaiNumber(1);
            zixishi.setZixishiDelete(1);
            zixishi.setCreateTime(new Date());
        zixishiService.insert(zixishi);

            return R.ok();
        }else {
            return R.error(511,"表中有相同数据");
        }
    }

}

