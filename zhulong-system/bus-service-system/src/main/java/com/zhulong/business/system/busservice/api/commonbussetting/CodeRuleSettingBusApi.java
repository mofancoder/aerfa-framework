package com.zhulong.business.system.busservice.api.commonbussetting;

import com.zhulong.business.system.baseservice.dto.commonbussetting.CodeRuleSettingDTO;
import com.zhulong.business.system.baseservice.dto.commonbussetting.CodeRuleSettingQueryDTO;
import com.zhulong.business.system.busservice.dto.commonbussetting.CodeRuleSettingListDTO;
import com.zhulong.business.system.busservice.dto.commonbussetting.CodeRuleSettingSaveDTO;
import com.zhulong.business.system.busservice.dto.commonbussetting.CodeRuleSettingShowDTO;
import com.zhulong.business.system.busservice.dto.commonbussetting.CodeRuleSettingUpdateDTO;
import com.zhulong.framework.auth.common.AuthUser;
import com.zhulong.framework.auth.common.mvc.AuthUserParam;
import com.zhulong.framework.common.dto.ResultDTO;
import com.zhulong.framework.common.page.Pagination;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

/**
 * 描述：编码生成规则 BusApi接口
 *
 * @author 初。
 * @date 2019-03-28 10:21
 */
@RequestMapping("/codeRuleSettingBus")
@Api(value = "编码生成规则 - 业务层接口", description = "编码生成规则 - 业务层接口")
public interface CodeRuleSettingBusApi {

    /**
     * 保存编码生成规则
     *
     * @param dto 保存的数据DTO
     * @param user 当前登录的用户
     * @return 保存后的对象信息
     */
    @PostMapping("save")
    @ApiOperation("保存编码生成规则")
    ResultDTO<CodeRuleSettingDTO> save(@RequestBody CodeRuleSettingSaveDTO dto, @AuthUserParam AuthUser user);

    /**
     * 更新编码生成规则
     *
     * @param dto 更新的数据DTO
     * @param user 当前登录用户
     * @return 更新后的数据对象
     */
    @PostMapping("update")
    @ApiOperation("更新编码生成规则")
    ResultDTO<CodeRuleSettingDTO> update(@RequestBody CodeRuleSettingUpdateDTO dto, @AuthUserParam AuthUser user);

    /**
     * 分页查询数据
     *
     * @param queryDTO 查询参数对象
     * @param user 当前登录的用户
     * @return 获取到的分页信息
     */
    @PostMapping("findPage")
    @ApiOperation("查询分页数据")
    ResultDTO<Pagination<CodeRuleSettingListDTO>> findPage(@RequestBody CodeRuleSettingQueryDTO queryDTO, @AuthUserParam AuthUser user);

    /**
     * 通过主键查询数据
     *
     * @param guid 数据主键
     * @param user 当前登录的用户
     * @return 页面展示数据
     */
    @GetMapping("getByGuid")
    @ApiOperation("通过主键获取")
    ResultDTO<CodeRuleSettingShowDTO> getByGuid(@RequestParam("guid") String guid, @AuthUserParam AuthUser user);

    /**
     * 根据Id删除
     *
     * @param guid 要删除数据的主键
     * @return 无-无论是否删除到数据，都表示成功。
     */
    @PostMapping("deleteById")
    @ApiOperation("通过主键删除数据")
    ResultDTO<Void> deleteById(@RequestParam("guid") String guid);

}
