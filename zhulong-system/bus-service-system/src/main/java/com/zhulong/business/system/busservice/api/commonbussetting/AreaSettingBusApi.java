package com.zhulong.business.system.busservice.api.commonbussetting;

import com.zhulong.business.system.baseservice.dto.commonbussetting.AreaSettingDTO;
import com.zhulong.business.system.baseservice.dto.commonbussetting.AreaSettingQueryDTO;
import com.zhulong.business.system.busservice.dto.commonbussetting.AreaSettingListDTO;
import com.zhulong.business.system.busservice.dto.commonbussetting.AreaSettingSaveDTO;
import com.zhulong.business.system.busservice.dto.commonbussetting.AreaSettingShowDTO;
import com.zhulong.business.system.busservice.dto.commonbussetting.AreaSettingUpdateDTO;
import com.zhulong.framework.auth.common.AuthUser;
import com.zhulong.framework.auth.common.mvc.AuthUserParam;
import com.zhulong.framework.common.dto.KeyValueDTO;
import com.zhulong.framework.common.dto.ResultDTO;
import com.zhulong.framework.common.dto.TreeDTO;
import com.zhulong.framework.common.page.Pagination;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

/**
 * 描述：行政区域 BusApi接口
 *
 * @author 初。
 * @date 2019-03-28 09:23
 */
@RequestMapping("/areaSettingBus")
@Api(value = "行政区域 - 业务层接口", description = "行政区域 - 业务层接口")
public interface AreaSettingBusApi {

    /**
     * 保存行政区域
     *
     * @param dto  保存的数据DTO
     * @param user 当前登录的用户
     * @return 保存后的对象信息
     */
    @PostMapping("save")
    @ApiOperation("保存行政区域")
    ResultDTO<AreaSettingDTO> save(@RequestBody AreaSettingSaveDTO dto, @AuthUserParam AuthUser user);

    /**
     * 更新行政区域
     *
     * @param dto  更新的数据DTO
     * @param user 当前登录用户
     * @return 更新后的数据对象
     */
    @PostMapping("update")
    @ApiOperation("更新行政区域")
    ResultDTO<AreaSettingDTO> update(@RequestBody AreaSettingUpdateDTO dto, @AuthUserParam AuthUser user);

    /**
     * 分页查询数据
     *
     * @param queryDTO 查询参数对象
     * @param user     当前登录的用户
     * @return 获取到的分页信息
     */
    @PostMapping("findPage")
    @ApiOperation("查询分页数据")
    ResultDTO<Pagination<AreaSettingListDTO>> findPage(@RequestBody AreaSettingQueryDTO queryDTO, @AuthUserParam AuthUser user);

    /**
     * 通过主键查询数据
     *
     * @param guid 数据主键
     * @param user 当前登录的用户
     * @return 页面展示数据
     */
    @GetMapping("getByGuid")
    @ApiOperation("通过主键获取")
    ResultDTO<AreaSettingShowDTO> getByGuid(@RequestParam("guid") String guid, @AuthUserParam AuthUser user);

    /**
     * 根据Id删除
     *
     * @param guid 要删除数据的主键
     * @return 无-无论是否删除到数据，都表示成功。
     */
    @PostMapping("deleteById")
    @ApiOperation("通过主键删除数据")
    ResultDTO<Void> deleteById(@RequestParam("guid") String guid);

    /**
     * 更新序号
     *
     * @param keyValueDTOList
     * @param user
     * @return
     */
    @PostMapping("updateSortNum")
    @ApiOperation("更新序号")
    ResultDTO<Void> updateSortNum(@RequestBody List<KeyValueDTO<String, BigDecimal>> keyValueDTOList, @AuthUserParam AuthUser user);

    /**
     * 获取树形结构
     *
     * @return
     */
    @PostMapping("getTree")
    @ApiOperation("获取树形结构")
    ResultDTO<List<TreeDTO>> getTree();

}
