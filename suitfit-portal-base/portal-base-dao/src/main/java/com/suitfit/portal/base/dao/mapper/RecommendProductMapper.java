package com.suitfit.portal.base.dao.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.suitfit.framework.dynamic.annotation.DBSource;
import com.suitfit.portal.model.entity.RecommendProduct;
import org.apache.ibatis.annotations.Mapper;

@DBSource("order")
@Mapper
public interface RecommendProductMapper extends BaseMapper<RecommendProduct> {
}
