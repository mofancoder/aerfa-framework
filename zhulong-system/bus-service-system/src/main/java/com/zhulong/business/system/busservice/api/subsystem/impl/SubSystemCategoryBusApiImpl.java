package com.zhulong.business.system.busservice.api.subsystem.impl;

import com.zhulong.business.system.baseservice.api.subsystem.SubSystemCategoryBaseApi;
import com.zhulong.business.system.baseservice.dto.subsystem.SubSystemCategoryDTO;
import com.zhulong.business.system.baseservice.dto.subsystem.SubSystemCategoryQueryDTO;
import com.zhulong.business.system.busservice.api.subsystem.SubSystemCategoryBusApi;
import com.zhulong.business.system.busservice.dto.subsystem.SubSystemCategorySaveDTO;
import com.zhulong.business.system.busservice.dto.subsystem.SubSystemCategoryUpdateDTO;
import com.zhulong.framework.auth.common.AuthUser;
import com.zhulong.framework.auth.common.mvc.AuthUserParam;
import com.zhulong.framework.common.annotion.Module;
import com.zhulong.framework.common.dto.KeyValueDTO;
import com.zhulong.framework.common.dto.ResultDTO;
import com.zhulong.framework.common.page.Pagination;
import com.zhulong.framework.common.util.POJOConvertUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.UUID;

/**
 * Created by shanb on 2019-2-26.
 */
@Module("SUBSYSTEM")
@RestController
public class SubSystemCategoryBusApiImpl implements SubSystemCategoryBusApi {

    @Autowired
    private SubSystemCategoryBaseApi subSystemCategoryBaseApi;

    @Override
    public ResultDTO<SubSystemCategoryDTO> save(@RequestBody @Valid SubSystemCategorySaveDTO dto, @AuthUserParam AuthUser authUser) {
        SubSystemCategoryDTO subSystemCategoryDTO = POJOConvertUtil.convert(dto,SubSystemCategoryDTO.class);
        //设置需要保存的信息
        subSystemCategoryDTO.setGuid(UUID.randomUUID().toString());
        subSystemCategoryDTO.setCreateInfo(authUser);
        //设置编号
        subSystemCategoryDTO.setCode(genaratorCode());

        subSystemCategoryBaseApi.save(subSystemCategoryDTO);
        return ResultDTO.of(subSystemCategoryDTO);
    }

    @Override
    public ResultDTO<SubSystemCategoryDTO> update(@RequestBody @Valid SubSystemCategoryUpdateDTO dto, @AuthUserParam AuthUser authUser) {
        SubSystemCategoryDTO subSystemCategoryDTO = POJOConvertUtil.convert(dto,SubSystemCategoryDTO.class);
        subSystemCategoryDTO.setModifyInfo(authUser);
        subSystemCategoryBaseApi.update(subSystemCategoryDTO);
        return ResultDTO.of(subSystemCategoryDTO);
    }

    @Override
    public ResultDTO<Boolean> deleteByGuid(String guid) {
        Boolean result = subSystemCategoryBaseApi.deleteByGuid(guid);
        return ResultDTO.of(result);
    }

    @Override
    public ResultDTO<SubSystemCategoryDTO> getByGuid(String guid, @AuthUserParam AuthUser authUser) {
        SubSystemCategoryDTO subSystemCategoryDTO = subSystemCategoryBaseApi.getByGuid(guid);
        return ResultDTO.of(subSystemCategoryDTO);
    }

    @Override
    public ResultDTO<Pagination<SubSystemCategoryDTO>> findPage(@RequestBody SubSystemCategoryQueryDTO queryDTO, @AuthUserParam AuthUser authUser) {
        Pagination<SubSystemCategoryDTO> pagination = subSystemCategoryBaseApi.findPageByCondition(queryDTO);
        return ResultDTO.of(pagination);
    }

    @Override
    public ResultDTO<Void> updateSortNums(@RequestBody List<KeyValueDTO<String, BigDecimal>> sortNumList, @AuthUserParam AuthUser authUser) {
        subSystemCategoryBaseApi.updateSortNums(sortNumList);
        return ResultDTO.of(null);
    }

    //TODO:需要修改成自动生成策略
    private String genaratorCode(){
        StringBuilder sb = new StringBuilder();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
        sb.append(sdf.format(new Date()));
        Random r = new Random();
        Integer sequence = r.nextInt(9999);
        return sb.append(String.format("%04d",sequence)).toString();
    }

}