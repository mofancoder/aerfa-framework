package ${package_name}.busservice.dto${module_name};

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import java.io.Serializable;
<#if (showDTOImport?size>0)>
<#list showDTOImport as import>
import ${import};
</#list>
</#if>

/**
* 描述：${table_annotation}页面显示数据
* @author ${author}
* @date ${date}
*/
@ApiModel("${table_annotation}-查看页面显示数据")
@Getter
@Setter
public class ${table_name}ShowDTO implements Serializable {

<#if (columnDataList?? && columnDataList?size>0)>
    <#list columnDataList as column>
        <#if column.isShowData>

   /**
    * ${column.remarks}
    */
    @ApiModelProperty("${column.remarks}")
    private ${column.javaTypeName} ${column.propertyName};
        </#if>
    </#list>
</#if>

}