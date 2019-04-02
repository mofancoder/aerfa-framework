package com.zhulong.business.system.baseservice.api.subsystem.impl;

import com.zhulong.business.system.baseservice.api.subsystem.MenuConfigBaseApi;
import com.zhulong.framework.common.dto.KeyValueDTO;
import com.zhulong.framework.common.jpa.BaseDao;
import com.zhulong.framework.common.jpa.JPAUtils;
import com.zhulong.framework.common.page.Pagination;
import com.zhulong.framework.common.util.POJOConvertUtil;
import com.zhulong.business.system.baseservice.repository.subsystem.MenuConfigRepository;
import com.zhulong.business.system.baseservice.dto.subsystem.MenuConfigDTO;
import com.zhulong.business.system.baseservice.dto.subsystem.MenuConfigQueryDTO;
import com.zhulong.business.system.baseservice.entity.subsystem.MenuConfigEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.*;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

/**
 * 菜单管理-基础服务层实现
 * Created by shanb on 2019-2-28.
 */
@RestController
@Transactional
public class MenuConfigBaseApiImpl extends BaseDao implements MenuConfigBaseApi {

    @Autowired
    private MenuConfigRepository menuConfigRepository;

    @Override
    public void save(@RequestBody MenuConfigDTO dto) {
        MenuConfigEntity entity = POJOConvertUtil.convert(dto,MenuConfigEntity.class);
        menuConfigRepository.save(entity);
    }

    @Override
    public Boolean update(@RequestBody MenuConfigDTO dto) {
        MenuConfigEntity entity = POJOConvertUtil.convert(dto,MenuConfigEntity.class);
        entity = menuConfigRepository.update(entity);
        return entity!=null;
    }

    @Override
    public Boolean deleteByGuid(String guid) {
        boolean result = true;
        try {
            menuConfigRepository.deleteById(guid);
        }catch (EmptyResultDataAccessException e){
            result = false;
        }
        return result;
    }

    @Override
    public MenuConfigDTO getByGuid(String guid) {
        Optional<MenuConfigEntity> entity = menuConfigRepository.findById(guid);
        if(entity.isPresent()){
            return POJOConvertUtil.convert(entity.get(),MenuConfigDTO.class);
        }
        return null;
    }

    @Override
    public List<MenuConfigDTO> findByGuidList(@RequestBody List<String> guidList) {
        if(guidList!=null && !guidList.isEmpty()) {
           List<MenuConfigEntity> entityList = menuConfigRepository.findAllById(guidList);
           return POJOConvertUtil.convertList(entityList,MenuConfigDTO.class);
        }
        return Collections.emptyList();
    }

    @Override
    public Pagination<MenuConfigDTO> findPageByCondition(@RequestBody MenuConfigQueryDTO dto) {
        MenuConfigEntity entity = POJOConvertUtil.convert(dto,MenuConfigEntity.class);
        ExampleMatcher matcher = ExampleMatcher.matching().withIgnoreNullValues().withMatcher("subSystemGuid", ExampleMatcher.GenericPropertyMatcher.of(ExampleMatcher.StringMatcher.EXACT))
                .withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING);
        Example<MenuConfigEntity> example = Example.of(entity,matcher);
        Pageable pageable = JPAUtils.toJpaPageableAndSort(dto.getPageOrderDTO(), Sort.by(Sort.Direction.ASC,"sortNum","guid"));
        Page<MenuConfigEntity> entityPage = menuConfigRepository.findAll(example,pageable);
        return JPAUtils.jpaPageToPagination(entityPage,MenuConfigDTO.class);
    }

    @Override
    public List<MenuConfigDTO> findListByCondition(@RequestBody MenuConfigQueryDTO dto) {
        MenuConfigEntity entity = POJOConvertUtil.convert(dto,MenuConfigEntity.class);
        ExampleMatcher matcher = ExampleMatcher.matching().withIgnoreNullValues().withMatcher("subSystemGuid", ExampleMatcher.GenericPropertyMatcher.of(ExampleMatcher.StringMatcher.EXACT))
                .withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING);
        Example<MenuConfigEntity> example = Example.of(entity,matcher);
        List<MenuConfigEntity> entityList = menuConfigRepository.findAll(example, Sort.by(Sort.Direction.ASC,"sortNum","guid"));
        return POJOConvertUtil.convertList(entityList,MenuConfigDTO.class);
    }

    @Override
    public void updateSortNum(@RequestBody List<KeyValueDTO<String, BigDecimal>> sortNumList) {
        sortNumList.forEach(dto ->
            menuConfigRepository.updateSortNum(dto.getKey(),dto.getValue())
        );
    }
}