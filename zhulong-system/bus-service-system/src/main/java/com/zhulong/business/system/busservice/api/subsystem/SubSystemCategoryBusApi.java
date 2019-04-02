package com.zhulong.business.system.busservice.api.subsystem;

import com.zhulong.business.system.busservice.dto.subsystem.SubSystemCategorySaveDTO;
import com.zhulong.framework.auth.common.AuthUser;
import com.zhulong.framework.auth.common.mvc.AuthUserParam;
import com.zhulong.framework.common.dto.KeyValueDTO;
import com.zhulong.framework.common.dto.ResultDTO;
import com.zhulong.framework.common.page.Pagination;
import com.zhulong.business.system.baseservice.dto.subsystem.SubSystemCategoryDTO;
import com.zhulong.business.system.baseservice.dto.subsystem.SubSystemCategoryQueryDTO;
import com.zhulong.business.system.busservice.dto.subsystem.SubSystemCategoryUpdateDTO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.math.BigDecimal;
import java.util.List;

/**
 * 子系统分类信息-业务层接口
 * Created by shanb on 2019-2-25.
 */
@RequestMapping("subSystemCategoryBus")
@Api(value = "子系统分类管理",description = "子系统分类管理")
public interface SubSystemCategoryBusApi {

    /**
     * 保存信息
     * @param dto 保存的信息
     * @return 返回保存后的数据
     */
    @PostMapping("save")
    @ApiOperation(value = "保存数据")
    ResultDTO<SubSystemCategoryDTO> save(@RequestBody @Valid SubSystemCategorySaveDTO dto, @AuthUserParam AuthUser authUser);

    /**
     * 更新信息
     * @param dto 更新子系统分类信息
     * @return 返回保存后的信息
     */
    @PostMapping("update")
    @ApiOperation(value = "更新数据")
    ResultDTO<SubSystemCategoryDTO> update(@RequestBody @Valid SubSystemCategoryUpdateDTO dto, @AuthUserParam AuthUser authUser);

    /**
     * 删除信息
     * @param guid 主键
     * @return 返回是否更新到数据
     */
    @PostMapping("deleteByGuid")
    @ApiOperation(value = "通过主键删除数据")
    ResultDTO<Boolean> deleteByGuid(@RequestParam("guid") String guid);

    /**
     * 根据主键获取guid
     * @param guid 主键
     * @param authUser 当前登录用户信息
     * @return 子系统分类信息
     */
    @GetMapping("getByGuid")
    @ApiOperation("通过主键获取数据")
    ResultDTO<SubSystemCategoryDTO> getByGuid(String guid, @AuthUserParam AuthUser authUser);

    /**
     * 查询分页信息
     * @param queryDTO 查询信息
     * @param authUser 当前登录用户信息
     * @return 子系统分类的分页信息
     */
    @PostMapping("findPage")
    @ApiOperation("分页查询数据")
    ResultDTO<Pagination<SubSystemCategoryDTO>> findPage(@RequestBody SubSystemCategoryQueryDTO queryDTO, @AuthUserParam AuthUser authUser);

    /**
     * 更新排序信息
     * @param sortNumList 要更新的主键及对应的排序号
     * @return 无。未出异常，则表示更新成功。
     */
    @PostMapping
    @ApiOperation("批量更新排序号")
    ResultDTO<Void> updateSortNums(@RequestBody List<KeyValueDTO<String,BigDecimal>> sortNumList, @AuthUserParam AuthUser authUser);
}
