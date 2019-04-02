package com.zhulong.business.system.busservice.api.commonbussetting;

import com.zhulong.business.system.baseservice.dto.commonbussetting.AgreementSettingDTO;
import com.zhulong.business.system.baseservice.dto.commonbussetting.AgreementSettingQueryDTO;
import com.zhulong.business.system.busservice.dto.commonbussetting.AgreementSettingListDTO;
import com.zhulong.business.system.busservice.dto.commonbussetting.AgreementSettingSaveDTO;
import com.zhulong.business.system.busservice.dto.commonbussetting.AgreementSettingShowDTO;
import com.zhulong.business.system.busservice.dto.commonbussetting.AgreementSettingUpdateDTO;
import com.zhulong.framework.auth.common.AuthUser;
import com.zhulong.framework.auth.common.mvc.AuthUserParam;
import com.zhulong.framework.common.dto.ResultDTO;
import com.zhulong.framework.common.page.Pagination;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 描述：协议管理 BusApi接口
 * @author MOFAN889
 * @date 2019-04-01 18:06
 */
@RequestMapping("/agreementSettingBus")
@Api(value = "协议管理-业务层接口",description = "协议管理-业务层接口")
public interface AgreementSettingBusApi {

    /**
     * 保存协议管理
     * @param dto 保存的数据DTO
     * @param user 当前登录的用户
     * @return 保存后的对象信息
     */
    @PostMapping("save")
    @ApiOperation("保存协议管理")
    ResultDTO<AgreementSettingDTO> save(@RequestBody AgreementSettingSaveDTO dto, @AuthUserParam AuthUser user);

    /**
     * 更新协议管理
     * @param dto 更新的数据DTO
     * @param user 当前登录用户
     * @return 更新后的数据对象
     */
    @PostMapping("update")
    @ApiOperation("更新协议管理")
    ResultDTO<AgreementSettingDTO> update(@RequestBody AgreementSettingUpdateDTO dto, @AuthUserParam AuthUser user);

    /**
     * 分页查询数据
     * @param queryDTO 查询参数对象
     * @param user 当前登录的用户
     * @return 获取到的分页信息
     */
    @PostMapping("findPage")
    @ApiOperation("查询分页数据")
    ResultDTO<Pagination<AgreementSettingListDTO>> findPage(@RequestBody AgreementSettingQueryDTO queryDTO, @AuthUserParam AuthUser user);

    /**
     *查询列表
     * @param queryDTO 查询的参数对象
     * @param user 当前登录的用户
     * @return 列表显示的数据
     */
    @PostMapping("findList")
    @ApiOperation("查询列表")
    ResultDTO<List<AgreementSettingListDTO>> findList(@RequestBody AgreementSettingQueryDTO queryDTO, @AuthUserParam AuthUser user);

    /**
     * 通过主键查询数据
     * @param guid 数据主键
     * @param user 当前登录的用户
     * @return 页面展示数据
     */
    @GetMapping("getByGuid")
    @ApiOperation("通过主键获取")
    ResultDTO<AgreementSettingShowDTO> getByGuid(@RequestParam("guid") String guid, @AuthUserParam AuthUser user);

    /**
     * 根据Id删除
     * @param guid 要删除数据的主键
     * @return 无-无论是否删除到数据，都表示成功。
     */
    @PostMapping("deleteById")
    @ApiOperation("通过主键删除数据")
    ResultDTO<Void> deleteById(@RequestParam("guid") String guid);

}
