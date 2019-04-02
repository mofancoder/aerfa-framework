package com.zhulong.business.system.baseservice.api.systemconfig.impl;

import com.zhulong.business.system.baseservice.api.systemconfig.DataDictionaryTypeBaseApi;
import com.zhulong.business.system.baseservice.dto.systemconfig.DataDictionaryTypeDTO;
import com.zhulong.business.system.baseservice.dto.systemconfig.DataDictionaryTypeQueryDTO;
import com.zhulong.business.system.baseservice.dto.systemconfig.DataDictionaryValueDTO;
import com.zhulong.business.system.baseservice.entity.systemconfig.DataDictionaryTypeEntity;
import com.zhulong.business.system.baseservice.repository.systemconfig.DataDictionaryTypeRepository;
import com.zhulong.framework.common.jpa.BaseDao;
import com.zhulong.framework.common.jpa.Finder;
import com.zhulong.framework.common.page.Pagination;
import com.zhulong.framework.common.util.POJOConvertUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

/**
 * 描述：数据字典类型 BaseApiImpl实现
 *
 * @author 初。
 * @date 2019-03-20 10:00:06
 */
@Transactional
@RestController
public class DataDictionaryTypeBaseApiImpl extends BaseDao implements DataDictionaryTypeBaseApi {

    @Autowired
    private DataDictionaryTypeRepository dataDictionaryTypeRepository;

    @Override
    public DataDictionaryTypeDTO getById(@RequestParam("guid") String guid) {

        Optional<DataDictionaryTypeEntity> dataDictionaryTypeOptional = dataDictionaryTypeRepository.findById(guid);
        DataDictionaryTypeDTO dataDictionaryValueDTO = dataDictionaryTypeOptional.isPresent() ? POJOConvertUtil.convert(dataDictionaryTypeOptional.get(), DataDictionaryTypeDTO.class) : null;

        return dataDictionaryValueDTO;
    }

    @Override
    public List<DataDictionaryTypeDTO> findAll() {

        List<DataDictionaryTypeEntity> dataDictionaryTypeList = dataDictionaryTypeRepository.findAll();
        List<DataDictionaryTypeDTO> dataDictionaryTypeDTOList = POJOConvertUtil.convertList(dataDictionaryTypeList, DataDictionaryTypeDTO.class);

        return dataDictionaryTypeDTOList;
    }

    @Override
    public Pagination<DataDictionaryTypeDTO> findPageByCondition(@RequestBody DataDictionaryTypeQueryDTO queryDTO) {

        String sql = "select d from " + DataDictionaryTypeEntity.class.getSimpleName() + " d where 1=1 ";

        Finder finder = Finder.create(sql);
        if (StringUtils.isNotBlank(queryDTO.getCode())) {
            finder.append(" and d.code like :code ");
            finder.setParam("code", "%" + queryDTO.getCode() + "%");
        }
        if (StringUtils.isNotBlank(queryDTO.getName())) {
            finder.append(" and d.name like :name ");
            finder.setParam("name", "%" + queryDTO.getName() + "%");
        }
        if (queryDTO.getStatus() != null) {
            finder.append(" and d.status = :status ");
            finder.setParam("status", queryDTO.getStatus());
        }

        finder.append(" order by d.createTime desc ");

        Pagination pagination = this.find(finder, queryDTO.getPageOrderDTO().getPageNo(), queryDTO.getPageOrderDTO().getPageSize());
        pagination.setList(POJOConvertUtil.convertList(pagination.getList(), DataDictionaryValueDTO.class));

        return pagination;
    }

    @Override
    public void save(@RequestBody DataDictionaryTypeDTO dataDictionaryTypeDTO) {
        DataDictionaryTypeEntity dataDictionaryType = POJOConvertUtil.convert(dataDictionaryTypeDTO, DataDictionaryTypeEntity.class);
        dataDictionaryTypeRepository.save(dataDictionaryType);
    }

    @Override
    public void update(@RequestBody DataDictionaryTypeDTO dataDictionaryTypeDTO) {
        DataDictionaryTypeEntity dataDictionaryType = POJOConvertUtil.convert(dataDictionaryTypeDTO, DataDictionaryTypeEntity.class);
        dataDictionaryTypeRepository.update(dataDictionaryType);
    }

    @Override
    public void deleteById(@RequestParam("guid") String guid) {
        dataDictionaryTypeRepository.deleteById(guid);
    }

}

