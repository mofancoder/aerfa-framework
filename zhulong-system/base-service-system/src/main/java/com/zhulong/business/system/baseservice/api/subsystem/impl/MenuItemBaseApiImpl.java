package com.zhulong.business.system.baseservice.api.subsystem.impl;

import com.zhulong.business.system.baseservice.api.subsystem.MenuItemBaseApi;
import com.zhulong.business.system.baseservice.dto.subsystem.MenuItemDTO;
import com.zhulong.business.system.baseservice.dto.subsystem.MenuItemQueryDTO;
import com.zhulong.business.system.baseservice.entity.subsystem.MenuItemEntity;
import com.zhulong.business.system.baseservice.repository.subsystem.MenuItemRepository;
import com.zhulong.framework.common.dto.KeyValueDTO;
import com.zhulong.framework.common.jpa.BaseDao;
import com.zhulong.framework.common.jpa.JPAUtils;
import com.zhulong.framework.common.page.Pagination;
import com.zhulong.framework.common.util.POJOConvertUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.*;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

/**
* 描述：菜单项管理 BaseApiImpl实现
* @author shanb
* @date 2019-03-18 11:44:45
*/
@Transactional
@RestController
public class MenuItemBaseApiImpl extends BaseDao implements MenuItemBaseApi {

    @Autowired
    private  MenuItemRepository menuItemRepository;

    @Override
    public MenuItemDTO getByGuid(@RequestParam("guid") String guid) {

        Optional<MenuItemEntity> menuItemOptional = menuItemRepository.findById(guid);

        MenuItemDTO menuItemDTO = menuItemOptional.isPresent() ? POJOConvertUtil.convert(menuItemOptional.get(), MenuItemDTO.class) : null;

        return menuItemDTO;
    }

    @Override
    public List<MenuItemDTO> findByGuidList(@RequestBody List<String> guidList) {
        if(guidList!=null){
            List<MenuItemEntity> entityList = menuItemRepository.findAllById(guidList);
            return POJOConvertUtil.convertList(entityList,MenuItemDTO.class);
        }
        return null;
    }

    @Override
    public List<MenuItemDTO> findList(@RequestBody MenuItemQueryDTO queryDTO){

        Example<MenuItemEntity> example = getMenuItemEntityExample(queryDTO);

        List<MenuItemEntity>  menuItemList = menuItemRepository.findAll(example,Sort.by(Sort.Direction.ASC,"sortNum"));

        List<MenuItemDTO> menuItemDTOList = POJOConvertUtil.convertList(menuItemList,MenuItemDTO.class);

        return menuItemDTOList;
    }

    private Example<MenuItemEntity> getMenuItemEntityExample(@RequestBody MenuItemQueryDTO queryDTO) {
        MenuItemEntity entity = POJOConvertUtil.convert(queryDTO,MenuItemEntity.class);

        ExampleMatcher exampleMatcher = ExampleMatcher.matching().withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING)
                .withIgnoreNullValues().withMatcher("parentGuid", ExampleMatcher.GenericPropertyMatcher.of(ExampleMatcher.StringMatcher.EXACT))
                .withMatcher("menuConfigGuid",ExampleMatcher.GenericPropertyMatcher.of(ExampleMatcher.StringMatcher.EXACT));
        return Example.of(entity,exampleMatcher);
    }

    @Override
    public Pagination<MenuItemDTO> findPage(@RequestBody MenuItemQueryDTO queryDTO){

        Example<MenuItemEntity> example = getMenuItemEntityExample(queryDTO);
        Pageable pageable = JPAUtils.toJpaPageableAndSort(queryDTO.getPageOrderDTO(),Sort.by(Sort.Direction.ASC,"sortNum","guid"));
        Page<MenuItemEntity> page = menuItemRepository.findAll(example,pageable);
        return  JPAUtils.jpaPageToPagination(page,MenuItemDTO.class);

    }

    @Override
    public void save(@RequestBody MenuItemDTO dto) {
        MenuItemEntity menuItem = POJOConvertUtil.convert(dto, MenuItemEntity.class);
        menuItemRepository.save(menuItem);
    }

    @Override
    public MenuItemDTO update(@RequestBody MenuItemDTO dto) {
        MenuItemEntity menuItem = POJOConvertUtil.convert(dto, MenuItemEntity.class);
        menuItem = menuItemRepository.update(menuItem);
        if(menuItem!=null){
            return POJOConvertUtil.convert(menuItem,MenuItemDTO.class);
        }else{
            return null;
        }
    }

    @Override
    public void deleteById(@RequestParam("guid") String guid) {
        try {
            menuItemRepository.deleteById(guid);
        }catch (EmptyResultDataAccessException e){
            //do nothing
        }
    }

    @Override
    public void updateSortNum(@RequestBody List<KeyValueDTO<String, BigDecimal>> keyValueDTOList) {
        if(keyValueDTOList!=null && !keyValueDTOList.isEmpty()){
            for (KeyValueDTO<String, BigDecimal> keyValueDTO : keyValueDTOList) {
                menuItemRepository.updateSortNum(keyValueDTO.getKey(),keyValueDTO.getValue());
            }
        }
    }
}

