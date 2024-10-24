package com.dao;

import com.entity.ForumEntity;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import java.util.List;
import java.util.Map;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;

import org.apache.ibatis.annotations.Param;
import com.entity.view.ForumView;

/**
 * 论坛 Dao 接口
 *
 * @author 
 */
public interface ForumDao extends BaseMapper<ForumEntity> {

   List<ForumView> selectListView(Pagination page,@Param("params")Map<String,Object> params);

}
