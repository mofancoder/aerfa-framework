package com.zhulong.business.system.busservice.api.commonbussetting.impl;

import com.zhulong.business.system.baseservice.api.commonbussetting.CodeRuleSettingBaseApi;
import com.zhulong.business.system.baseservice.dto.commonbussetting.CodeRuleSettingDTO;
import com.zhulong.business.system.baseservice.dto.commonbussetting.CodeRuleSettingQueryDTO;
import com.zhulong.business.system.busservice.api.commonbussetting.CodeRuleSettingBusApi;
import com.zhulong.business.system.busservice.dto.commonbussetting.CodeRuleSettingListDTO;
import com.zhulong.business.system.busservice.dto.commonbussetting.CodeRuleSettingSaveDTO;
import com.zhulong.business.system.busservice.dto.commonbussetting.CodeRuleSettingShowDTO;
import com.zhulong.business.system.busservice.dto.commonbussetting.CodeRuleSettingUpdateDTO;
import com.zhulong.framework.auth.common.AuthUser;
import com.zhulong.framework.auth.common.mvc.AuthUserParam;
import com.zhulong.framework.common.annotion.Module;
import com.zhulong.framework.common.dto.ResultDTO;
import com.zhulong.framework.common.page.Pagination;
import com.zhulong.framework.common.util.POJOConvertUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.*;

/**
 * 描述：编码生成规则 BusApiImpl实现
 *
 * @author 初。
 * @date 2019-03-28 10:21
 */
@RestController
@Module("COMMONBUSSETTING")
public class CodeRuleSettingBusApiImpl implements CodeRuleSettingBusApi {

    @Autowired
    private  CodeRuleSettingBaseApi codeRuleSettingBaseApi;

    @Override
    public ResultDTO<CodeRuleSettingDTO> save(@Valid @RequestBody CodeRuleSettingSaveDTO dto, @AuthUserParam AuthUser user) {

        CodeRuleSettingDTO codeRuleSettingDTO = POJOConvertUtil.convert(dto,CodeRuleSettingDTO.class);
        codeRuleSettingDTO.setGuid(UUID.randomUUID().toString());
        codeRuleSettingDTO.setCreateInfo(user);
        codeRuleSettingBaseApi.save(codeRuleSettingDTO);

        return ResultDTO.of(codeRuleSettingDTO);
    }

    @Override
    public ResultDTO<CodeRuleSettingDTO> update(@Valid @RequestBody CodeRuleSettingUpdateDTO dto, @AuthUserParam AuthUser user) {

        CodeRuleSettingDTO codeRuleSettingDTO = POJOConvertUtil.convert(dto,CodeRuleSettingDTO.class);
        codeRuleSettingDTO.setModifyInfo(user);
        codeRuleSettingDTO = codeRuleSettingBaseApi.update(codeRuleSettingDTO);

        return ResultDTO.of(codeRuleSettingDTO);
    }

    @Override
    public ResultDTO<Pagination<CodeRuleSettingListDTO>> findPage(@RequestBody CodeRuleSettingQueryDTO queryDTO, @AuthUserParam AuthUser user) {

        Pagination<CodeRuleSettingDTO> codeRuleSettingDTOPage = codeRuleSettingBaseApi.findPage(queryDTO);
        List<CodeRuleSettingDTO> itemDTOList = codeRuleSettingDTOPage.getList();
        List<CodeRuleSettingListDTO> listDTOList = getCodeRuleSettingListDTOs(itemDTOList);

        return ResultDTO.of(codeRuleSettingDTOPage.of(listDTOList));
    }

    private List<CodeRuleSettingListDTO> getCodeRuleSettingListDTOs(List<CodeRuleSettingDTO> itemDTOList) {
        return POJOConvertUtil.convertList(itemDTOList, CodeRuleSettingListDTO.class);
    }

    @Override
    public ResultDTO<CodeRuleSettingShowDTO> getByGuid(@RequestParam("guid") String guid, @AuthUserParam AuthUser user) {

        CodeRuleSettingDTO codeRuleSettingDTO = codeRuleSettingBaseApi.getByGuid(guid);
        CodeRuleSettingShowDTO showDTO = POJOConvertUtil.convert(codeRuleSettingDTO, CodeRuleSettingShowDTO.class);

        return ResultDTO.of(showDTO);
    }

    @Override
    public ResultDTO<Void> deleteById(@RequestParam("guid") String guid) {
        codeRuleSettingBaseApi.deleteById(guid);
        return ResultDTO.of(null);
    }

}

