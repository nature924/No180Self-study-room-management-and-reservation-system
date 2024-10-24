package com.entity.model;

import com.entity.ForumEntity;

import com.baomidou.mybatisplus.annotations.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.util.Date;
import org.springframework.format.annotation.DateTimeFormat;
import java.io.Serializable;


/**
 * 论坛
 * 接收传参的实体类
 *（实际开发中配合移动端接口开发手动去掉些没用的字段， 后端一般用entity就够用了）
 * 取自ModelAndView 的model名称
 */
public class ForumModel implements Serializable {
    private static final long serialVersionUID = 1L;




    /**
     * 主键
     */
    private Integer id;


    /**
     * 帖子标题
     */
    private String forumName;


    /**
     * 用户
     */
    private Integer yonghuId;


    /**
     * 管理员
     */
    private Integer usersId;


    /**
     * 发布内容
     */
    private String forumContent;


    /**
     * 父id
     */
    private Integer superIds;


    /**
     * 帖子状态
     */
    private Integer forumStateTypes;


    /**
     * 发帖时间
     */
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat
    private Date insertTime;


    /**
     * 修改时间
     */
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat
    private Date updateTime;


    /**
     * 创建时间 show2
     */
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat
    private Date createTime;


    /**
	 * 获取：主键
	 */
    public Integer getId() {
        return id;
    }


    /**
	 * 设置：主键
	 */
    public void setId(Integer id) {
        this.id = id;
    }
    /**
	 * 获取：帖子标题
	 */
    public String getForumName() {
        return forumName;
    }


    /**
	 * 设置：帖子标题
	 */
    public void setForumName(String forumName) {
        this.forumName = forumName;
    }
    /**
	 * 获取：用户
	 */
    public Integer getYonghuId() {
        return yonghuId;
    }


    /**
	 * 设置：用户
	 */
    public void setYonghuId(Integer yonghuId) {
        this.yonghuId = yonghuId;
    }
    /**
	 * 获取：管理员
	 */
    public Integer getUsersId() {
        return usersId;
    }


    /**
	 * 设置：管理员
	 */
    public void setUsersId(Integer usersId) {
        this.usersId = usersId;
    }
    /**
	 * 获取：发布内容
	 */
    public String getForumContent() {
        return forumContent;
    }


    /**
	 * 设置：发布内容
	 */
    public void setForumContent(String forumContent) {
        this.forumContent = forumContent;
    }
    /**
	 * 获取：父id
	 */
    public Integer getSuperIds() {
        return superIds;
    }


    /**
	 * 设置：父id
	 */
    public void setSuperIds(Integer superIds) {
        this.superIds = superIds;
    }
    /**
	 * 获取：帖子状态
	 */
    public Integer getForumStateTypes() {
        return forumStateTypes;
    }


    /**
	 * 设置：帖子状态
	 */
    public void setForumStateTypes(Integer forumStateTypes) {
        this.forumStateTypes = forumStateTypes;
    }
    /**
	 * 获取：发帖时间
	 */
    public Date getInsertTime() {
        return insertTime;
    }


    /**
	 * 设置：发帖时间
	 */
    public void setInsertTime(Date insertTime) {
        this.insertTime = insertTime;
    }
    /**
	 * 获取：修改时间
	 */
    public Date getUpdateTime() {
        return updateTime;
    }


    /**
	 * 设置：修改时间
	 */
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
    /**
	 * 获取：创建时间 show2
	 */
    public Date getCreateTime() {
        return createTime;
    }


    /**
	 * 设置：创建时间 show2
	 */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    }
