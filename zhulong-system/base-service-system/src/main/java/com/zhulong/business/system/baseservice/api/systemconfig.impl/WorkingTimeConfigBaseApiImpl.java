package com.zhulong.business.system.baseservice.api.systemconfig.impl;

import com.zhulong.business.system.baseservice.api.systemconfig.WorkingTimeConfigBaseApi;
import com.zhulong.business.system.baseservice.dto.systemconfig.WorkingTimeConfigDTO;
import com.zhulong.business.system.baseservice.entity.systemconfig.WorkingTimeConfigEntity;
import com.zhulong.business.system.baseservice.repository.systemconfig.WorkingTimeConfigRepository;
import com.zhulong.framework.common.jpa.BaseDao;
import com.zhulong.framework.common.util.POJOConvertUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

/**
 * 描述：工作时间管理 BaseApiImpl实现
 *
 * @author 初。
 * @date 2019-03-21 04:05:52
 */
@Transactional
@RestController
public class WorkingTimeConfigBaseApiImpl extends BaseDao implements WorkingTimeConfigBaseApi {

    @Autowired
    private WorkingTimeConfigRepository workingTimeConfigRepository;

    @Override
    public WorkingTimeConfigDTO getById(@RequestParam("guid") String guid) {

        Optional<WorkingTimeConfigEntity> workingTimeConfigOptional = workingTimeConfigRepository.findById(guid);
        WorkingTimeConfigDTO workingTimeConfigDTO = workingTimeConfigOptional.isPresent() ? POJOConvertUtil.convert(workingTimeConfigOptional.get(), WorkingTimeConfigDTO.class) : null;

        return workingTimeConfigDTO;
    }

    @Override
    public List<WorkingTimeConfigDTO> findAll() {

        List<WorkingTimeConfigEntity> workingTimeConfigList = workingTimeConfigRepository.findAll();
        List<WorkingTimeConfigDTO> workingTimeConfigDTOList = POJOConvertUtil.convertList(workingTimeConfigList, WorkingTimeConfigDTO.class);

        return workingTimeConfigDTOList;
    }

    @Override
    public void save(@RequestBody WorkingTimeConfigDTO workingTimeConfigDTO) {
        WorkingTimeConfigEntity workingTimeConfig = POJOConvertUtil.convert(workingTimeConfigDTO, WorkingTimeConfigEntity.class);
        workingTimeConfigRepository.save(workingTimeConfig);
    }

    @Override
    public void update(@RequestBody WorkingTimeConfigDTO workingTimeConfigDTO) {
        WorkingTimeConfigEntity workingTimeConfig = POJOConvertUtil.convert(workingTimeConfigDTO, WorkingTimeConfigEntity.class);
        workingTimeConfigRepository.update(workingTimeConfig);
    }

    @Override
    public void deleteById(@RequestParam("guid") String guid) {
        workingTimeConfigRepository.deleteById(guid);
    }

}

