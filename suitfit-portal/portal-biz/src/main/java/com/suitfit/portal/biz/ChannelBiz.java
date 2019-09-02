package com.suitfit.portal.biz;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.suitfit.framework.utils.page.PageVO;
import com.suitfit.portal.model.pojo.vo.req.ChannelReq;
import com.suitfit.portal.model.pojo.vo.resp.ChannelVO;

public interface ChannelBiz {
    void create(ChannelReq req);

    void update(ChannelReq req);

    PageVO<ChannelVO> channels(ChannelReq req, Page initPage);

    void delete(Long id);
}
