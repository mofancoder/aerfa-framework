package com.zhulong.business.system.busservice.api.subsystem;

import com.zhulong.business.system.busservice.dto.subsystem.HomePageConfigListDTO;
import com.zhulong.business.system.busservice.dto.subsystem.HomePageConfigSaveDTO;
import com.zhulong.business.system.busservice.dto.subsystem.HomePageConfigShowDTO;
import com.zhulong.business.system.busservice.dto.subsystem.HomePageConfigUpdateDTO;
import com.zhulong.framework.auth.common.AuthUser;
import com.zhulong.framework.auth.common.mvc.AuthUserParam;
import com.zhulong.framework.common.dto.ResultDTO;
import com.zhulong.framework.common.page.Pagination;
import com.zhulong.business.system.baseservice.dto.subsystem.HomePageConfigDTO;
import com.zhulong.business.system.baseservice.dto.subsystem.HomePageConfigQueryDTO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;
import java.util.List;

/**
 * 首页管理-业务层接口
 * Created by shanb on 2019-2-27.
 */
@RequestMapping("homePageConfigBus")
@Api(value = "首页管理-业务层接口",description = "首页管理-业务层接口")
public interface HomePageConfigBusApi {

    /**
     * 保存接口
     * @param dto 要保存的信息
     * @param authUser 当前登录用户
     * @return 保存后的信息
     */
    @PostMapping("save")
    @ApiOperation("保存方法")
    ResultDTO<HomePageConfigDTO> save(@Valid @RequestBody HomePageConfigSaveDTO dto, @AuthUserParam AuthUser authUser);

    /**
     * 更新接口
     * @param dto 要更新的信息-为null的不更新
     * @param authUser 当前登录用户
     * @return 更新后的数据
     */
    @PostMapping("update")
    @ApiOperation("更新方法")
    ResultDTO<HomePageConfigDTO> update(@Valid @RequestBody HomePageConfigUpdateDTO dto, @AuthUserParam AuthUser authUser);

    /**
     * 根据主键删除数据
     * @param guid 主键
     * @return 无
     */
    @PostMapping("deleteByGuid")
    @ApiOperation("删除数据")
    ResultDTO<Void> deleteByGuid(String guid,@AuthUserParam AuthUser authUser);

    /**
     * 根据主键查询首页配置信息
     * @param guid 主键信息
     * @param authUser 当前登录用户
     * @return 查询到的数据，null表示未查询到数据
     */
    @GetMapping("getByGuid")
    @ApiOperation(value = "通过主键获取数据",notes = "用于查看页面之类的")
    ResultDTO<HomePageConfigShowDTO> getByGuid(String guid, @AuthUserParam AuthUser authUser);

    /**
     * 根据查询信息查询分页信息
     * @param queryDTO 查询对象
     * @param authUser 当前登录用户
     * @return 数据的分页信息
     */
    @PostMapping("findPage")
    @ApiOperation(value = "查询分页列表数据")
    ResultDTO<Pagination<HomePageConfigListDTO>> findPage(@RequestBody HomePageConfigQueryDTO queryDTO, @AuthUserParam AuthUser authUser);

    /**
     * 根据查询信息获取列表数据
     * @param queryDTO 查询对象
     * @param authUser 当前登录用户
     * @return 数据列表
     */
    @PostMapping("findList")
    @ApiOperation(value = "查询列表数据")
    ResultDTO<List<HomePageConfigListDTO>> findList(@RequestBody HomePageConfigQueryDTO queryDTO,@AuthUserParam AuthUser authUser);

}