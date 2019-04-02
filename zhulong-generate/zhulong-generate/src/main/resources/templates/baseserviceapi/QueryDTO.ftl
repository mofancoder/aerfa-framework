package ${package_name}.baseservice.dto${module_name};

import com.zhulong.framework.common.dto.PageOrderDTO;
<#if (queryDTOImport?size>0)>
    <#list queryDTOImport as import>
    import ${import};
    </#list>
</#if>
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
* 描述：${table_annotation}数据查询对象
* @author ${author}
* @date ${date}
*/
@ApiModel("${table_annotation}查询数据对象")
@Getter
@Setter
public class ${table_name}QueryDTO implements Serializable {

<#if (columnDataList?? && columnDataList?size>0)>
<#list columnDataList as column>
<#if column.isQueryParam>

    /**
    * ${column.remarks!}
    */
    @ApiModelProperty("${column.remarks!}")
    private ${column.javaTypeName} ${column.propertyName};
</#if>
</#list>
</#if>

    @ApiModelProperty("分页排序信息")
    private PageOrderDTO pageOrderDTO;
}