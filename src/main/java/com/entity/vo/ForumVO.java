package com.entity.vo;

import com.entity.ForumEntity;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.util.Date;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;

/**
 * 论坛
 * 手机端接口返回实体辅助类
 * （主要作用去除一些不必要的字段）
 */
@TableName("forum")
public class ForumVO implements Serializable {
    private static final long serialVersionUID = 1L;


    /**
     * 主键
     */

    @TableField(value = "id")
    private Integer id;


    /**
     * 帖子标题
     */

    @TableField(value = "forum_name")
    private String forumName;


    /**
     * 用户
     */

    @TableField(value = "yonghu_id")
    private Integer yonghuId;


    /**
     * 管理员
     */

    @TableField(value = "users_id")
    private Integer usersId;


    /**
     * 发布内容
     */

    @TableField(value = "forum_content")
    private String forumContent;


    /**
     * 父id
     */

    @TableField(value = "super_ids")
    private Integer superIds;


    /**
     * 帖子状态
     */

    @TableField(value = "forum_state_types")
    private Integer forumStateTypes;


    /**
     * 发帖时间
     */
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat

    @TableField(value = "insert_time")
    private Date insertTime;


    /**
     * 修改时间
     */
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat

    @TableField(value = "update_time")
    private Date updateTime;


    /**
     * 创建时间 show2
     */
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat

    @TableField(value = "create_time")
    private Date createTime;


    /**
	 * 设置：主键
	 */
    public Integer getId() {
        return id;
    }


    /**
	 * 获取：主键
	 */

    public void setId(Integer id) {
        this.id = id;
    }
    /**
	 * 设置：帖子标题
	 */
    public String getForumName() {
        return forumName;
    }


    /**
	 * 获取：帖子标题
	 */

    public void setForumName(String forumName) {
        this.forumName = forumName;
    }
    /**
	 * 设置：用户
	 */
    public Integer getYonghuId() {
        return yonghuId;
    }


    /**
	 * 获取：用户
	 */

    public void setYonghuId(Integer yonghuId) {
        this.yonghuId = yonghuId;
    }
    /**
	 * 设置：管理员
	 */
    public Integer getUsersId() {
        return usersId;
    }


    /**
	 * 获取：管理员
	 */

    public void setUsersId(Integer usersId) {
        this.usersId = usersId;
    }
    /**
	 * 设置：发布内容
	 */
    public String getForumContent() {
        return forumContent;
    }


    /**
	 * 获取：发布内容
	 */

    public void setForumContent(String forumContent) {
        this.forumContent = forumContent;
    }
    /**
	 * 设置：父id
	 */
    public Integer getSuperIds() {
        return superIds;
    }


    /**
	 * 获取：父id
	 */

    public void setSuperIds(Integer superIds) {
        this.superIds = superIds;
    }
    /**
	 * 设置：帖子状态
	 */
    public Integer getForumStateTypes() {
        return forumStateTypes;
    }


    /**
	 * 获取：帖子状态
	 */

    public void setForumStateTypes(Integer forumStateTypes) {
        this.forumStateTypes = forumStateTypes;
    }
    /**
	 * 设置：发帖时间
	 */
    public Date getInsertTime() {
        return insertTime;
    }


    /**
	 * 获取：发帖时间
	 */

    public void setInsertTime(Date insertTime) {
        this.insertTime = insertTime;
    }
    /**
	 * 设置：修改时间
	 */
    public Date getUpdateTime() {
        return updateTime;
    }


    /**
	 * 获取：修改时间
	 */

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
    /**
	 * 设置：创建时间 show2
	 */
    public Date getCreateTime() {
        return createTime;
    }


    /**
	 * 获取：创建时间 show2
	 */

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

}
