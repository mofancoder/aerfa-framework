package com.zhulong.business.system.baseservice.entity.organization;

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
* 描述：组织管理实体
* @author shanb
* @date 2019-03-27 10:14
*/
@Data
@Entity
@DynamicUpdate
@DynamicInsert
@Table(name="OGRANIZATION_BASE_INFO")
public class OrganizationBaseInfoEntity extends BaseExtendEntity implements Serializable  {

    private static final long serialVersionUID = 1L;

   /**
    * 上级机构
    */
    @Column(name = "PARENT_GUID")
    private String parentGuid;

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
    * 简称
    */
    @Column(name = "SIMPLE_NAME")
    private String simpleName;

   /**
    * 排序号
    */
    @Column(name = "SORT_NUM")
    private BigDecimal sortNum;

   /**
    * 状态(1:有效，0:无效)
    */
    @Column(name = "STATUS")
    private Short status;

   /**
    * 类型（有运营机构和交易机构-交易中心），应该对应人员类型的一级CODE.
    */
    @Column(name = "TYPE_CODE")
    private String typeCode;

   /**
    * 统一社会信用代码
    */
    @Column(name = "UNIFIED_CODE")
    private String unifiedCode;

   /**
    * 负责人名称
    */
    @Column(name = "DIRECTOR_NAME")
    private String directorName;

   /**
    * 负责人证件类型（1：身份证）
    */
    @Column(name = "DIRECTOR_ID_TYPE")
    private Short directorIdType;

   /**
    * 负责人证件号
    */
    @Column(name = "DIRECTOR_ID_NUM")
    private String directorIdNum;

   /**
    * 联系人名称
    */
    @Column(name = "CONTACT_NAME")
    private String contactName;

   /**
    * 联系人手机
    */
    @Column(name = "CONTACT_PHONE")
    private String contactPhone;

   /**
    * 联系人邮箱
    */
    @Column(name = "CONTACT_EMAIL")
    private String contactEmail;

   /**
    * 联系人电话
    */
    @Column(name = "CONTACT_TEL")
    private String contactTel;

   /**
    * 联系人传真
    */
    @Column(name = "CONTACT_FAX")
    private String contactFax;

   /**
    * 邮编
    */
    @Column(name = "POSTCODE")
    private String postcode;

   /**
    * 地址
    */
    @Column(name = "ADDRESS")
    private String address;

   /**
    * 备注
    */
    @Column(name = "REMARK")
    private String remark;

   /**
    * 运营状态（0:初始状态，1：运营状态，3：停止运营）
    */
    @Column(name = "BUS_STATUS")
    private Short busStatus;

   /**
    * 业务模式
    */
    @Column(name = "BUS_MODE_GUID")
    private String busModeGuid;

   /**
    * 业务模式名称-冗余
    */
    @Column(name = "BUS_MODE_NAME")
    private String busModeName;

}
