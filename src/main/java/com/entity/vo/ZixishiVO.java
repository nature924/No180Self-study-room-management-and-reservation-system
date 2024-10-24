package com.entity.vo;

import com.entity.ZixishiEntity;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.util.Date;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;

/**
 * 自习室信息
 * 手机端接口返回实体辅助类
 * （主要作用去除一些不必要的字段）
 */
@TableName("zixishi")
public class ZixishiVO implements Serializable {
    private static final long serialVersionUID = 1L;


    /**
     * 主键
     */

    @TableField(value = "id")
    private Integer id;


    /**
     * 自习室标题
     */

    @TableField(value = "zixishi_name")
    private String zixishiName;


    /**
     * 自习室照片
     */

    @TableField(value = "zixishi_photo")
    private String zixishiPhoto;


    /**
     * 自习室类型
     */

    @TableField(value = "zixishi_types")
    private Integer zixishiTypes;


    /**
     * 座位
     */

    @TableField(value = "zuowei_number")
    private Integer zuoweiNumber;


    /**
     * 赞
     */

    @TableField(value = "zan_number")
    private Integer zanNumber;


    /**
     * 踩
     */

    @TableField(value = "cai_number")
    private Integer caiNumber;


    /**
     * 逻辑删除
     */

    @TableField(value = "zixishi_delete")
    private Integer zixishiDelete;


    /**
     * 详情
     */

    @TableField(value = "zixishi_content")
    private String zixishiContent;


    /**
     * 创建时间  show1 show2 photoShow
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
	 * 设置：自习室标题
	 */
    public String getZixishiName() {
        return zixishiName;
    }


    /**
	 * 获取：自习室标题
	 */

    public void setZixishiName(String zixishiName) {
        this.zixishiName = zixishiName;
    }
    /**
	 * 设置：自习室照片
	 */
    public String getZixishiPhoto() {
        return zixishiPhoto;
    }


    /**
	 * 获取：自习室照片
	 */

    public void setZixishiPhoto(String zixishiPhoto) {
        this.zixishiPhoto = zixishiPhoto;
    }
    /**
	 * 设置：自习室类型
	 */
    public Integer getZixishiTypes() {
        return zixishiTypes;
    }


    /**
	 * 获取：自习室类型
	 */

    public void setZixishiTypes(Integer zixishiTypes) {
        this.zixishiTypes = zixishiTypes;
    }
    /**
	 * 设置：座位
	 */
    public Integer getZuoweiNumber() {
        return zuoweiNumber;
    }


    /**
	 * 获取：座位
	 */

    public void setZuoweiNumber(Integer zuoweiNumber) {
        this.zuoweiNumber = zuoweiNumber;
    }
    /**
	 * 设置：赞
	 */
    public Integer getZanNumber() {
        return zanNumber;
    }


    /**
	 * 获取：赞
	 */

    public void setZanNumber(Integer zanNumber) {
        this.zanNumber = zanNumber;
    }
    /**
	 * 设置：踩
	 */
    public Integer getCaiNumber() {
        return caiNumber;
    }


    /**
	 * 获取：踩
	 */

    public void setCaiNumber(Integer caiNumber) {
        this.caiNumber = caiNumber;
    }
    /**
	 * 设置：逻辑删除
	 */
    public Integer getZixishiDelete() {
        return zixishiDelete;
    }


    /**
	 * 获取：逻辑删除
	 */

    public void setZixishiDelete(Integer zixishiDelete) {
        this.zixishiDelete = zixishiDelete;
    }
    /**
	 * 设置：详情
	 */
    public String getZixishiContent() {
        return zixishiContent;
    }


    /**
	 * 获取：详情
	 */

    public void setZixishiContent(String zixishiContent) {
        this.zixishiContent = zixishiContent;
    }
    /**
	 * 设置：创建时间  show1 show2 photoShow
	 */
    public Date getCreateTime() {
        return createTime;
    }


    /**
	 * 获取：创建时间  show1 show2 photoShow
	 */

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

}
