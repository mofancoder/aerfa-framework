package ${package_name}.baseservice.repository${module_name};

import com.zhulong.framework.common.jpa.BaseRepository;
import ${package_name}.baseservice.entity${module_name}.${table_name}Entity;
import org.springframework.stereotype.Repository;

/**
* 描述：${table_annotation} Repository接口
* @author ${author}
* @date ${date}
*/
@Repository
public interface ${table_name}Repository extends BaseRepository<${table_name}Entity,String> {



}
