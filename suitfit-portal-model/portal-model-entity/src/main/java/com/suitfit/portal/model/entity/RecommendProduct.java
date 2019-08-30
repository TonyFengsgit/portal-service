package com.suitfit.portal.model.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.suitfit.framework.entity.BaseEntity;
import lombok.Data;

import java.math.BigDecimal;

/**
 * @program: RecommendProduct
 * @description:
 * @author: Chonzi.xiao
 * @create: 2019-07-24 17:46
 */
@Data
@TableName("recommend_product")
public class RecommendProduct extends BaseEntity {
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
    /**
     * 最小可借时长
     */
    private Integer minPeriods;
    /**
     * 最大可借时长
     */
    private Integer maxPeriods;
    /**
     * 是否多期
     */
    private Integer productType;
    /**
     * logo链接
     */
    private String logoUrl;
    /**
     * 产品链接
     */
    private String productUrl;
    /**
     * 推荐权重
     */
    private Integer recommendIndex;
    /**
     * 显示标记 0 全显示 1失败显示 2 成功显示
     */
    private Integer showFlag;

    /**
     * 是否上架 1.是，0否
     */
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

    private String appCode;
}
