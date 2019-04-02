package com.zhulong.business.system.baseservice.api.systemconfig.impl;

import com.zhulong.business.system.baseservice.api.systemconfig.DataDictionaryBaseApi;
import com.zhulong.business.system.baseservice.dto.systemconfig.DataDictionaryDTO;
import com.zhulong.business.system.baseservice.dto.systemconfig.DataDictionaryQueryDTO;
import com.zhulong.business.system.baseservice.entity.systemconfig.DataDictionaryEntity;
import com.zhulong.business.system.baseservice.repository.systemconfig.DataDictionaryRepository;
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
 * 描述：数据字典 BaseApiImpl实现
 *
 * @author 初。
 * @date 2019-03-19 02:01:42
 */
@Transactional
@RestController
public class DataDictionaryBaseApiImpl extends BaseDao implements DataDictionaryBaseApi {

    @Autowired
    private DataDictionaryRepository dataDictionaryRepository;

    @Autowired
    private DataDictionaryValueRepository dataDictionaryValueRepository;

    @Override
    public DataDictionaryDTO getById(@RequestParam("guid") String guid) {

        Optional<DataDictionaryEntity> dataDictionaryOptional = dataDictionaryRepository.findById(guid);
        DataDictionaryDTO dataDictionaryDTO = dataDictionaryOptional.isPresent() ? POJOConvertUtil.convert(dataDictionaryOptional.get(), DataDictionaryDTO.class) : null;

        return dataDictionaryDTO;
    }

    @Override
    public List<DataDictionaryDTO> findAll() {

        List<DataDictionaryEntity> dataDictionaryList = dataDictionaryRepository.findAll();
        List<DataDictionaryDTO> dataDictionaryDTOList = POJOConvertUtil.convertList(dataDictionaryList, DataDictionaryDTO.class);

        return dataDictionaryDTOList;
    }

    // TODO 查询出分类名称
    @Override
    public Pagination<DataDictionaryDTO> findPageByCondition(@RequestBody DataDictionaryQueryDTO queryDTO) {

        String hql = "select d from " + DataDictionaryEntity.class.getSimpleName() + " d where 1=1 ";

        Finder finder = Finder.create(hql);
        if (StringUtils.isNotBlank(queryDTO.getCode())) {
            finder.append(" and d.code like :code ");
            finder.setParam("code", "%" + queryDTO.getCode() + "%");
        }
        if (StringUtils.isNotBlank(queryDTO.getName())) {
            finder.append(" and d.name like :name ");
            finder.setParam("name", "%" + queryDTO.getName() + "%");
        }
        if (queryDTO.getSuitLevel() != null) {
            finder.append(" and d.suitLevel = :suitLevel ");
            finder.setParam("suitLevel", queryDTO.getSuitLevel());
        }

        finder.append(" order by d.createTime desc ");

        Pagination pagination = this.find(finder, queryDTO.getPageOrderDTO().getPageNo(), queryDTO.getPageOrderDTO().getPageSize());
        pagination.setList(POJOConvertUtil.convertList(pagination.getList(), DataDictionaryDTO.class));

        return pagination;
    }

    @Override
    public void save(@RequestBody DataDictionaryDTO dataDictionaryDTO) {
        DataDictionaryEntity dataDictionary = POJOConvertUtil.convert(dataDictionaryDTO, DataDictionaryEntity.class);
        dataDictionaryRepository.save(dataDictionary);
    }

    @Override
    public Boolean update(@RequestBody DataDictionaryDTO dataDictionaryDTO) {
        DataDictionaryEntity dataDictionary = POJOConvertUtil.convert(dataDictionaryDTO, DataDictionaryEntity.class);
        // 若数据字典发生变化时，相应的更新数据字典值的状态值
        dataDictionaryValueRepository.updateStatusByDataDictionary(dataDictionaryDTO);
        return dataDictionaryRepository.update(dataDictionary) != null;
    }

    @Override
    public void deleteById(@RequestParam("guid") String guid) {
        // 设置该数据字典对应下的数据字典值为删除状态
        dataDictionaryValueRepository.deleteByDictionaryGuid(guid);
        dataDictionaryRepository.deleteById(guid);
    }

    @Override
    public Long countDataDictionaryByTypeGuid(@RequestParam("typeGuid") String typeGuid) {

        return dataDictionaryRepository.countDataDictionaryByTypeGuid(typeGuid);
    }

}

