package com.zhulong.business.system.busservice.api.subsystem;

import com.zhulong.business.system.busservice.dto.subsystem.SubSystemUpdateDTO;
import com.zhulong.framework.auth.common.AuthUser;
import com.zhulong.framework.auth.common.mvc.AuthUserParam;
import com.zhulong.framework.common.dto.KeyValueDTO;
import com.zhulong.framework.common.dto.ResultDTO;
import com.zhulong.framework.common.dto.ZhEnglishDispayDTO;
import com.zhulong.framework.common.page.Pagination;
import com.zhulong.business.system.baseservice.dto.subsystem.SubSystemDTO;
import com.zhulong.business.system.baseservice.dto.subsystem.SubSystemQueryDTO;
import com.zhulong.business.system.busservice.dto.subsystem.SubSystemListDTO;
import com.zhulong.business.system.busservice.dto.subsystem.SubSystemSaveDTO;
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
 * 子系统管理-业务层接口
 * Created by shanb on 2019-2-26.
 */
@Api(value = "子系统管理-业务层接口",description = "子系统管理-业务层接口")
@RequestMapping("subSystemBus")
public interface SubSystemBusApi {

    /**
     * 保存方法
     * @param dto 保存的信息
     * @param authUser 登录的用户信息
     * @return 子系统信息
     */
    @PostMapping("save")
    @ApiOperation(value = "保存数据")
    ResultDTO<SubSystemDTO> save(@Valid @RequestBody SubSystemSaveDTO dto, @AuthUserParam AuthUser authUser);

    /**
     * 更新方法
     * @param dto 要更新的信息-不为null的更新
     * @param authUser 登录的用户信息
     * @return 子系统信息
     */
    @PostMapping("update")
    @ApiOperation(value = "更新数据")
    ResultDTO<SubSystemDTO> update(@RequestBody @Valid SubSystemUpdateDTO dto, @AuthUserParam AuthUser authUser);

    /**
     * 删除数据
     * @param guid 主键
     * @param authUser 登录的用户信息
     * @return 是否成功删除到数据
     */
    @PostMapping("deleteByGuid")
    @ApiOperation(value = "通过主键删除数据")
    ResultDTO<Boolean> deleteByGuid(String guid,@AuthUserParam AuthUser authUser);

    /**
     * 查询分页信息
     * @param queryDTO 查询参数
     * @param authUser 登录的用户信息
     * @return 分页信息的数据
     */
    @PostMapping("findPage")
    @ApiOperation(value = "分页查询数据")
    ResultDTO<Pagination<SubSystemListDTO>> findPage(@RequestBody SubSystemQueryDTO queryDTO,@AuthUserParam AuthUser authUser);

    /**
     * 查询列表信息
     * @param queryDTO 查询参数
     * @param authUser 登录的用户信息
     * @return 列表数据
     */
    @PostMapping("findList")
    @ApiOperation(value = "查询列表数据")
    ResultDTO<List<SubSystemListDTO>> findList(@RequestBody SubSystemQueryDTO queryDTO,@AuthUserParam AuthUser authUser);

    /**
     * 通过主键查询
     * @param guid 主键
     * @param authUser 登录用户信息
     * @return 返回子系统信息
     */
    @GetMapping("getByGuid")
    @ApiOperation(value = "通过主键获取数据")
    ResultDTO<SubSystemDTO> getByGuid(String guid,@AuthUserParam AuthUser authUser);

    /**
     * 更新排序信息
     * @param sortNumList 需要更新的列表信息
     * @param authUser 登录用户信息
     * @return 无
     */
    @PostMapping("updateSortNum")
    @ApiOperation(value = "批量更新排序号")
    ResultDTO<Void> updateSortNum(@RequestBody List<KeyValueDTO<String,BigDecimal>> sortNumList, @AuthUserParam AuthUser authUser);

    /**
     * 查询所有的用户类型
     * @return 所有的用户类型
     */
    @GetMapping("findAllUserTypeList")
    @ApiOperation(value = "查询所有用户类型")
    ResultDTO<List<ZhEnglishDispayDTO>> findAllUserTypeList();
}