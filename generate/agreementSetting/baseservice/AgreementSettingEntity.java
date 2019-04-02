package com.zhulong.business.system.baseservice.entity.commonbussetting.baseservice.entity.commonbussetting;

import com.zhulong.framework.common.entity.BaseExtendEntity;
import lombok.Data;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;

/**
* 描述：协议管理实体
* @author MOFAN889
* @date 2019-04-01 18:06
*/
@Data
@Entity
@DynamicUpdate
@DynamicInsert
@Table(name="AGREEMENT_SETTING")
public class AgreementSettingEntity extends BaseExtendEntity implements Serializable  {

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
    * 标题中文
    */
    @Column(name = "TITLE_ZH")
    private String titleZh;

   /**
    * 标题英文
    */
    @Column(name = "TITLE_EN")
    private String titleEn;

   /**
    * 类型
    */
    @Column(name = "TYPE")
    private Short type;

   /**
    * 级别
    */
    @Column(name = "SUIT_LEVEL")
    private Short suitLevel;

   /**
    * 机构主键
    */
    @Column(name = "ENTERPRISE_GUID")
    private String enterpriseGuid;

   /**
    * 状态
    */
    @Column(name = "STATUS")
    private Short status;

   /**
    * 协议地址
    */
    @Column(name = "URL")
    private String url;

   /**
    * 附件主键
    */
    @Column(name = "ATTACH_GUID")
    private String attachGuid;

   /**
    * 内容
    */
    @Column(name = "CONTENT")
    private UNKOWN content;

}
