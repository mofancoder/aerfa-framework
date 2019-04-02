package com.zhulong.framework.common.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

/**
 * 基础类
 * @author xxc
 * @time 2019-1-7 14:22
 */
@MappedSuperclass
@Getter
@Setter
public class BaseEntity {

    @Id
    @Column(name="GUID")
    private String guid; //主键guid


}