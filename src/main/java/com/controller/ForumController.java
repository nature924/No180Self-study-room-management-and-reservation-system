
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
 * 论坛
 * 后端接口
 * @author
 * @email
*/
@RestController
@Controller
@RequestMapping("/forum")
public class ForumController {
    private static final Logger logger = LoggerFactory.getLogger(ForumController.class);

    private static final String TABLE_NAME = "forum";

    @Autowired
    private ForumService forumService;


    @Autowired
    private TokenService tokenService;

    @Autowired
    private ChatService chatService;//客服聊天
    @Autowired
    private DictionaryService dictionaryService;//字典表
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
    private ZixishiOrderService zixishiOrderService;//订座订单
    @Autowired
    private UsersService usersService;//管理员


    /**
    * 后端列表
    */
    @RequestMapping("/page")
    public R page(@RequestParam Map<String, Object> params, HttpServletRequest request){
        logger.debug("page方法:,,Controller:{},,params:{}",this.getClass().getName(),JSONObject.toJSONString(params));
        CommonUtil.checkMap(params);
        PageUtils page = forumService.queryPage(params);

        //字典表数据转换
        List<ForumView> list =(List<ForumView>)page.getList();
        for(ForumView c:list){
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
        ForumEntity forum = forumService.selectById(id);
        if(forum !=null){
            //entity转view
            ForumView view = new ForumView();
            BeanUtils.copyProperties( forum , view );//把实体数据重构到view中
            //级联表 用户
            //级联表
            YonghuEntity yonghu = yonghuService.selectById(forum.getYonghuId());
            if(yonghu != null){
            BeanUtils.copyProperties( yonghu , view ,new String[]{ "id", "createTime", "insertTime", "updateTime", "yonghuId"
, "usersId"});//把级联的数据添加到view中,并排除id和创建时间字段,当前表的级联注册表
            view.setYonghuId(yonghu.getId());
            }
            //级联表 管理员
            //管理员表做额外的处理
            UsersEntity users = usersService.selectById(forum.getUsersId());
            if(users != null){
                view.setUsersId(users.getId());
                view.setUusername(users.getUsername());
                view.setUpassword(users.getPassword());
                view.setUrole(users.getRole());
                view.setUaddtime(users.getAddtime());
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
    public R save(@RequestBody ForumEntity forum, HttpServletRequest request){
        logger.debug("save方法:,,Controller:{},,forum:{}",this.getClass().getName(),forum.toString());

        String role = String.valueOf(request.getSession().getAttribute("role"));
        if(false)
            return R.error(511,"永远不会进入");
        else if("用户".equals(role))
            forum.setYonghuId(Integer.valueOf(String.valueOf(request.getSession().getAttribute("userId"))));
        else if("管理员".equals(role))
            forum.setUsersId(Integer.valueOf(String.valueOf(request.getSession().getAttribute("userId"))));

        Wrapper<ForumEntity> queryWrapper = new EntityWrapper<ForumEntity>()
            .eq("forum_name", forum.getForumName())
            .eq("yonghu_id", forum.getYonghuId())
            .eq("users_id", forum.getUsersId())
            .eq("super_ids", forum.getSuperIds())
            .eq("forum_state_types", forum.getForumStateTypes())
            ;

        logger.info("sql语句:"+queryWrapper.getSqlSegment());
        ForumEntity forumEntity = forumService.selectOne(queryWrapper);
        if(forumEntity==null){
            forum.setInsertTime(new Date());
            forum.setCreateTime(new Date());
            forumService.insert(forum);
            return R.ok();
        }else {
            return R.error(511,"表中有相同数据");
        }
    }

    /**
    * 后端修改
    */
    @RequestMapping("/update")
    public R update(@RequestBody ForumEntity forum, HttpServletRequest request) throws NoSuchFieldException, ClassNotFoundException, IllegalAccessException, InstantiationException {
        logger.debug("update方法:,,Controller:{},,forum:{}",this.getClass().getName(),forum.toString());
        ForumEntity oldForumEntity = forumService.selectById(forum.getId());//查询原先数据

        String role = String.valueOf(request.getSession().getAttribute("role"));
//        if(false)
//            return R.error(511,"永远不会进入");
//        else if("用户".equals(role))
//            forum.setYonghuId(Integer.valueOf(String.valueOf(request.getSession().getAttribute("userId"))));
//        else if("管理员".equals(role))
//            forum.setUsersId(Integer.valueOf(String.valueOf(request.getSession().getAttribute("userId"))));
        forum.setUpdateTime(new Date());

            forumService.updateById(forum);//根据id更新
            return R.ok();
    }



    /**
    * 删除
    */
    @RequestMapping("/delete")
    public R delete(@RequestBody Integer[] ids, HttpServletRequest request){
        logger.debug("delete:,,Controller:{},,ids:{}",this.getClass().getName(),ids.toString());
        List<ForumEntity> oldForumList =forumService.selectBatchIds(Arrays.asList(ids));//要删除的数据
        forumService.deleteBatchIds(Arrays.asList(ids));

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
            List<ForumEntity> forumList = new ArrayList<>();//上传的东西
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
                            ForumEntity forumEntity = new ForumEntity();
//                            forumEntity.setForumName(data.get(0));                    //帖子标题 要改的
//                            forumEntity.setYonghuId(Integer.valueOf(data.get(0)));   //用户 要改的
//                            forumEntity.setUsersId(Integer.valueOf(data.get(0)));   //管理员 要改的
//                            forumEntity.setForumContent("");//详情和图片
//                            forumEntity.setSuperIds(Integer.valueOf(data.get(0)));   //父id 要改的
//                            forumEntity.setForumStateTypes(Integer.valueOf(data.get(0)));   //帖子状态 要改的
//                            forumEntity.setInsertTime(date);//时间
//                            forumEntity.setUpdateTime(sdf.parse(data.get(0)));          //修改时间 要改的
//                            forumEntity.setCreateTime(date);//时间
                            forumList.add(forumEntity);


                            //把要查询是否重复的字段放入map中
                        }

                        //查询是否重复
                        forumService.insertBatch(forumList);
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
        PageUtils page = forumService.queryPage(params);

        //字典表数据转换
        List<ForumView> list =(List<ForumView>)page.getList();
        for(ForumView c:list)
            dictionaryService.dictionaryConvert(c, request); //修改对应字典表字段

        return R.ok().put("data", page);
    }

    /**
    * 前端详情
    */
    @RequestMapping("/detail/{id}")
    public R detail(@PathVariable("id") Long id, HttpServletRequest request){
        logger.debug("detail方法:,,Controller:{},,id:{}",this.getClass().getName(),id);
        ForumEntity forum = forumService.selectById(id);
            if(forum !=null){


                //entity转view
                ForumView view = new ForumView();
                BeanUtils.copyProperties( forum , view );//把实体数据重构到view中

                //级联表
                    YonghuEntity yonghu = yonghuService.selectById(forum.getYonghuId());
                if(yonghu != null){
                    BeanUtils.copyProperties( yonghu , view ,new String[]{ "id", "createDate"});//把级联的数据添加到view中,并排除id和创建时间字段
                    view.setYonghuId(yonghu.getId());
                }
                    UsersEntity users = usersService.selectById(forum.getUsersId());
                if(users != null){
                    view.setUsersId(users.getId());
                    view.setUusername(users.getUsername());
                    view.setUpassword(users.getPassword());
                    view.setUrole(users.getRole());
                    view.setUaddtime(users.getAddtime());
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
    public R add(@RequestBody ForumEntity forum, HttpServletRequest request){
        logger.debug("add方法:,,Controller:{},,forum:{}",this.getClass().getName(),forum.toString());
        Wrapper<ForumEntity> queryWrapper = new EntityWrapper<ForumEntity>()
            .eq("forum_name", forum.getForumName())
            .eq("yonghu_id", forum.getYonghuId())
            .eq("users_id", forum.getUsersId())
            .eq("super_ids", forum.getSuperIds())
            .eq("forum_state_types", forum.getForumStateTypes())
//            .notIn("forum_types", new Integer[]{102})
            ;
        logger.info("sql语句:"+queryWrapper.getSqlSegment());
        ForumEntity forumEntity = forumService.selectOne(queryWrapper);
        if(forumEntity==null){
            forum.setInsertTime(new Date());
            forum.setCreateTime(new Date());
        forumService.insert(forum);

            return R.ok();
        }else {
            return R.error(511,"表中有相同数据");
        }
    }

}

