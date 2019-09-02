package com.suitfit.portal.biz.impl;

import com.suitfit.framework.exception.BaseException;
import com.suitfit.framework.utils.bean.BeanUtils;
import com.suitfit.portal.base.service.DepartmentService;
import com.suitfit.portal.base.service.utils.DataScope;
import com.suitfit.portal.biz.DepartmentBiz;
import com.suitfit.portal.model.entity.Department;
import com.suitfit.portal.model.pojo.code.ResponseCode;
import com.suitfit.portal.model.pojo.criteria.DeptQueryCriteria;
import com.suitfit.portal.model.pojo.vo.req.DeptReq;
import com.suitfit.portal.model.pojo.vo.resp.DeptVO;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class DepartmentBizImpl implements DepartmentBiz {

    @Autowired
    private DataScope dataScope;

    @Autowired
    private DepartmentService departmentService;

    @Override
    public Collection<DeptVO> getDepts(DeptQueryCriteria criteria) {
        criteria.setIds(dataScope.getDeptIds());
        List<Department> entityList = departmentService.findByCriteria(criteria);
        List<DeptVO> deptVOList = (List<DeptVO>) BeanUtils.convert(entityList, DeptVO.class);
        return buildTree(deptVOList);
    }

    private Collection<DeptVO> buildTree(List<DeptVO> deptDTOS) {
        Set<DeptVO> trees = new LinkedHashSet<>();
        Set<DeptVO> depts = new LinkedHashSet<>();
        List<String> deptNames = deptDTOS.stream().map(DeptVO::getName).collect(Collectors.toList());
        Boolean isChild;
        if (deptDTOS!=null) {
            for (DeptVO deptDTO : deptDTOS) {
                isChild = false;
                if ("0".equals(deptDTO.getParentId().toString())) {
                    trees.add(deptDTO);
                }
                for (DeptVO it : deptDTOS) {
                    if (it.getParentId().equals(deptDTO.getId())) {
                        isChild = true;
                        if (deptDTO.getChildren() == null) {
                            deptDTO.setChildren(new ArrayList<DeptVO>());
                        }
                        deptDTO.getChildren().add(it);
                    }
                }
                if (isChild)
                    depts.add(deptDTO);
                else if (!deptNames.contains(departmentService.findNameById(deptDTO.getParentId())))
                    depts.add(deptDTO);
            }
        }

        if (CollectionUtils.isEmpty(trees)) {
            trees = depts;
        }
        if (CollectionUtils.isEmpty(trees)) {
            return deptDTOS;
        } else {
            return trees;
        }
    }

    @Override
    public void create(DeptReq req) {
        req.setId(null);
        Department entity = new Department();
        BeanUtils.copyProperties(req, entity);
        departmentService.save(entity);
    }

    @Override
    public void update(DeptReq req) {
        if (req.getParentId().equals(req.getId())) {
            throw new BaseException(ResponseCode.PARENT_NOT_SELF_ERROR);
        }
        Department dept = departmentService.findById(req.getId());
        if (dept != null) {
            Department entity = new Department();
            BeanUtils.copyProperties(req, entity);
            entity.setId(dept.getId());
            departmentService.updateEntity(entity);
        }
    }

    @Override
    public void delete(Long id) {
        departmentService.deleteById(id);
    }
}
