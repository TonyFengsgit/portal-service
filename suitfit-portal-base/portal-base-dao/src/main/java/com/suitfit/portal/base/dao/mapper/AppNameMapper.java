package com.suitfit.portal.base.dao.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.suitfit.framework.dynamic.annotation.DBSource;
import com.suitfit.portal.model.entity.AppName;
import org.apache.ibatis.annotations.Mapper;

@DBSource("base")
@Mapper
public interface AppNameMapper extends BaseMapper<AppName> {
}
