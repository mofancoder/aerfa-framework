package ${package_name}.busservice.feign;

import ${package_name}.baseservice.api${module_name}.${table_name}BaseApi;
import org.springframework.cloud.openfeign.FeignClient;

/**
 * 描述：${table_annotation} Feign接口
 * @author ${author}
 * @date ${date}
 */
@FeignClient(${baseServiceAppName})
public interface ${table_name}BaseApiFeign extends ${table_name}BaseApi {

}