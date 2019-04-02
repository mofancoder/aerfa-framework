package ${package_name}.baseservice.entity${module_name};

import com.zhulong.framework.common.entity.BaseExtendEntity;
<#if (entityDTOImport?size>0)>
    <#list entityDTOImport as import>
import ${import};
    </#list>
</#if>
import lombok.Data;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;

/**
* 描述：${table_annotation}实体
* @author ${author}
* @date ${date}
*/
@Data
@Entity
@DynamicUpdate
@DynamicInsert
@Table(name="${table_name_small}")
public class ${table_name}Entity extends BaseExtendEntity implements Serializable  {

    private static final long serialVersionUID = 1L;
<#if (columnDataList?? && columnDataList?size>0)>
<#list columnDataList as column>
<#if (column.propertyName!='guid' && column.propertyName!='createTime' && column.propertyName!='creatorGuid' && column.propertyName!='creatorName'
        && column.propertyName!='modifyTime' && column.propertyName!='modifierGuid' && column.propertyName!='modifierName' )>

   /**
    * ${column.remarks}
    */
    @Column(name = "${column.columnName!}")
    private ${column.javaTypeName} ${column.propertyName};
</#if>
</#list>
</#if>

}
