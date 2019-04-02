package com.zhulong.business.system.baseservice.entity.commonbussetting;

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
 * 描述：交易类型实体
 * @author 初。
 * @date 2019-03-28 09:17
 */
@Data
@Entity
@DynamicUpdate
@DynamicInsert
@Table(name = "TRANS_TYPE_SETTING")
public class TransTypeSettingEntity extends BaseExtendEntity implements Serializable  {

    private static final long serialVersionUID = 1L;

   /**
    * 上级主键
    */
    @Column(name = "PARENT_GUID")
    private String parentGuid;

   /**
    * 编号
    */
    @Column(name = "CODE")
    private String code;

   /**
    * 简称-中文
    */
    @Column(name = "SIMPLE_NAME_ZH")
    private String simpleNameZh;

   /**
    * 简称-英文
    */
    @Column(name = "SIMPLE_NAME_EN")
    private String simpleNameEn;

   /**
    * 全称-中文
    */
    @Column(name = "FULL_NAME_ZH")
    private String fullNameZh;

   /**
    * 全称-英文
    */
    @Column(name = "FULL_NAME_EN")
    private String fullNameEn;

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
