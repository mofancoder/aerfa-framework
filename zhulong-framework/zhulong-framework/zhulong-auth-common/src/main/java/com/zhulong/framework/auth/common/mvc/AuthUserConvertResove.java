package com.zhulong.framework.auth.common.mvc;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.zhulong.framework.auth.common.AuthConstance;
import com.zhulong.framework.auth.common.AuthUser;
import org.springframework.core.MethodParameter;
import org.springframework.lang.Nullable;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import java.util.Base64;

/**
 * Created by shanb on 2019-1-17.
 * 用于解析标记注解的@AuthUserParam的参数，并且类型为AuthUser
 */
public class AuthUserConvertResove implements HandlerMethodArgumentResolver {

    @Override
    public boolean supportsParameter(MethodParameter methodParameter) {
        return methodParameter.getParameterAnnotation(AuthUserParam.class)!=null && methodParameter.getParameterType().isAssignableFrom(AuthUser.class);
    }

    @Override
    public Object resolveArgument(MethodParameter methodParameter, @Nullable ModelAndViewContainer modelAndViewContainer, NativeWebRequest nativeWebRequest, @Nullable WebDataBinderFactory webDataBinderFactory) throws Exception {
        AuthUserParam authUserParam = methodParameter.getParameterAnnotation(AuthUserParam.class);
        boolean isRequired = authUserParam.required();
        String authUserInfo = nativeWebRequest.getHeader(AuthConstance.HEAD_USER_INFO);
        if(StringUtils.isEmpty(authUserInfo)){
            if(!isRequired) {
                return null;
            }else{
                throw new RuntimeException("can conver authUser");
            }
        }else{
            ObjectMapper objectMapper = new ObjectMapper();
            AuthUser authUser = objectMapper.readValue(new String(Base64.getDecoder().decode(authUserInfo.getBytes("UTF-8")),"UTF-8"),AuthUser.class);
            return authUser;
        }
    }
}