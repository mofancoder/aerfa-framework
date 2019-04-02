package com.zhulong.business.system.baseservice.entity.systemconfig;

import com.zhulong.framework.common.entity.BaseExtendEntity;
import lombok.Data;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.Where;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * 描述：工作时间管理实体
 *
 * @author 初。
 * @date 2019-03-21 04:05:52
 */
@Data
@Entity
@DynamicUpdate
@DynamicInsert
@Table(name = "WORKING_TIME_CONFIG")
public class WorkingTimeConfigEntity extends BaseExtendEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 编号
     */
    @Column(name = "CODE")
    private String code;

    /**
     * 名称
     */
    @Column(name = "NAME")
    private String name;

    /**
     * 状态（0：无效，1：有效）
     */
    @Column(name = "STATUS")
    private Short status;

    /**
     * 适用月份，各月份使用英文逗号隔开
     */
    @Column(name = "MONTHS")
    private String months;

    /**
     * 备注
     */
    @Column(name = "REMARK")
    private String remark;

    /**
     * 星期一工作时间json数组字符串
     */
    @Column(name = "MONDAY")
    private String monday;

    /**
     * 星期二工作时间json数组字符串
     */
    @Column(name = "TUESDAY")
    private String tuesday;

    /**
     * 星期三工作时间json数组字符串
     */
    @Column(name = "WEDNESDAY")
    private String wednesday;

    /**
     * 星期四工作时间json数组字符串
     */
    @Column(name = "THURSDAY")
    private String thursday;

    /**
     * 星期五工作时间json数组字符串
     */
    @Column(name = "FRIDAY")
    private String friday;

    /**
     * 星期六工作时间json数组字符串
     */
    @Column(name = "SATURDAY")
    private String saturday;

    /**
     * 星期天工作时间json数组字符串
     */
    @Column(name = "SUNDAY")
    private String sunday;

}
