package com.zhulong.framework.localmessage.jpa.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 本地消息表
 * Created by shanb on 2018-12-18.
 */
@Entity
@Table(name = "LOCAL_MESSAGE")
@Getter
@Setter
public class LocalMessage {

    @Column(name="GUID")
    @Id
    private String guid;

    /**
     * 消息目的地
     */
    @Column(name="DESTINATION")
    private String destination;

    /**
     *消息主体
     */
    @Column(name="MESSAGE")
    private String message;

    /**
     * 状态
     */
    @Column(name="STATUS")
    private Short status;


    /**
     * 重试次数
     */
    @Column(name="RETRY_TIMES")
    private Integer retryTimes;

    /**
     * 创建时间
     */
    @Column(name="CREATE_TIME")
    private Long createTime;

    /**
     * 上次推送时间
     */
    @Column(name="LAST_PUSH_TIME")
    private Long lastPushTime;

    /**
     * 失败原因
     */
    @Column(name="FAILURE_REASON")
    private String failureReason;


    public  enum  StatusEnum {

        NOT_PUSH((short)0,"未推送"),
        HAS_PUSH((short)1,"已推送"),
        FAIL_PUSH((short)2,"推送失败");

        private short code;

        private String description;

        StatusEnum(short code,String description){
            this.code = code;
            this.description = description;
        }

        public short getCode() {
            return code;
        }

        public String getDescription() {
            return description;
        }

    }
}