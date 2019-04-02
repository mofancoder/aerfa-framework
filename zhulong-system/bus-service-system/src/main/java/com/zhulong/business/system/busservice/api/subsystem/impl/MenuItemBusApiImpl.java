package com.zhulong.business.system.busservice.api.subsystem.impl;

import com.zhulong.business.system.baseservice.api.subsystem.MenuConfigBaseApi;
import com.zhulong.business.system.baseservice.api.subsystem.MenuItemBaseApi;
import com.zhulong.business.system.baseservice.api.subsystem.SubSystemBaseApi;
import com.zhulong.business.system.baseservice.dto.subsystem.MenuConfigDTO;
import com.zhulong.business.system.baseservice.dto.subsystem.MenuItemDTO;
import com.zhulong.business.system.baseservice.dto.subsystem.MenuItemQueryDTO;
import com.zhulong.business.system.baseservice.dto.subsystem.SubSystemDTO;
import com.zhulong.business.system.busservice.api.subsystem.MenuItemBusApi;
import com.zhulong.business.system.busservice.dto.subsystem.MenuItemListDTO;
import com.zhulong.business.system.busservice.dto.subsystem.MenuItemSaveDTO;
import com.zhulong.business.system.busservice.dto.subsystem.MenuItemShowDTO;
import com.zhulong.business.system.busservice.dto.subsystem.MenuItemUpdateDTO;
import com.zhulong.framework.auth.common.AuthUser;
import com.zhulong.framework.auth.common.mvc.AuthUserParam;
import com.zhulong.framework.common.annotion.Module;
import com.zhulong.framework.common.dto.KeyValueDTO;
import com.zhulong.framework.common.dto.ResultDTO;
import com.zhulong.framework.common.dto.TreeDTO;
import com.zhulong.framework.common.page.Pagination;
import com.zhulong.framework.common.util.POJOConvertUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

/**
* 描述：菜单项管理 BusApiImpl实现
* @author shanb
* @date 2019-03-18 11:44:45
*/
@RestController
@Module("SUBSYSTEM")
public class MenuItemBusApiImpl implements MenuItemBusApi {

    @Autowired
    private  MenuItemBaseApi menuItemBaseApi;

    @Autowired
    private MenuConfigBaseApi menuConfigBaseApi;

    @Autowired
    private SubSystemBaseApi subSystemBaseApi;

    @Override
    public ResultDTO<MenuItemDTO> save(@Valid @RequestBody MenuItemSaveDTO dto, @AuthUserParam AuthUser user) {

        MenuItemDTO menuItemDTO = POJOConvertUtil.convert(dto,MenuItemDTO.class);
        //TODO:修改自动生成
        menuItemDTO.setCode(genaratorCode());
        menuItemDTO.setGuid(UUID.randomUUID().toString());
        menuItemDTO.setCreateInfo(user);
        menuItemBaseApi.save(menuItemDTO);
        return ResultDTO.of(menuItemDTO);
    }

    @Override
    public ResultDTO<MenuItemDTO> update(@Valid @RequestBody MenuItemUpdateDTO dto, @AuthUserParam AuthUser user) {
        MenuItemDTO menuItemDTO = POJOConvertUtil.convert(dto,MenuItemDTO.class);
        menuItemDTO.setModifyInfo(user);
        menuItemDTO = menuItemBaseApi.update(menuItemDTO);
        return ResultDTO.of(menuItemDTO);
    }

    @Override
    public ResultDTO<Pagination<MenuItemListDTO>> findPage(@RequestBody MenuItemQueryDTO queryDTO, @AuthUserParam AuthUser user) {
        Pagination<MenuItemDTO> menuItemDTOPage = menuItemBaseApi.findPage(queryDTO);

        List<MenuItemDTO> itemDTOList = menuItemDTOPage.getList();
        List<MenuItemListDTO> listDTOList = getMenuItemListDTOs(itemDTOList);
        return ResultDTO.of(menuItemDTOPage.of(listDTOList));
    }

