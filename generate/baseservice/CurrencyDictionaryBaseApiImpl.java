package com.zhulong.business.system.baseservice.entity.commonbussetting.baseservice.api.commonbussetting.impl;

import com.zhulong.business.system.baseservice.entity.commonbussetting.baseservice.api.commonbussetting.CurrencyDictionaryBaseApi;
import com.zhulong.business.system.baseservice.entity.commonbussetting.baseservice.dto.commonbussetting.CurrencyDictionaryDTO;
import com.zhulong.business.system.baseservice.entity.commonbussetting.baseservice.dto.commonbussetting.CurrencyDictionaryQueryDTO;
import com.zhulong.business.system.baseservice.entity.commonbussetting.baseservice.entity.commonbussetting.CurrencyDictionaryEntity;
import com.zhulong.business.system.baseservice.entity.commonbussetting.baseservice.repository.commonbussetting.CurrencyDictionaryRepository;

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
* 描述：币种管理 BaseApiImpl实现
* @author MOFAN889
* @date 2019-03-18 11:44:45
*/
@Transactional
@RestController
public class CurrencyDictionaryBaseApiImpl extends BaseDao implements CurrencyDictionaryBaseApi {

    @Autowired
    private  CurrencyDictionaryRepository currencyDictionaryRepository;

    @Override
    public CurrencyDictionaryDTO getByGuid(@RequestParam("guid") String guid) {

        Optional<CurrencyDictionaryEntity> currencyDictionaryOptional = currencyDictionaryRepository.findById(guid);

        CurrencyDictionaryDTO currencyDictionaryDTO = currencyDictionaryOptional.isPresent() ? POJOConvertUtil.convert(currencyDictionaryOptional.get(), CurrencyDictionaryDTO.class) : null;

        return currencyDictionaryDTO;
    }

    @Override
    public List<CurrencyDictionaryDTO> findByGuidList(@RequestBody List<String> guidList) {
        if(guidList!=null){
            List<CurrencyDictionaryEntity> entityList = currencyDictionaryRepository.findAllById(guidList);
            return POJOConvertUtil.convertList(entityList,CurrencyDictionaryDTO.class);
        }
        return null;
    }

    @Override
    public List<CurrencyDictionaryDTO> findList(@RequestBody CurrencyDictionaryQueryDTO queryDTO){

        Example<CurrencyDictionaryEntity> example = getCurrencyDictionaryEntityExample(queryDTO);
        //TODO:修改排序字段
        List<CurrencyDictionaryEntity>  currencyDictionaryList = currencyDictionaryRepository.findAll(example,Sort.by(Sort.Direction.ASC,"createTime"));

        List<CurrencyDictionaryDTO> currencyDictionaryDTOList = POJOConvertUtil.convertList(currencyDictionaryList,CurrencyDictionaryDTO.class);

        return currencyDictionaryDTOList;
    }

    private Example<CurrencyDictionaryEntity> getCurrencyDictionaryEntityExample(@RequestBody CurrencyDictionaryQueryDTO queryDTO) {
        CurrencyDictionaryEntity entity = POJOConvertUtil.convert(queryDTO,CurrencyDictionaryEntity.class);

        //TODO:按照对象，增加不是匹配查询的条件
        ExampleMatcher exampleMatcher = ExampleMatcher.matching().withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING);

        return Example.of(entity,exampleMatcher);
    }

    @Override
    public Pagination<CurrencyDictionaryDTO> findPage(@RequestBody CurrencyDictionaryQueryDTO queryDTO){

        Example<CurrencyDictionaryEntity> example = getCurrencyDictionaryEntityExample(queryDTO);
        //TODO:修改排序字段
        Pageable pageable = JPAUtils.toJpaPageableAndSort(queryDTO.getPageOrderDTO(),Sort.by(Sort.Direction.ASC,"createTime","guid"));
        Page<CurrencyDictionaryEntity> page = currencyDictionaryRepository.findAll(example,pageable);
        return  JPAUtils.jpaPageToPagination(page,CurrencyDictionaryDTO.class);

    }

    @Override
    public void save(@RequestBody CurrencyDictionaryDTO dto) {
        CurrencyDictionaryEntity currencyDictionary = POJOConvertUtil.convert(dto, CurrencyDictionaryEntity.class);
        currencyDictionaryRepository.save(currencyDictionary);
    }

    @Override
    public CurrencyDictionaryDTO update(@RequestBody CurrencyDictionaryDTO dto) {
        CurrencyDictionaryEntity currencyDictionary = POJOConvertUtil.convert(dto, CurrencyDictionaryEntity.class);
        currencyDictionary = currencyDictionaryRepository.update(currencyDictionary);
        if(currencyDictionary!=null){
            return POJOConvertUtil.convert(currencyDictionary,CurrencyDictionaryDTO.class);
        }else{
            return null;
        }
    }

    @Override
    public void deleteById(@RequestParam("guid") String guid) {
        try {
            currencyDictionaryRepository.deleteById(guid);
        }catch (EmptyResultDataAccessException e){
            //do nothing
        }
    }

}

