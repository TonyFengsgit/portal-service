package com.suitfit.portal.biz;

import com.suitfit.portal.model.pojo.criteria.DeptQueryCriteria;
import com.suitfit.portal.model.pojo.vo.req.DeptReq;
import com.suitfit.portal.model.pojo.vo.resp.DeptVO;

import java.util.Collection;

public interface DepartmentBiz {
    Collection<DeptVO> getDepts(DeptQueryCriteria criteria);

    void create(DeptReq req);

    void update(DeptReq req);

    void delete(Long id);
}
