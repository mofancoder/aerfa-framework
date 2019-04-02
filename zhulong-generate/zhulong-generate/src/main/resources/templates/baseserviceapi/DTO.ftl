package ${package_name}.baseservice.dto${module_name};

import com.zhulong.framework.common.dto.BaseDTO;
<#if (entityDTOImport?size>0)>
<#list entityDTOImport as import>
import ${import};
</#list>
</#if>
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
* 描述：${table_annotation}DTO
* @author ${author}
* @date ${date}
*/
@ApiModel("${table_annotation}-数据传输对象")
@Setter
@Getter
public class ${table_name}DTO extends BaseDTO implements Serializable {

    private static final long serialVersionUID = 1L;

   /**
    * 主键
    */
    @ApiModelProperty("主键")
    private String guid;

    <#if (columnDataList?? && columnDataList?size>0)>
    <#list columnDataList as column>
    <#if (column.propertyName!='guid' && column.propertyName!='createTime' && column.propertyName!='creatorGuid' && column.propertyName!='creatorName'
      && column.propertyName!='modifyTime' && column.propertyName!='modifierGuid' && column.propertyName!='modifierName' )>

    /**
    * ${column.remarks!}
    */
    @ApiModelProperty("${column.remarks!}")
    private ${column.javaTypeName} ${column.propertyName};
    </#if>
    </#list>
    </#if>
}
