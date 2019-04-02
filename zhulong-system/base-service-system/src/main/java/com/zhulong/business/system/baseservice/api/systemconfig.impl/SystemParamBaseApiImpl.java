package com.zhulong.business.system.baseservice.api.systemconfig.impl;

import com.zhulong.business.system.baseservice.api.systemconfig.SystemParamBaseApi;
import com.zhulong.business.system.baseservice.dto.systemconfig.SystemParamDTO;
import com.zhulong.business.system.baseservice.dto.systemconfig.SystemParamQueryDTO;
import com.zhulong.business.system.baseservice.entity.systemconfig.SystemParamEntity;
import com.zhulong.business.system.baseservice.repository.systemconfig.SystemParamRepository;
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
 * 描述：系统参数 BaseApiImpl实现
 *
 * @author 初。
 * @date 2019-03-20 10:01:09
 */
@Transactional
@RestController
public class SystemParamBaseApiImpl extends BaseDao implements SystemParamBaseApi {

    @Autowired
    private SystemParamRepository systemParamRepository;

    @Override
    public SystemParamDTO getById(@RequestParam("guid") String guid) {

        Optional<SystemParamEntity> systemParamOptional = systemParamRepository.findById(guid);
        SystemParamDTO systemParamDTO = systemParamOptional.isPresent() ? POJOConvertUtil.convert(systemParamOptional.get(), SystemParamDTO.class) : null;

        return systemParamDTO;
    }

    @Override
    public List<SystemParamDTO> findAll() {

        List<SystemParamEntity> systemParamList = systemParamRepository.findAll();
        List<SystemParamDTO> systemParamDTOList = POJOConvertUtil.convertList(systemParamList, SystemParamDTO.class);

        return systemParamDTOList;
    }

    // TODO 查出类型名称
    @Override
    public Pagination<SystemParamDTO> findPageByCondition(@RequestBody SystemParamQueryDTO queryDTO) {

        String sql = "select s from " + SystemParamEntity.class.getSimpleName() + " s where 1=1 ";

        Finder finder = Finder.create(sql);
        if (StringUtils.isNotBlank(queryDTO.getCode())) {
            finder.append(" and code = :code ");
            finder.setParam("code", queryDTO.getCode());
        }
        if (StringUtils.isNotBlank(queryDTO.getName())) {
            finder.append(" and name = :name ");
            finder.setParam("name", queryDTO.getName());
        }
        if (queryDTO.getSuitLevel() != null) {
            finder.append(" and suitLevel = :suitLevel ");
            finder.setParam("suitLevel", queryDTO.getSuitLevel());
        }
        if (queryDTO.getStatus() != null) {
            finder.append(" and status = :status ");
            finder.setParam("status", queryDTO.getStatus());
        }

        finder.append(" order by createTime desc ");

        Pagination pagination = this.find(finder, queryDTO.getPageOrderDTO().getPageNo(), queryDTO.getPageOrderDTO().getPageSize());
        pagination.setList(POJOConvertUtil.convertList(pagination.getList(), SystemParamDTO.class));

        return pagination;
    }

    @Override
    public void save(@RequestBody SystemParamDTO systemParamDTO) {
        SystemParamEntity systemParam = POJOConvertUtil.convert(systemParamDTO, SystemParamEntity.class);
        systemParamRepository.save(systemParam);
    }

    @Override
    public void update(@RequestBody SystemParamDTO systemParamDTO) {
        SystemParamEntity systemParam = POJOConvertUtil.convert(systemParamDTO, SystemParamEntity.class);
        systemParamRepository.update(systemParam);
    }

    @Override
    public void deleteById(@RequestParam("guid") String guid) {
        systemParamRepository.deleteById(guid);
    }

}

