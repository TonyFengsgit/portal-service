package com.suitfit.portal.model.pojo.vo.common;

import lombok.Data;

import java.io.Serializable;

/**
 * @author Exrickx
 */
@Data
public class SearchVO implements Serializable {

    private String startDate;

    private String endDate;
}
