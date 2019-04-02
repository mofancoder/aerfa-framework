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
 * 描述：数据字典类型实体
 *
 * @author 初。
 * @date 2019-03-20 10:00:06
 */
@Data
@Entity
@DynamicUpdate
@DynamicInsert
@Table(name = "DATA_DICTIONARY_TYPE")
public class DataDictionaryTypeEntity extends BaseExtendEntity implements Serializable {

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
