package com.suitfit.portal.base.service;

import com.suitfit.framework.service.BaseService;
import com.suitfit.portal.model.entity.Department;
import com.suitfit.portal.model.entity.RoleDepartment;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;

import java.util.List;

/**
 * @program: RoleDepartmentService
 * @description:
 * @author: Chonzi.xiao
 * @create: 2019-08-16 10:06
 */
@CacheConfig(cacheNames = "roleDepartment")
public interface RoleDepartmentService extends BaseService<RoleDepartment> {

    @Cacheable(key = "'findByRoleId:'+#p0")
    List<Department> findByRoleId(Long id);

    void deleteByRoleId(Long id);

}
