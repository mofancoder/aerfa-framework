package com.zhulong.business.system.baseservice.api.commonbussetting;

import com.zhulong.business.system.baseservice.dto.commonbussetting.TransTypeSettingDTO;
import com.zhulong.business.system.baseservice.dto.commonbussetting.TransTypeSettingQueryDTO;
import com.zhulong.business.system.baseservice.entity.commonbussetting.IndustryTypeSettingEntity;
import com.zhulong.business.system.baseservice.entity.commonbussetting.TransTypeSettingEntity;
import com.zhulong.business.system.baseservice.repository.commonbussetting.TransTypeSettingRepository;
import com.zhulong.framework.common.dto.KeyValueDTO;
import com.zhulong.framework.common.jpa.BaseDao;
import com.zhulong.framework.common.jpa.Finder;
import com.zhulong.framework.common.page.Pagination;
import com.zhulong.framework.common.util.POJOConvertUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.*;

/**
 * 描述：交易类型 BaseApiImpl实现
 *
 * @author 初。
 * @date 2019-03-18 11:44:45
 */
@Transactional
@RestController
public class TransTypeSettingBaseApiImpl extends BaseDao implements TransTypeSettingBaseApi {

    @Autowired
    private TransTypeSettingRepository transTypeSettingRepository;

    @Override
    public TransTypeSettingDTO getByGuid(@RequestParam("guid") String guid) {

        Optional<TransTypeSettingEntity> transTypeSettingOptional = transTypeSettingRepository.findById(guid);
        TransTypeSettingDTO transTypeSettingDTO = transTypeSettingOptional.isPresent() ? POJOConvertUtil.convert(transTypeSettingOptional.get(), TransTypeSettingDTO.class) : null;

        return transTypeSettingDTO;
    }

    @Override
    public List<TransTypeSettingDTO> findByGuidList(@RequestBody List<String> guidList) {

        if (guidList != null) {
            List<TransTypeSettingEntity> entityList = transTypeSettingRepository.findAllById(guidList);
            return POJOConvertUtil.convertList(entityList, TransTypeSettingDTO.class);
        }

        return null;
    }

    @Override
    public Pagination<TransTypeSettingDTO> findPage(@RequestBody TransTypeSettingQueryDTO queryDTO) {

        String hql = "select d from " + TransTypeSettingEntity.class.getSimpleName() + " d where 1=1 ";

        Finder finder = Finder.create(hql);
        finder.append(" order by d.sortNum asc ");

        Pagination pagination = this.find(finder, queryDTO.getPageOrderDTO().getPageNo(), queryDTO.getPageOrderDTO().getPageSize());
        List<TransTypeSettingEntity> transTypeSettingEntities = pagination.getList();
        // 处理数据，获取上级名称
        if (transTypeSettingEntities != null && transTypeSettingEntities.size() > 0) {

            List<String> parentGuidList = new ArrayList<>();
            for (TransTypeSettingEntity transTypeSettingEntity : transTypeSettingEntities) {
                parentGuidList.add(transTypeSettingEntity.getParentGuid());
            }

            List<TransTypeSettingEntity> parentEntities = transTypeSettingRepository.findAllById(parentGuidList);
            Map<String, String> parentInfoMap = new HashMap<>();
            for (TransTypeSettingEntity parentEntity : parentEntities) {
                parentInfoMap.put(parentEntity.getGuid(), parentEntity.getFullNameZh());
            }

            List<TransTypeSettingDTO> dataList = POJOConvertUtil.convertList(transTypeSettingEntities, TransTypeSettingDTO.class);
            for (TransTypeSettingDTO transTypeSettingDTO : dataList) {
                transTypeSettingDTO.setParentName(parentInfoMap.get(transTypeSettingDTO.getParentGuid()));
            }

            pagination.setList(dataList);
        }

        return pagination;
    }

    @Override
    public List<TransTypeSettingDTO> findList(@RequestBody TransTypeSettingQueryDTO queryDTO) {

        String hql = "select d from " + IndustryTypeSettingEntity.class.getSimpleName() + " d where 1=1 ";

        Finder finder = Finder.create(hql);
        finder.append(" order by d.sortNum asc ");

        return POJOConvertUtil.convertList(this.find(finder), TransTypeSettingDTO.class);
    }

    @Override
    public void save(@RequestBody TransTypeSettingDTO dto) {
        TransTypeSettingEntity transTypeSetting = POJOConvertUtil.convert(dto, TransTypeSettingEntity.class);
        transTypeSettingRepository.save(transTypeSetting);
    }

    @Override
    public TransTypeSettingDTO update(@RequestBody TransTypeSettingDTO dto) {
        TransTypeSettingEntity transTypeSetting = POJOConvertUtil.convert(dto, TransTypeSettingEntity.class);
        transTypeSetting = transTypeSettingRepository.update(transTypeSetting);
        if (transTypeSetting != null) {
            return POJOConvertUtil.convert(transTypeSetting, TransTypeSettingDTO.class);
        } else {
            return null;
        }
    }

    @Override
    public void deleteById(@RequestParam("guid") String guid) {
        try {
            transTypeSettingRepository.deleteById(guid);
        } catch (EmptyResultDataAccessException e) {
            //do nothing
        }
    }

    @Override
    public void updateSortNum(@RequestBody List<KeyValueDTO<String, BigDecimal>> keyValueDTOList) {
        if (keyValueDTOList != null && !keyValueDTOList.isEmpty()) {
            for (KeyValueDTO<String, BigDecimal> keyValueDTO : keyValueDTOList) {
                transTypeSettingRepository.updateSortNum(keyValueDTO.getKey(), keyValueDTO.getValue());
            }
        }
    }

}

