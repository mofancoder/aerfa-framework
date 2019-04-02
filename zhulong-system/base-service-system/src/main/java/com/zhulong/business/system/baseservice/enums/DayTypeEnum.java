package com.zhulong.business.system.baseservice.enums;

/**
 * 日期类型枚举
 *
 * Author: 初。
 * Date: 2019-3-20
 * Time: 14:40
 */
public enum DayTypeEnum {

    /**
     * 放假
     */
    UN_WORK(Short.valueOf("0")),

    /**
     * 工作
     */
    WORK(Short.valueOf("1"));

    private Short type;

    DayTypeEnum(Short type) {
        this.type = type;
    }

    public Short getType() {
        return this.type;
    }

}
