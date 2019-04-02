package com.zhulong.business.system.busservice.api.subsystem.impl;

import com.zhulong.business.system.busservice.api.subsystem.HomePageConfigBusApi;
import com.zhulong.business.system.busservice.dto.subsystem.HomePageConfigListDTO;
import com.zhulong.business.system.busservice.dto.subsystem.HomePageConfigSaveDTO;
import com.zhulong.business.system.busservice.dto.subsystem.HomePageConfigShowDTO;
import com.zhulong.business.system.busservice.dto.subsystem.HomePageConfigUpdateDTO;
import com.zhulong.framework.auth.common.AuthUser;
import com.zhulong.framework.auth.common.mvc.AuthUserParam;
import com.zhulong.framework.common.annotion.Module;
import com.zhulong.framework.common.dto.ResultDTO;
import com.zhulong.framework.common.page.Pagination;
import com.zhulong.framework.common.util.POJOConvertUtil;
import com.zhulong.business.system.baseservice.api.subsystem.HomePageConfigBaseApi;
import com.zhulong.business.system.baseservice.api.subsystem.SubSystemBaseApi;
import com.zhulong.business.system.baseservice.dto.subsystem.HomePageConfigDTO;
import com.zhulong.business.system.baseservice.dto.subsystem.HomePageConfigQueryDTO;
import com.zhulong.business.system.baseservice.dto.subsystem.SubSystemDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

/**
 * 首页管理-业务层接口
 * Created by shanb on 2019-2-27.
 */
@RestController
@Module("SUBSYSTEM")
public class HomePageConfigBusApiImpl implements HomePageConfigBusApi {

    @Autowired
    private HomePageConfigBaseApi homePageConfigBaseApi;

    @Autowired
    private SubSystemBaseApi subSystemBaseApi;

    @Override
    public ResultDTO<HomePageConfigDTO> save(@RequestBody @Valid HomePageConfigSaveDTO dto, @AuthUserParam AuthUser authUser) {
        HomePageConfigDTO homePageConfigDTO = POJOConvertUtil.convert(dto,HomePageConfigDTO.class);
        //处理其他字段
        homePageConfigDTO.setGuid(UUID.randomUUID().toString());
        homePageConfigDTO.setCode(generatorCode());
        homePageConfigDTO.setCreateInfo(authUser);
        homePageConfigBaseApi.save(homePageConfigDTO);
        return ResultDTO.of(homePageConfigDTO);
    }

    @Override
    public ResultDTO<HomePageConfigDTO> update(@RequestBody @Valid HomePageConfigUpdateDTO dto, @AuthUserParam AuthUser authUser) {
        HomePageConfigDTO homePageConfigDTO = POJOConvertUtil.convert(dto,HomePageConfigDTO.class);
        homePageConfigDTO.setModifyInfo(authUser);
        homePageConfigBaseApi.update(homePageConfigDTO);
        return ResultDTO.of(homePageConfigDTO);
    }

    @Override
    public ResultDTO<Void> deleteByGuid(String guid, @AuthUserParam AuthUser authUser) {
        homePageConfigBaseApi.deleteByGuid(guid);
        return ResultDTO.of(null);
    }

    @Override
    public ResultDTO<HomePageConfigShowDTO> getByGuid(String guid, @AuthUserParam AuthUser authUser) {
        HomePageConfigDTO homePageConfigDTO = homePageConfigBaseApi.getByGuid(guid);
        HomePageConfigShowDTO showDTO = POJOConvertUtil.convert(homePageConfigDTO,HomePageConfigShowDTO.class);
        //设置页面显示需要的字段
        if(!StringUtils.isEmpty(showDTO.getSubSystemGuid())) {
            SubSystemDTO subSystemDTO = subSystemBaseApi.getByGuid(showDTO.getSubSystemGuid());
            showDTO.setSubSystemCode(subSystemDTO.getCode());
            showDTO.setSubSystemName(subSystemDTO.getName());
        }
        return ResultDTO.of(showDTO);
    }

    @Override
    public ResultDTO<Pagination<HomePageConfigListDTO>> findPage(@RequestBody HomePageConfigQueryDTO queryDTO, @AuthUserParam AuthUser authUser) {
        Pagination<HomePageConfigDTO> page = homePageConfigBaseApi.findPageByCondition(queryDTO);
        List<HomePageConfigDTO> dtoList = page.getList();
        List<HomePageConfigListDTO> listDTOList = getHomePageConfigListDTOs(dtoList);
        return ResultDTO.of(page.of(listDTOList));
    }

    private List<HomePageConfigListDTO> getHomePageConfigListDTOs(List<HomePageConfigDTO> dtoList) {
        List<String> subSystemGuidList = dtoList.stream().filter(dto -> !StringUtils.isEmpty(dto.getSubSystemGuid()))
                .map(dto -> dto.getSubSystemGuid()).collect(Collectors.toList());
        List<SubSystemDTO> subSystemList = subSystemBaseApi.findByGuidList(subSystemGuidList);
        return dtoList.stream().map(dto->{
            HomePageConfigListDTO listDTO = POJOConvertUtil.convert(dto,HomePageConfigListDTO.class);
            //设置页面需要的值
            if(!StringUtils.isEmpty(dto.getSubSystemGuid())){
                Optional<SubSystemDTO> subSystemDTO = subSystemList.stream().filter(subDto -> dto.getSubSystemGuid().equals(subDto.getGuid())).findFirst();
                if(subSystemDTO.isPresent()){
                    listDTO.setSubSystemName(subSystemDTO.get().getName());
                }
            }
            return listDTO;
        }).collect(Collectors.toList());
    }

    @Override
    public ResultDTO<List<HomePageConfigListDTO>> findList(@RequestBody HomePageConfigQueryDTO queryDTO, @AuthUserParam AuthUser authUser) {
        List<HomePageConfigDTO> dtoList = homePageConfigBaseApi.findListByCondition(queryDTO);
        List<HomePageConfigListDTO> listDTOList = getHomePageConfigListDTOs(dtoList);
        return ResultDTO.of(listDTOList);
    }

    //TODO:修改成从配置信息中读取并生成
    private static String generatorCode(){
        StringBuilder sb = new StringBuilder();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
        sb.append(sdf.format(new Date()));
        Random r = new Random();
        Integer sequence = r.nextInt(9999);
        return sb.append(String.format("%04d",sequence)).toString();
    }
}