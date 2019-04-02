package com.zhulong.business.system.busservice.api.systemconfig.impl;

import com.zhulong.business.system.baseservice.api.systemconfig.DataDictionaryValueBaseApi;
import com.zhulong.business.system.baseservice.dto.systemconfig.DataDictionaryValueDTO;
import com.zhulong.business.system.baseservice.dto.systemconfig.DataDictionaryValueQueryDTO;
import com.zhulong.business.system.busservice.api.systemconfig.DataDictionaryValueBusApi;
import com.zhulong.business.system.busservice.dto.systemconfig.DataDictionaryValueSaveDTO;
import com.zhulong.business.system.busservice.dto.systemconfig.DataDictionaryValueUpdateDTO;
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
 * 描述：数据字典值 BusApiImpl实现
 *
 * @author 初。
 * @date 2019-03-20 09:59:31
 */
@RestController
@Module("系统配置 - 数据字典值业务层接口")
public class DataDictionaryValueBusApiImpl implements DataDictionaryValueBusApi {

    @Autowired
    private DataDictionaryValueBaseApi dataDictionaryValueBaseApi;

    @Override
    public ResultDTO<DataDictionaryValueDTO> getById(String guid) {

        DataDictionaryValueDTO dataDictionaryValueDTO = dataDictionaryValueBaseApi.getById(guid);

        return ResultDTO.of(dataDictionaryValueDTO);
    }

    @Override
    public ResultDTO<DataDictionaryValueDTO> save(@RequestBody @Valid DataDictionaryValueSaveDTO saveDTO, @AuthUserParam AuthUser authUser) {

        DataDictionaryValueDTO dataDictionaryValueDTO = POJOConvertUtil.convert(saveDTO, DataDictionaryValueDTO.class);
        dataDictionaryValueDTO.setGuid(UUID.randomUUID().toString());
        dataDictionaryValueDTO.setCreateInfo(authUser);
        dataDictionaryValueBaseApi.save(dataDictionaryValueDTO);

        return ResultDTO.of(dataDictionaryValueDTO);
    }

    @Override
    public ResultDTO<DataDictionaryValueDTO> update(@RequestBody @Valid DataDictionaryValueUpdateDTO updateDTO, @AuthUserParam AuthUser authUser) {

        DataDictionaryValueDTO dataDictionaryValueDTO = POJOConvertUtil.convert(updateDTO, DataDictionaryValueDTO.class);
        dataDictionaryValueDTO.setModifyInfo(authUser);
        dataDictionaryValueBaseApi.update(dataDictionaryValueDTO);

        return ResultDTO.of(dataDictionaryValueDTO);
    }

    @Override
    public ResultDTO<Object> deleteByGuid(String guid, @AuthUserParam AuthUser authUser) {

        dataDictionaryValueBaseApi.deleteById(guid);

        return ResultDTO.of(null);
    }

    @Override
    public ResultDTO<Pagination<DataDictionaryValueDTO>> findByCondition(@RequestBody DataDictionaryValueQueryDTO queryDTO) {

        Pagination<DataDictionaryValueDTO> pageination = dataDictionaryValueBaseApi.findPageByCondition(queryDTO);

        return ResultDTO.of(pageination);
    }

}

