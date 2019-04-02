package com.zhulong.business.system.baseservice.entity.user;

import com.zhulong.framework.common.entity.BaseExtendEntity;
import lombok.Data;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.Where;
import org.springframework.stereotype.Component;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * 用户实体类
 * Created by shanb on 2019-1-16.
 */
@Entity
@Table(name="USER")
@DynamicUpdate
@DynamicInsert
@Data
public class UserEntity extends BaseExtendEntity implements Serializable {

    @Column(name="ACCOUNT")
    private String account;

    @Column(name="USER_NAME")
    private String userName;

    @Column(name="PASSWORD")
    private String password;

    @Column(name="DEPT_GUID")
    private String deptGuid;

    @Column(name="DEPT_NAME")
    private String deptName;

    @Column(name="ID_TYPE")
    private Short idType;

    @Column(name="ID_NUM")
    private String idNum;

    @Column(name="PHONE_NUM")
    private String phoneNum;

    @Column(name="TEL_NUM")
    private String telNum;

    @Column(name="EMAIL")
    private String email;

    @Column(name="IS_VALIDATE")
    private Boolean validate;

    @Column(name="CHANGE_STATUS_REASON")
    private String changeStatusReason;

    @Column(name="IS_LOCKED")
    private Boolean locked;

    @Column(name="LOCKED_TIME")
    private Long lockedTime;

    @Column(name="SORT_NUM")
    private Integer sortNum;

    @Column(name="DESCRIPTION")
    private String description;
}