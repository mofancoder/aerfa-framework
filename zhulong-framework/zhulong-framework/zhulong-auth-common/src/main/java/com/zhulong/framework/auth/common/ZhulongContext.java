package com.zhulong.framework.auth.common;

/**
 * Created by shanb on 2019-1-14.
 */
public class ZhulongContext {

    public static ThreadLocal<AuthUser> userThreadLocal = new ThreadLocal<>();

    public static AuthUser getUser(){
        return userThreadLocal.get();
    }

    public static void  setUser(AuthUser authUser){
        userThreadLocal.set(authUser);
    }
}