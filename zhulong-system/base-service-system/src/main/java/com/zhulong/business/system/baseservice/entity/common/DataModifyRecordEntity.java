package com.zhulong.business.system.baseservice.entity.common;

import com.zhulong.framework.common.entity.BaseEntity;
import com.zhulong.framework.common.entity.BaseExtendEntity;
import lombok.Data;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;

/**
* 描述：数据修改记录实体
* @author shanb
* @date 2019-03-27 17:41
*/
@Data
@Entity
@DynamicUpdate
@DynamicInsert
@Table(name="DATA_MODIFY_RECORD")
public class DataModifyRecordEntity extends BaseEntity implements Serializable  {

    private static final long serialVersionUID = 1L;

   /**
    * 数据类型
    */
    @Column(name = "DATA_TYPE")
    private Short dataType;

   /**
    * 数据主键-对应的业务的数据主键
    */
    @Column(name = "DATA_GUID")
    private String dataGuid;

   /**
    * 旧值
    */
    @Column(name = "OLD_VALUE")
    private String oldValue;

   /**
    * 冗余旧值-展示值
    */
    @Column(name = "OLD_VALUE_DISPLAY")
    private String oldValueDisplay;

   /**
    * 新值
    */
    @Column(name = "NEW_VALUE")
    private String newValue;

   /**
    * 冗余新值的展示值
    */
    @Column(name = "NEW_VALUE_DISPLAY")
    private String newValueDisplay;

   /**
    * 备注
    */
    @Column(name = "REMARK")
    private String remark;

   /**
    * 扩展信息,特殊业务，可通过扩展信息进行扩展
    */
    @Column(name = "EXT_INFO")
    private String extInfo;

    @Column(name = "CREATE_TIME")
    private Long createTime;

    @Column(name = "CREATOR_GUID")
    private String creatorGuid;

    @Column(name = "CREATOR_NAME")
    private String creatorName;

}
