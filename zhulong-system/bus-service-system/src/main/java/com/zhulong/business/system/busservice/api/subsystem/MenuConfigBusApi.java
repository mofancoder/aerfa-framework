package com.zhulong.business.system.busservice.api.subsystem;

import com.zhulong.framework.auth.common.AuthUser;
import com.zhulong.framework.auth.common.mvc.AuthUserParam;
import com.zhulong.framework.common.dto.KeyValueDTO;
import com.zhulong.framework.common.dto.ResultDTO;
import com.zhulong.framework.common.page.Pagination;
import com.zhulong.business.system.baseservice.dto.subsystem.MenuConfigDTO;
import com.zhulong.business.system.baseservice.dto.subsystem.MenuConfigQueryDTO;
import com.zhulong.business.system.busservice.dto.subsystem.MenuConfigListDTO;
import com.zhulong.business.system.busservice.dto.subsystem.MenuConfigSaveDTO;
import com.zhulong.business.system.busservice.dto.subsystem.MenuConfigShowDTO;
import com.zhulong.business.system.busservice.dto.subsystem.MenuConfigUpdateDTO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;
import java.math.BigDecimal;
import java.util.List;

/**
 * 菜单管理-业务层接口
 * Created by shanb on 2019-2-27.
 */
@RequestMapping("/menuConfig")
@Api(value = "菜单管理-业务层接口",description = "菜单管理-业务层接口")
public interface MenuConfigBusApi {

    /**
     * 保存信息
     * @param dto 保存信息
     * @param authUser 登录后的用户
     * @return 保存后的信息
     */
    @PostMapping("save")
    @ApiOperation(value = "保存方法")
    ResultDTO<MenuConfigDTO> save(@Valid @RequestBody MenuConfigSaveDTO dto, @AuthUserParam AuthUser authUser);

    /**
     * 更新信息
     * @param dto 更新的信息
     * @param authUser 登录后的用户
     * @return 更新后的数据
     */
    @PostMapping("update")
    @ApiOperation(value = "更新方法")
    ResultDTO<MenuConfigDTO> update(@Valid @RequestBody MenuConfigUpdateDTO dto,@AuthUserParam AuthUser authUser);

    /**
     * 删除信息
     * @param guid 主键
     * @param authUser 登录后的用户
     * @return 无
     */
    @PostMapping("deleteByGuid")
    @ApiOperation(value = "删除方法")
    ResultDTO<Void> deleteByGuid(String guid,@AuthUserParam AuthUser authUser);

    /**
     * 获取菜单信息
     * @param guid 主键
     * @param authUser 登录的用户信息
     * @return 菜单配置信息
     */
    @GetMapping("getByGuid")
    @ApiOperation(value = "通过主键获取信息")
    ResultDTO<MenuConfigShowDTO> getByGuid(String guid,@AuthUserParam AuthUser authUser);

    /**
     * 查询分页信息
     * @param queryDTO 查询对象
     * @param authUser 登录的用户信息
     * @return 查询分页信息
     */
    @PostMapping("findPage")
    @ApiOperation(value = "获取分页信息数据")
    ResultDTO<Pagination<MenuConfigListDTO>> findPage(@RequestBody MenuConfigQueryDTO queryDTO,@AuthUserParam AuthUser authUser);

    /**
     * 查询列表信息
     * @param queryDTO 查询对象
     * @param authUser 用户信息
     * @return 查询列表信息
     */
    @PostMapping("findList")
    @ApiOperation(value = "获取列表数据")
    ResultDTO<List<MenuConfigListDTO>> findList(@RequestBody MenuConfigQueryDTO queryDTO,@AuthUserParam AuthUser authUser);

    /**
     * 更新排序信息
     * @param  sortNumList 排序信息
     * @param authUser 用户信息
     * @return 更新排序信息
     */
    @PostMapping("updateSortNum")
    @ApiOperation(value = "批量更新序号")
    ResultDTO<Void> updateSortNum(@RequestBody List<KeyValueDTO<String,BigDecimal>> sortNumList, @AuthUserParam AuthUser authUser);
}
