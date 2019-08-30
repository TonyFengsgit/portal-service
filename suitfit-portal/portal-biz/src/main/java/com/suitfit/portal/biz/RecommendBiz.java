package com.suitfit.portal.biz;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.suitfit.portal.model.pojo.vo.common.PageVO;
import com.suitfit.portal.model.pojo.vo.req.RecommendReq;
import com.suitfit.portal.model.pojo.vo.resp.RecommendVO;

public interface RecommendBiz {
    void create(RecommendReq req);

    void update(RecommendReq req);

    void delete(String productCode);

    PageVO<RecommendVO> products(RecommendReq req, Page initPage);

    void disable(String productCode);

    void undisable(String productCode);

    void updateShowFlag(String productCode, Integer showFlag);
}
