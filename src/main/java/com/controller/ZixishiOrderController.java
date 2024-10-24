
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
 * 订座订单
 * 后端接口
 * @author
 * @email
*/
@RestController
@Controller
@RequestMapping("/zixishiOrder")
public class ZixishiOrderController {
    private static final Logger logger = LoggerFactory.getLogger(ZixishiOrderController.class);

    private static final String TABLE_NAME = "zixishiOrder";

    @Autowired
    private ZixishiOrderService zixishiOrderService;


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
    private ZixishiService zixishiService;//自习室信息
    @Autowired
    private ZixishiCollectionService zixishiCollectionService;//自习室收藏
    @Autowired
    private ZixishiLiuyanService zixishiLiuyanService;//自习室留言
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
        CommonUtil.checkMap(params);
        PageUtils page = zixishiOrderService.queryPage(params);

        //字典表数据转换
        List<ZixishiOrderView> list =(List<ZixishiOrderView>)page.getList();
        for(ZixishiOrderView c:list){
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
        ZixishiOrderEntity zixishiOrder = zixishiOrderService.selectById(id);
        if(zixishiOrder !=null){
            //entity转view
            ZixishiOrderView view = new ZixishiOrderView();
            BeanUtils.copyProperties( zixishiOrder , view );//把实体数据重构到view中
            //级联表 自习室信息
            //级联表
            ZixishiEntity zixishi = zixishiService.selectById(zixishiOrder.getZixishiId());
            if(zixishi != null){
            BeanUtils.copyProperties( zixishi , view ,new String[]{ "id", "createTime", "insertTime", "updateTime", "yonghuId"});//把级联的数据添加到view中,并排除id和创建时间字段,当前表的级联注册表
            view.setZixishiId(zixishi.getId());
            }
            //级联表 用户
            //级联表
            YonghuEntity yonghu = yonghuService.selectById(zixishiOrder.getYonghuId());
            if(yonghu != null){
            BeanUtils.copyProperties( yonghu , view ,new String[]{ "id", "createTime", "insertTime", "updateTime", "yonghuId"});//把级联的数据添加到view中,并排除id和创建时间字段,当前表的级联注册表
            view.setYonghuId(yonghu.getId());
            }
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
    public R save(@RequestBody ZixishiOrderEntity zixishiOrder, HttpServletRequest request){
        logger.debug("save方法:,,Controller:{},,zixishiOrder:{}",this.getClass().getName(),zixishiOrder.toString());

        String role = String.valueOf(request.getSession().getAttribute("role"));
        if(false)
            return R.error(511,"永远不会进入");
        else if("用户".equals(role))
            zixishiOrder.setYonghuId(Integer.valueOf(String.valueOf(request.getSession().getAttribute("userId"))));

        zixishiOrder.setCreateTime(new Date());
        zixishiOrder.setInsertTime(new Date());
        zixishiOrderService.insert(zixishiOrder);

        return R.ok();
    }

    /**
    * 后端修改
    */
    @RequestMapping("/update")
    public R update(@RequestBody ZixishiOrderEntity zixishiOrder, HttpServletRequest request) throws NoSuchFieldException, ClassNotFoundException, IllegalAccessException, InstantiationException {
        logger.debug("update方法:,,Controller:{},,zixishiOrder:{}",this.getClass().getName(),zixishiOrder.toString());
        ZixishiOrderEntity oldZixishiOrderEntity = zixishiOrderService.selectById(zixishiOrder.getId());//查询原先数据

        String role = String.valueOf(request.getSession().getAttribute("role"));
//        if(false)
//            return R.error(511,"永远不会进入");
//        else if("用户".equals(role))
//            zixishiOrder.setYonghuId(Integer.valueOf(String.valueOf(request.getSession().getAttribute("userId"))));

            zixishiOrderService.updateById(zixishiOrder);//根据id更新
            return R.ok();
    }



    /**
    * 删除
    */
    @RequestMapping("/delete")
    public R delete(@RequestBody Integer[] ids, HttpServletRequest request){
        logger.debug("delete:,,Controller:{},,ids:{}",this.getClass().getName(),ids.toString());
        List<ZixishiOrderEntity> oldZixishiOrderList =zixishiOrderService.selectBatchIds(Arrays.asList(ids));//要删除的数据
        zixishiOrderService.deleteBatchIds(Arrays.asList(ids));

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
            List<ZixishiOrderEntity> zixishiOrderList = new ArrayList<>();//上传的东西
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
                            ZixishiOrderEntity zixishiOrderEntity = new ZixishiOrderEntity();
//                            zixishiOrderEntity.setZixishiOrderUuidNumber(data.get(0));                    //订单号 要改的
//                            zixishiOrderEntity.setZixishiId(Integer.valueOf(data.get(0)));   //自习室 要改的
//                            zixishiOrderEntity.setYonghuId(Integer.valueOf(data.get(0)));   //用户 要改的
//                            zixishiOrderEntity.setZixishiOrderTypes(Integer.valueOf(data.get(0)));   //订单类型 要改的
//                            zixishiOrderEntity.setBuyZuoweiNumber(data.get(0));                    //预定的座位 要改的
//                            zixishiOrderEntity.setBuyZuoweiTime(sdf.parse(data.get(0)));          //预定日期 要改的
//                            zixishiOrderEntity.setInsertTime(date);//时间
//                            zixishiOrderEntity.setCreateTime(date);//时间
                            zixishiOrderList.add(zixishiOrderEntity);


                            //把要查询是否重复的字段放入map中
                                //订单号
                                if(seachFields.containsKey("zixishiOrderUuidNumber")){
                                    List<String> zixishiOrderUuidNumber = seachFields.get("zixishiOrderUuidNumber");
                                    zixishiOrderUuidNumber.add(data.get(0));//要改的
                                }else{
                                    List<String> zixishiOrderUuidNumber = new ArrayList<>();
                                    zixishiOrderUuidNumber.add(data.get(0));//要改的
                                    seachFields.put("zixishiOrderUuidNumber",zixishiOrderUuidNumber);
                                }
                        }

                        //查询是否重复
                         //订单号
                        List<ZixishiOrderEntity> zixishiOrderEntities_zixishiOrderUuidNumber = zixishiOrderService.selectList(new EntityWrapper<ZixishiOrderEntity>().in("zixishi_order_uuid_number", seachFields.get("zixishiOrderUuidNumber")));
                        if(zixishiOrderEntities_zixishiOrderUuidNumber.size() >0 ){
                            ArrayList<String> repeatFields = new ArrayList<>();
                            for(ZixishiOrderEntity s:zixishiOrderEntities_zixishiOrderUuidNumber){
                                repeatFields.add(s.getZixishiOrderUuidNumber());
                            }
                            return R.error(511,"数据库的该表中的 [订单号] 字段已经存在 存在数据为:"+repeatFields.toString());
                        }
                        zixishiOrderService.insertBatch(zixishiOrderList);
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
    * 前端列表
    */
    @IgnoreAuth
    @RequestMapping("/list")
    public R list(@RequestParam Map<String, Object> params, HttpServletRequest request){
        logger.debug("list方法:,,Controller:{},,params:{}",this.getClass().getName(),JSONObject.toJSONString(params));

        CommonUtil.checkMap(params);
        PageUtils page = zixishiOrderService.queryPage(params);

        //字典表数据转换
        List<ZixishiOrderView> list =(List<ZixishiOrderView>)page.getList();
        for(ZixishiOrderView c:list)
            dictionaryService.dictionaryConvert(c, request); //修改对应字典表字段

        return R.ok().put("data", page);
    }

    /**
    * 前端详情
    */
    @RequestMapping("/detail/{id}")
    public R detail(@PathVariable("id") Long id, HttpServletRequest request){
        logger.debug("detail方法:,,Controller:{},,id:{}",this.getClass().getName(),id);
        ZixishiOrderEntity zixishiOrder = zixishiOrderService.selectById(id);
            if(zixishiOrder !=null){


                //entity转view
                ZixishiOrderView view = new ZixishiOrderView();
                BeanUtils.copyProperties( zixishiOrder , view );//把实体数据重构到view中

                //级联表
                    ZixishiEntity zixishi = zixishiService.selectById(zixishiOrder.getZixishiId());
                if(zixishi != null){
                    BeanUtils.copyProperties( zixishi , view ,new String[]{ "id", "createDate"});//把级联的数据添加到view中,并排除id和创建时间字段
                    view.setZixishiId(zixishi.getId());
                }
                //级联表
                    YonghuEntity yonghu = yonghuService.selectById(zixishiOrder.getYonghuId());
                if(yonghu != null){
                    BeanUtils.copyProperties( yonghu , view ,new String[]{ "id", "createDate"});//把级联的数据添加到view中,并排除id和创建时间字段
                    view.setYonghuId(yonghu.getId());
                }
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
    public R add(@RequestBody ZixishiOrderEntity zixishiOrder, HttpServletRequest request){
        logger.debug("add方法:,,Controller:{},,zixishiOrder:{}",this.getClass().getName(),zixishiOrder.toString());
            ZixishiEntity zixishiEntity = zixishiService.selectById(zixishiOrder.getZixishiId());
            if(zixishiEntity == null){
                return R.error(511,"查不到该自习室信息");
            }
            // Double zixishiNewMoney = zixishiEntity.getZixishiNewMoney();

            if(false){
            }
            List<String> buyZuoweiNumberList = new ArrayList<>(Arrays.asList(zixishiOrder.getBuyZuoweiNumber().split(",")));//这次购买的座位
            List<String> beforeBuyZuoweiNumberList = new ArrayList<>();//之前已经购买的座位

                    //某天日期的某个分段
            List<ZixishiOrderEntity> zixishiOrderEntityList =
                        zixishiOrderService.selectList(new EntityWrapper<ZixishiOrderEntity>()
                        .eq("zixishi_id", zixishiOrder.getZixishiId())
                        .eq("buy_zuowei_time", zixishiOrder.getBuyZuoweiTime())
                        .notIn("zixishi_order_types",102)//已取消的订单

                        );
            for(ZixishiOrderEntity d:zixishiOrderEntityList){
                beforeBuyZuoweiNumberList.addAll(Arrays.asList(d.getBuyZuoweiNumber().split(",")));
            }
            buyZuoweiNumberList.retainAll(beforeBuyZuoweiNumberList);//判断当前购买list包含已经被购买的list中是否有重复的  有的话 就保留
            if(buyZuoweiNumberList != null && buyZuoweiNumberList.size()>0 ){
                return R.error(511,buyZuoweiNumberList.toString()+" 的座位已经被使用");
            }

            //计算所获得积分
            Double buyJifen =0.0;
            Integer userId = (Integer) request.getSession().getAttribute("userId");
            YonghuEntity yonghuEntity = yonghuService.selectById(userId);
            zixishiOrder.setZixishiOrderTypes(101); //设置订单状态为已预约
            zixishiOrder.setYonghuId(userId); //设置订单支付人id
            zixishiOrder.setZixishiOrderUuidNumber(String.valueOf(new Date().getTime()));
            zixishiOrder.setInsertTime(new Date());
            zixishiOrder.setCreateTime(new Date());
                zixishiOrderService.insert(zixishiOrder);//新增订单


            return R.ok();
    }


    /**
    * 取消
    */
    @RequestMapping("/refund")
    public R refund(Integer id, HttpServletRequest request){
        logger.debug("refund方法:,,Controller:{},,id:{}",this.getClass().getName(),id);
        String role = String.valueOf(request.getSession().getAttribute("role"));

            ZixishiOrderEntity zixishiOrder = zixishiOrderService.selectById(id);//当前表service
            Integer buyNumber = zixishiOrder.getBuyZuoweiNumber().split(",").length;
            Integer zixishiId = zixishiOrder.getZixishiId();
            if(zixishiId == null)
                return R.error(511,"查不到该自习室信息");
            ZixishiEntity zixishiEntity = zixishiService.selectById(zixishiId);
            if(zixishiEntity == null)
                return R.error(511,"查不到该自习室信息");

            Integer userId = (Integer) request.getSession().getAttribute("userId");
            YonghuEntity yonghuEntity = yonghuService.selectById(userId);
            if(yonghuEntity == null)
                return R.error(511,"用户不能为空");
            Double zhekou = 1.0;


            zixishiOrder.setZixishiOrderTypes(102);//设置订单状态为已取消
            zixishiOrderService.updateById(zixishiOrder);//根据id更新
            yonghuService.updateById(yonghuEntity);//更新用户信息
            zixishiService.updateById(zixishiEntity);//更新订单中自习室信息的信息

            return R.ok();
    }

    /**
     * 完成
     */
    @RequestMapping("/deliver")
    public R deliver(Integer id  , HttpServletRequest request){
        logger.debug("refund:,,Controller:{},,ids:{}",this.getClass().getName(),id.toString());
        ZixishiOrderEntity  zixishiOrderEntity = zixishiOrderService.selectById(id);
        zixishiOrderEntity.setZixishiOrderTypes(103);//设置订单状态为已完成
        zixishiOrderService.updateById( zixishiOrderEntity);

        return R.ok();
    }


}

