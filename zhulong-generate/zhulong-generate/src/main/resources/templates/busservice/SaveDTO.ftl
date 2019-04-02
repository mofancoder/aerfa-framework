package ${package_name}.busservice.dto${module_name};

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.*;
import java.io.Serializable;
<#if (saveDTOImport?size>0)>
    <#list saveDTOImport as import>
    import ${import};
    </#list>
</#if>

/**
* 描述：${table_annotation}-保存数据
* @author ${author}
* @date ${date}
*/
@Getter
@Setter
@ApiModel("${table_annotation}-保存数据")
public class ${table_name}SaveDTO implements Serializable{

<#if (columnDataList?? && columnDataList?size>0)>
    <#list columnDataList as column>
        <#if column.isSaveData && column.propertyName!='createTime' && column.propertyName!='creatorGuid' && column.propertyName!='creatorName'
        && column.propertyName!='modifyTime' && column.propertyName!='modifierGuid' && column.propertyName!='modifierName'>

   /**
    * ${column.remarks}
    */
    @ApiModelProperty("${column.remarks}")
    <#list column.validateAnnotion as validate>
    ${validate}
    </#list>
    private ${column.javaTypeName} ${column.propertyName};
        </#if>
    </#list>
</#if>

}