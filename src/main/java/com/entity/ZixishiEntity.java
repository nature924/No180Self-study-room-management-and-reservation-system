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
 * 自习室信息
 *
 * @author 
 * @email
 */
@TableName("zixishi")
public class ZixishiEntity<T> implements Serializable {
    private static final long serialVersionUID = 1L;


	public ZixishiEntity() {

	}

	public ZixishiEntity(T t) {
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
     * 自习室标题
     */
    @ColumnInfo(comment="自习室标题",type="varchar(200)")
    @TableField(value = "zixishi_name")

    private String zixishiName;


    /**
     * 自习室照片
     */
    @ColumnInfo(comment="自习室照片",type="varchar(200)")
    @TableField(value = "zixishi_photo")

    private String zixishiPhoto;


    /**
     * 自习室类型
     */
    @ColumnInfo(comment="自习室类型",type="int(11)")
    @TableField(value = "zixishi_types")

    private Integer zixishiTypes;


    /**
     * 座位
     */
    @ColumnInfo(comment="座位",type="int(11)")
    @TableField(value = "zuowei_number")

    private Integer zuoweiNumber;


    /**
     * 赞
     */
    @ColumnInfo(comment="赞",type="int(11)")
    @TableField(value = "zan_number")

    private Integer zanNumber;


    /**
     * 踩
     */
    @ColumnInfo(comment="踩",type="int(11)")
    @TableField(value = "cai_number")

    private Integer caiNumber;


    /**
     * 逻辑删除
     */
    @ColumnInfo(comment="逻辑删除",type="int(11)")
    @TableField(value = "zixishi_delete")

    private Integer zixishiDelete;


    /**
     * 详情
     */
    @ColumnInfo(comment="详情",type="longtext")
    @TableField(value = "zixishi_content")

    private String zixishiContent;


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
	 * 获取：自习室标题
	 */
    public String getZixishiName() {
        return zixishiName;
    }
    /**
	 * 设置：自习室标题
	 */

    public void setZixishiName(String zixishiName) {
        this.zixishiName = zixishiName;
    }
    /**
	 * 获取：自习室照片
	 */
    public String getZixishiPhoto() {
        return zixishiPhoto;
    }
    /**
	 * 设置：自习室照片
	 */

    public void setZixishiPhoto(String zixishiPhoto) {
        this.zixishiPhoto = zixishiPhoto;
    }
    /**
	 * 获取：自习室类型
	 */
    public Integer getZixishiTypes() {
        return zixishiTypes;
    }
    /**
	 * 设置：自习室类型
	 */

    public void setZixishiTypes(Integer zixishiTypes) {
        this.zixishiTypes = zixishiTypes;
    }
    /**
	 * 获取：座位
	 */
    public Integer getZuoweiNumber() {
        return zuoweiNumber;
    }
    /**
	 * 设置：座位
	 */

    public void setZuoweiNumber(Integer zuoweiNumber) {
        this.zuoweiNumber = zuoweiNumber;
    }
    /**
	 * 获取：赞
	 */
    public Integer getZanNumber() {
        return zanNumber;
    }
    /**
	 * 设置：赞
	 */

    public void setZanNumber(Integer zanNumber) {
        this.zanNumber = zanNumber;
    }
    /**
	 * 获取：踩
	 */
    public Integer getCaiNumber() {
        return caiNumber;
    }
    /**
	 * 设置：踩
	 */

    public void setCaiNumber(Integer caiNumber) {
        this.caiNumber = caiNumber;
    }
    /**
	 * 获取：逻辑删除
	 */
    public Integer getZixishiDelete() {
        return zixishiDelete;
    }
    /**
	 * 设置：逻辑删除
	 */

    public void setZixishiDelete(Integer zixishiDelete) {
        this.zixishiDelete = zixishiDelete;
    }
    /**
	 * 获取：详情
	 */
    public String getZixishiContent() {
        return zixishiContent;
    }
    /**
	 * 设置：详情
	 */

    public void setZixishiContent(String zixishiContent) {
        this.zixishiContent = zixishiContent;
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
        return "Zixishi{" +
            ", id=" + id +
            ", zixishiName=" + zixishiName +
            ", zixishiPhoto=" + zixishiPhoto +
            ", zixishiTypes=" + zixishiTypes +
            ", zuoweiNumber=" + zuoweiNumber +
            ", zanNumber=" + zanNumber +
            ", caiNumber=" + caiNumber +
            ", zixishiDelete=" + zixishiDelete +
            ", zixishiContent=" + zixishiContent +
            ", createTime=" + DateUtil.convertString(createTime,"yyyy-MM-dd") +
        "}";
    }
}
