package com.zhulong.business.system.busservice.api.systemconfig.impl;

import com.zhulong.business.system.baseservice.api.systemconfig.SystemParamBaseApi;
import com.zhulong.business.system.baseservice.dto.systemconfig.SystemParamDTO;
import com.zhulong.business.system.baseservice.dto.systemconfig.SystemParamQueryDTO;
import com.zhulong.business.system.busservice.api.systemconfig.SystemParamBusApi;
import com.zhulong.business.system.busservice.dto.systemconfig.SystemParamSaveDTO;
import com.zhulong.business.system.busservice.dto.systemconfig.SystemParamUpdateDTO;
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
import java.util.UUID;

/**
 * 描述：系统参数 BusApiImpl实现
 *
 * @author 初。
 * @date 2019-03-20 10:01:09
 */
@RestController
@Module("系统配置 - 系统参数业务层接口")
public class SystemParamBusApiImpl implements SystemParamBusApi {

    @Autowired
    private SystemParamBaseApi systemParamBaseApi;

    @Override
    public ResultDTO<SystemParamDTO> getById(String guid) {
        return ResultDTO.of(systemParamBaseApi.getById(guid));
    }

    @Override
    public ResultDTO<SystemParamDTO> save(@RequestBody @Valid SystemParamSaveDTO saveDTO, @AuthUserParam AuthUser authUser) {

        SystemParamDTO systemParamDTO = POJOConvertUtil.convert(saveDTO, SystemParamDTO.class);
        systemParamDTO.setGuid(UUID.randomUUID().toString());
        systemParamDTO.setCreateInfo(authUser);
        systemParamBaseApi.save(systemParamDTO);

        return ResultDTO.of(systemParamDTO);
    }

    @Override
    public ResultDTO<SystemParamDTO> update(@RequestBody @Valid SystemParamUpdateDTO updateDTO, @AuthUserParam AuthUser authUser) {

        SystemParamDTO systemParamDTO = POJOConvertUtil.convert(updateDTO, SystemParamDTO.class);
        systemParamDTO.setModifyInfo(authUser);
        systemParamBaseApi.update(systemParamDTO);

        return ResultDTO.of(systemParamDTO);
    }

    @Override
    public ResultDTO<Object> deleteByGuid(String guid, @AuthUserParam AuthUser authUser) {

        systemParamBaseApi.deleteById(guid);

        return ResultDTO.of(null);
    }

    @Override
    public ResultDTO<Pagination<SystemParamDTO>> findByCondition(@RequestBody SystemParamQueryDTO queryDTO) {

        Pagination<SystemParamDTO> pagination = systemParamBaseApi.findPageByCondition(queryDTO);

        return ResultDTO.of(pagination);
    }

}

