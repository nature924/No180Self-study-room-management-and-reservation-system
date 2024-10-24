package com.dao;

import com.entity.ZixishiLiuyanEntity;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import java.util.List;
import java.util.Map;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;

import org.apache.ibatis.annotations.Param;
import com.entity.view.ZixishiLiuyanView;

/**
 * 自习室留言 Dao 接口
 *
 * @author 
 */
public interface ZixishiLiuyanDao extends BaseMapper<ZixishiLiuyanEntity> {

   List<ZixishiLiuyanView> selectListView(Pagination page,@Param("params")Map<String,Object> params);

}
