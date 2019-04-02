package com.zhulong.business.system.busservice.api.commonbussetting.impl;

import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.zhulong.business.system.baseservice.api.commonbussetting.CurrencyDictionaryBaseApi;
import com.zhulong.business.system.baseservice.dto.commonbussetting.CurrencyDictionaryDTO;
import com.zhulong.business.system.baseservice.dto.commonbussetting.CurrencyDictionaryQueryDTO;
import com.zhulong.business.system.busservice.api.commonbussetting.CurrencyDictionaryBusApi;
import com.zhulong.business.system.busservice.dto.commonbussetting.CurrencyDictionaryListDTO;
import com.zhulong.business.system.busservice.dto.commonbussetting.CurrencyDictionarySaveDTO;
import com.zhulong.business.system.busservice.dto.commonbussetting.CurrencyDictionaryShowDTO;
import com.zhulong.business.system.busservice.dto.commonbussetting.CurrencyDictionaryUpdateDTO;
import com.zhulong.framework.auth.common.AuthLegalPersonInfo;
import com.zhulong.framework.auth.common.AuthOrganizationInfo;
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
import java.util.Base64;
import java.util.List;
import java.util.UUID;

/**
 * 描述：币种管理 BusApiImpl实现
 * @author MOFAN889
 * @date 2019-04-01 11:37
 */
@RestController
@Module("COMMONBUSSETTING")
public class CurrencyDictionaryBusApiImpl implements CurrencyDictionaryBusApi {

    @Autowired
    private CurrencyDictionaryBaseApi currencyDictionaryBaseApi;

    @Override
    public ResultDTO<CurrencyDictionaryDTO> save(@Valid @RequestBody CurrencyDictionarySaveDTO dto, @AuthUserParam AuthUser user) {

        CurrencyDictionaryDTO currencyDictionaryDTO = POJOConvertUtil.convert(dto,CurrencyDictionaryDTO.class);
        currencyDictionaryDTO.setGuid(UUID.randomUUID().toString());
        currencyDictionaryDTO.setCreateInfo(user);
        currencyDictionaryBaseApi.save(currencyDictionaryDTO);
        return ResultDTO.of(currencyDictionaryDTO);
    }

    @Override
    public ResultDTO<CurrencyDictionaryDTO> update(@Valid @RequestBody CurrencyDictionaryUpdateDTO dto, @AuthUserParam AuthUser user) {
        CurrencyDictionaryDTO currencyDictionaryDTO = POJOConvertUtil.convert(dto,CurrencyDictionaryDTO.class);
        currencyDictionaryDTO.setModifyInfo(user);
        currencyDictionaryDTO = currencyDictionaryBaseApi.update(currencyDictionaryDTO);
        return ResultDTO.of(currencyDictionaryDTO);
    }

    @Override
    public ResultDTO<Pagination<CurrencyDictionaryListDTO>> findPage(@RequestBody CurrencyDictionaryQueryDTO queryDTO, @AuthUserParam AuthUser user) {
        Pagination<CurrencyDictionaryDTO> currencyDictionaryDTOPage = currencyDictionaryBaseApi.findPage(queryDTO);

        List<CurrencyDictionaryDTO> itemDTOList = currencyDictionaryDTOPage.getList();
        List<CurrencyDictionaryListDTO> listDTOList = getCurrencyDictionaryListDTOs(itemDTOList);
        return ResultDTO.of(currencyDictionaryDTOPage.of(listDTOList));
    }

    private List<CurrencyDictionaryListDTO> getCurrencyDictionaryListDTOs(List<CurrencyDictionaryDTO> itemDTOList) {

        return POJOConvertUtil.convertList(itemDTOList,CurrencyDictionaryListDTO.class);
    }

    @Override
    public ResultDTO<List<CurrencyDictionaryListDTO>> findList(@RequestBody CurrencyDictionaryQueryDTO queryDTO, @AuthUserParam AuthUser user) {
        List<CurrencyDictionaryDTO> itemDTOList = currencyDictionaryBaseApi.findList(queryDTO);
        List<CurrencyDictionaryListDTO> listDTOList = getCurrencyDictionaryListDTOs(itemDTOList);
        return ResultDTO.of(listDTOList);
    }

    @Override
    public ResultDTO<CurrencyDictionaryShowDTO> getByGuid(@RequestParam("guid") String guid, @AuthUserParam AuthUser user) {
        CurrencyDictionaryDTO currencyDictionaryDTO = currencyDictionaryBaseApi.getByGuid(guid);
        CurrencyDictionaryShowDTO showDTO = POJOConvertUtil.convert(currencyDictionaryDTO,CurrencyDictionaryShowDTO.class);
        return ResultDTO.of(showDTO);
    }

    @Override
    public ResultDTO<Void> deleteById(@RequestParam("guid") String guid) {
        currencyDictionaryBaseApi.deleteById(guid);
        return ResultDTO.of(null);
    }

    public static void main(String[] args) throws Exception {
        AuthUser authUser = new AuthUser();
        authUser.setGuid("123");
        authUser.setName("单一");
        authUser.setIdNum("123");
        authUser.setUserType("inneruser");
        AuthOrganizationInfo authOrganizationInfo = new AuthOrganizationInfo();
        authOrganizationInfo.setGuid("123");
        authOrganizationInfo.setName("hhh");
        authOrganizationInfo.setUnifiedCode("123");
        AuthLegalPersonInfo authLegalPersonInfo = new AuthLegalPersonInfo();
        authLegalPersonInfo.setGuid("321");
        authOrganizationInfo.setName("321321");
        authLegalPersonInfo.setUnifiedCode("321231");
        authUser.setLegalPersonInfo(authLegalPersonInfo);
        authUser.setOrganizationInfo(authOrganizationInfo);
        String s = JSONObject.toJSONString(authUser).replaceAll("\"bh\":\"123\",","");
        System.out.println(s);
        String s1 = new String(Base64.getEncoder().encode(s.getBytes()));
        System.out.println(s1);

        ObjectMapper objectMapper = new ObjectMapper();
        AuthUser authUser1 = objectMapper.readValue(new String(Base64.getDecoder().decode(s1.getBytes("UTF-8")),"UTF-8"),AuthUser.class);
    }

}

