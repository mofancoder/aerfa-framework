package com.zhulong.framework.common.dto;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * 查询分页dto
 * @author xxc
 * @time 2019-1-22 14:34
 */
@Setter
@Getter
public class QueryPageDTO implements Serializable {

    private static final long serialVersionUID = -1L;

    private Integer pageNo; //页码

    private Integer pageSize; //每页数量
}