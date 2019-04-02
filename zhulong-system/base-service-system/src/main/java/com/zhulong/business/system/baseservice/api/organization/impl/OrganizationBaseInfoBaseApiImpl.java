package com.zhulong.business.system.baseservice.api.organization.impl;

import com.zhulong.business.system.baseservice.api.organization.OrganizationBaseInfoBaseApi;
import com.zhulong.business.system.baseservice.dto.organization.OrganizationBaseInfoDTO;
import com.zhulong.business.system.baseservice.dto.organization.OrganizationBaseInfoQueryDTO;
import com.zhulong.business.system.baseservice.dto.organization.OrganizationUpdateBusStatusDTO;
import com.zhulong.business.system.baseservice.entity.common.DataModifyRecordEntity;
import com.zhulong.business.system.baseservice.entity.organization.OrganizationBaseInfoEntity;
import com.zhulong.business.system.baseservice.repository.common.DataModifyRecordRepository;
import com.zhulong.business.system.baseservice.repository.organization.OrganizationBaseInfoRepository;

import com.zhulong.framework.common.dto.KeyValueDTO;
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

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

/**
* 描述：组织管理 BaseApiImpl实现
* @author shanb
* @date 2019-03-18 11:44:45
*/
@Transactional
@RestController
public class OrganizationBaseInfoBaseApiImpl extends BaseDao implements OrganizationBaseInfoBaseApi {

    @Autowired
    private OrganizationBaseInfoRepository organizationBaseInfoRepository;

    @Autowired
    private DataModifyRecordRepository dataModifyRecordRepository;

    @Override
    public OrganizationBaseInfoDTO getByGuid(@RequestParam("guid") String guid) {

        Optional<OrganizationBaseInfoEntity> organizationBaseInfoOptional = organizationBaseInfoRepository.findById(guid);

        OrganizationBaseInfoDTO organizationBaseInfoDTO = organizationBaseInfoOptional.isPresent() ? POJOConvertUtil.convert(organizationBaseInfoOptional.get(), OrganizationBaseInfoDTO.class) : null;

        return organizationBaseInfoDTO;
    }

    @Override
    public List<OrganizationBaseInfoDTO> findByGuidList(@RequestBody List<String> guidList) {
        if(guidList!=null){
            List<OrganizationBaseInfoEntity> entityList = organizationBaseInfoRepository.findAllById(guidList);
            return POJOConvertUtil.convertList(entityList,OrganizationBaseInfoDTO.class);
        }
        return null;
    }

    @Override
    public List<OrganizationBaseInfoDTO> findList(@RequestBody OrganizationBaseInfoQueryDTO queryDTO){

        Example<OrganizationBaseInfoEntity> example = getOgranizationBaseInfoEntityExample(queryDTO);
        List<OrganizationBaseInfoEntity>  organizationBaseInfoList = organizationBaseInfoRepository.findAll(example,Sort.by(Sort.Direction.ASC,"sortNum","createTime"));

        List<OrganizationBaseInfoDTO> organizationBaseInfoDTOList = POJOConvertUtil.convertList(organizationBaseInfoList,OrganizationBaseInfoDTO.class);

        return organizationBaseInfoDTOList;
    }

    private Example<OrganizationBaseInfoEntity> getOgranizationBaseInfoEntityExample(@RequestBody OrganizationBaseInfoQueryDTO queryDTO) {
        OrganizationBaseInfoEntity entity = POJOConvertUtil.convert(queryDTO,OrganizationBaseInfoEntity.class);
        ExampleMatcher exampleMatcher = ExampleMatcher.matching().withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING)
                .withMatcher("parentGuid", ExampleMatcher.GenericPropertyMatcher.of(ExampleMatcher.StringMatcher.EXACT))
                .withMatcher("typeCode", ExampleMatcher.GenericPropertyMatcher.of(ExampleMatcher.StringMatcher.EXACT));
        return Example.of(entity,exampleMatcher);
    }

    @Override
    public Pagination<OrganizationBaseInfoDTO> findPage(@RequestBody OrganizationBaseInfoQueryDTO queryDTO){

        Example<OrganizationBaseInfoEntity> example = getOgranizationBaseInfoEntityExample(queryDTO);
        Pageable pageable = JPAUtils.toJpaPageableAndSort(queryDTO.getPageOrderDTO(),Sort.by(Sort.Direction.ASC,"sortNum","guid"));
        Page<OrganizationBaseInfoEntity> page = organizationBaseInfoRepository.findAll(example,pageable);
        return  JPAUtils.jpaPageToPagination(page,OrganizationBaseInfoDTO.class);

    }

    @Override
    public void save(@RequestBody OrganizationBaseInfoDTO dto) {
        OrganizationBaseInfoEntity organizationBaseInfo = POJOConvertUtil.convert(dto, OrganizationBaseInfoEntity.class);
        organizationBaseInfoRepository.save(organizationBaseInfo);
    }

    @Override
    public OrganizationBaseInfoDTO update(@RequestBody OrganizationBaseInfoDTO dto) {
        OrganizationBaseInfoEntity organizationBaseInfo = POJOConvertUtil.convert(dto, OrganizationBaseInfoEntity.class);
        organizationBaseInfo = organizationBaseInfoRepository.update(organizationBaseInfo);
        if(organizationBaseInfo!=null){
            return POJOConvertUtil.convert(organizationBaseInfo,OrganizationBaseInfoDTO.class);
        }else{
            return null;
        }
    }

    @Override
    public void deleteById(@RequestParam("guid") String guid) {
        try {
            organizationBaseInfoRepository.deleteById(guid);
        }catch (EmptyResultDataAccessException e){
            //do nothing
        }
    }

    @Override
    public void updateSortNum(@RequestBody List<KeyValueDTO<String, BigDecimal>> guidSortNumList) {
        for (KeyValueDTO<String, BigDecimal> guidSortNum : guidSortNumList) {
            organizationBaseInfoRepository.updateSortNum(guidSortNum.getKey(),guidSortNum.getValue());
        }
    }

    @Override
    public void updateBusStatus(@RequestBody OrganizationUpdateBusStatusDTO updateDTO) {
        OrganizationBaseInfoEntity entity = new OrganizationBaseInfoEntity();
        entity.setGuid(updateDTO.getGuid());
        entity.setBusStatus(updateDTO.getBusStatus());
        organizationBaseInfoRepository.update(entity);

        if(updateDTO.getModifyRecordDTO()!=null) {
            DataModifyRecordEntity modifyRecordEntity = POJOConvertUtil.convert(updateDTO.getModifyRecordDTO(),DataModifyRecordEntity.class);
            dataModifyRecordRepository.save(modifyRecordEntity);
        }
    }
}

