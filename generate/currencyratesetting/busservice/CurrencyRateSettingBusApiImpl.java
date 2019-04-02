package com.zhulong.business.system.baseservice.entity.commonbussetting.busservice.api.commonbussetting.impl;

import com.zhulong.business.system.baseservice.entity.commonbussetting.baseservice.api.commonbussetting.CurrencyRateSettingBaseApi;
import com.zhulong.business.system.baseservice.entity.commonbussetting.baseservice.dto.commonbussetting.CurrencyRateSettingDTO;
import com.zhulong.business.system.baseservice.entity.commonbussetting.baseservice.dto.commonbussetting.CurrencyRateSettingQueryDTO;
import com.zhulong.business.system.baseservice.entity.commonbussetting.busservice.api.commonbussetting.CurrencyRateSettingBusApi;
import com.zhulong.business.system.baseservice.entity.commonbussetting.busservice.dto.commonbussetting.CurrencyRateSettingListDTO;
import com.zhulong.business.system.baseservice.entity.commonbussetting.busservice.dto.commonbussetting.CurrencyRateSettingSaveDTO;
import com.zhulong.business.system.baseservice.entity.commonbussetting.busservice.dto.commonbussetting.CurrencyRateSettingShowDTO;
import com.zhulong.business.system.baseservice.entity.commonbussetting.busservice.dto.commonbussetting.CurrencyRateSettingUpdateDTO;
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
 * 描述：汇率管理 BusApiImpl实现
 * @author MOFAN889
 * @date 2019-04-01 16:21
 */
@RestController
@Module("COMMONBUSSETTING")
public class CurrencyRateSettingBusApiImpl implements CurrencyRateSettingBusApi {

    @Autowired
    private  CurrencyRateSettingBaseApi currencyRateSettingBaseApi;

    @Override
    public ResultDTO<CurrencyRateSettingDTO> save(@Valid @RequestBody CurrencyRateSettingSaveDTO dto, @AuthUserParam AuthUser user) {

        CurrencyRateSettingDTO currencyRateSettingDTO = POJOConvertUtil.convert(dto,CurrencyRateSettingDTO.class);
        currencyRateSettingDTO.setGuid(UUID.randomUUID().toString());
        currencyRateSettingDTO.setCreateInfo(user);
        currencyRateSettingBaseApi.save(currencyRateSettingDTO);
        return ResultDTO.of(currencyRateSettingDTO);
    }

    @Override
    public ResultDTO<CurrencyRateSettingDTO> update(@Valid @RequestBody CurrencyRateSettingUpdateDTO dto, @AuthUserParam AuthUser user) {
        CurrencyRateSettingDTO currencyRateSettingDTO = POJOConvertUtil.convert(dto,CurrencyRateSettingDTO.class);
        currencyRateSettingDTO.setModifyInfo(user);
        currencyRateSettingDTO = currencyRateSettingBaseApi.update(currencyRateSettingDTO);
        return ResultDTO.of(currencyRateSettingDTO);
    }

    @Override
    public ResultDTO<Pagination<CurrencyRateSettingListDTO>> findPage(@RequestBody CurrencyRateSettingQueryDTO queryDTO, @AuthUserParam AuthUser user) {
        Pagination<CurrencyRateSettingDTO> currencyRateSettingDTOPage = currencyRateSettingBaseApi.findPage(queryDTO);

        List<CurrencyRateSettingDTO> itemDTOList = currencyRateSettingDTOPage.getList();
        List<CurrencyRateSettingListDTO> listDTOList = getCurrencyRateSettingListDTOs(itemDTOList);
        return ResultDTO.of(currencyRateSettingDTOPage.of(listDTOList));
    }

    private List<CurrencyRateSettingListDTO> getCurrencyRateSettingListDTOs(List<CurrencyRateSettingDTO> itemDTOList) {

        return POJOConvertUtil.convertList(itemDTOList,CurrencyRateSettingListDTO.class);
    }

    @Override
    public ResultDTO<List<CurrencyRateSettingListDTO>> findList(@RequestBody CurrencyRateSettingQueryDTO queryDTO, @AuthUserParam AuthUser user) {
        List<CurrencyRateSettingDTO> itemDTOList = currencyRateSettingBaseApi.findList(queryDTO);
        List<CurrencyRateSettingListDTO> listDTOList = getCurrencyRateSettingListDTOs(itemDTOList);
        return ResultDTO.of(listDTOList);
    }

    @Override
    public ResultDTO<CurrencyRateSettingShowDTO> getByGuid(@RequestParam("guid") String guid, @AuthUserParam AuthUser user) {
        CurrencyRateSettingDTO currencyRateSettingDTO = currencyRateSettingBaseApi.getByGuid(guid);
        CurrencyRateSettingShowDTO showDTO = POJOConvertUtil.convert(currencyRateSettingDTO,CurrencyRateSettingShowDTO.class);
        return ResultDTO.of(showDTO);
    }

    @Override
    public ResultDTO<Void> deleteById(@RequestParam("guid") String guid) {
        currencyRateSettingBaseApi.deleteById(guid);
        return ResultDTO.of(null);
    }

}

