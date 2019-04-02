package com.zhulong.business.system.baseservice.entity.commonbussetting.baseservice.entity.commonbussetting;

import com.zhulong.framework.common.entity.BaseExtendEntity;
import java.math.BigDecimal;
import lombok.Data;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;

/**
* 描述：汇率管理实体
* @author MOFAN889
* @date 2019-04-01 16:21
*/
@Data
@Entity
@DynamicUpdate
@DynamicInsert
@Table(name="CURRENCY_RATE_SETTING")
public class CurrencyRateSettingEntity extends BaseExtendEntity implements Serializable  {

    private static final long serialVersionUID = 1L;

   /**
    * 源货币
    */
    @Column(name = "ORIGINAL_CURRENCY_GUID")
    private String originalCurrencyGuid;

   /**
    * 目的货币
    */
    @Column(name = "DEST_CURRENTCY_GUID")
    private String destCurrentcyGuid;

   /**
    * 汇率
    */
    @Column(name = "RATE")
    private BigDecimal rate;

   /**
    * 有效期
    */
    @Column(name = "VALIDITY_DAY")
    private Long validityDay;

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
