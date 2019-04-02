package com.zhulong.business.system.baseservice.api.commonbussetting;

import com.zhulong.business.system.baseservice.dto.commonbussetting.PublicSourceTransSettingDTO;
import com.zhulong.business.system.baseservice.dto.commonbussetting.PublicSourceTransSettingQueryDTO;
import com.zhulong.business.system.baseservice.entity.commonbussetting.PublicSourceTransSettingEntity;
import com.zhulong.business.system.baseservice.repository.commonbussetting.PublicSourceTransSettingRepository;
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
 * 描述：公共资源分类 BaseApiImpl实现
 *
 * @author 初。
 * @date 2019-03-18 11:44:45
 */
@Transactional
@RestController
public class PublicSourceTransSettingBaseApiImpl extends BaseDao implements PublicSourceTransSettingBaseApi {

    @Autowired
    private PublicSourceTransSettingRepository publicSourceTransSettingRepository;

    @Override
    public PublicSourceTransSettingDTO getByGuid(@RequestParam("guid") String guid) {

        Optional<PublicSourceTransSettingEntity> publicSourceTransSettingOptional = publicSourceTransSettingRepository.findById(guid);
        PublicSourceTransSettingDTO publicSourceTransSettingDTO = publicSourceTransSettingOptional.isPresent() ? POJOConvertUtil.convert(publicSourceTransSettingOptional.get(), PublicSourceTransSettingDTO.class) : null;

        return publicSourceTransSettingDTO;
    }

    @Override
    public List<PublicSourceTransSettingDTO> findByGuidList(@RequestBody List<String> guidList) {

        if (guidList != null) {
            List<PublicSourceTransSettingEntity> entityList = publicSourceTransSettingRepository.findAllById(guidList);
            return POJOConvertUtil.convertList(entityList, PublicSourceTransSettingDTO.class);
        }

        return null;
    }

    @Override
    public Pagination<PublicSourceTransSettingDTO> findPage(@RequestBody PublicSourceTransSettingQueryDTO queryDTO) {

        String hql = "select d from " + PublicSourceTransSettingEntity.class.getSimpleName() + " d where 1=1 ";

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
        List<PublicSourceTransSettingEntity> publicSourceTransSettingEntities = pagination.getList();
        // 处理数据，获取上级名称
        if (publicSourceTransSettingEntities != null && publicSourceTransSettingEntities.size() > 0) {

            List<String> parentGuidList = new ArrayList<>();
            for (PublicSourceTransSettingEntity publicSourceTransSettingEntity : publicSourceTransSettingEntities) {
                parentGuidList.add(publicSourceTransSettingEntity.getParentGuid());
            }

            List<PublicSourceTransSettingEntity> parentEntities = publicSourceTransSettingRepository.findAllById(parentGuidList);
            Map<String, String> parentInfoMap = new HashMap<>();
            for (PublicSourceTransSettingEntity parentEntity : parentEntities) {
                parentInfoMap.put(parentEntity.getGuid(), parentEntity.getFullNameZh());
            }

            List<PublicSourceTransSettingDTO> dataList = POJOConvertUtil.convertList(publicSourceTransSettingEntities, PublicSourceTransSettingDTO.class);
            for (PublicSourceTransSettingDTO publicSourceTransSettingDTO : dataList) {
                publicSourceTransSettingDTO.setParentName(parentInfoMap.get(publicSourceTransSettingDTO.getParentGuid()));
            }

            pagination.setList(dataList);
        }

        return pagination;
    }

    @Override
    public List<PublicSourceTransSettingDTO> findList(@RequestBody PublicSourceTransSettingQueryDTO queryDTO) {

        String hql = "select d from " + PublicSourceTransSettingEntity.class.getSimpleName() + " d where 1=1 ";

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

        return POJOConvertUtil.convertList(this.find(finder), PublicSourceTransSettingDTO.class);
    }

    @Override
    public void save(@RequestBody PublicSourceTransSettingDTO dto) {
        PublicSourceTransSettingEntity publicSourceTransSetting = POJOConvertUtil.convert(dto, PublicSourceTransSettingEntity.class);
        publicSourceTransSettingRepository.save(publicSourceTransSetting);
    }

    @Override
    public PublicSourceTransSettingDTO update(@RequestBody PublicSourceTransSettingDTO dto) {
        PublicSourceTransSettingEntity publicSourceTransSetting = POJOConvertUtil.convert(dto, PublicSourceTransSettingEntity.class);
        publicSourceTransSetting = publicSourceTransSettingRepository.update(publicSourceTransSetting);
        if (publicSourceTransSetting != null) {
            return POJOConvertUtil.convert(publicSourceTransSetting, PublicSourceTransSettingDTO.class);
        } else {
            return null;
        }
    }

    @Override
    public void deleteById(@RequestParam("guid") String guid) {
        try {
            publicSourceTransSettingRepository.deleteById(guid);
        } catch (EmptyResultDataAccessException e) {
            //do nothing
        }
    }

}

