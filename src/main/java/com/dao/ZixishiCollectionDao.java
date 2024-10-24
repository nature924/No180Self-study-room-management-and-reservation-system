package com.dao;

import com.entity.ZixishiCollectionEntity;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import java.util.List;
import java.util.Map;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;

import org.apache.ibatis.annotations.Param;
import com.entity.view.ZixishiCollectionView;

/**
 * 自习室收藏 Dao 接口
 *
 * @author 
 */
public interface ZixishiCollectionDao extends BaseMapper<ZixishiCollectionEntity> {

   List<ZixishiCollectionView> selectListView(Pagination page,@Param("params")Map<String,Object> params);

}
