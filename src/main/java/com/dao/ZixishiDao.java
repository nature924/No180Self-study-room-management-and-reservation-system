package com.dao;

import com.entity.ZixishiEntity;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import java.util.List;
import java.util.Map;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;

import org.apache.ibatis.annotations.Param;
import com.entity.view.ZixishiView;

/**
 * 自习室信息 Dao 接口
 *
 * @author 
 */
public interface ZixishiDao extends BaseMapper<ZixishiEntity> {

   List<ZixishiView> selectListView(Pagination page,@Param("params")Map<String,Object> params);

}
