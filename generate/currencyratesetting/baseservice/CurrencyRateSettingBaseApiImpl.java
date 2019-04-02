package com.zhulong.business.system.baseservice.entity.commonbussetting.baseservice.api.commonbussetting.impl;

import com.zhulong.business.system.baseservice.entity.commonbussetting.baseservice.api.commonbussetting.CurrencyRateSettingBaseApi;
import com.zhulong.business.system.baseservice.entity.commonbussetting.baseservice.dto.commonbussetting.CurrencyRateSettingDTO;
import com.zhulong.business.system.baseservice.entity.commonbussetting.baseservice.dto.commonbussetting.CurrencyRateSettingQueryDTO;
import com.zhulong.business.system.baseservice.entity.commonbussetting.baseservice.entity.commonbussetting.CurrencyRateSettingEntity;
import com.zhulong.business.system.baseservice.entity.commonbussetting.baseservice.repository.commonbussetting.CurrencyRateSettingRepository;

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
* 描述：汇率管理 BaseApiImpl实现
* @author MOFAN889
* @date 2019-03-18 11:44:45
*/
@Transactional
@RestController
public class CurrencyRateSettingBaseApiImpl extends BaseDao implements CurrencyRateSettingBaseApi {

    @Autowired
    private  CurrencyRateSettingRepository currencyRateSettingRepository;

    @Override
    public CurrencyRateSettingDTO getByGuid(@RequestParam("guid") String guid) {

        Optional<CurrencyRateSettingEntity> currencyRateSettingOptional = currencyRateSettingRepository.findById(guid);

        CurrencyRateSettingDTO currencyRateSettingDTO = currencyRateSettingOptional.isPresent() ? POJOConvertUtil.convert(currencyRateSettingOptional.get(), CurrencyRateSettingDTO.class) : null;

        return currencyRateSettingDTO;
    }

    @Override
    public List<CurrencyRateSettingDTO> findByGuidList(@RequestBody List<String> guidList) {
        if(guidList!=null){
            List<CurrencyRateSettingEntity> entityList = currencyRateSettingRepository.findAllById(guidList);
            return POJOConvertUtil.convertList(entityList,CurrencyRateSettingDTO.class);
        }
        return null;
    }

    @Override
    public List<CurrencyRateSettingDTO> findList(@RequestBody CurrencyRateSettingQueryDTO queryDTO){

        Example<CurrencyRateSettingEntity> example = getCurrencyRateSettingEntityExample(queryDTO);
        //TODO:修改排序字段
        List<CurrencyRateSettingEntity>  currencyRateSettingList = currencyRateSettingRepository.findAll(example,Sort.by(Sort.Direction.ASC,"createTime"));

        List<CurrencyRateSettingDTO> currencyRateSettingDTOList = POJOConvertUtil.convertList(currencyRateSettingList,CurrencyRateSettingDTO.class);

        return currencyRateSettingDTOList;
    }

    private Example<CurrencyRateSettingEntity> getCurrencyRateSettingEntityExample(@RequestBody CurrencyRateSettingQueryDTO queryDTO) {
        CurrencyRateSettingEntity entity = POJOConvertUtil.convert(queryDTO,CurrencyRateSettingEntity.class);

        //TODO:按照对象，增加不是匹配查询的条件
        ExampleMatcher exampleMatcher = ExampleMatcher.matching().withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING);

        return Example.of(entity,exampleMatcher);
    }

    @Override
    public Pagination<CurrencyRateSettingDTO> findPage(@RequestBody CurrencyRateSettingQueryDTO queryDTO){

        Example<CurrencyRateSettingEntity> example = getCurrencyRateSettingEntityExample(queryDTO);
        //TODO:修改排序字段
        Pageable pageable = JPAUtils.toJpaPageableAndSort(queryDTO.getPageOrderDTO(),Sort.by(Sort.Direction.ASC,"createTime","guid"));
        Page<CurrencyRateSettingEntity> page = currencyRateSettingRepository.findAll(example,pageable);
        return  JPAUtils.jpaPageToPagination(page,CurrencyRateSettingDTO.class);

    }

    @Override
    public void save(@RequestBody CurrencyRateSettingDTO dto) {
        CurrencyRateSettingEntity currencyRateSetting = POJOConvertUtil.convert(dto, CurrencyRateSettingEntity.class);
        currencyRateSettingRepository.save(currencyRateSetting);
    }

    @Override
    public CurrencyRateSettingDTO update(@RequestBody CurrencyRateSettingDTO dto) {
        CurrencyRateSettingEntity currencyRateSetting = POJOConvertUtil.convert(dto, CurrencyRateSettingEntity.class);
        currencyRateSetting = currencyRateSettingRepository.update(currencyRateSetting);
        if(currencyRateSetting!=null){
            return POJOConvertUtil.convert(currencyRateSetting,CurrencyRateSettingDTO.class);
        }else{
            return null;
        }
    }

    @Override
    public void deleteById(@RequestParam("guid") String guid) {
        try {
            currencyRateSettingRepository.deleteById(guid);
        }catch (EmptyResultDataAccessException e){
            //do nothing
        }
    }

}

