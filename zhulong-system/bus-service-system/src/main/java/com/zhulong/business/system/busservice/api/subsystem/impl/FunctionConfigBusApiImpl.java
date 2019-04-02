package com.zhulong.business.system.busservice.api.subsystem.impl;

import com.zhulong.business.system.baseservice.api.subsystem.FunctionConfigBaseApi;
import com.zhulong.business.system.baseservice.api.subsystem.MenuConfigBaseApi;
import com.zhulong.business.system.baseservice.api.subsystem.MenuItemBaseApi;
import com.zhulong.business.system.baseservice.dto.subsystem.FunctionConfigDTO;
import com.zhulong.business.system.baseservice.dto.subsystem.FunctionConfigQueryDTO;
import com.zhulong.business.system.baseservice.dto.subsystem.MenuConfigDTO;
import com.zhulong.business.system.baseservice.dto.subsystem.MenuItemDTO;
import com.zhulong.business.system.busservice.api.subsystem.FunctionConfigBusApi;
import com.zhulong.business.system.busservice.dto.subsystem.FunctionConfigListDTO;
import com.zhulong.business.system.busservice.dto.subsystem.FunctionConfigSaveDTO;
import com.zhulong.business.system.busservice.dto.subsystem.FunctionConfigShowDTO;
import com.zhulong.business.system.busservice.dto.subsystem.FunctionConfigUpdateDTO;
import com.zhulong.framework.auth.common.AuthUser;
import com.zhulong.framework.auth.common.mvc.AuthUserParam;
import com.zhulong.framework.common.annotion.Module;
import com.zhulong.framework.common.dto.ResultDTO;
import com.zhulong.framework.common.page.Pagination;
import com.zhulong.framework.common.util.POJOConvertUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

/**
 * 描述：功能管理 BusApiImpl实现
 * @author shanb
 * @date 2019-03-26 15:52
 */
@RestController
@Module("SUBSYSTEM")
public class FunctionConfigBusApiImpl implements FunctionConfigBusApi {

    @Autowired
    private  FunctionConfigBaseApi functionConfigBaseApi;

    @Autowired
    private MenuItemBaseApi menuItemBaseApi;

    @Autowired
    private MenuConfigBaseApi menuConfigBaseApi;

    @Override
    public ResultDTO<FunctionConfigDTO> save(@Valid @RequestBody FunctionConfigSaveDTO dto, @AuthUserParam AuthUser user) {

        FunctionConfigDTO functionConfigDTO = POJOConvertUtil.convert(dto,FunctionConfigDTO.class);
        functionConfigDTO.setGuid(UUID.randomUUID().toString());
        functionConfigDTO.setCode(genaratorCode());
        functionConfigDTO.setCreateInfo(user);
        //获取菜单项
        String menuItemGuid = dto.getMenuItemGuid();
        MenuItemDTO menuItemDTO = menuItemBaseApi.getByGuid(menuItemGuid);
        functionConfigDTO.setMenuConfigGuid(menuItemDTO.getMenuConfigGuid());
        functionConfigBaseApi.save(functionConfigDTO);
        return ResultDTO.of(functionConfigDTO);
    }

    @Override
    public ResultDTO<FunctionConfigDTO> update(@Valid @RequestBody FunctionConfigUpdateDTO dto, @AuthUserParam AuthUser user) {
        FunctionConfigDTO functionConfigDTO = POJOConvertUtil.convert(dto,FunctionConfigDTO.class);
        functionConfigDTO.setModifyInfo(user);
        functionConfigDTO = functionConfigBaseApi.update(functionConfigDTO);
        return ResultDTO.of(functionConfigDTO);
    }

    @Override
    public ResultDTO<Pagination<FunctionConfigListDTO>> findPage(@RequestBody FunctionConfigQueryDTO queryDTO, @AuthUserParam AuthUser user) {
        Pagination<FunctionConfigDTO> functionConfigDTOPage = functionConfigBaseApi.findPage(queryDTO);

        List<FunctionConfigDTO> itemDTOList = functionConfigDTOPage.getList();
        List<FunctionConfigListDTO> listDTOList = getFunctionConfigListDTOs(itemDTOList);
        return ResultDTO.of(functionConfigDTOPage.of(listDTOList));
    }

