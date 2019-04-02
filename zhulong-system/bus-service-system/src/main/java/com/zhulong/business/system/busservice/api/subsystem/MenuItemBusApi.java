package com.zhulong.business.system.busservice.api.subsystem;

import com.zhulong.business.system.baseservice.dto.subsystem.MenuItemDTO;
import com.zhulong.business.system.baseservice.dto.subsystem.MenuItemQueryDTO;
import com.zhulong.business.system.busservice.dto.subsystem.MenuItemListDTO;
import com.zhulong.business.system.busservice.dto.subsystem.MenuItemSaveDTO;
import com.zhulong.business.system.busservice.dto.subsystem.MenuItemShowDTO;
import com.zhulong.business.system.busservice.dto.subsystem.MenuItemUpdateDTO;
import com.zhulong.framework.auth.common.AuthUser;
import com.zhulong.framework.auth.common.mvc.AuthUserParam;
import com.zhulong.framework.common.dto.KeyValueDTO;
import com.zhulong.framework.common.dto.QueryPageDTO;
import com.zhulong.framework.common.dto.ResultDTO;
import com.zhulong.framework.common.dto.TreeDTO;
import com.zhulong.framework.common.page.Pagination;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

/**
* 描述：菜单项管理 BusApi接口
* @author shanb
* @date 2019-03-18 11:44:45
*/
@RequestMapping("/menuItemBus")
@Api(value = "菜单项管理-业务层接口",description = "菜单项管理-业务层接口")
public interface MenuItemBusApi {

    /**
     * 保存菜单项
     */
    @PostMapping("save")
    @ApiOperation("保存菜单项")
    ResultDTO<MenuItemDTO> save(@RequestBody MenuItemSaveDTO dto, @AuthUserParam AuthUser user);

    /**
     * 更新菜单项
     */
    @PostMapping("update")
    @ApiOperation("更新菜单项")
    ResultDTO<MenuItemDTO> update(@RequestBody MenuItemUpdateDTO dto, @AuthUserParam AuthUser user);

    /**
     * 分页查询数据
     */
    @PostMapping("findPage")
    @ApiOperation("查询分页数据")
    ResultDTO<Pagination<MenuItemListDTO>> findPage(@RequestBody MenuItemQueryDTO queryDTO,@AuthUserParam AuthUser user);

    /**
     *查询列表
     */
    @PostMapping("findList")
    @ApiOperation("查询列表")
    ResultDTO<List<MenuItemListDTO>> findList(@RequestBody MenuItemQueryDTO queryDTO, @AuthUserParam AuthUser user);

    /**
     * 通过主键查询数据
     */
    @GetMapping("getByGuid")
    @ApiOperation("通过主键获取")
    ResultDTO<MenuItemShowDTO> getByGuid(@RequestParam("guid") String guid, @AuthUserParam AuthUser user);

    /**
     * 更新序号
     */
    @PostMapping("updateSortNum")
    @ApiOperation("更新序号")
    ResultDTO<Void> updateSortNum(@RequestBody List<KeyValueDTO<String,BigDecimal>> keyValueDTOList, @AuthUserParam AuthUser user);

    /**
     * 根据菜单主键，获取同步树信息
     */
    @GetMapping
    @ApiOperation("获取同步树")
    ResultDTO<List<TreeDTO>> findTreeSyn(@RequestParam("menuConfigGuid") String menuConfigGuid);

    /**
     * 删除信息
     * @param guid 主键
     * @param authUser 登录后的用户
     * @return 无
     */
    @PostMapping("deleteByGuid")
    @ApiOperation(value = "删除方法")
    ResultDTO<Void> deleteByGuid(String guid,@AuthUserParam AuthUser authUser);
}
