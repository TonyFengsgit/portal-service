package com.suitfit.portal.biz;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.suitfit.framework.utils.page.PageVO;
import com.suitfit.portal.model.pojo.vo.req.UserPassReq;
import com.suitfit.portal.model.pojo.vo.req.UserReq;
import com.suitfit.portal.model.pojo.vo.resp.UserVO;

public interface UserBiz {

    PageVO<UserVO> users(UserReq query, Page initPage);

    void create(UserReq query);

    void update(UserReq req);

    void delete(Long id);

    void updatePass(UserPassReq req);
}
