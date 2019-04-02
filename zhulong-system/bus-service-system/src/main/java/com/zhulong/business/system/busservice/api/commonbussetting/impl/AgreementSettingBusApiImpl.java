package com.zhulong.business.system.busservice.api.commonbussetting.impl;

import com.zhulong.business.system.baseservice.api.commonbussetting.AgreementSettingBaseApi;
import com.zhulong.business.system.baseservice.dto.commonbussetting.AgreementSettingDTO;
import com.zhulong.business.system.baseservice.dto.commonbussetting.AgreementSettingQueryDTO;
import com.zhulong.business.system.busservice.api.commonbussetting.AgreementSettingBusApi;
import com.zhulong.business.system.busservice.dto.commonbussetting.AgreementSettingListDTO;
import com.zhulong.business.system.busservice.dto.commonbussetting.AgreementSettingSaveDTO;
import com.zhulong.business.system.busservice.dto.commonbussetting.AgreementSettingShowDTO;
import com.zhulong.business.system.busservice.dto.commonbussetting.AgreementSettingUpdateDTO;
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
import java.util.List;
import java.util.UUID;

/**
 * 描述：协议管理 BusApiImpl实现
 * @author MOFAN889
 * @date 2019-04-01 18:06
 */
@RestController
@Module("COMMONBUSSETTING")
public class AgreementSettingBusApiImpl implements AgreementSettingBusApi {

    @Autowired
    private AgreementSettingBaseApi agreementSettingBaseApi;

    @Override
    public ResultDTO<AgreementSettingDTO> save(@Valid @RequestBody AgreementSettingSaveDTO dto, @AuthUserParam AuthUser user) {

        AgreementSettingDTO agreementSettingDTO = POJOConvertUtil.convert(dto,AgreementSettingDTO.class);
        agreementSettingDTO.setGuid(UUID.randomUUID().toString());
        agreementSettingDTO.setCreateInfo(user);
        agreementSettingBaseApi.save(agreementSettingDTO);
        return ResultDTO.of(agreementSettingDTO);
    }

    @Override
    public ResultDTO<AgreementSettingDTO> update(@Valid @RequestBody AgreementSettingUpdateDTO dto, @AuthUserParam AuthUser user) {
        AgreementSettingDTO agreementSettingDTO = POJOConvertUtil.convert(dto,AgreementSettingDTO.class);
        agreementSettingDTO.setModifyInfo(user);
        agreementSettingDTO = agreementSettingBaseApi.update(agreementSettingDTO);
        return ResultDTO.of(agreementSettingDTO);
    }

    @Override
    public ResultDTO<Pagination<AgreementSettingListDTO>> findPage(@RequestBody AgreementSettingQueryDTO queryDTO, @AuthUserParam AuthUser user) {
        Pagination<AgreementSettingDTO> agreementSettingDTOPage = agreementSettingBaseApi.findPage(queryDTO);

        List<AgreementSettingDTO> itemDTOList = agreementSettingDTOPage.getList();
        List<AgreementSettingListDTO> listDTOList = getAgreementSettingListDTOs(itemDTOList);
        return ResultDTO.of(agreementSettingDTOPage.of(listDTOList));
    }

    private List<AgreementSettingListDTO> getAgreementSettingListDTOs(List<AgreementSettingDTO> itemDTOList) {

        return POJOConvertUtil.convertList(itemDTOList,AgreementSettingListDTO.class);
    }

    @Override
    public ResultDTO<List<AgreementSettingListDTO>> findList(@RequestBody AgreementSettingQueryDTO queryDTO, @AuthUserParam AuthUser user) {
        List<AgreementSettingDTO> itemDTOList = agreementSettingBaseApi.findList(queryDTO);
        List<AgreementSettingListDTO> listDTOList = getAgreementSettingListDTOs(itemDTOList);
        return ResultDTO.of(listDTOList);
    }

    @Override
    public ResultDTO<AgreementSettingShowDTO> getByGuid(@RequestParam("guid") String guid, @AuthUserParam AuthUser user) {
        AgreementSettingDTO agreementSettingDTO = agreementSettingBaseApi.getByGuid(guid);
        AgreementSettingShowDTO showDTO = POJOConvertUtil.convert(agreementSettingDTO,AgreementSettingShowDTO.class);
        return ResultDTO.of(showDTO);
    }

    @Override
    public ResultDTO<Void> deleteById(@RequestParam("guid") String guid) {
        agreementSettingBaseApi.deleteById(guid);
        return ResultDTO.of(null);
    }

}

