package com.zhulong.business.system.baseservice.api.systemconfig.impl;

import com.zhulong.business.system.baseservice.api.systemconfig.SystemParamTypeBaseApi;
import com.zhulong.business.system.baseservice.dto.systemconfig.SystemParamTypeDTO;
import com.zhulong.business.system.baseservice.dto.systemconfig.SystemParamTypeQueryDTO;
import com.zhulong.business.system.baseservice.entity.systemconfig.SystemParamTypeEntity;
import com.zhulong.business.system.baseservice.repository.systemconfig.SystemParamTypeRepository;
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
 * 描述：系统参数分类 BaseApiImpl实现
 *
 * @author 初。
 * @date 2019-03-20 10:01:25
 */
@Transactional
@RestController
public class SystemParamTypeBaseApiImpl extends BaseDao implements SystemParamTypeBaseApi {

    @Autowired
    private SystemParamTypeRepository systemParamTypeRepository;

    @Override
    public SystemParamTypeDTO getById(@RequestParam("guid") String guid) {

        Optional<SystemParamTypeEntity> systemParamTypeOptional = systemParamTypeRepository.findById(guid);
        SystemParamTypeDTO systemParamTypeDTO = systemParamTypeOptional.isPresent() ? POJOConvertUtil.convert(systemParamTypeOptional.get(), SystemParamTypeDTO.class) : null;

        return systemParamTypeDTO;
    }

    @Override
    public List<SystemParamTypeDTO> findAll() {

        List<SystemParamTypeEntity> systemParamTypeList = systemParamTypeRepository.findAll();
        List<SystemParamTypeDTO> systemParamTypeDTOList = POJOConvertUtil.convertList(systemParamTypeList, SystemParamTypeDTO.class);

        return systemParamTypeDTOList;
    }

    @Override
    public Pagination<SystemParamTypeDTO> findPageByCondition(@RequestBody SystemParamTypeQueryDTO queryDTO) {

        String sql = "select s from " + SystemParamTypeEntity.class.getSimpleName() + " s where 1=1 ";

        Finder finder = Finder.create(sql);
        if (StringUtils.isNotBlank(queryDTO.getCode())) {
            finder.append(" and code = :code ");
            finder.setParam("code", queryDTO.getCode());
        }
        if (StringUtils.isNotBlank(queryDTO.getName())) {
            finder.append(" and name = :name ");
            finder.setParam("name", queryDTO.getName());
        }
        if (queryDTO.getStatus() != null) {
            finder.append(" and status = :status ");
            finder.setParam("status", queryDTO.getStatus());
        }

        finder.append(" order by createTime desc ");

        Pagination pagination = this.find(finder, queryDTO.getPageOrderDTO().getPageNo(), queryDTO.getPageOrderDTO().getPageSize());
        pagination.setList(POJOConvertUtil.convertList(pagination.getList(), SystemParamTypeDTO.class));

        return pagination;
    }

    @Override
    public void save(@RequestBody SystemParamTypeDTO systemParamTypeDTO) {
        SystemParamTypeEntity systemParamType = POJOConvertUtil.convert(systemParamTypeDTO, SystemParamTypeEntity.class);
        systemParamTypeRepository.save(systemParamType);
    }

    @Override
    public void update(@RequestBody SystemParamTypeDTO systemParamTypeDTO) {
        SystemParamTypeEntity systemParamType = POJOConvertUtil.convert(systemParamTypeDTO, SystemParamTypeEntity.class);
        systemParamTypeRepository.update(systemParamType);
    }

    @Override
    public void deleteById(@RequestParam("guid") String guid) {
        systemParamTypeRepository.deleteById(guid);
    }

}

