package com.zhulong.business.system.busservice.api.commonbussetting;

import com.zhulong.business.system.baseservice.dto.commonbussetting.CurrencyRateSettingDTO;
import com.zhulong.business.system.baseservice.dto.commonbussetting.CurrencyRateSettingQueryDTO;
import com.zhulong.business.system.busservice.dto.commonbussetting.CurrencyRateSettingListDTO;
import com.zhulong.business.system.busservice.dto.commonbussetting.CurrencyRateSettingSaveDTO;
import com.zhulong.business.system.busservice.dto.commonbussetting.CurrencyRateSettingShowDTO;
import com.zhulong.business.system.busservice.dto.commonbussetting.CurrencyRateSettingUpdateDTO;
import com.zhulong.framework.auth.common.AuthUser;
import com.zhulong.framework.auth.common.mvc.AuthUserParam;
import com.zhulong.framework.common.dto.ResultDTO;
import com.zhulong.framework.common.page.Pagination;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 描述：汇率管理 BusApi接口
 * @author MOFAN889
 * @date 2019-04-01 16:21
 */
@RequestMapping("/currencyRateSettingBus")
@Api(value = "汇率管理-业务层接口",description = "汇率管理-业务层接口")
public interface CurrencyRateSettingBusApi {

    /**
     * 保存汇率管理
     * @param dto 保存的数据DTO
     * @param user 当前登录的用户
     * @return 保存后的对象信息
     */
    @PostMapping("save")
    @ApiOperation("保存汇率管理")
    ResultDTO<CurrencyRateSettingDTO> save(@RequestBody CurrencyRateSettingSaveDTO dto, @AuthUserParam AuthUser user);

    /**
     * 更新汇率管理
     * @param dto 更新的数据DTO
     * @param user 当前登录用户
     * @return 更新后的数据对象
     */
    @PostMapping("update")
    @ApiOperation("更新汇率管理")
    ResultDTO<CurrencyRateSettingDTO> update(@RequestBody CurrencyRateSettingUpdateDTO dto, @AuthUserParam AuthUser user);

    /**
     * 分页查询数据
     * @param queryDTO 查询参数对象
     * @param user 当前登录的用户
     * @return 获取到的分页信息
     */
    @PostMapping("findPage")
    @ApiOperation("查询分页数据")
    ResultDTO<Pagination<CurrencyRateSettingListDTO>> findPage(@RequestBody CurrencyRateSettingQueryDTO queryDTO, @AuthUserParam AuthUser user);

    /**
     *查询列表
     * @param queryDTO 查询的参数对象
     * @param user 当前登录的用户
     * @return 列表显示的数据
     */
    @PostMapping("findList")
    @ApiOperation("查询列表")
    ResultDTO<List<CurrencyRateSettingListDTO>> findList(@RequestBody CurrencyRateSettingQueryDTO queryDTO, @AuthUserParam AuthUser user);

    /**
     * 通过主键查询数据
     * @param guid 数据主键
     * @param user 当前登录的用户
     * @return 页面展示数据
     */
    @GetMapping("getByGuid")
    @ApiOperation("通过主键获取")
    ResultDTO<CurrencyRateSettingShowDTO> getByGuid(@RequestParam("guid") String guid, @AuthUserParam AuthUser user);

    /**
     * 根据Id删除
     * @param guid 要删除数据的主键
     * @return 无-无论是否删除到数据，都表示成功。
     */
    @PostMapping("deleteById")
    @ApiOperation("通过主键删除数据")
    ResultDTO<Void> deleteById(@RequestParam("guid") String guid);

}
