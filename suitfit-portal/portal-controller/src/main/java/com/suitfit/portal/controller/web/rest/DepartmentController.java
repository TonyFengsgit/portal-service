package com.suitfit.portal.controller.web.rest;

import com.suitfit.framework.mvc.ResponseMessage;
import com.suitfit.portal.biz.DepartmentBiz;
import com.suitfit.portal.model.pojo.criteria.DeptQueryCriteria;
import com.suitfit.portal.model.pojo.vo.req.DeptReq;
import com.suitfit.portal.model.pojo.vo.resp.DeptVO;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;


/**
 * @author Exrick
 */
@Slf4j
@RestController
@RequestMapping("department")
public class DepartmentController {

    @Autowired
    private DepartmentBiz departmentBiz;

    @ApiOperation("查询部门")
    @GetMapping(value = "/dept")
    @PreAuthorize("hasAnyRole('ADMIN','USER_ALL','USER_SELECT','DEPT_ALL','DEPT_SELECT')")
    public ResponseMessage getDepts(DeptQueryCriteria criteria) {
        // 数据权限
        Collection<DeptVO> deptList = departmentBiz.getDepts(criteria);
        return ResponseMessage.ok(deptList);
    }

    @ApiOperation("新增部门")
    @PostMapping(value = "/dept")
    @PreAuthorize("hasAnyRole('ADMIN','DEPT_ALL','DEPT_CREATE')")
    public ResponseMessage create(@Validated @RequestBody DeptReq req) {
        departmentBiz.create(req);
        return ResponseMessage.ok();
    }

    @ApiOperation("修改部门")
    @PutMapping(value = "/dept")
    @PreAuthorize("hasAnyRole('ADMIN','DEPT_ALL','DEPT_EDIT')")
    public ResponseMessage update(@Validated @RequestBody DeptReq req) {
        departmentBiz.update(req);
        return ResponseMessage.ok();
    }

    @ApiOperation("删除部门")
    @DeleteMapping(value = "/dept/{id}")
    @PreAuthorize("hasAnyRole('ADMIN','DEPT_ALL','DEPT_DELETE')")
    public ResponseMessage delete(@PathVariable Long id) {
        departmentBiz.delete(id);
        return ResponseMessage.ok();
    }
}
