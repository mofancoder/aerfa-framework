package com.zhulong.business.system.baseservice.entity.systemconfig;

import lombok.Data;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.Where;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * 描述：日期类型配置实体
 *
 * @author 初。
 * @date 2019-03-20 10:00:44
 */
@Data
@Entity
@DynamicUpdate
@DynamicInsert
@Table(name = "DAY_TYPE_CONFIG")
public class DayTypeConfigEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @Id
    @Column(name = "GUID")
    private String guid;

    /**
     * 年份
     */
    @Column(name = "YEAR")
    private Short year;

    /**
     * 月
     */
    @Column(name = "MONTH")
    private Short month;

    /**
     * 日
     */
    @Column(name = "DAY")
    private Short day;

    /**
     * 日子类型(0:放假，1：上班)
     */
    @Column(name = "DAY_TYPE")
    private Short dayType;

    /**
     * 创建时间
     */
    @Column(name = "CREATE_TIME")
    private Long createTime;

}
