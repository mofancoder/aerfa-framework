package com.zhulong.business.system.busservice.api.systemconfig.impl;

import com.zhulong.business.system.baseservice.api.systemconfig.DataDictionaryBaseApi;
import com.zhulong.business.system.baseservice.dto.systemconfig.DataDictionaryDTO;
import com.zhulong.business.system.baseservice.dto.systemconfig.DataDictionaryQueryDTO;
import com.zhulong.business.system.busservice.api.systemconfig.CodeGenerateUtil;
import com.zhulong.business.system.busservice.api.systemconfig.DataDictionaryBusApi;
import com.zhulong.business.system.busservice.dto.systemconfig.DataDictionaryListDTO;
import com.zhulong.business.system.busservice.dto.systemconfig.DataDictionarySaveDTO;
import com.zhulong.business.system.busservice.dto.systemconfig.DataDictionaryUpdateDTO;
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
import java.util.List;
import java.util.UUID;

/**
 * 描述：数据字典 BusApiImpl实现
 *
 * @author 初。
 * @date 2019-03-19 02:01:42
 */
@RestController
@Module("系统配置 - 数据字典业务层接口")
public class DataDictionaryBusApiImpl implements DataDictionaryBusApi {

    @Autowired
    private DataDictionaryBaseApi dataDictionaryBaseApi;

    @Override
    public ResultDTO<DataDictionaryDTO> getById(String guid) {

        DataDictionaryDTO dataDictionaryDTO = dataDictionaryBaseApi.getById(guid);

        return ResultDTO.of(dataDictionaryDTO);
    }

    @Override
    public ResultDTO<DataDictionaryDTO> save(@RequestBody @Valid DataDictionarySaveDTO saveDTO, @AuthUserParam AuthUser authUser) {

        DataDictionaryDTO dataDictionaryDTO = POJOConvertUtil.convert(saveDTO, DataDictionaryDTO.class);
        dataDictionaryDTO.setGuid(UUID.randomUUID().toString());
        dataDictionaryDTO.setCode(CodeGenerateUtil.generateCode());
        dataDictionaryDTO.setCreateInfo(authUser);
        dataDictionaryBaseApi.save(dataDictionaryDTO);

        return ResultDTO.of(dataDictionaryDTO);
    }

    @Override
    public ResultDTO<DataDictionaryDTO> update(@RequestBody @Valid DataDictionaryUpdateDTO updateDTO, @AuthUserParam AuthUser authUser) {

        DataDictionaryDTO dataDictionaryDTO = POJOConvertUtil.convert(updateDTO, DataDictionaryDTO.class);
        dataDictionaryDTO.setModifyInfo(authUser);
        dataDictionaryBaseApi.update(dataDictionaryDTO);

        return ResultDTO.of(dataDictionaryDTO);
    }

    @Override
    public ResultDTO<Void> deleteById(String guid, AuthUser authUser) {

        dataDictionaryBaseApi.deleteById(guid);

        return ResultDTO.of(null);
    }

    @Override
    public ResultDTO<Pagination<DataDictionaryListDTO>> findPageByCondition(@RequestBody DataDictionaryQueryDTO queryDTO) {

        Pagination<DataDictionaryDTO> pageByCondition = dataDictionaryBaseApi.findPageByCondition(queryDTO);
        List<DataDictionaryDTO> dataDictionaryDTOList = pageByCondition.getList();
        // 转换数据
        List<DataDictionaryListDTO> dataDictionaryListDTOList = POJOConvertUtil.convertList(dataDictionaryDTOList,DataDictionaryListDTO.class);
        return ResultDTO.of(pageByCondition.of(dataDictionaryListDTOList));
    }

}

