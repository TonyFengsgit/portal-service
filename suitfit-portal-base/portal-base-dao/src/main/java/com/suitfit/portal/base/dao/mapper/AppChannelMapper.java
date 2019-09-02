package com.suitfit.portal.base.dao.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.suitfit.framework.dynamic.annotation.DBSource;
import com.suitfit.portal.model.entity.AppChannel;
import org.apache.ibatis.annotations.Mapper;

@DBSource("portal")
@Mapper
public interface AppChannelMapper extends BaseMapper<AppChannel> {
}
