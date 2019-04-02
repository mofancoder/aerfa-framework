package com.zhulong.business.system.baseservice.api.subsystem.impl;

import com.zhulong.business.system.baseservice.api.subsystem.FunctionConfigBaseApi;
import com.zhulong.business.system.baseservice.dto.subsystem.FunctionConfigDTO;
import com.zhulong.business.system.baseservice.dto.subsystem.FunctionConfigQueryDTO;
import com.zhulong.business.system.baseservice.entity.subsystem.FunctionConfigEntity;
import com.zhulong.business.system.baseservice.repository.subsystem.FunctionConfigRepository;

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
* 描述：功能管理 BaseApiImpl实现
* @author shanb
* @date 2019-03-18 11:44:45
*/
@Transactional
@RestController
public class FunctionConfigBaseApiImpl extends BaseDao implements FunctionConfigBaseApi {

    @Autowired
    private  FunctionConfigRepository functionConfigRepository;

    @Override
    public FunctionConfigDTO getByGuid(@RequestParam("guid") String guid) {

        Optional<FunctionConfigEntity> functionConfigOptional = functionConfigRepository.findById(guid);

        FunctionConfigDTO functionConfigDTO = functionConfigOptional.isPresent() ? POJOConvertUtil.convert(functionConfigOptional.get(), FunctionConfigDTO.class) : null;

        return functionConfigDTO;
    }

    @Override
    public List<FunctionConfigDTO> findByGuidList(@RequestBody List<String> guidList) {
        if(guidList!=null){
            List<FunctionConfigEntity> entityList = functionConfigRepository.findAllById(guidList);
            return POJOConvertUtil.convertList(entityList,FunctionConfigDTO.class);
        }
        return null;
    }

    @Override
    public List<FunctionConfigDTO> findList(@RequestBody FunctionConfigQueryDTO queryDTO){

        Example<FunctionConfigEntity> example = getFunctionConfigEntityExample(queryDTO);

        List<FunctionConfigEntity>  functionConfigList = functionConfigRepository.findAll(example,Sort.by(Sort.Direction.ASC,"sortNum"));

        List<FunctionConfigDTO> functionConfigDTOList = POJOConvertUtil.convertList(functionConfigList,FunctionConfigDTO.class);

        return functionConfigDTOList;
    }

    private Example<FunctionConfigEntity> getFunctionConfigEntityExample(@RequestBody FunctionConfigQueryDTO queryDTO) {
        FunctionConfigEntity entity = POJOConvertUtil.convert(queryDTO,FunctionConfigEntity.class);

        ExampleMatcher exampleMatcher = ExampleMatcher.matching().withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING)
                .withMatcher("menuItemGuid", ExampleMatcher.GenericPropertyMatcher.of(ExampleMatcher.StringMatcher.EXACT))
                .withMatcher("menuConfigGuid", ExampleMatcher.GenericPropertyMatcher.of(ExampleMatcher.StringMatcher.EXACT));

        return Example.of(entity,exampleMatcher);
    }

    @Override
    public Pagination<FunctionConfigDTO> findPage(@RequestBody FunctionConfigQueryDTO queryDTO){

        Example<FunctionConfigEntity> example = getFunctionConfigEntityExample(queryDTO);
        Pageable pageable = JPAUtils.toJpaPageableAndSort(queryDTO.getPageOrderDTO(),Sort.by(Sort.Direction.ASC,"sortNum","guid"));
        Page<FunctionConfigEntity> page = functionConfigRepository.findAll(example,pageable);
        return  JPAUtils.jpaPageToPagination(page,FunctionConfigDTO.class);

    }

    @Override
    public void save(@RequestBody FunctionConfigDTO dto) {
        FunctionConfigEntity functionConfig = POJOConvertUtil.convert(dto, FunctionConfigEntity.class);
        functionConfigRepository.save(functionConfig);
    }

    @Override
    public FunctionConfigDTO update(@RequestBody FunctionConfigDTO dto) {
        FunctionConfigEntity functionConfig = POJOConvertUtil.convert(dto, FunctionConfigEntity.class);
        functionConfig = functionConfigRepository.update(functionConfig);
        if(functionConfig!=null){
            return POJOConvertUtil.convert(functionConfig,FunctionConfigDTO.class);
        }else{
            return null;
        }
    }

    @Override
    public void deleteById(@RequestParam("guid") String guid) {
        try {
            functionConfigRepository.deleteById(guid);
        }catch (EmptyResultDataAccessException e){
            //do nothing
        }
    }

}

