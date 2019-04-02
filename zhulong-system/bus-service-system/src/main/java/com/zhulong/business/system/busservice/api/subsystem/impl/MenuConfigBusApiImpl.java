package com.zhulong.business.system.busservice.api.subsystem.impl;

import com.zhulong.business.system.busservice.api.subsystem.MenuConfigBusApi;
import com.zhulong.business.system.busservice.dto.subsystem.MenuConfigSaveDTO;
import com.zhulong.business.system.busservice.dto.subsystem.MenuConfigShowDTO;
import com.zhulong.framework.auth.common.AuthUser;
import com.zhulong.framework.auth.common.mvc.AuthUserParam;
import com.zhulong.framework.common.annotion.Module;
import com.zhulong.framework.common.dto.KeyValueDTO;
import com.zhulong.framework.common.dto.ResultDTO;
import com.zhulong.framework.common.page.Pagination;
import com.zhulong.framework.common.util.POJOConvertUtil;
import com.zhulong.business.system.baseservice.api.subsystem.MenuConfigBaseApi;
import com.zhulong.business.system.baseservice.api.subsystem.SubSystemBaseApi;
import com.zhulong.business.system.baseservice.dto.subsystem.MenuConfigDTO;
import com.zhulong.business.system.baseservice.dto.subsystem.MenuConfigQueryDTO;
import com.zhulong.business.system.baseservice.dto.subsystem.SubSystemDTO;
import com.zhulong.business.system.busservice.dto.subsystem.MenuConfigListDTO;
import com.zhulong.business.system.busservice.dto.subsystem.MenuConfigUpdateDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

/**
 * 菜单管理-业务层接口
 * Created by shanb on 2019-2-28.
 */
@RestController
@Module("SUBSYSTEM")
public class MenuConfigBusApiImpl implements MenuConfigBusApi {

    @Autowired
    private MenuConfigBaseApi menuConfigBaseApi;

    @Autowired
    private SubSystemBaseApi subSystemBaseApi;

    @Override
    public ResultDTO<MenuConfigDTO> save(@RequestBody @Valid MenuConfigSaveDTO dto, @AuthUserParam AuthUser authUser) {
        MenuConfigDTO menuConfigDTO = POJOConvertUtil.convert(dto,MenuConfigDTO.class);
        menuConfigDTO.setGuid(UUID.randomUUID().toString());
        menuConfigDTO.setCode(genaratorCode());
        menuConfigDTO.setCreateInfo(authUser);
        menuConfigBaseApi.save(menuConfigDTO);
        return ResultDTO.of(menuConfigDTO);
    }

    @Override
    public ResultDTO<MenuConfigDTO> update(@RequestBody @Valid MenuConfigUpdateDTO dto, @AuthUserParam AuthUser authUser) {
        MenuConfigDTO menuConfigDTO = POJOConvertUtil.convert(dto,MenuConfigDTO.class);
        menuConfigDTO.setModifyInfo(authUser);
        menuConfigBaseApi.update(menuConfigDTO);
        return ResultDTO.of(menuConfigDTO);
    }

    @Override
    public ResultDTO<Void> deleteByGuid(String guid, @AuthUserParam AuthUser authUser) {
        menuConfigBaseApi.deleteByGuid(guid);
        return ResultDTO.of(null);
    }

    @Override
    public ResultDTO<MenuConfigShowDTO> getByGuid(String guid, @AuthUserParam AuthUser authUser) {
        MenuConfigDTO dto = menuConfigBaseApi.getByGuid(guid);
        if(dto!=null) {
            MenuConfigShowDTO showDTO = POJOConvertUtil.convert(dto, MenuConfigShowDTO.class);
            //设置展示需要的值
            SubSystemDTO subSystemDTO = subSystemBaseApi.getByGuid(dto.getSubSystemGuid());
            showDTO.setSubSystemName(subSystemDTO.getName());
            return ResultDTO.of(showDTO);
        }
        return ResultDTO.of(null);
    }

    @Override
    public ResultDTO<Pagination<MenuConfigListDTO>> findPage(@RequestBody MenuConfigQueryDTO queryDTO, @AuthUserParam AuthUser authUser) {
        Pagination<MenuConfigDTO> page = menuConfigBaseApi.findPageByCondition(queryDTO);
        List<MenuConfigDTO> dtoList = page.getList();
        List<MenuConfigListDTO> listDtoList = getMenuConfigListDTOs(dtoList);
        return ResultDTO.of(page.of(listDtoList));
    }



    @Override
    public ResultDTO<List<MenuConfigListDTO>> findList(@RequestBody MenuConfigQueryDTO queryDTO, @AuthUserParam AuthUser authUser) {
        List<MenuConfigDTO> configDTOList = menuConfigBaseApi.findListByCondition(queryDTO);
        List<MenuConfigListDTO> listDTOList = getMenuConfigListDTOs(configDTOList);
        return ResultDTO.of(listDTOList);
    }

    @Override
    public ResultDTO<Void> updateSortNum(@RequestBody List<KeyValueDTO<String, BigDecimal>> sortNumList, @AuthUserParam AuthUser authUser) {
        menuConfigBaseApi.updateSortNum(sortNumList);
        return ResultDTO.of(null);
    }

    private List<MenuConfigListDTO> getMenuConfigListDTOs(List<MenuConfigDTO> dtoList) {
        List<String> subSystemGuidList = dtoList.stream().filter(dto -> !StringUtils.isEmpty(dto.getSubSystemGuid())).map(dto -> dto.getSubSystemGuid()).collect(Collectors.toList());
        List<SubSystemDTO> subSystemDTOList = subSystemBaseApi.findByGuidList(subSystemGuidList);
        return dtoList.stream().map(dto -> {
            MenuConfigListDTO listDTO = POJOConvertUtil.convert(dto,MenuConfigListDTO.class);
            if(!StringUtils.isEmpty(dto.getSubSystemGuid())) {
                Optional<SubSystemDTO> subSystemDTO = subSystemDTOList.stream().filter(subDTo -> dto.getSubSystemGuid().equals(subDTo.getGuid())).findFirst();
                listDTO.setSubSystemName(subSystemDTO.isPresent()?subSystemDTO.get().getName() : null);
            }
            return listDTO;
        }).collect(Collectors.toList());
    }

    //TODO:修改按照编码规则进行
    private String genaratorCode(){
        StringBuilder sb = new StringBuilder();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
        sb.append(sdf.format(new Date()));
        Random r = new Random();
        Integer sequence = r.nextInt(9999);
        return sb.append(String.format("%04d",sequence)).toString();
    }
}