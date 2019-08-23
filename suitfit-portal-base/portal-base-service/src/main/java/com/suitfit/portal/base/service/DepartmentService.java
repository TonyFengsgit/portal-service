package com.suitfit.portal.base.service;

import com.suitfit.framework.service.BaseService;
import com.suitfit.portal.model.entity.Department;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;

import java.util.List;

/**
 * @program: DepartmentService
 * @description:
 * @author: Chonzi.xiao
 * @create: 2019-08-16 10:04
 */
@CacheConfig(cacheNames = "dept")
public interface DepartmentService extends BaseService<Department> {
    @Cacheable(key = "#p0")
    List<Department> getByPid(Long id);
}
