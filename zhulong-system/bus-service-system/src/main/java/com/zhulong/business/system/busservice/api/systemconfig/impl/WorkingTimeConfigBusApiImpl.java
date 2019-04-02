package com.zhulong.business.system.busservice.api.systemconfig.impl;

import com.zhulong.business.system.baseservice.api.systemconfig.WorkingTimeConfigBaseApi;
import com.zhulong.business.system.baseservice.dto.systemconfig.WorkingTimeConfigDTO;
import com.zhulong.business.system.busservice.api.systemconfig.CodeGenerateUtil;
import com.zhulong.business.system.busservice.api.systemconfig.WorkingTimeConfigBusApi;
import com.zhulong.business.system.busservice.dto.systemconfig.WorkingTimeConfigSaveDTO;
import com.zhulong.business.system.busservice.dto.systemconfig.WorkingTimeConfigUpdateDTO;
import com.zhulong.framework.auth.common.AuthUser;
import com.zhulong.framework.auth.common.mvc.AuthUserParam;
import com.zhulong.framework.common.dto.ResultDTO;
import com.zhulong.framework.common.util.POJOConvertUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;
import java.util.UUID;

/**
 * 描述：工作时间管理 BusApiImpl实现
 *
 * @author 初。
 * @date 2019-03-21 04:05:52
 */
@RestController
public class WorkingTimeConfigBusApiImpl implements WorkingTimeConfigBusApi {

    @Autowired
    private WorkingTimeConfigBaseApi workingTimeConfigBaseApi;

    @Override
    public ResultDTO<WorkingTimeConfigDTO> getById(String guid) {
        return ResultDTO.of(workingTimeConfigBaseApi.getById(guid));
    }

    @Override
    public ResultDTO<WorkingTimeConfigDTO> save(@RequestBody @Valid WorkingTimeConfigSaveDTO saveDTO, @AuthUserParam AuthUser authUser) {

        WorkingTimeConfigDTO workingTimeConfigDTO = POJOConvertUtil.convert(saveDTO, WorkingTimeConfigDTO.class);
        workingTimeConfigDTO.setGuid(UUID.randomUUID().toString());
        workingTimeConfigDTO.setCode(CodeGenerateUtil.generateCode());
        workingTimeConfigDTO.setCreateInfo(authUser);
        workingTimeConfigBaseApi.save(workingTimeConfigDTO);

        return ResultDTO.of(workingTimeConfigDTO);
    }

    @Override
    public ResultDTO<WorkingTimeConfigDTO> update(@RequestBody @Valid WorkingTimeConfigUpdateDTO updateDTO, @AuthUserParam AuthUser authUser) {

        WorkingTimeConfigDTO workingTimeConfigDTO = POJOConvertUtil.convert(updateDTO, WorkingTimeConfigDTO.class);
        workingTimeConfigDTO.setModifyInfo(authUser);
        workingTimeConfigBaseApi.update(workingTimeConfigDTO);

        return ResultDTO.of(workingTimeConfigDTO);
    }

    @Override
    public ResultDTO<Object> deleteByGuid(String guid, @AuthUserParam AuthUser authUser) {

        workingTimeConfigBaseApi.deleteById(guid);

        return ResultDTO.of(null);
    }

    @Override
    public ResultDTO<List<WorkingTimeConfigDTO>> findAll() {
        return ResultDTO.of(workingTimeConfigBaseApi.findAll());
    }

}

