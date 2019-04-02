package com.zhulong.business.system.busservice.api.systemconfig.impl;

import com.zhulong.business.system.baseservice.api.systemconfig.SystemParamTypeBaseApi;
import com.zhulong.business.system.baseservice.dto.systemconfig.SystemParamTypeDTO;
import com.zhulong.business.system.baseservice.dto.systemconfig.SystemParamTypeQueryDTO;
import com.zhulong.business.system.busservice.api.systemconfig.CodeGenerateUtil;
import com.zhulong.business.system.busservice.api.systemconfig.SystemParamTypeBusApi;
import com.zhulong.business.system.busservice.dto.systemconfig.SystemParamTypeListDTO;
import com.zhulong.business.system.busservice.dto.systemconfig.SystemParamTypeSaveDTO;
import com.zhulong.business.system.busservice.dto.systemconfig.SystemParamTypeUpdateDTO;
import com.zhulong.framework.auth.common.AuthUser;
import com.zhulong.framework.auth.common.mvc.AuthUserParam;
import com.zhulong.framework.common.annotion.Module;
import com.zhulong.framework.common.dto.ResultDTO;
import com.zhulong.framework.common.page.Pagination;
import com.zhulong.framework.common.util.POJOConvertUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * 描述：系统参数分类 BusApiImpl实现
 *
 * @author 初。
 * @date 2019-03-20 10:01:25
 */
@RestController
@Module("系统配置 - 系统参数分类业务层接口")
public class SystemParamTypeBusApiImpl implements SystemParamTypeBusApi {

    @Autowired
    private SystemParamTypeBaseApi systemParamTypeBaseApi;

    @Override
    public ResultDTO<SystemParamTypeDTO> getById(String guid) {
        return ResultDTO.of(systemParamTypeBaseApi.getById(guid));
    }

    @Override
    public ResultDTO<SystemParamTypeDTO> save(@RequestBody @Valid SystemParamTypeSaveDTO saveDTO, @AuthUserParam AuthUser authUser) {

        SystemParamTypeDTO systemParamTypeDTO = POJOConvertUtil.convert(saveDTO, SystemParamTypeDTO.class);
        systemParamTypeDTO.setGuid(UUID.randomUUID().toString());
        systemParamTypeDTO.setCode(CodeGenerateUtil.generateCode());
        systemParamTypeDTO.setCreateInfo(authUser);
        systemParamTypeBaseApi.save(systemParamTypeDTO);

        return ResultDTO.of(systemParamTypeDTO);
    }

    @Override
    public ResultDTO<SystemParamTypeDTO> update(@RequestBody @Valid SystemParamTypeUpdateDTO updateDTO, @AuthUserParam AuthUser authUser) {

        SystemParamTypeDTO systemParamTypeDTO = POJOConvertUtil.convert(updateDTO, SystemParamTypeDTO.class);
        systemParamTypeDTO.setModifyInfo(authUser);
        systemParamTypeBaseApi.update(systemParamTypeDTO);

        return ResultDTO.of(systemParamTypeDTO);
    }

    @Override
    public ResultDTO<Object> deleteByGuid(String guid, @AuthUserParam AuthUser authUser) {

        systemParamTypeBaseApi.deleteById(guid);

        return ResultDTO.of(null);
    }

    @Override
    public ResultDTO<Pagination<SystemParamTypeListDTO>> findByCondition(@RequestBody SystemParamTypeQueryDTO queryDTO) {

        Pagination<SystemParamTypeDTO> pagination = systemParamTypeBaseApi.findPageByCondition(queryDTO);
        List<SystemParamTypeDTO> systemParamTypeDTOList = pagination.getList();
        List<SystemParamTypeListDTO> systemParamTypeListDTOS = new ArrayList<>();
        if (systemParamTypeDTOList != null && systemParamTypeDTOList.size() > 0) {
            systemParamTypeListDTOS = POJOConvertUtil.convertList(systemParamTypeDTOList, SystemParamTypeListDTO.class);
        }

        return ResultDTO.of(pagination.of(systemParamTypeListDTOS));
    }
    
}

