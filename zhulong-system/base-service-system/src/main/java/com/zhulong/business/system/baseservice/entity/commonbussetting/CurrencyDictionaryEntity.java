package com.zhulong.business.system.baseservice.entity.commonbussetting;

import com.zhulong.framework.common.entity.BaseExtendEntity;
import lombok.Data;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;

/**
* 描述：币种管理实体
* @author MOFAN889
* @date 2019-04-01 11:37
*/
@Data
@Entity
@DynamicUpdate
@DynamicInsert
@Table(name="CURRENCY_DICTIONARY")
public class CurrencyDictionaryEntity extends BaseExtendEntity implements Serializable  {

    private static final long serialVersionUID = 1L;

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
    * 符号
    */
    @Column(name = "SYMBOL")
    private String symbol;

   /**
    * 是否本币
    */
    @Column(name = "IS_LOCAL_CURRENCY")
    private Boolean localCurrency;

   /**
    * 状态
    */
    @Column(name = "STATUS")
    private Short status;

   /**
    * 备注
    */
    @Column(name = "REMARK")
    private String remark;

}
