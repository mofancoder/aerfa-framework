package com.zhulong.business.system.busservice.api.systemconfig.impl;

import com.zhulong.business.system.baseservice.api.systemconfig.DataDictionaryBaseApi;
import com.zhulong.business.system.baseservice.api.systemconfig.DataDictionaryTypeBaseApi;
import com.zhulong.business.system.baseservice.dto.systemconfig.DataDictionaryTypeDTO;
import com.zhulong.business.system.baseservice.dto.systemconfig.DataDictionaryTypeQueryDTO;
import com.zhulong.business.system.busservice.api.systemconfig.CodeGenerateUtil;
import com.zhulong.business.system.busservice.api.systemconfig.DataDictionaryTypeBusApi;
import com.zhulong.business.system.busservice.dto.systemconfig.DataDictionaryTypeListDTO;
import com.zhulong.business.system.busservice.dto.systemconfig.DataDictionaryTypeSaveDTO;
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
import java.util.Collections;
import java.util.List;
import java.util.UUID;

/**
 * 描述：数据字典类型 BusApiImpl实现
 *
 * @author 初。
 * @date 2019-03-20 10:00:06
 */
@RestController
@Module("系统配置 - 数据字典类型业务层接口")
public class DataDictionaryTypeBusApiImpl implements DataDictionaryTypeBusApi {

    @Autowired
    private DataDictionaryBaseApi dataDictionaryBaseApi;

    @Autowired
    private DataDictionaryTypeBaseApi dataDictionaryTypeBaseApi;

    @Override
    public ResultDTO<DataDictionaryTypeDTO> getById(String guid) {

        DataDictionaryTypeDTO dataDictionaryTypeDTO = dataDictionaryTypeBaseApi.getById(guid);

        return ResultDTO.of(dataDictionaryTypeDTO);
    }

    @Override
    public ResultDTO<DataDictionaryTypeDTO> save(@RequestBody @Valid DataDictionaryTypeSaveDTO saveDTO, @AuthUserParam AuthUser authUser) {

        DataDictionaryTypeDTO dataDictionaryTypeDTO = POJOConvertUtil.convert(saveDTO, DataDictionaryTypeDTO.class);
        dataDictionaryTypeDTO.setGuid(UUID.randomUUID().toString());
        dataDictionaryTypeDTO.setCode(CodeGenerateUtil.generateCode());
        dataDictionaryTypeDTO.setCreateInfo(authUser);
        dataDictionaryTypeBaseApi.save(dataDictionaryTypeDTO);

        return ResultDTO.of(dataDictionaryTypeDTO);
    }

    @Override
    public ResultDTO<Object> deleteByGuid(String guid, @AuthUserParam AuthUser authUser) {

        // 查出当前分类下的字典数据总数，在为0的情况下才能删除
        Long dataDictionaryCount = dataDictionaryBaseApi.countDataDictionaryByTypeGuid(guid);
        if (dataDictionaryCount == null || dataDictionaryCount == 0) {
            dataDictionaryTypeBaseApi.deleteById(guid);
            return ResultDTO.of(null);
        }

        return ResultDTO.error("", "");
    }

    @Override
    public ResultDTO<Pagination<DataDictionaryTypeListDTO>> findByCondition(@RequestBody DataDictionaryTypeQueryDTO queryDTO) {

        Pagination<DataDictionaryTypeDTO> pagination = dataDictionaryTypeBaseApi.findPageByCondition(queryDTO);
        List<DataDictionaryTypeDTO> dataDictionaryTypeDTOList = pagination.getList();
        List<DataDictionaryTypeListDTO> dataDictionaryTypeListDTOs = null;
        if (dataDictionaryTypeDTOList != null) {
            dataDictionaryTypeListDTOs = POJOConvertUtil.convertList(dataDictionaryTypeDTOList, DataDictionaryTypeListDTO.class);
        } else {
            dataDictionaryTypeListDTOs = Collections.emptyList();
        }

        return ResultDTO.of(pagination.of(dataDictionaryTypeListDTOs));
    }

}

