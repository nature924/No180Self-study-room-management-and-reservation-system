package com.entity;

import com.annotation.ColumnInfo;
import javax.validation.constraints.*;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.lang.reflect.InvocationTargetException;
import java.io.Serializable;
import java.util.*;
import org.apache.tools.ant.util.DateUtils;
import org.springframework.format.annotation.DateTimeFormat;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.beanutils.BeanUtils;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;
import com.baomidou.mybatisplus.enums.FieldFill;
import com.utils.DateUtil;


/**
 * 论坛
 *
 * @author 
 * @email
 */
@TableName("forum")
public class ForumEntity<T> implements Serializable {
    private static final long serialVersionUID = 1L;


	public ForumEntity() {

	}

	public ForumEntity(T t) {
		try {
			BeanUtils.copyProperties(this, t);
		} catch (IllegalAccessException | InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}


    /**
     * 主键
     */
    @TableId(type = IdType.AUTO)
    @ColumnInfo(comment="主键",type="int(11)")
    @TableField(value = "id")

    private Integer id;


    /**
     * 帖子标题
     */
    @ColumnInfo(comment="帖子标题",type="varchar(200)")
    @TableField(value = "forum_name")

    private String forumName;


    /**
     * 用户
     */
    @ColumnInfo(comment="用户",type="int(11)")
    @TableField(value = "yonghu_id")

    private Integer yonghuId;


    /**
     * 管理员
     */
    @ColumnInfo(comment="管理员",type="int(11)")
    @TableField(value = "users_id")

    private Integer usersId;


    /**
     * 发布内容
     */
    @ColumnInfo(comment="发布内容",type="longtext")
    @TableField(value = "forum_content")

    private String forumContent;


    /**
     * 父id
     */
    @ColumnInfo(comment="父id",type="int(11)")
    @TableField(value = "super_ids")

    private Integer superIds;


    /**
     * 帖子状态
     */
    @ColumnInfo(comment="帖子状态",type="int(11)")
    @TableField(value = "forum_state_types")

    private Integer forumStateTypes;


    /**
     * 发帖时间
     */
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat
    @ColumnInfo(comment="发帖时间",type="timestamp")
    @TableField(value = "insert_time",fill = FieldFill.INSERT)

    private Date insertTime;


    /**
     * 修改时间
     */
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat
    @ColumnInfo(comment="修改时间",type="timestamp")
    @TableField(value = "update_time",fill = FieldFill.UPDATE)

    private Date updateTime;


    /**
     * 创建时间
     */
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat
    @ColumnInfo(comment="创建时间",type="timestamp")
    @TableField(value = "create_time",fill = FieldFill.INSERT)

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
	 * 获取：创建时间
	 */
    public Date getCreateTime() {
        return createTime;
    }
    /**
	 * 设置：创建时间
	 */

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    @Override
    public String toString() {
        return "Forum{" +
            ", id=" + id +
            ", forumName=" + forumName +
            ", yonghuId=" + yonghuId +
            ", usersId=" + usersId +
            ", forumContent=" + forumContent +
            ", superIds=" + superIds +
            ", forumStateTypes=" + forumStateTypes +
            ", insertTime=" + DateUtil.convertString(insertTime,"yyyy-MM-dd") +
            ", updateTime=" + DateUtil.convertString(updateTime,"yyyy-MM-dd") +
            ", createTime=" + DateUtil.convertString(createTime,"yyyy-MM-dd") +
        "}";
    }
}
