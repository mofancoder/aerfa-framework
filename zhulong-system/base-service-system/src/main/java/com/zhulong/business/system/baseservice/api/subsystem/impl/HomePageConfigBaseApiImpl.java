package com.zhulong.business.system.baseservice.api.subsystem.impl;

import com.zhulong.business.system.baseservice.api.subsystem.HomePageConfigBaseApi;
import com.zhulong.business.system.baseservice.repository.subsystem.HomePageConfigRepository;
import com.zhulong.business.system.baseservice.entity.subsystem.HomePageConfigEntity;
import com.zhulong.framework.common.jpa.BaseDao;
import com.zhulong.framework.common.jpa.JPAUtils;
import com.zhulong.framework.common.page.Pagination;
import com.zhulong.framework.common.util.POJOConvertUtil;
import com.zhulong.business.system.baseservice.dto.subsystem.HomePageConfigDTO;
import com.zhulong.business.system.baseservice.dto.subsystem.HomePageConfigQueryDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.*;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

/**
 * Created by shanb on 2019-2-28.
 */
@RestController
@Transactional
public class HomePageConfigBaseApiImpl extends BaseDao implements HomePageConfigBaseApi {

    @Autowired
    private HomePageConfigRepository homePageConfigRepository;

    @Override
    public void save(@RequestBody HomePageConfigDTO dto) {
        HomePageConfigEntity entity = POJOConvertUtil.convert(dto,HomePageConfigEntity.class);
        homePageConfigRepository.save(entity);
    }

    @Override
    public Boolean update(@RequestBody HomePageConfigDTO dto) {
        HomePageConfigEntity entity = POJOConvertUtil.convert(dto,HomePageConfigEntity.class);
        return homePageConfigRepository.update(entity)!=null;
    }

    @Override
    public Boolean deleteByGuid(String guid) {
        boolean result = true;
        try {
            homePageConfigRepository.deleteById(guid);
        }catch (EmptyResultDataAccessException e){
            result = false;
        }
        return result;
    }

    @Override
    public Pagination<HomePageConfigDTO> findPageByCondition(@RequestBody HomePageConfigQueryDTO queryDTO) {
        HomePageConfigEntity entity = POJOConvertUtil.convert(queryDTO,HomePageConfigEntity.class);
        ExampleMatcher exampleMatcher = ExampleMatcher.matching().withIgnoreNullValues().withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING)
                .withMatcher("subSystemGuid", ExampleMatcher.GenericPropertyMatcher.of(ExampleMatcher.StringMatcher.EXACT));
        Example<HomePageConfigEntity> example = Example.of(entity,exampleMatcher);
        Pageable pageable = JPAUtils.toJpaPageableAndSort(queryDTO.getPageOrderDTO(), Sort.by(Sort.Direction.DESC,"createTime","guid"));
        Page<HomePageConfigEntity> entityPage = homePageConfigRepository.findAll(example,pageable);
        return JPAUtils.jpaPageToPagination(entityPage,HomePageConfigDTO.class);
    }

    @Override
    public List<HomePageConfigDTO> findListByCondition(@RequestBody HomePageConfigQueryDTO queryDTO) {
        HomePageConfigEntity entity = POJOConvertUtil.convert(queryDTO,HomePageConfigEntity.class);
        ExampleMatcher exampleMatcher = ExampleMatcher.matching().withIgnoreNullValues().withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING)
                .withMatcher("subSystemGuid", ExampleMatcher.GenericPropertyMatcher.of(ExampleMatcher.StringMatcher.EXACT));
        Example<HomePageConfigEntity> example = Example.of(entity,exampleMatcher);
        List<HomePageConfigEntity> entityList = homePageConfigRepository.findAll(example,Sort.by(Sort.Direction.DESC,"createTime","guid"));
        return POJOConvertUtil.convertList(entityList,HomePageConfigDTO.class);
    }

    @Override
    public HomePageConfigDTO getByGuid(String guid) {
        Optional<HomePageConfigEntity> entity = homePageConfigRepository.findById(guid);
        return entity.isPresent() ? POJOConvertUtil.convert(entity.get(),HomePageConfigDTO.class):null;
    }

    @Override
    public HomePageConfigDTO getCanUseConfig(String subSystemGuid, Short busModel) {
        List<HomePageConfigEntity> entityList = homePageConfigRepository.findBySubSystemGuidAndBusModelAndStatus(subSystemGuid,busModel,(short)1);
        if(!entityList.isEmpty()) {
            entityList.sort((o1, o2) -> (o1.getCreateTime() > o2.getCreateTime()) ? 1 : -1);
            return POJOConvertUtil.convert(entityList.get(0),HomePageConfigDTO.class);
        }
        return null;
    }
}