    private List<MenuItemListDTO> getMenuItemListDTOs(List<MenuItemDTO> itemDTOList) {
        Set<String> parentGuidSet = new HashSet<>();
        List<MenuItemListDTO> listDTOList =  itemDTOList.stream().map(dto->{
            if(!StringUtils.isEmpty(dto.getParentGuid())){
                parentGuidSet.add(dto.getParentGuid());
            }
            return POJOConvertUtil.convert(dto,MenuItemListDTO.class);
        }).collect(Collectors.toList());
        if(!parentGuidSet.isEmpty()){
            List<MenuItemDTO> parentDTOList = menuItemBaseApi.findByGuidList(new ArrayList<>(parentGuidSet));
            listDTOList = listDTOList.stream().map(dto->{
                if(!StringUtils.isEmpty(dto.getParentGuid())) {
                   Optional<MenuItemDTO> parentDTO = parentDTOList.stream().filter(pdto -> pdto.equals(dto.getParentGuid())).findAny();
                   if(parentDTO.isPresent()){
                       dto.setParentName(parentDTO.get().getName());
                   }
                }
                return dto;
            }).collect(Collectors.toList());
        }
        return listDTOList;
    }

    @Override
    public ResultDTO<List<MenuItemListDTO>> findList(@RequestBody MenuItemQueryDTO queryDTO, @AuthUserParam AuthUser user) {
        List<MenuItemDTO> itemDTOList = menuItemBaseApi.findList(queryDTO);
        List<MenuItemListDTO> listDTOList = getMenuItemListDTOs(itemDTOList);
        return ResultDTO.of(listDTOList);
    }

    @Override
    public ResultDTO<MenuItemShowDTO> getByGuid(@RequestParam("guid") String guid, @AuthUserParam AuthUser user) {
        MenuItemDTO menuItemDTO = menuItemBaseApi.getByGuid(guid);
        MenuItemShowDTO showDTO = POJOConvertUtil.convert(menuItemDTO,MenuItemShowDTO.class);
        if(!StringUtils.isEmpty(showDTO.getParentGuid())){
            MenuItemDTO parentDTO = menuItemBaseApi.getByGuid(showDTO.getParentGuid());
            if(parentDTO!=null) {
                showDTO.setParentName(parentDTO.getName());
            }
        }
        String menuConfigGuid = menuItemDTO.getMenuConfigGuid();
        MenuConfigDTO menuConfigDTO = menuConfigBaseApi.getByGuid(menuConfigGuid);
        showDTO.setMenuConfigName(menuConfigDTO.getName());
        String subSystemGuid = menuConfigDTO.getSubSystemGuid();
        SubSystemDTO subSystemDTO = subSystemBaseApi.getByGuid(subSystemGuid);
        showDTO.setSubSystemName(subSystemDTO.getName());
        return ResultDTO.of(showDTO);
    }

    @Override
    public ResultDTO<Void> updateSortNum(@RequestBody List<KeyValueDTO<String,BigDecimal>> keyValueDTOList, @AuthUserParam AuthUser user) {
        menuItemBaseApi.updateSortNum(keyValueDTOList);
        return ResultDTO.of(null);
    }

    @Override
    public ResultDTO<List<TreeDTO>> findTreeSyn(@RequestParam("menuConfigGuid") String menuConfigGuid) {
        MenuItemQueryDTO queryDTO = new MenuItemQueryDTO();
        queryDTO.setMenuConfigGuid(menuConfigGuid);
        List<MenuItemDTO> menuItemDTOList = menuItemBaseApi.findList(queryDTO);
        //进行数据组装
        List<TreeDTO> treeDTOList = buildTreeDTO(menuItemDTOList,null);
        return ResultDTO.of(treeDTOList);
    }

    private List<TreeDTO> buildTreeDTO(List<MenuItemDTO> menuItemDTOList,String parentGuid){
        List<TreeDTO> treeDTOList = new ArrayList<>();
        if(menuItemDTOList!=null && !menuItemDTOList.isEmpty()){
            for (MenuItemDTO menuItemDTO : menuItemDTOList) {
                if((parentGuid==null && StringUtils.isEmpty(menuItemDTO.getParentGuid())) || (parentGuid!=null && parentGuid.equals(menuItemDTO.getParentGuid()))){
                    TreeDTO treeDTO = new TreeDTO(menuItemDTO.getGuid(),menuItemDTO.getName());
                    treeDTO.setChildren(buildTreeDTO(menuItemDTOList,menuItemDTO.getGuid()));
                    treeDTOList.add(treeDTO);
                }
            }
        }
        return treeDTOList;
    }

    @Override
    public ResultDTO<Void> deleteByGuid(String guid, @AuthUserParam AuthUser authUser) {
        menuItemBaseApi.deleteById(guid);
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

