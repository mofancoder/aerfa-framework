package com.zhulong.business.system.baseservice.api.commonbussetting;

import com.zhulong.business.system.baseservice.dto.commonbussetting.AreaSettingDTO;
import com.zhulong.business.system.baseservice.dto.commonbussetting.AreaSettingQueryDTO;
import com.zhulong.business.system.baseservice.entity.commonbussetting.AreaSettingEntity;
import com.zhulong.business.system.baseservice.repository.commonbussetting.AreaSettingRepository;
import com.zhulong.framework.common.dto.KeyValueDTO;
import com.zhulong.framework.common.jpa.BaseDao;
import com.zhulong.framework.common.jpa.Finder;
import com.zhulong.framework.common.page.Pagination;
import com.zhulong.framework.common.util.POJOConvertUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.*;

/**
 * 描述：行政区域 BaseApiImpl实现
 * @author 初。
 * @date 2019-03-18 11:44:45
 */
@Transactional
@RestController
public class AreaSettingBaseApiImpl extends BaseDao implements AreaSettingBaseApi {

    @Autowired
    private AreaSettingRepository areaSettingRepository;

    @Override
    public AreaSettingDTO getByGuid(@RequestParam("guid") String guid) {

        Optional<AreaSettingEntity> areaSettingOptional = areaSettingRepository.findById(guid);
        AreaSettingDTO areaSettingDTO = areaSettingOptional.isPresent() ? POJOConvertUtil.convert(areaSettingOptional.get(), AreaSettingDTO.class) : null;

        return areaSettingDTO;
    }

    @Override
    public List<AreaSettingDTO> findByGuidList(@RequestBody List<String> guidList) {

        if (guidList != null) {
            List<AreaSettingEntity> entityList = areaSettingRepository.findAllById(guidList);
            return POJOConvertUtil.convertList(entityList, AreaSettingDTO.class);
        }

        return null;
    }

    @Override
    public Pagination<AreaSettingDTO> findPage(@RequestBody AreaSettingQueryDTO queryDTO) {

        String hql = "select d from " + AreaSettingEntity.class.getSimpleName() + " d where 1=1 ";

        Finder finder = Finder.create(hql);
        if (StringUtils.isNotBlank(queryDTO.getCode())) {
        finder.append(" and d.code like :code ");
        finder.setParam("code", "%" + queryDTO.getCode() + "%");
        }
        if (StringUtils.isNotBlank(queryDTO.getFullNameZh())) {
        finder.append(" and d.fullNameZh like :fullNameZh ");
        finder.setParam("fullNameZh", "%" + queryDTO.getFullNameZh() + "%");
        }

        finder.append(" order by d.sortNum asc ");

        Pagination pagination = this.find(finder, queryDTO.getPageOrderDTO().getPageNo(), queryDTO.getPageOrderDTO().getPageSize());
        List<AreaSettingEntity> areaSettingEntities = pagination.getList();
        // 处理数据，获取上级名称
        if (areaSettingEntities != null && areaSettingEntities.size() > 0) {

            List<String> parentGuidList = new ArrayList<>();
            for (AreaSettingEntity areaSettingEntity : areaSettingEntities) {
                parentGuidList.add(areaSettingEntity.getParentGuid());
            }

            List<AreaSettingEntity> parentEntities = areaSettingRepository.findAllById(parentGuidList);
            Map<String, String> parentInfoMap = new HashMap<>();
            for (AreaSettingEntity areaSettingEntity : parentEntities) {
                parentInfoMap.put(areaSettingEntity.getGuid(), areaSettingEntity.getFullNameZh());
            }

            List<AreaSettingDTO> dataList = POJOConvertUtil.convertList(areaSettingEntities, AreaSettingDTO.class);
            for (AreaSettingDTO areaSettingDTO : dataList) {
                areaSettingDTO.setParentName(parentInfoMap.get(areaSettingDTO.getParentGuid()));
            }

            pagination.setList(dataList);
        }

        return pagination;
    }

    @Override
    public List<AreaSettingDTO> findList(@RequestBody AreaSettingQueryDTO queryDTO) {

        String hql = "select d from " + AreaSettingEntity.class.getSimpleName() + " d where 1=1 ";

        Finder finder = Finder.create(hql);
        if (StringUtils.isNotBlank(queryDTO.getCode())) {
            finder.append(" and d.code like :code ");
            finder.setParam("code", "%" + queryDTO.getCode() + "%");
        }
        if (StringUtils.isNotBlank(queryDTO.getFullNameZh())) {
            finder.append(" and d.fullNameZh like :fullNameZh ");
            finder.setParam("fullNameZh", "%" + queryDTO.getFullNameZh() + "%");
        }

        finder.append(" order by d.sortNum asc ");

        return POJOConvertUtil.convertList(this.find(finder), AreaSettingDTO.class);
    }

    @Override
    public void save(@RequestBody AreaSettingDTO dto) {
        AreaSettingEntity areaSetting = POJOConvertUtil.convert(dto, AreaSettingEntity.class);
        areaSettingRepository.save(areaSetting);
    }

    @Override
    public AreaSettingDTO update(@RequestBody AreaSettingDTO dto) {
        AreaSettingEntity areaSetting = POJOConvertUtil.convert(dto, AreaSettingEntity.class);
        areaSetting = areaSettingRepository.update(areaSetting);
        if (areaSetting != null) {
            return POJOConvertUtil.convert(areaSetting,AreaSettingDTO.class);
        } else {
            return null;
        }
    }

    @Override
    public void deleteById(@RequestParam("guid") String guid) {
        try {
            areaSettingRepository.deleteById(guid);
        } catch (EmptyResultDataAccessException e){
            //do nothing
        }
    }

    @Override
    public void updateSortNum(@RequestBody List<KeyValueDTO<String, BigDecimal>> keyValueDTOList) {
        if (keyValueDTOList != null && !keyValueDTOList.isEmpty()) {
            for (KeyValueDTO<String, BigDecimal> keyValueDTO : keyValueDTOList) {
                areaSettingRepository.updateSortNum(keyValueDTO.getKey(), keyValueDTO.getValue());
            }
        }
    }

}

