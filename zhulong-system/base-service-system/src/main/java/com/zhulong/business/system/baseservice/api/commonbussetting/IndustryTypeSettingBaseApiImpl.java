package com.zhulong.business.system.baseservice.api.commonbussetting;

import com.zhulong.business.system.baseservice.dto.commonbussetting.IndustryTypeSettingDTO;
import com.zhulong.business.system.baseservice.dto.commonbussetting.IndustryTypeSettingQueryDTO;
import com.zhulong.business.system.baseservice.entity.commonbussetting.IndustryTypeSettingEntity;
import com.zhulong.business.system.baseservice.repository.commonbussetting.IndustryTypeSettingRepository;
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

import java.util.*;

/**
 * 描述：国民经济行业分类 BaseApiImpl实现
 *
 * @author 初。
 * @date 2019-03-18 11:44:45
 */
@Transactional
@RestController
public class IndustryTypeSettingBaseApiImpl extends BaseDao implements IndustryTypeSettingBaseApi {

    @Autowired
    private IndustryTypeSettingRepository industryTypeSettingRepository;

    @Override
    public IndustryTypeSettingDTO getByGuid(@RequestParam("guid") String guid) {

        Optional<IndustryTypeSettingEntity> industryTypeSettingOptional = industryTypeSettingRepository.findById(guid);
        IndustryTypeSettingDTO industryTypeSettingDTO = industryTypeSettingOptional.isPresent() ? POJOConvertUtil.convert(industryTypeSettingOptional.get(), IndustryTypeSettingDTO.class) : null;

        return industryTypeSettingDTO;
    }

    @Override
    public List<IndustryTypeSettingDTO> findByGuidList(@RequestBody List<String> guidList) {

        if (guidList != null) {
            List<IndustryTypeSettingEntity> entityList = industryTypeSettingRepository.findAllById(guidList);
            return POJOConvertUtil.convertList(entityList, IndustryTypeSettingDTO.class);
        }

        return null;
    }

    @Override
    public Pagination<IndustryTypeSettingDTO> findPage(@RequestBody IndustryTypeSettingQueryDTO queryDTO) {

        String hql = "select d from " + IndustryTypeSettingEntity.class.getSimpleName() + " d where 1=1 ";

        Finder finder = Finder.create(hql);
        if (StringUtils.isNotBlank(queryDTO.getCode())) {
            finder.append(" and d.code like :code ");
            finder.setParam("code", "%" + queryDTO.getCode() + "%");
        }
        if (StringUtils.isNotBlank(queryDTO.getFullNameZh())) {
            finder.append(" and d.fullNameZh like :fullNameZh ");
            finder.setParam("fullNameZh", "%" + queryDTO.getFullNameZh() + "%");
        }

        finder.append(" order by d.createTime desc ");

        Pagination pagination = this.find(finder, queryDTO.getPageOrderDTO().getPageNo(), queryDTO.getPageOrderDTO().getPageSize());
        List<IndustryTypeSettingEntity> industryTypeSettingEntities = pagination.getList();
        // 处理数据，获取上级名称
        if (industryTypeSettingEntities != null && industryTypeSettingEntities.size() > 0) {

            List<String> parentGuidList = new ArrayList<>();
            for (IndustryTypeSettingEntity industryTypeSettingEntity : industryTypeSettingEntities) {
                parentGuidList.add(industryTypeSettingEntity.getParentGuid());
            }

            List<IndustryTypeSettingEntity> parentEntities = industryTypeSettingRepository.findAllById(parentGuidList);
            Map<String, String> parentInfoMap = new HashMap<>();
            for (IndustryTypeSettingEntity parentEntity : parentEntities) {
                parentInfoMap.put(parentEntity.getGuid(), parentEntity.getFullNameZh());
            }

            List<IndustryTypeSettingDTO> dataList = POJOConvertUtil.convertList(industryTypeSettingEntities, IndustryTypeSettingDTO.class);
            for (IndustryTypeSettingDTO industryTypeSettingDTO : dataList) {
                industryTypeSettingDTO.setParentName(parentInfoMap.get(industryTypeSettingDTO.getParentGuid()));
            }

            pagination.setList(dataList);
        }

        return pagination;
    }

    @Override
    public List<IndustryTypeSettingDTO> findList(@RequestBody IndustryTypeSettingQueryDTO queryDTO) {

        String hql = "select d from " + IndustryTypeSettingEntity.class.getSimpleName() + " d where 1=1 ";

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

        return POJOConvertUtil.convertList(this.find(finder), IndustryTypeSettingDTO.class);
    }

    @Override
    public void save(@RequestBody IndustryTypeSettingDTO dto) {
        IndustryTypeSettingEntity industryTypeSetting = POJOConvertUtil.convert(dto, IndustryTypeSettingEntity.class);
        industryTypeSettingRepository.save(industryTypeSetting);
    }

    @Override
    public IndustryTypeSettingDTO update(@RequestBody IndustryTypeSettingDTO dto) {
        IndustryTypeSettingEntity industryTypeSetting = POJOConvertUtil.convert(dto, IndustryTypeSettingEntity.class);
        industryTypeSetting = industryTypeSettingRepository.update(industryTypeSetting);
        if (industryTypeSetting != null) {
            return POJOConvertUtil.convert(industryTypeSetting, IndustryTypeSettingDTO.class);
        } else {
            return null;
        }
    }

    @Override
    public void deleteById(@RequestParam("guid") String guid) {
        try {
            industryTypeSettingRepository.deleteById(guid);
        } catch (EmptyResultDataAccessException e) {
            //do nothing
        }
    }

}

