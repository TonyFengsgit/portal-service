package com.suitfit.portal.controller.web.rest;

import com.suitfit.framework.mvc.ResponseMessage;
import com.suitfit.portal.base.dao.utils.PageUtils;
import com.suitfit.portal.biz.RecommendBiz;
import com.suitfit.portal.model.pojo.vo.common.PageVO;
import com.suitfit.portal.model.pojo.vo.req.RecommendReq;
import com.suitfit.portal.model.pojo.vo.resp.RecommendVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("recommend")
public class RecommendController {

    @Autowired
    private RecommendBiz recommendBiz;


    @GetMapping
    public ResponseMessage products(@ModelAttribute RecommendReq req, PageVO pageVO){
        PageVO<RecommendVO> voPageVO = recommendBiz.products(req, PageUtils.initPage(pageVO));
        return ResponseMessage.ok(voPageVO);
    }

    @PostMapping
    public ResponseMessage create(@Validated @RequestBody RecommendReq req){
        recommendBiz.create(req);
        return ResponseMessage.ok();
    }

    @PutMapping
    public ResponseMessage update(@Validated @RequestBody RecommendReq req){
        recommendBiz.update(req);
        return ResponseMessage.ok();
    }

    @DeleteMapping("{productCode}")
    public ResponseMessage delete(@PathVariable String productCode){
        recommendBiz.delete(productCode);
        return ResponseMessage.ok();
    }

    @PutMapping("disable/{productCode}")
    public ResponseMessage disable(@PathVariable String productCode){
        recommendBiz.disable(productCode);
        return ResponseMessage.ok();
    }

    @PutMapping("undisable/{productCode}")
    public ResponseMessage undisable(@PathVariable String productCode){
        recommendBiz.undisable(productCode);
        return ResponseMessage.ok();
    }

    @PutMapping("show/{productCode}/{showFlag}")
    public ResponseMessage show(@PathVariable String productCode,@PathVariable Integer showFlag){
        recommendBiz.updateShowFlag(productCode, showFlag);
        return ResponseMessage.ok();
    }
}
