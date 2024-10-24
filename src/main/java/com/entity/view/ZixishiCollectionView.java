package com.entity.view;

import org.apache.tools.ant.util.DateUtils;
import com.annotation.ColumnInfo;
import com.entity.ZixishiCollectionEntity;
import com.baomidou.mybatisplus.annotations.TableName;
import org.apache.commons.beanutils.BeanUtils;
import java.lang.reflect.InvocationTargetException;
import org.springframework.format.annotation.DateTimeFormat;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.io.Serializable;
import java.util.Date;
import com.utils.DateUtil;

/**
* 自习室收藏
* 后端返回视图实体辅助类
* （通常后端关联的表或者自定义的字段需要返回使用）
*/
@TableName("zixishi_collection")
public class ZixishiCollectionView extends ZixishiCollectionEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	//当前表
	/**
	* 类型的值
	*/
	@ColumnInfo(comment="类型的字典表值",type="varchar(200)")
	private String zixishiCollectionValue;

	//级联表 用户
		/**
		* 用户姓名
		*/

		@ColumnInfo(comment="用户姓名",type="varchar(200)")
		private String yonghuName;
		/**
		* 头像
		*/

		@ColumnInfo(comment="头像",type="varchar(255)")
		private String yonghuPhoto;
		/**
		* 用户手机号
		*/

		@ColumnInfo(comment="用户手机号",type="varchar(200)")
		private String yonghuPhone;
		/**
		* 用户身份证号
		*/

		@ColumnInfo(comment="用户身份证号",type="varchar(200)")
		private String yonghuIdNumber;
		/**
		* 假删
		*/

		@ColumnInfo(comment="假删",type="int(11)")
		private Integer yonghuDelete;
	//级联表 自习室信息
		/**
		* 自习室标题
		*/

		@ColumnInfo(comment="自习室标题",type="varchar(200)")
		private String zixishiName;
		/**
		* 自习室照片
		*/

		@ColumnInfo(comment="自习室照片",type="varchar(200)")
		private String zixishiPhoto;
		/**
		* 自习室类型
		*/
		@ColumnInfo(comment="自习室类型",type="int(11)")
		private Integer zixishiTypes;
			/**
			* 自习室类型的值
			*/
			@ColumnInfo(comment="自习室类型的字典表值",type="varchar(200)")
			private String zixishiValue;
		/**
		* 座位
		*/

		@ColumnInfo(comment="座位",type="int(11)")
		private Integer zuoweiNumber;
		/**
		* 赞
		*/

		@ColumnInfo(comment="赞",type="int(11)")
		private Integer zanNumber;
		/**
		* 踩
		*/

		@ColumnInfo(comment="踩",type="int(11)")
		private Integer caiNumber;
		/**
		* 逻辑删除
		*/

		@ColumnInfo(comment="逻辑删除",type="int(11)")
		private Integer zixishiDelete;
		/**
		* 详情
		*/

		@ColumnInfo(comment="详情",type="longtext")
		private String zixishiContent;



	public ZixishiCollectionView() {

	}

	public ZixishiCollectionView(ZixishiCollectionEntity zixishiCollectionEntity) {
		try {
			BeanUtils.copyProperties(this, zixishiCollectionEntity);
		} catch (IllegalAccessException | InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}



	//当前表的
	/**
	* 获取： 类型的值
	*/
	public String getZixishiCollectionValue() {
		return zixishiCollectionValue;
	}
	/**
	* 设置： 类型的值
	*/
	public void setZixishiCollectionValue(String zixishiCollectionValue) {
		this.zixishiCollectionValue = zixishiCollectionValue;
	}


	//级联表的get和set 用户

		/**
		* 获取： 用户姓名
		*/
		public String getYonghuName() {
			return yonghuName;
		}
		/**
		* 设置： 用户姓名
		*/
		public void setYonghuName(String yonghuName) {
			this.yonghuName = yonghuName;
		}

		/**
		* 获取： 头像
		*/
		public String getYonghuPhoto() {
			return yonghuPhoto;
		}
		/**
		* 设置： 头像
		*/
		public void setYonghuPhoto(String yonghuPhoto) {
			this.yonghuPhoto = yonghuPhoto;
		}

		/**
		* 获取： 用户手机号
		*/
		public String getYonghuPhone() {
			return yonghuPhone;
		}
		/**
		* 设置： 用户手机号
		*/
		public void setYonghuPhone(String yonghuPhone) {
			this.yonghuPhone = yonghuPhone;
		}

		/**
		* 获取： 用户身份证号
		*/
		public String getYonghuIdNumber() {
			return yonghuIdNumber;
		}
		/**
		* 设置： 用户身份证号
		*/
		public void setYonghuIdNumber(String yonghuIdNumber) {
			this.yonghuIdNumber = yonghuIdNumber;
		}

		/**
		* 获取： 假删
		*/
		public Integer getYonghuDelete() {
			return yonghuDelete;
		}
		/**
		* 设置： 假删
		*/
		public void setYonghuDelete(Integer yonghuDelete) {
			this.yonghuDelete = yonghuDelete;
		}
	//级联表的get和set 自习室信息

		/**
		* 获取： 自习室标题
		*/
		public String getZixishiName() {
			return zixishiName;
		}
		/**
		* 设置： 自习室标题
		*/
		public void setZixishiName(String zixishiName) {
			this.zixishiName = zixishiName;
		}

		/**
		* 获取： 自习室照片
		*/
		public String getZixishiPhoto() {
			return zixishiPhoto;
		}
		/**
		* 设置： 自习室照片
		*/
		public void setZixishiPhoto(String zixishiPhoto) {
			this.zixishiPhoto = zixishiPhoto;
		}
		/**
		* 获取： 自习室类型
		*/
		public Integer getZixishiTypes() {
			return zixishiTypes;
		}
		/**
		* 设置： 自习室类型
		*/
		public void setZixishiTypes(Integer zixishiTypes) {
			this.zixishiTypes = zixishiTypes;
		}


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

		/**
		* 获取： 座位
		*/
		public Integer getZuoweiNumber() {
			return zuoweiNumber;
		}
		/**
		* 设置： 座位
		*/
		public void setZuoweiNumber(Integer zuoweiNumber) {
			this.zuoweiNumber = zuoweiNumber;
		}

		/**
		* 获取： 赞
		*/
		public Integer getZanNumber() {
			return zanNumber;
		}
		/**
		* 设置： 赞
		*/
		public void setZanNumber(Integer zanNumber) {
			this.zanNumber = zanNumber;
		}

		/**
		* 获取： 踩
		*/
		public Integer getCaiNumber() {
			return caiNumber;
		}
		/**
		* 设置： 踩
		*/
		public void setCaiNumber(Integer caiNumber) {
			this.caiNumber = caiNumber;
		}

		/**
		* 获取： 逻辑删除
		*/
		public Integer getZixishiDelete() {
			return zixishiDelete;
		}
		/**
		* 设置： 逻辑删除
		*/
		public void setZixishiDelete(Integer zixishiDelete) {
			this.zixishiDelete = zixishiDelete;
		}

		/**
		* 获取： 详情
		*/
		public String getZixishiContent() {
			return zixishiContent;
		}
		/**
		* 设置： 详情
		*/
		public void setZixishiContent(String zixishiContent) {
			this.zixishiContent = zixishiContent;
		}


	@Override
	public String toString() {
		return "ZixishiCollectionView{" +
			", zixishiCollectionValue=" + zixishiCollectionValue +
			", zixishiName=" + zixishiName +
			", zixishiPhoto=" + zixishiPhoto +
			", zuoweiNumber=" + zuoweiNumber +
			", zanNumber=" + zanNumber +
			", caiNumber=" + caiNumber +
			", zixishiDelete=" + zixishiDelete +
			", zixishiContent=" + zixishiContent +
			", yonghuName=" + yonghuName +
			", yonghuPhoto=" + yonghuPhoto +
			", yonghuPhone=" + yonghuPhone +
			", yonghuIdNumber=" + yonghuIdNumber +
			", yonghuDelete=" + yonghuDelete +
			"} " + super.toString();
	}
}
