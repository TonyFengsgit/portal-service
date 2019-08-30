package com.suitfit.portal.model.pojo.vo.req;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Data
public class RecommendReq {
    /**
     * 产品code
     */
    @NotBlank
    private String productCode;
    /**
     * 产品名称
     */
    @NotBlank
    private String productName;

    @NotNull
    private BigDecimal minPrincipal;
    /**
     * 最大金额
     */
    @NotNull
    private BigDecimal maxPrincipal;
    /**
     * 日利率
     */
    @NotNull
    private BigDecimal interestRate;
    /**
     * 最小可借时长
     */
    private Integer minPeriods;
    /**
     * 最大可借时长
     */
    @NotNull
    private Integer maxPeriods;
    /**
     * 是否多期
     */
    private Integer productType;
    /**
     * logo链接
     */
    @NotBlank
    private String logoUrl;
    /**
     * 产品链接
     */
    @NotBlank
    private String productUrl;
    /**
     * 推荐权重
     */
    @NotNull
    private Integer recommendIndex;
    /**
     * 显示标记 0 全显示 1失败显示 2 成功显示
     */
    @NotNull
    private Integer showFlag;

    @NotNull
    private Integer state;
    /**
     * 可用系统
     */
    private String os;
    /**
     * 描述
     */
    private String description;
    /**
     * 需求量
     */
    private Integer requireAmount;
    /**
     * 转化率
     */
    private BigDecimal convertRate;

    @NotBlank
    private String appCode;
}
