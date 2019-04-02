package com.zhulong.business.system.busservice.api.systemconfig;

import com.zhulong.business.system.baseservice.dto.systemconfig.DataDictionaryValueDTO;
import com.zhulong.business.system.baseservice.dto.systemconfig.DataDictionaryValueQueryDTO;
import com.zhulong.business.system.busservice.dto.systemconfig.DataDictionaryValueSaveDTO;
import com.zhulong.business.system.busservice.dto.systemconfig.DataDictionaryValueUpdateDTO;
import com.zhulong.framework.auth.common.AuthUser;
import com.zhulong.framework.auth.common.mvc.AuthUserParam;
import com.zhulong.framework.common.dto.ResultDTO;
import com.zhulong.framework.common.page.Pagination;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

/**
 * 描述：数据字典值 BusApi接口
 *
 * @author 初。
 * @date 2019-03-20 09:59:31
 */
@RequestMapping("/dataDictionaryValue")
@Api(value = "系统配置 - 数据字典值", description = "系统配置 - 数据字典值")
public interface DataDictionaryValueBusApi {

    /**
     * 根据主键查询一条数据
     *
     * @param guid 主键信息
     * @return 查询到的数据，null表示未查询到数据
     */
    @GetMapping("getById")
    @ApiOperation(value = "通过主键获取数据字典")
    ResultDTO<DataDictionaryValueDTO> getById(String guid);

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
    ResultDTO<DataDictionaryValueDTO> save(@Valid @RequestBody DataDictionaryValueSaveDTO saveDTO, @AuthUserParam AuthUser authUser);

    /**
     * 更新接口
     *
     * @param updateDTO 要更新的信息-为null的不更新
     * @param authUser  当前登录用户
     * @return 更新后返回的DTO
     */
    @PostMapping("update")
    @ApiOperation("更新方法")
    ResultDTO<DataDictionaryValueDTO> update(@Valid @RequestBody DataDictionaryValueUpdateDTO updateDTO, @AuthUserParam AuthUser authUser);

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
     * 查出指定条件下的数据
     *
     * @param queryDTO 搜索条件DTO
     * @return
     */
    @PostMapping("findByCondition")
    @ApiOperation("查出指定条件下的数据")
    ResultDTO<Pagination<DataDictionaryValueDTO>> findByCondition(@RequestBody DataDictionaryValueQueryDTO queryDTO);

}
