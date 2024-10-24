package com.entity.model;

import com.entity.ZixishiEntity;

import com.baomidou.mybatisplus.annotations.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.util.Date;
import org.springframework.format.annotation.DateTimeFormat;
import java.io.Serializable;


/**
 * 自习室信息
 * 接收传参的实体类
 *（实际开发中配合移动端接口开发手动去掉些没用的字段， 后端一般用entity就够用了）
 * 取自ModelAndView 的model名称
 */
public class ZixishiModel implements Serializable {
    private static final long serialVersionUID = 1L;




    /**
     * 主键
     */
    private Integer id;


    /**
     * 自习室标题
     */
    private String zixishiName;


    /**
     * 自习室照片
     */
    private String zixishiPhoto;


    /**
     * 自习室类型
     */
    private Integer zixishiTypes;


    /**
     * 座位
     */
    private Integer zuoweiNumber;


    /**
     * 赞
     */
    private Integer zanNumber;


    /**
     * 踩
     */
    private Integer caiNumber;


    /**
     * 逻辑删除
     */
    private Integer zixishiDelete;


    /**
     * 详情
     */
    private String zixishiContent;


    /**
     * 创建时间  show1 show2 photoShow
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
	 * 获取：创建时间  show1 show2 photoShow
	 */
    public Date getCreateTime() {
        return createTime;
    }


    /**
	 * 设置：创建时间  show1 show2 photoShow
	 */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    }
