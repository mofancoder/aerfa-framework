package com.zhulong.business.system.baseservice.api.subsystem.impl;

import com.zhulong.business.system.baseservice.api.subsystem.SubSystemBaseApi;
import com.zhulong.framework.common.dto.KeyValueDTO;
import com.zhulong.framework.common.jpa.BaseDao;
import com.zhulong.framework.common.jpa.JPAUtils;
import com.zhulong.framework.common.page.Pagination;
import com.zhulong.framework.common.util.POJOConvertUtil;
import com.zhulong.business.system.baseservice.repository.subsystem.SubSystemRepository;
import com.zhulong.business.system.baseservice.dto.subsystem.SubSystemDTO;
import com.zhulong.business.system.baseservice.dto.subsystem.SubSystemQueryDTO;
import com.zhulong.business.system.baseservice.entity.subsystem.SubSystemEntity;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.*;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

/**
 * 子系统管理-基础服务层接口
 * Created by shanb on 2019-2-26.
 */
@RestController
@Transactional
public class SubSystemBaseApiImpl extends BaseDao implements SubSystemBaseApi {

    @Autowired
    private SubSystemRepository subSystemRepository;

    @Override
    public void save(@RequestBody SubSystemDTO dto) {
        SubSystemEntity entity = POJOConvertUtil.convert(dto,SubSystemEntity.class);
        subSystemRepository.save(entity);
    }

    @Override
    public Boolean update(@RequestBody SubSystemDTO dto) {
        SubSystemEntity entity = POJOConvertUtil.convert(dto,SubSystemEntity.class);
        entity = subSystemRepository.update(entity);
        return entity!=null;
    }

    @Override
    public Boolean deleteByGuid(String guid) {
        boolean result = true;
        try {
            subSystemRepository.deleteById(guid);
        }catch (EmptyResultDataAccessException e){
            result = false;
        }
        return result;
    }

    @Override
    public Pagination<SubSystemDTO> queryPageByCondition(@RequestBody SubSystemQueryDTO queryDTO) {
        //简单查询  使用example进行查询
        SubSystemEntity entity = new SubSystemEntity();
        BeanUtils.copyProperties(queryDTO,entity);
        ExampleMatcher exampleMatcher = ExampleMatcher.matching().withIgnoreNullValues().withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING)
                .withMatcher("categoryGuid", ExampleMatcher.GenericPropertyMatcher.of(ExampleMatcher.StringMatcher.EXACT));
        Example<SubSystemEntity> example = Example.of(entity,exampleMatcher);
        Pageable pageable = JPAUtils.toJpaPageableAndSort(queryDTO.getPageOrderDTO(), Sort.by(Sort.Direction.ASC,"sortNum","guid"));
        Page<SubSystemEntity> pageEntity = subSystemRepository.findAll(example,pageable);
        return JPAUtils.jpaPageToPagination(pageEntity,SubSystemDTO.class);
    }

    @Override
    public List<SubSystemDTO> queryListByConditon(@RequestBody SubSystemQueryDTO queryDTO) {
        SubSystemEntity entity = new SubSystemEntity();
        BeanUtils.copyProperties(queryDTO,entity);
        ExampleMatcher exampleMatcher = ExampleMatcher.matching().withIgnoreNullValues().withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING)
                .withMatcher("categoryGuid", ExampleMatcher.GenericPropertyMatcher.of(ExampleMatcher.StringMatcher.EXACT));
        Example<SubSystemEntity> example = Example.of(entity,exampleMatcher);
        List<SubSystemEntity> entityList = subSystemRepository.findAll(example,Sort.by(Sort.Direction.ASC,"sortNum","guid"));
        return POJOConvertUtil.convertList(entityList,SubSystemDTO.class);
    }

    @Override
    public SubSystemDTO getByGuid(String guid) {
        Optional<SubSystemEntity> subSystemEntity = subSystemRepository.findById(guid);
        if(subSystemEntity.isPresent()){
            return POJOConvertUtil.convert(subSystemEntity.get(),SubSystemDTO.class);
        }
        return null;
    }

    @Override
    public List<SubSystemDTO> findByGuidList(@RequestBody List<String> guidList) {
        List<SubSystemEntity> entityList = subSystemRepository.findAllById(guidList);
        List<SubSystemDTO> dtoList = POJOConvertUtil.convertList(entityList,SubSystemDTO.class);
        return dtoList;
    }

    @Override
    public void updateSortNumList(@RequestBody List<KeyValueDTO<String, BigDecimal>> sortNumList) {
        for (KeyValueDTO<String, BigDecimal> sortNum : sortNumList) {
            subSystemRepository.updateSortNum(sortNum.getKey(),sortNum.getValue());
        }
    }

    @Override
    public void deleteByCategoryCode(String categoryCode) {
        subSystemRepository.deleteBySubSystemCode(categoryCode);
    }
}