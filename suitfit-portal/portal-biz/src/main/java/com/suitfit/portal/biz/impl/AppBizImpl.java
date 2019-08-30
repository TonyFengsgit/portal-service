package com.suitfit.portal.biz.impl;

import com.suitfit.framework.utils.bean.BeanUtils;
import com.suitfit.portal.base.service.AppNameService;
import com.suitfit.portal.biz.AppBiz;
import com.suitfit.portal.model.entity.AppName;
import com.suitfit.portal.model.pojo.vo.resp.AppNameVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AppBizImpl implements AppBiz {

    @Autowired
    private AppNameService service;

    @Override
    public List<AppNameVO> apps() {
       List<AppName> entityList = service.list();
       List<AppNameVO> voList = (List<AppNameVO>) BeanUtils.convert(entityList, AppNameVO.class);
       return voList;
    }
}
