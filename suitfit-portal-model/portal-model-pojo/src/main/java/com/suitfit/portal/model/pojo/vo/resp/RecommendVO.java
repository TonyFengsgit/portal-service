package com.suitfit.portal.model.pojo.vo.resp;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class RecommendVO {
    /**
     * 产品code
     */
    private String productCode;
    /**
     * 产品名称
     */
    private String productName;
    /**
     * 最小金额
     */
    private BigDecimal minPrincipal;
    /**
     * 最大金额
     */
    private BigDecimal maxPrincipal;

    /**
     * 最小可借时长
     */
    private Integer minPeriods;
    /**
     * 最大可借时长
     */
    private Integer maxPeriods;

    /**
     * 日利率
     */
    private BigDecimal interestRate;

    /**
     * logo链接
     */
    private String logoUrl;

    /**
     * 产品链接
     */
    private String productUrl;

    private Integer state;

    /**
     * 推荐权重
     */
    private Integer recommendIndex;
    /**
     * 显示标记 0 全显示 1失败显示 2 成功显示
     */
    private Integer showFlag;

    private String description;

    private String appCode;
}