    private List<FunctionConfigListDTO> getFunctionConfigListDTOs(List<FunctionConfigDTO> itemDTOList) {
        if(itemDTOList!=null && !itemDTOList.isEmpty()) {
            //查询上级主键
            Set<String> parentGuid = new HashSet<>();
            //查询所属菜单项
            Set<String> menuItemGuid = new HashSet<>();
            //查询所属菜单名称
            Set<String> menuConfigGuid = new HashSet<>();

            List<FunctionConfigListDTO> listDtoList = itemDTOList.stream().map(dto -> {
                FunctionConfigListDTO listDTO = POJOConvertUtil.convert(dto, FunctionConfigListDTO.class);
                if (!StringUtils.isEmpty(dto.getParentGuid())) {
                    parentGuid.add(dto.getParentGuid());
                }
                menuItemGuid.add(dto.getMenuItemGuid());
                menuConfigGuid.add(dto.getMenuConfigGuid());
                return listDTO;
            }).collect(Collectors.toList());

            //进行批量查询
            List<FunctionConfigDTO> parentDtoList = null;
            if (!parentGuid.isEmpty()) {
                parentDtoList = functionConfigBaseApi.findByGuidList(new ArrayList<>(parentGuid));
            } else {
                parentDtoList = Collections.EMPTY_LIST;
            }
            List<MenuItemDTO> menuItemDTOList = menuItemBaseApi.findByGuidList(new ArrayList<>(menuItemGuid));
            List<MenuConfigDTO> menuConfigDTOList = menuConfigBaseApi.findByGuidList(new ArrayList<>(menuConfigGuid));

            for (FunctionConfigListDTO listDTO : listDtoList) {
                if(!StringUtils.isEmpty(listDTO.getParentGuid())) {
                    Optional<FunctionConfigDTO> parentDTO = parentDtoList.stream().filter(pdto -> pdto.getGuid().equals(listDTO.getParentGuid())).findFirst();
                    if (parentDTO.isPresent()){
                        listDTO.setParentName(parentDTO.get().getName());
                    }
                }

                Optional< MenuItemDTO> menuItemDTO = menuItemDTOList.stream().filter(mdto -> mdto.getGuid().equals(listDTO.getMenuItemGuid())).findFirst();
                if(menuItemDTO.isPresent()){
                    listDTO.setMenuItemName(menuItemDTO.get().getName());
                }

                Optional<MenuConfigDTO> menuConfigDTO = menuConfigDTOList.stream().filter(mcdto -> mcdto.getGuid().equals(listDTO.getMenuConfigGuid())).findFirst();
                if(menuConfigDTO.isPresent()){
                    listDTO.setMenuConfigName(menuConfigDTO.get().getName());
                }
            }

            return listDtoList;
        }else{
            return Collections.emptyList();
        }
    }

    @Override
    public ResultDTO<List<FunctionConfigListDTO>> findList(@RequestBody FunctionConfigQueryDTO queryDTO, @AuthUserParam AuthUser user) {
        List<FunctionConfigDTO> itemDTOList = functionConfigBaseApi.findList(queryDTO);
        List<FunctionConfigListDTO> listDTOList = getFunctionConfigListDTOs(itemDTOList);
        return ResultDTO.of(listDTOList);
    }

    @Override
    public ResultDTO<FunctionConfigShowDTO> getByGuid(@RequestParam("guid") String guid, @AuthUserParam AuthUser user) {
        FunctionConfigDTO functionConfigDTO = functionConfigBaseApi.getByGuid(guid);
        FunctionConfigShowDTO showDTO = POJOConvertUtil.convert(functionConfigDTO,FunctionConfigShowDTO.class);
        if(!StringUtils.isEmpty(functionConfigDTO.getParentGuid())){
            FunctionConfigDTO parentDTO = functionConfigBaseApi.getByGuid(functionConfigDTO.getParentGuid());
            if(parentDTO!=null) {
                showDTO.setParentName(parentDTO.getName());
                showDTO.setParentCode(parentDTO.getCode());
            }
        }
        MenuItemDTO menuItemDTO = menuItemBaseApi.getByGuid(functionConfigDTO.getMenuItemGuid());
        if(menuItemDTO!=null) {
            showDTO.setMenuItemName(menuItemDTO.getName());
        }

        MenuConfigDTO menuConfigDTO = menuConfigBaseApi.getByGuid(functionConfigDTO.getMenuConfigGuid());
        if(menuConfigDTO!=null) {
            showDTO.setMenuConfigName(menuConfigDTO.getName());
        }

        return ResultDTO.of(showDTO);
    }

    @Override
    public ResultDTO<Void> deleteByGuid(String guid, @AuthUserParam AuthUser authUser) {
        functionConfigBaseApi.deleteById(guid);
        return ResultDTO.of(null);
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

