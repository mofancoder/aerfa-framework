package com.zhulong.framework.auth.dto;

import com.zhulong.framework.auth.common.AuthUser;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * Created by shanb on 2019-1-14.
 */
@Getter
@Setter
public class LoginRtDTO implements Serializable {

    private String token;

    private AuthUser authUser;
}