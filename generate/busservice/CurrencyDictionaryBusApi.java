package com.zhulong.business.system.baseservice.entity.commonbussetting.busservice.api.commonbussetting;

import com.zhulong.business.system.baseservice.entity.commonbussetting.baseservice.dto.commonbussetting.CurrencyDictionaryDTO;
import com.zhulong.business.system.baseservice.entity.commonbussetting.baseservice.dto.commonbussetting.CurrencyDictionaryQueryDTO;
import com.zhulong.business.system.baseservice.entity.commonbussetting.busservice.dto.commonbussetting.CurrencyDictionaryListDTO;
import com.zhulong.business.system.baseservice.entity.commonbussetting.busservice.dto.commonbussetting.CurrencyDictionarySaveDTO;
import com.zhulong.business.system.baseservice.entity.commonbussetting.busservice.dto.commonbussetting.CurrencyDictionaryShowDTO;
import com.zhulong.business.system.baseservice.entity.commonbussetting.busservice.dto.commonbussetting.CurrencyDictionaryUpdateDTO;
import com.zhulong.framework.auth.common.AuthUser;
import com.zhulong.framework.auth.common.mvc.AuthUserParam;
import com.zhulong.framework.common.dto.ResultDTO;
import com.zhulong.framework.common.page.Pagination;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 描述：币种管理 BusApi接口
 * @author MOFAN889
 * @date 2019-04-01 11:37
 */
@RequestMapping("/currencyDictionaryBus")
@Api(value = "币种管理-业务层接口",description = "币种管理-业务层接口")
public interface CurrencyDictionaryBusApi {

    /**
     * 保存币种管理
     * @param dto 保存的数据DTO
     * @param user 当前登录的用户
     * @return 保存后的对象信息
     */
    @PostMapping("save")
    @ApiOperation("保存币种管理")
    ResultDTO<CurrencyDictionaryDTO> save(@RequestBody CurrencyDictionarySaveDTO dto, @AuthUserParam AuthUser user);

    /**
     * 更新币种管理
     * @param dto 更新的数据DTO
     * @param user 当前登录用户
     * @return 更新后的数据对象
     */
    @PostMapping("update")
    @ApiOperation("更新币种管理")
    ResultDTO<CurrencyDictionaryDTO> update(@RequestBody CurrencyDictionaryUpdateDTO dto, @AuthUserParam AuthUser user);

    /**
     * 分页查询数据
     * @param queryDTO 查询参数对象
     * @param user 当前登录的用户
     * @return 获取到的分页信息
     */
    @PostMapping("findPage")
    @ApiOperation("查询分页数据")
    ResultDTO<Pagination<CurrencyDictionaryListDTO>> findPage(@RequestBody CurrencyDictionaryQueryDTO queryDTO,@AuthUserParam AuthUser user);

    /**
     *查询列表
     * @param queryDTO 查询的参数对象
     * @param user 当前登录的用户
     * @return 列表显示的数据
     */
    @PostMapping("findList")
    @ApiOperation("查询列表")
    ResultDTO<List<CurrencyDictionaryListDTO>> findList(@RequestBody CurrencyDictionaryQueryDTO queryDTO, @AuthUserParam AuthUser user);

    /**
     * 通过主键查询数据
     * @param guid 数据主键
     * @param user 当前登录的用户
     * @return 页面展示数据
     */
    @GetMapping("getByGuid")
    @ApiOperation("通过主键获取")
    ResultDTO<CurrencyDictionaryShowDTO> getByGuid(@RequestParam("guid") String guid, @AuthUserParam AuthUser user);

    /**
     * 根据Id删除
     * @param guid 要删除数据的主键
     * @return 无-无论是否删除到数据，都表示成功。
     */
    @PostMapping("deleteById")
    @ApiOperation("通过主键删除数据")
    ResultDTO<Void> deleteById(@RequestParam("guid") String guid);

}
