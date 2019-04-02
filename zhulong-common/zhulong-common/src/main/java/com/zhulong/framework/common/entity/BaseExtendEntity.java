package com.zhulong.framework.common.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

/**
 * 基础扩展类
 * @author xxc
 * @time 2018-12-26 9:22
 */
@MappedSuperclass
@Getter
@Setter
public class BaseExtendEntity extends BaseEntity {

    @Column(name="CREATE_TIME")
    private Long createTime; //创建时间

    @Column(name="CREATOR_GUID")
    private String creatorGuid; //创建人guid

    @Column(name="CREATOR_NAME")
    private String creatorName; //创建人名称

    @Column(name="MODIFY_TIME")
    private Long modifyTime;  //修改时间

    @Column(name="MODIFIER_GUID")
    private String modifierGuid;  //修改人guid

    @Column(name="MODIFIER_NAME")
    private String modifierName;  //修改人名称

    //系统采取物理删除的方式进行开发。
/*    @Column(name="IS_DELETED")
    private Boolean deleted;  //是否删除，逻辑删除，0 未删除 1 已删除

    @Column(name="DELETE_TIME")
    private Long deleteTime; //删除时间，记录删除时间，也用作防止删除的数据导致唯一约束的失效*/
}