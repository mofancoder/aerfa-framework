package com.zhulong.business.system.baseservice.api.systemconfig.impl;

import com.zhulong.business.system.baseservice.api.systemconfig.DataDictionaryValueBaseApi;
import com.zhulong.business.system.baseservice.dto.systemconfig.DataDictionaryValueDTO;
import com.zhulong.business.system.baseservice.dto.systemconfig.DataDictionaryValueQueryDTO;
import com.zhulong.business.system.baseservice.entity.systemconfig.DataDictionaryValueEntity;
import com.zhulong.business.system.baseservice.repository.systemconfig.DataDictionaryValueRepository;
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
 * 描述：数据字典值 BaseApiImpl实现
 *
 * @author 初。
 * @date 2019-03-20 09:59:31
 */
@Transactional
@RestController
public class DataDictionaryValueBaseApiImpl extends BaseDao implements DataDictionaryValueBaseApi {

    @Autowired
    private DataDictionaryValueRepository dataDictionaryValueRepository;

    @Override
    public DataDictionaryValueDTO getById(@RequestParam("guid") String guid) {

        Optional<DataDictionaryValueEntity> dataDictionaryValueOptional = dataDictionaryValueRepository.findById(guid);
        DataDictionaryValueDTO dataDictionaryValueDTO = dataDictionaryValueOptional.isPresent() ? POJOConvertUtil.convert(dataDictionaryValueOptional.get(), DataDictionaryValueDTO.class) : null;

        return dataDictionaryValueDTO;
    }

    @Override
    public List<DataDictionaryValueDTO> findAll() {

        List<DataDictionaryValueEntity> dataDictionaryValueList = dataDictionaryValueRepository.findAll();
        List<DataDictionaryValueDTO> dataDictionaryValueDTOList = POJOConvertUtil.convertList(dataDictionaryValueList, DataDictionaryValueDTO.class);

        return dataDictionaryValueDTOList;
    }

    @Override
    public Pagination<DataDictionaryValueDTO> findPageByCondition(@RequestBody DataDictionaryValueQueryDTO queryDTO) {

        String sql = "select d from " + DataDictionaryValueEntity.class.getSimpleName() + " d where 1=1 ";

        Finder finder = Finder.create(sql);
        if (StringUtils.isNotBlank(queryDTO.getDictionaryGuid())) {
            finder.append(" and d.dictionaryGuid = :dictionaryGuid ");
            finder.setParam("dictionaryGuid", queryDTO.getDictionaryGuid());
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
    public void save(@RequestBody DataDictionaryValueDTO dataDictionaryValueDTO) {
        DataDictionaryValueEntity dataDictionaryValue = POJOConvertUtil.convert(dataDictionaryValueDTO, DataDictionaryValueEntity.class);
        dataDictionaryValueRepository.save(dataDictionaryValue);
    }

    @Override
    public void update(@RequestBody DataDictionaryValueDTO dataDictionaryValueDTO) {
        DataDictionaryValueEntity dataDictionaryValue = POJOConvertUtil.convert(dataDictionaryValueDTO, DataDictionaryValueEntity.class);
        dataDictionaryValueRepository.update(dataDictionaryValue);
    }

    @Override
    public void deleteById(@RequestParam("guid") String guid) {
        dataDictionaryValueRepository.deleteById(guid);
    }

}

