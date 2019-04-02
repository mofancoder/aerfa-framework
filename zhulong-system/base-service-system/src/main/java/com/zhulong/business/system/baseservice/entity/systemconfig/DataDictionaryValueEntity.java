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
import java.math.BigDecimal;

/**
 * 描述：数据字典值实体
 *
 * @author 初。
 * @date 2019-03-20 09:59:31
 */
@Data
@Entity
@DynamicUpdate
@DynamicInsert
@Table(name = "DATA_DICTIONARY_VALUE")
public class DataDictionaryValueEntity extends BaseExtendEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 字典主键
     */
    @Column(name = "DICTIONARY_GUID")
    private String dictionaryGuid;

    /**
     * 编号
     */
    @Column(name = "CODE")
    private String code;

    /**
     * 值-简称-中文
     */
    @Column(name = "SIMPLE_VALUE_ZH")
    private String simpleValueZh;

    /**
     * 值-简称-英文
     */
    @Column(name = "SIMPLE_VALUE_EN")
    private String simpleValueEn;

    /**
     * 值-全程-中文
     */
    @Column(name = "FULL_VALUE_ZH")
    private String fullValueZh;

    /**
     * 值-全称-英文
     */
    @Column(name = "FULL_VALUE_EN")
    private String fullValueEn;

    /**
     * 排序号
     */
    @Column(name = "SORT_NUM")
    private BigDecimal sortNum;

    /**
     * 状态（0：无效，1：有效）
     */
    @Column(name = "STATUS")
    private Short status;

    /**
     * 备注
     */
    @Column(name = "REMARK")
    private String remark;

}
