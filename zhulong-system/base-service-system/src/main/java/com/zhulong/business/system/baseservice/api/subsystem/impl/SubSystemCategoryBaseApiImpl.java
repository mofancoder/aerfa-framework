package com.zhulong.business.system.baseservice.api.subsystem.impl;

import com.zhulong.business.system.baseservice.api.subsystem.SubSystemCategoryBaseApi;
import com.zhulong.business.system.baseservice.dto.subsystem.SubSystemCategoryDTO;
import com.zhulong.business.system.baseservice.dto.subsystem.SubSystemCategoryQueryDTO;
import com.zhulong.business.system.baseservice.entity.subsystem.SubSystemCategoryEntity;
import com.zhulong.business.system.baseservice.repository.subsystem.SubSystemCategoryRepository;
import com.zhulong.framework.common.dto.KeyValueDTO;
import com.zhulong.framework.common.dto.PageOrderDTO;
import com.zhulong.framework.common.jpa.BaseDao;
import com.zhulong.framework.common.jpa.Finder;
import com.zhulong.framework.common.jpa.JPAUtils;
import com.zhulong.framework.common.page.Pagination;
import com.zhulong.framework.common.util.POJOConvertUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.*;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

/**
 * 子系统分类-基础服务层接口实现
 * Created by shanb on 2019-2-25.
 */
@Transactional
@RestController
public class SubSystemCategoryBaseApiImpl extends BaseDao implements SubSystemCategoryBaseApi {

    @Autowired
    private SubSystemCategoryRepository subSystemCategoryRepository;

    @Override
    public void save(@RequestBody SubSystemCategoryDTO dto) {
        SubSystemCategoryEntity entity = POJOConvertUtil.convert(dto,SubSystemCategoryEntity.class);
        subSystemCategoryRepository.save(entity);
    }

    @Override
    public Boolean update(@RequestBody SubSystemCategoryDTO dto) {
        SubSystemCategoryEntity entity = POJOConvertUtil.convert(dto,SubSystemCategoryEntity.class);
        entity = subSystemCategoryRepository.update(entity);
        return entity!=null;
    }

    @Override
    public void updateSortNums(@RequestBody List<KeyValueDTO<String, BigDecimal>> sortNumList) {
        for (KeyValueDTO<String, BigDecimal> sortNumKeyValue : sortNumList) {
            subSystemCategoryRepository.updateSortNum(sortNumKeyValue.getKey(),sortNumKeyValue.getValue());
        }
    }

    @Override
    public Boolean deleteByGuid(String guid) {
        boolean result = true;
        try {
            subSystemCategoryRepository.deleteById(guid);
        }catch (EmptyResultDataAccessException e){
            result = false;
        }
        return result;
    }

    @Override
    public SubSystemCategoryDTO getByGuid(String guid) {
        Optional<SubSystemCategoryEntity> entityOptional = subSystemCategoryRepository.findById(guid);
        if(entityOptional.isPresent()){
            SubSystemCategoryDTO dto = POJOConvertUtil.convert(entityOptional.get(),SubSystemCategoryDTO.class);
            return dto;
        }
        return null;
    }

    @Override
    public Pagination<SubSystemCategoryDTO> findPageByCondition(@RequestBody SubSystemCategoryQueryDTO queryDto) {
        //使用example查询
        SubSystemCategoryEntity entity = POJOConvertUtil.convert(queryDto,SubSystemCategoryEntity.class);
        BeanUtils.copyProperties(entity,queryDto);
        ExampleMatcher exampleMatcher = ExampleMatcher.matching().withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING).withIgnoreNullValues();
        Example<SubSystemCategoryEntity> example = Example.of(entity,exampleMatcher);
        //组装sort
        PageOrderDTO pageOrderDto = queryDto.getPageOrderDTO();
        Pageable pageable = JPAUtils.toJpaPageableAndSort(pageOrderDto,Sort.by("sortNum","guid"));
        Page<SubSystemCategoryEntity> jpaPageEntity = subSystemCategoryRepository.findAll(example, pageable);
        return JPAUtils.jpaPageToPagination(jpaPageEntity,SubSystemCategoryDTO.class);
    }

    @Override
    public List<SubSystemCategoryDTO> findListByCondition(@RequestBody SubSystemCategoryQueryDTO queryDTO) {
        //使用example查询
        SubSystemCategoryEntity entity = POJOConvertUtil.convert(queryDTO,SubSystemCategoryEntity.class);
        BeanUtils.copyProperties(entity,queryDTO);
        ExampleMatcher exampleMatcher = ExampleMatcher.matching().withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING).withIgnoreNullValues();
        Example<SubSystemCategoryEntity> example = Example.of(entity,exampleMatcher);
        List<SubSystemCategoryEntity> entityList = subSystemCategoryRepository.findAll(example);
        return POJOConvertUtil.convertList(entityList,SubSystemCategoryDTO.class);
    }

    @Override
    public List<SubSystemCategoryDTO> findListByGuidList(@RequestBody List<String> guidList) {
        List<SubSystemCategoryEntity> entityList = subSystemCategoryRepository.findAllById(guidList);
        return POJOConvertUtil.convertList(entityList,SubSystemCategoryDTO.class);
    }

    @Override
    public List<SubSystemCategoryDTO> findPageByJpql(@RequestBody SubSystemCategoryDTO categoryDTO){
        Finder finder = Finder.create("select t from "+SubSystemCategoryEntity.class.getSimpleName()+" t where deleted = false");
        if(!StringUtils.isEmpty(categoryDTO.getCode())){
            finder.append(" and code like :code");
            finder .setParam("code","%"+categoryDTO.getCode()+"%");
        }
        List<SubSystemCategoryEntity> subSystemCategoryEntityList = find(finder);
        return POJOConvertUtil.convertList(subSystemCategoryEntityList,SubSystemCategoryDTO.class);
    }
}