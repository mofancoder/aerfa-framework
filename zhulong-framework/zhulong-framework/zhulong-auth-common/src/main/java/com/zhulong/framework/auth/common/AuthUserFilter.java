package com.zhulong.framework.auth.common;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Base64;

/**
 * 解析code信息为用户信息
 * Created by shanb on 2019-1-14.
 */
public class AuthUserFilter extends GenericFilterBean {


    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain) throws IOException, ServletException {
        String headUserInfobase64 = ((HttpServletRequest) request).getHeader(AuthConstance.HEAD_USER_INFO);
        if(!StringUtils.isEmpty(headUserInfobase64)){
            //base64解码
            String headUserInfo = new String(Base64.getDecoder().decode(headUserInfobase64.getBytes("UTF-8")),"UTF-8");
            //进行对象转化  是否需要根据不同的userType进行转化？
            ObjectMapper objectMapper = new ObjectMapper();
            AuthUser authUser = objectMapper.readValue(headUserInfo,AuthUser.class);
            ZhulongContext.setUser(authUser);
        }
        filterChain.doFilter(request,response);
    }
}