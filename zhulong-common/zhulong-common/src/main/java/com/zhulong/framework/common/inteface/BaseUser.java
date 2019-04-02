package com.zhulong.framework.common.inteface;

/**
 * 用户基础信息类
 * Created by shanb on 2019-2-21.
 */
public interface BaseUser {

    /**
     * 获取用户主键
     */
    String getGuid();

    /**
     * 获取名称
     */
    String getName();

    /**
     * 获取编号
     */
    String getBh();
}
