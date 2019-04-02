package com.zhulong.business.system.busservice.api.subsystem;

import com.zhulong.business.system.baseservice.dto.subsystem.FunctionConfigDTO;
import com.zhulong.business.system.baseservice.dto.subsystem.FunctionConfigQueryDTO;
import com.zhulong.business.system.busservice.dto.subsystem.FunctionConfigListDTO;
import com.zhulong.business.system.busservice.dto.subsystem.FunctionConfigSaveDTO;
import com.zhulong.business.system.busservice.dto.subsystem.FunctionConfigShowDTO;
import com.zhulong.business.system.busservice.dto.subsystem.FunctionConfigUpdateDTO;
import com.zhulong.framework.auth.common.AuthUser;
import com.zhulong.framework.auth.common.mvc.AuthUserParam;
import com.zhulong.framework.common.dto.ResultDTO;
import com.zhulong.framework.common.page.Pagination;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 描述：功能管理 BusApi接口
 * @author shanb
 * @date 2019-03-26 15:52
 */
@RequestMapping("/functionConfigBus")
@Api(value = "功能管理-业务层接口",description = "功能管理-业务层接口")
public interface FunctionConfigBusApi {

    /**
     * 保存功能管理
     * @param dto 保存的数据DTO
     * @param user 当前登录的用户
     * @return 保存后的对象信息
     */
    @PostMapping("save")
    @ApiOperation("保存功能管理")
    ResultDTO<FunctionConfigDTO> save(@RequestBody FunctionConfigSaveDTO dto, @AuthUserParam AuthUser user);

    /**
     * 更新功能管理
     * @param dto 更新的数据DTO
     * @param user 当前登录用户
     * @return 更新后的数据对象
     */
    @PostMapping("update")
    @ApiOperation("更新功能管理")
    ResultDTO<FunctionConfigDTO> update(@RequestBody FunctionConfigUpdateDTO dto, @AuthUserParam AuthUser user);

    /**
     * 分页查询数据
     * @param queryDTO 查询参数对象
     * @param user 当前登录的用户
     * @return 获取到的分页信息
     */
    @PostMapping("findPage")
    @ApiOperation("查询分页数据")
    ResultDTO<Pagination<FunctionConfigListDTO>> findPage(@RequestBody FunctionConfigQueryDTO queryDTO, @AuthUserParam AuthUser user);

    /**
     *查询列表
     * @param queryDTO 查询的参数对象
     * @param user 当前登录的用户
     * @return 列表显示的数据
     */
    @PostMapping("findList")
    @ApiOperation("查询列表")
    ResultDTO<List<FunctionConfigListDTO>> findList(@RequestBody FunctionConfigQueryDTO queryDTO, @AuthUserParam AuthUser user);

    /**
     * 通过主键查询数据
     * @param guid 数据主键
     * @param user 当前登录的用户
     * @return 页面展示数据
     */
    @GetMapping("getByGuid")
    @ApiOperation("通过主键获取")
    ResultDTO<FunctionConfigShowDTO> getByGuid(@RequestParam("guid") String guid, @AuthUserParam AuthUser user);

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
