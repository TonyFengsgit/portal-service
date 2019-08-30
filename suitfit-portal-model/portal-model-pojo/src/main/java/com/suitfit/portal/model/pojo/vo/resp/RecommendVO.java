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
     * 日利率
     */
    private BigDecimal interestRate;


    private Integer productType;
    /**
     * logo链接
     */
    private String logoUrl;

    /**
     * 推荐权重
     */
    private Integer recommendIndex;
    /**
     * 显示标记 0 全显示 1失败显示 2 成功显示
     */
    private Integer showFlag;

    private String description;
    /**
     * 需求量
     */
    private Integer requireAmount;

    private String appCode;
}
