package com.suitfit.portal.base.service.impl;

import com.suitfit.framework.service.impl.BaseServiceImpl;
import com.suitfit.portal.base.dao.mapper.AppNameMapper;
import com.suitfit.portal.base.service.AppNameService;
import com.suitfit.portal.model.entity.AppName;
import org.springframework.stereotype.Service;

@Service
public class AppNameServiceImpl extends BaseServiceImpl<AppNameMapper, AppName> implements AppNameService {

}
