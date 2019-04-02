package com.zhulong.framework.common.dto;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * Created by shanb on 2019-2-25.
 */
@Getter
@Setter
public class PageOrderDTO extends QueryPageDTO implements Serializable {

    private String properties;

    private String direction;
}