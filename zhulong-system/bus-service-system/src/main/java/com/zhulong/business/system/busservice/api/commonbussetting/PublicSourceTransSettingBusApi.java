package com.zhulong.business.system.busservice.api.commonbussetting;

import com.zhulong.business.system.baseservice.dto.commonbussetting.PublicSourceTransSettingDTO;
import com.zhulong.business.system.baseservice.dto.commonbussetting.PublicSourceTransSettingQueryDTO;
import com.zhulong.business.system.busservice.dto.commonbussetting.PublicSourceTransSettingListDTO;
import com.zhulong.business.system.busservice.dto.commonbussetting.PublicSourceTransSettingSaveDTO;
import com.zhulong.business.system.busservice.dto.commonbussetting.PublicSourceTransSettingShowDTO;
import com.zhulong.business.system.busservice.dto.commonbussetting.PublicSourceTransSettingUpdateDTO;
import com.zhulong.framework.auth.common.AuthUser;
import com.zhulong.framework.auth.common.mvc.AuthUserParam;
import com.zhulong.framework.common.dto.ResultDTO;
import com.zhulong.framework.common.dto.TreeDTO;
import com.zhulong.framework.common.page.Pagination;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 描述：公共资源分类 BusApi接口
 * @author 初。
 * @date 2019-03-28 09:21
 */
@RequestMapping("/publicSourceTransSettingBus")
@Api(value = "公共资源分类 - 业务层接口", description = "公共资源分类 - 业务层接口")
public interface PublicSourceTransSettingBusApi {

    /**
     * 保存公共资源分类
     * @param dto 保存的数据DTO
     * @param user 当前登录的用户
     * @return 保存后的对象信息
     */
    @PostMapping("save")
    @ApiOperation("保存公共资源分类")
    ResultDTO<PublicSourceTransSettingDTO> save(@RequestBody PublicSourceTransSettingSaveDTO dto, @AuthUserParam AuthUser user);

    /**
     * 更新公共资源分类
     * @param dto 更新的数据DTO
     * @param user 当前登录用户
     * @return 更新后的数据对象
     */
    @PostMapping("update")
    @ApiOperation("更新公共资源分类")
    ResultDTO<PublicSourceTransSettingDTO> update(@RequestBody PublicSourceTransSettingUpdateDTO dto, @AuthUserParam AuthUser user);

    /**
     * 分页查询数据
     * @param queryDTO 查询参数对象
     * @param user 当前登录的用户
     * @return 获取到的分页信息
     */
    @PostMapping("findPage")
    @ApiOperation("查询分页数据")
    ResultDTO<Pagination<PublicSourceTransSettingListDTO>> findPage(@RequestBody PublicSourceTransSettingQueryDTO queryDTO, @AuthUserParam AuthUser user);

    /**
     * 通过主键查询数据
     * @param guid 数据主键
     * @param user 当前登录的用户
     * @return 页面展示数据
     */
    @GetMapping("getByGuid")
    @ApiOperation("通过主键获取")
    ResultDTO<PublicSourceTransSettingShowDTO> getByGuid(@RequestParam("guid") String guid, @AuthUserParam AuthUser user);

    /**
     * 根据Id删除
     * @param guid 要删除数据的主键
     * @return 无-无论是否删除到数据，都表示成功。
     */
    @PostMapping("deleteById")
    @ApiOperation("通过主键删除数据")
    ResultDTO<Void> deleteById(@RequestParam("guid") String guid);

    /**
     * 获取树形结构
     *
     * @return
     */
    @PostMapping("getTree")
    @ApiOperation("获取树形结构")
    ResultDTO<List<TreeDTO>> getTree();

}
