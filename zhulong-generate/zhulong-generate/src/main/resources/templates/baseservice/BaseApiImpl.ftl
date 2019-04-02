package ${package_name}.baseservice.api${module_name}.impl;

import ${package_name}.baseservice.api${module_name}.${table_name}BaseApi;
import ${package_name}.baseservice.dto${module_name}.${table_name}DTO;
import ${package_name}.baseservice.dto${module_name}.${table_name}QueryDTO;
import ${package_name}.baseservice.entity${module_name}.${table_name}Entity;
import ${package_name}.baseservice.repository${module_name}.${table_name}Repository;

import com.zhulong.framework.common.jpa.BaseDao;
import com.zhulong.framework.common.jpa.JPAUtils;
import com.zhulong.framework.common.page.Pagination;
import com.zhulong.framework.common.util.POJOConvertUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.*;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

/**
* 描述：${table_annotation} BaseApiImpl实现
* @author ${author}
* @date 2019-03-18 11:44:45
*/
@Transactional
@RestController
public class ${table_name}BaseApiImpl extends BaseDao implements ${table_name}BaseApi {

    @Autowired
    private  ${table_name}Repository ${table_name?uncap_first}Repository;

    @Override
    public ${table_name}DTO getByGuid(@RequestParam("guid") String guid) {

        Optional<${table_name}Entity> ${table_name?uncap_first}Optional = ${table_name?uncap_first}Repository.findById(guid);

        ${table_name}DTO ${table_name?uncap_first}DTO = ${table_name?uncap_first}Optional.isPresent() ? POJOConvertUtil.convert(${table_name?uncap_first}Optional.get(), ${table_name}DTO.class) : null;

        return ${table_name?uncap_first}DTO;
    }

    @Override
    public List<${table_name}DTO> findByGuidList(@RequestBody List<String> guidList) {
        if(guidList!=null){
            List<${table_name}Entity> entityList = ${table_name?uncap_first}Repository.findAllById(guidList);
            return POJOConvertUtil.convertList(entityList,${table_name}DTO.class);
        }
        return null;
    }

    @Override
    public List<${table_name}DTO> findList(@RequestBody ${table_name}QueryDTO queryDTO){

        Example<${table_name}Entity> example = get${table_name}EntityExample(queryDTO);
        //TODO:修改排序字段
        List<${table_name}Entity>  ${table_name?uncap_first}List = ${table_name?uncap_first}Repository.findAll(example,Sort.by(Sort.Direction.ASC,"createTime"));

        List<${table_name}DTO> ${table_name?uncap_first}DTOList = POJOConvertUtil.convertList(${table_name?uncap_first}List,${table_name}DTO.class);

        return ${table_name?uncap_first}DTOList;
    }

    private Example<${table_name}Entity> get${table_name}EntityExample(@RequestBody ${table_name}QueryDTO queryDTO) {
        ${table_name}Entity entity = POJOConvertUtil.convert(queryDTO,${table_name}Entity.class);

        //TODO:按照对象，增加不是匹配查询的条件
        ExampleMatcher exampleMatcher = ExampleMatcher.matching().withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING);

        return Example.of(entity,exampleMatcher);
    }

    @Override
    public Pagination<${table_name}DTO> findPage(@RequestBody ${table_name}QueryDTO queryDTO){

        Example<${table_name}Entity> example = get${table_name}EntityExample(queryDTO);
        //TODO:修改排序字段
        Pageable pageable = JPAUtils.toJpaPageableAndSort(queryDTO.getPageOrderDTO(),Sort.by(Sort.Direction.ASC,"createTime","guid"));
        Page<${table_name}Entity> page = ${table_name?uncap_first}Repository.findAll(example,pageable);
        return  JPAUtils.jpaPageToPagination(page,${table_name}DTO.class);

    }

    @Override
    public void save(@RequestBody ${table_name}DTO dto) {
        ${table_name}Entity ${table_name?uncap_first} = POJOConvertUtil.convert(dto, ${table_name}Entity.class);
        ${table_name?uncap_first}Repository.save(${table_name?uncap_first});
    }

    @Override
    public ${table_name}DTO update(@RequestBody ${table_name}DTO dto) {
        ${table_name}Entity ${table_name?uncap_first} = POJOConvertUtil.convert(dto, ${table_name}Entity.class);
        ${table_name?uncap_first} = ${table_name?uncap_first}Repository.update(${table_name?uncap_first});
        if(${table_name?uncap_first}!=null){
            return POJOConvertUtil.convert(${table_name?uncap_first},${table_name}DTO.class);
        }else{
            return null;
        }
    }

    @Override
    public void deleteById(@RequestParam("guid") String guid) {
        try {
            ${table_name?uncap_first}Repository.deleteById(guid);
        }catch (EmptyResultDataAccessException e){
            //do nothing
        }
    }

}

