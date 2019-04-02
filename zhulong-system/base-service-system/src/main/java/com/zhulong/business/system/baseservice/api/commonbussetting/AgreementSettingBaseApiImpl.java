package com.zhulong.business.system.baseservice.api.commonbussetting;

import com.zhulong.business.system.baseservice.dto.commonbussetting.AgreementSettingDTO;
import com.zhulong.business.system.baseservice.dto.commonbussetting.AgreementSettingQueryDTO;
import com.zhulong.business.system.baseservice.entity.commonbussetting.AgreementSettingEntity;
import com.zhulong.business.system.baseservice.repository.commonbussetting.AgreementSettingRepository;
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
* 描述：协议管理 BaseApiImpl实现
* @author MOFAN889
* @date 2019-03-18 11:44:45
*/
@Transactional
@RestController
public class AgreementSettingBaseApiImpl extends BaseDao implements AgreementSettingBaseApi {

    @Autowired
    private AgreementSettingRepository agreementSettingRepository;

    @Override
    public AgreementSettingDTO getByGuid(@RequestParam("guid") String guid) {

        Optional<AgreementSettingEntity> agreementSettingOptional = agreementSettingRepository.findById(guid);

        AgreementSettingDTO agreementSettingDTO = agreementSettingOptional.isPresent() ? POJOConvertUtil.convert(agreementSettingOptional.get(), AgreementSettingDTO.class) : null;

        return agreementSettingDTO;
    }

    @Override
    public List<AgreementSettingDTO> findByGuidList(@RequestBody List<String> guidList) {
        if(guidList!=null){
            List<AgreementSettingEntity> entityList = agreementSettingRepository.findAllById(guidList);
            return POJOConvertUtil.convertList(entityList,AgreementSettingDTO.class);
        }
        return null;
    }

    @Override
    public List<AgreementSettingDTO> findList(@RequestBody AgreementSettingQueryDTO queryDTO){

        Example<AgreementSettingEntity> example = getAgreementSettingEntityExample(queryDTO);
        //TODO:修改排序字段
        List<AgreementSettingEntity>  agreementSettingList = agreementSettingRepository.findAll(example,Sort.by(Sort.Direction.ASC,"createTime"));

        List<AgreementSettingDTO> agreementSettingDTOList = POJOConvertUtil.convertList(agreementSettingList,AgreementSettingDTO.class);

        return agreementSettingDTOList;
    }

    private Example<AgreementSettingEntity> getAgreementSettingEntityExample(@RequestBody AgreementSettingQueryDTO queryDTO) {
        AgreementSettingEntity entity = POJOConvertUtil.convert(queryDTO,AgreementSettingEntity.class);

        //TODO:按照对象，增加不是匹配查询的条件
        ExampleMatcher exampleMatcher = ExampleMatcher.matching().withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING);

        return Example.of(entity,exampleMatcher);
    }

    @Override
    public Pagination<AgreementSettingDTO> findPage(@RequestBody AgreementSettingQueryDTO queryDTO){

        Example<AgreementSettingEntity> example = getAgreementSettingEntityExample(queryDTO);
        //TODO:修改排序字段
        Pageable pageable = JPAUtils.toJpaPageableAndSort(queryDTO.getPageOrderDTO(),Sort.by(Sort.Direction.ASC,"createTime","guid"));
        Page<AgreementSettingEntity> page = agreementSettingRepository.findAll(example,pageable);
        return  JPAUtils.jpaPageToPagination(page,AgreementSettingDTO.class);

    }

    @Override
    public void save(@RequestBody AgreementSettingDTO dto) {
        AgreementSettingEntity agreementSetting = POJOConvertUtil.convert(dto, AgreementSettingEntity.class);
        agreementSettingRepository.save(agreementSetting);
    }

    @Override
    public AgreementSettingDTO update(@RequestBody AgreementSettingDTO dto) {
        AgreementSettingEntity agreementSetting = POJOConvertUtil.convert(dto, AgreementSettingEntity.class);
        agreementSetting = agreementSettingRepository.update(agreementSetting);
        if(agreementSetting!=null){
            return POJOConvertUtil.convert(agreementSetting,AgreementSettingDTO.class);
        }else{
            return null;
        }
    }

    @Override
    public void deleteById(@RequestParam("guid") String guid) {
        try {
            agreementSettingRepository.deleteById(guid);
        }catch (EmptyResultDataAccessException e){
            //do nothing
        }
    }

}

