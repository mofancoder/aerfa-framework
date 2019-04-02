package com.zhulong.business.system.baseservice.api.subsystem;

import com.zhulong.business.system.baseservice.dto.subsystem.MenuItemDTO;
import com.zhulong.business.system.baseservice.dto.subsystem.MenuItemQueryDTO;
import com.zhulong.framework.common.dto.KeyValueDTO;
import com.zhulong.framework.common.page.Pagination;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

/**
* 描述：菜单项管理 BaseApi接口
* @author shanb
* @date 2019-03-18 11:44:45
*/
public interface MenuItemBaseApi {

    //url前缀
    String URL_PRE="/menuItemBaseApi";

    /**
    * 描述：根据Id获取DTO
    */
    @GetMapping(URL_PRE+"/getByGuid")
    MenuItemDTO getByGuid(@RequestParam("guid") String guid);

    /**
     * 根据主键列表获取数据
     */
    @PostMapping(URL_PRE+"/findByGuidList")
    List<MenuItemDTO> findByGuidList(@RequestBody List<String> guidList);

    /**
    * 描述：按条件查询
    */
    @PostMapping(URL_PRE+"/findList")
    List<MenuItemDTO> findList(@RequestBody MenuItemQueryDTO menuItemQueryDTO);

    /**
    * 描述：查询分页
    */
    @PostMapping(URL_PRE+"/findPage")
    Pagination<MenuItemDTO> findPage(@RequestBody MenuItemQueryDTO menuItemQueryDTO);

   /**
    * 描述：根据DTO保存
    */
    @PostMapping(URL_PRE+"/save")
    void save(@RequestBody MenuItemDTO menuItemDTO);

   /**
    * 描述：根据DTO更新
    */
    @PostMapping(URL_PRE+"/update")
    MenuItemDTO update(@RequestBody MenuItemDTO menuItemDTO);

   /**
    * 描述：根据Id删除
    */
    @PostMapping(URL_PRE+"/deleteById")
    void deleteById(@RequestParam("guid") String guid);

    /**
     * 更新序号
     */
    @PostMapping(URL_PRE+"/updateSortNum")
    void updateSortNum(@RequestBody List<KeyValueDTO<String,BigDecimal>> keyValueDTOList);

}
