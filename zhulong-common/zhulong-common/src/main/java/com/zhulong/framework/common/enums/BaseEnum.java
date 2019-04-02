package com.zhulong.framework.common.enums;
/**
 * 枚举接口
 * @author xxc
 * @time 2018-12-28 11:31
 */
public interface BaseEnum<T> {
	
	public T getCode(); //枚举编号

	public String name(); //枚举名称

	public String getDescription(); //枚举描述
}
