package com.dao;

import com.entity.ZixishiOrderEntity;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import java.util.List;
import java.util.Map;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;

import org.apache.ibatis.annotations.Param;
import com.entity.view.ZixishiOrderView;

/**
 * 订座订单 Dao 接口
 *
 * @author 
 */
public interface ZixishiOrderDao extends BaseMapper<ZixishiOrderEntity> {

   List<ZixishiOrderView> selectListView(Pagination page,@Param("params")Map<String,Object> params);

}
