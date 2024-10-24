package com.entity.view;

import org.apache.tools.ant.util.DateUtils;
import com.annotation.ColumnInfo;
import com.entity.ZixishiEntity;
import com.baomidou.mybatisplus.annotations.TableName;
import org.apache.commons.beanutils.BeanUtils;
import java.lang.reflect.InvocationTargetException;
import org.springframework.format.annotation.DateTimeFormat;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.io.Serializable;
import java.util.Date;
import com.utils.DateUtil;

/**
* 自习室信息
* 后端返回视图实体辅助类
* （通常后端关联的表或者自定义的字段需要返回使用）
*/
@TableName("zixishi")
public class ZixishiView extends ZixishiEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	//当前表
	/**
	* 自习室类型的值
	*/
	@ColumnInfo(comment="自习室类型的字典表值",type="varchar(200)")
	private String zixishiValue;




	public ZixishiView() {

	}

	public ZixishiView(ZixishiEntity zixishiEntity) {
		try {
			BeanUtils.copyProperties(this, zixishiEntity);
		} catch (IllegalAccessException | InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}



	//当前表的
	/**
	* 获取： 自习室类型的值
	*/
	public String getZixishiValue() {
		return zixishiValue;
	}
	/**
	* 设置： 自习室类型的值
	*/
	public void setZixishiValue(String zixishiValue) {
		this.zixishiValue = zixishiValue;
	}




	@Override
	public String toString() {
		return "ZixishiView{" +
			", zixishiValue=" + zixishiValue +
			"} " + super.toString();
	}
}
