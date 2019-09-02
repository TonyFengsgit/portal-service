package com.suitfit.portal.controller.web.rest;

import com.suitfit.framework.exception.BaseException;
import com.suitfit.framework.mvc.ResponseMessage;
import com.suitfit.portal.base.dao.utils.PageUtils;
import com.suitfit.portal.biz.ChannelBiz;
import com.suitfit.portal.model.pojo.code.ResponseCode;
import com.suitfit.portal.model.pojo.vo.common.PageVO;
import com.suitfit.portal.model.pojo.vo.req.ChannelReq;
import com.suitfit.portal.model.pojo.vo.resp.ChannelVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("channel")
public class ChannelController {

    @Autowired
    private ChannelBiz channelBiz;

    @GetMapping
    public ResponseMessage channels(@ModelAttribute ChannelReq req, PageVO page){
        if (StringUtils.isEmpty(page.getSort())){
            page.setOrder("desc");
            page.setSort("ctime");
        }
        PageVO<ChannelVO> pageVO = channelBiz.channels(req, PageUtils.initPage(page));
        return ResponseMessage.ok(pageVO);
    }

    @PostMapping
    public ResponseMessage create(@Validated @RequestBody ChannelReq req){
        channelBiz.create(req);
        return ResponseMessage.ok();
    }

    @PutMapping
    public ResponseMessage update(@Validated @RequestBody ChannelReq req){
        if (ObjectUtils.isEmpty(req.getId())){
            throw new BaseException(ResponseCode.PARAM_INCOMPLETE);
        }
        channelBiz.update(req);
        return ResponseMessage.ok();
    }

    @DeleteMapping("{id}")
    public ResponseMessage delete(@PathVariable Long id){
        channelBiz.delete(id);
        return ResponseMessage.ok();
    }
}
