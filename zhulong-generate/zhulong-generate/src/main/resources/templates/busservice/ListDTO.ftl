package ${package_name}.busservice.dto${module_name};

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
<#if (listDTOImport?size>0)>
    <#list listDTOImport as import>
    import ${import};
    </#list>
</#if>

/**
 * 描述：${table_annotation}列表显示数据
 * @author ${author}
 * @date ${date}
 */
@ApiModel("${table_annotation}列表显示数据")
@Getter
@Setter
public class ${table_name}ListDTO implements Serializable {

<#if (columnDataList?? && columnDataList?size>0)>
    <#list columnDataList as column>
        <#if column.isListData>
   /**
    * ${column.remarks}
    */
    @ApiModelProperty("${column.remarks}")
    private ${column.javaTypeName} ${column.propertyName};
        </#if>
    </#list>
</#if>

}