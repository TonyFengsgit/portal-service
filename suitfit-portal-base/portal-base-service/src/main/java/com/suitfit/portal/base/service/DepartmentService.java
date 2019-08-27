package com.suitfit.portal.base.service;

import com.suitfit.framework.service.BaseService;
import com.suitfit.portal.model.entity.Department;
import com.suitfit.portal.model.pojo.criteria.DeptQueryCriteria;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
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

    @Cacheable(key = "'findById:' + #p0")
    Department findById(Long departmentId);

    @Cacheable(keyGenerator = "keyGenerator")
    List<Department> findByCriteria(DeptQueryCriteria criteria);

    @Cacheable(key = "'findNameById:'+ #p0")
    String findNameById(Long parentId);

    @CacheEvict(allEntries = true)
    void updateEntity(Department entity);

    @CacheEvict(allEntries = true)
    void deleteById(Long id);
}
