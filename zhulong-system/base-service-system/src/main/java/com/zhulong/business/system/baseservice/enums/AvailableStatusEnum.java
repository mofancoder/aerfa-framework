package com.zhulong.business.system.baseservice.enums;

/**
 * 有效无效枚举
 *
 * Author: 初。
 * Date: 2019-3-20
 * Time: 14:40
 */
public enum AvailableStatusEnum {

    /**
     * 有效的
     */
    AVAILABLE(Short.valueOf("1")),

    /**
     * 无效的
     */
    UN_AVAILABLE(Short.valueOf("0"));

    private Short status;

    AvailableStatusEnum(Short status) {
        this.status = status;
    }

    public Short getStatus() {
        return this.status;
    }

}
