package com.zhulong.business.system.baseservice.entity.systemconfig;

import com.zhulong.framework.common.entity.BaseExtendEntity;
import lombok.Data;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;
import java.math.BigDecimal;

/**
 * 描述：数据字典实体
 *
 * @author 初。
 * @date 2019-03-19 02:01:42
 */
@Data
@Entity
@DynamicUpdate
@DynamicInsert
@Table(name = "DATA_DICTIONARY")
public class DataDictionaryEntity extends BaseExtendEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 分类主键
     */
    @Column(name = "TYPE_GUID")
    private String typeGuid;

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
     * 级别（1：平台级，2：机构级）
     */
    @Column(name = "SUIT_LEVEL")
    private Short suitLevel;

    /**
     * 机构主键，如果为机构级别，则记录机构的数据字典。
     */
    @Column(name = "ORGANIZATION_GUID")
    private String organizationGuid;

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
