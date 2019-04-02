package com.zhulong.business.system.busservice.api.organization;

import com.zhulong.business.system.baseservice.dto.organization.OrganizationBaseInfoDTO;
import com.zhulong.business.system.baseservice.dto.organization.OrganizationBaseInfoQueryDTO;
import com.zhulong.business.system.busservice.dto.organization.OrganizationBaseInfoListDTO;
import com.zhulong.business.system.busservice.dto.organization.OrganizationBaseInfoSaveDTO;
import com.zhulong.business.system.busservice.dto.organization.OrganizationBaseInfoShowDTO;
import com.zhulong.business.system.busservice.dto.organization.OrganizationBaseInfoUpdateDTO;
import com.zhulong.framework.auth.common.AuthUser;
import com.zhulong.framework.auth.common.mvc.AuthUserParam;
import com.zhulong.framework.common.dto.KeyValueDTO;
import com.zhulong.framework.common.dto.ResultDTO;
import com.zhulong.framework.common.dto.TreeDTO;
import com.zhulong.framework.common.page.Pagination;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

/**
 * 描述：机构管理 BusApi接口
 * @author shanb
 * @date 2019-03-27 10:14
 */
@RequestMapping("/OgranizationBaseInfoBus")
@Api(value = "机构管理-业务层接口",description = "机构管理-业务层接口")
public interface OrganizationBaseInfoBusApi {

    /**
     * 保存机构管理
     * @param dto 保存的数据DTO
     * @param user 当前登录的用户
     * @return 保存后的对象信息
     */
    @PostMapping("save")
    @ApiOperation("保存机构管理")
    ResultDTO<OrganizationBaseInfoDTO> save(@RequestBody OrganizationBaseInfoSaveDTO dto, @AuthUserParam AuthUser user);

    /**
     * 更新机构管理
     * @param dto 更新的数据DTO
     * @param user 当前登录用户
     * @return 更新后的数据对象
     */
    @PostMapping("update")
    @ApiOperation("更新机构管理")
    ResultDTO<OrganizationBaseInfoDTO> update(@RequestBody OrganizationBaseInfoUpdateDTO dto, @AuthUserParam AuthUser user);

    /**
     * 分页查询数据
     * @param queryDTO 查询参数对象
     * @param user 当前登录的用户
     * @return 获取到的分页信息
     */
    @PostMapping("findPage")
    @ApiOperation("查询分页数据")
    ResultDTO<Pagination<OrganizationBaseInfoListDTO>> findPage(@RequestBody OrganizationBaseInfoQueryDTO queryDTO, @AuthUserParam AuthUser user);

    /**
     *查询列表
     * @param queryDTO 查询的参数对象
     * @param user 当前登录的用户
     * @return 列表显示的数据
     */
    @PostMapping("findList")
    @ApiOperation("查询列表")
    ResultDTO<List<OrganizationBaseInfoListDTO>> findList(@RequestBody OrganizationBaseInfoQueryDTO queryDTO, @AuthUserParam AuthUser user);

    /**
     * 通过主键查询数据
     * @param guid 数据主键
     * @param user 当前登录的用户
     * @return 页面展示数据
     */
    @GetMapping("getByGuid")
    @ApiOperation("通过主键获取")
    ResultDTO<OrganizationBaseInfoShowDTO> getByGuid(@RequestParam("guid") String guid, @AuthUserParam AuthUser user);

    /**
     * 根据Id删除
     * @param guid 要删除数据的主键
     * @return 无-无论是否删除到数据，都表示成功。
     */
    @PostMapping("deleteById")
    @ApiOperation("通过主键删除数据")
    ResultDTO<Void> deleteById(@RequestParam("guid") String guid);

    /**
     * 批量修改排序号
     * @param guidSortNumList 批量排序号的列表
     */
    @PostMapping("updateSortNum")
    @ApiOperation("批量修改序号")
    ResultDTO<Void> updateSortNum(@RequestBody List<KeyValueDTO<String,BigDecimal>> guidSortNumList);

    /**
     * 获取右侧属性结构,同步树。系统中机构的用户，不会很多，上下级关系的就更少，采用同步树，避免过多的查询。
     * @param parentGuid 上级树，若为根节点，传空值。
     * @return 下级数的信息
     */
    @GetMapping("getTreeList")
    @ApiOperation("获取属性")
    ResultDTO<List<TreeDTO>> getTreeListSyn(@RequestParam("parentGuid") String parentGuid);

}
