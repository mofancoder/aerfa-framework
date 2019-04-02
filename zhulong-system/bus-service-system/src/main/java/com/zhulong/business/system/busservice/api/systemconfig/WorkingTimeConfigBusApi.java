package com.zhulong.business.system.busservice.api.systemconfig;

import com.zhulong.business.system.baseservice.dto.systemconfig.WorkingTimeConfigDTO;
import com.zhulong.business.system.busservice.dto.systemconfig.WorkingTimeConfigSaveDTO;
import com.zhulong.business.system.busservice.dto.systemconfig.WorkingTimeConfigUpdateDTO;
import com.zhulong.framework.auth.common.AuthUser;
import com.zhulong.framework.auth.common.mvc.AuthUserParam;
import com.zhulong.framework.common.dto.ResultDTO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;
import java.util.List;

/**
 * 描述：工作时间管理 BusApi接口
 *
 * @author 初。
 * @date 2019-03-21 04:05:52
 */
@RequestMapping("/workingTimeConfig")
@Api(value = "系统配置 - 工作时间管理", description = "系统配置 - 工作时间管理")
public interface WorkingTimeConfigBusApi {
    
    /**
     * 根据主键查询一条数据
     *
     * @param guid 主键信息
     * @return 查询到的数据，null表示未查询到数据
     */
    @GetMapping("getById")
    @ApiOperation(value = "通过主键获取数据字典")
    ResultDTO<WorkingTimeConfigDTO> getById(String guid);

    /**
     * 保存接口
     *
     * @param saveDTO  要保存的信息
     * @param authUser 当前登录用户
     * @return 保存后返回的DTO
     * @author 初。
     */
    @PostMapping("save")
    @ApiOperation("保存方法")
    ResultDTO<WorkingTimeConfigDTO> save(@Valid @RequestBody WorkingTimeConfigSaveDTO saveDTO, @AuthUserParam AuthUser authUser);

    /**
     * 更新接口
     *
     * @param updateDTO 要更新的信息-为null的不更新
     * @param authUser  当前登录用户
     * @return 更新后返回的DTO
     */
    @PostMapping("update")
    @ApiOperation("更新方法")
    ResultDTO<WorkingTimeConfigDTO> update(@Valid @RequestBody WorkingTimeConfigUpdateDTO updateDTO, @AuthUserParam AuthUser authUser);

    /**
     * 根据主键删除数据
     *
     * @param guid 主键
     * @return 无
     */
    @PostMapping("deleteByGuid")
    @ApiOperation("删除数据")
    ResultDTO<Object> deleteByGuid(String guid, @AuthUserParam AuthUser authUser);

    /**
     * 获取所有工作时间配置
     * @return
     */
    @PostMapping("findAll")
    @ApiOperation("获取所有")
    ResultDTO<List<WorkingTimeConfigDTO>> findAll();

}
