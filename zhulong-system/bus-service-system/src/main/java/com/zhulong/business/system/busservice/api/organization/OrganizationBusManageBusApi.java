package com.zhulong.business.system.busservice.api.organization;

import com.zhulong.business.system.baseservice.dto.organization.OrganizationBaseInfoQueryDTO;
import com.zhulong.business.system.baseservice.dto.organization.OrganizationUpdateBusStatusDTO;
import com.zhulong.business.system.busservice.dto.organization.OrganizationBaseInfoListDTO;
import com.zhulong.business.system.busservice.dto.organization.OrganizationBaseInfoShowDTO;
import com.zhulong.framework.auth.common.AuthUser;
import com.zhulong.framework.auth.common.mvc.AuthUserParam;
import com.zhulong.framework.common.dto.ResultDTO;
import com.zhulong.framework.common.page.Pagination;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 描述：机构业务管理 BusApi接口
 * @author shanb
 * @date 2019-03-27 16:11
 */
@RequestMapping("/organizationBusManageBus")
@Api("机构业务管理")
public interface OrganizationBusManageBusApi {

    /**
     * 查询运营机构列表，应为数量少，不带分页信息
     * @param queryDTO 查询条件
     */
    @ApiOperation("查询运营机构列表")
    @PostMapping("findManageOrgList")
    ResultDTO<List<OrganizationBaseInfoListDTO>> findManageOrgList(OrganizationBaseInfoQueryDTO queryDTO, @AuthUserParam AuthUser authUser);

    /**
     * 通过主键查询运营机构
     * @param guid 主键
     */
    @ApiOperation("通过主键查询运营机构")
    @GetMapping("findManageOrgByGuid")
    ResultDTO<OrganizationBaseInfoShowDTO> getManageOrgByGuid(@RequestParam("guid") String guid,@AuthUserParam AuthUser authUser);

    /**
     *  更新运营机构业务状态
     *  @param
     */
    @ApiOperation("更新运营机构业务状态")
    @PostMapping("updateManageBusStatus")
    ResultDTO<Void> updateManageBusStatus(@RequestParam("guid") String guid,@RequestParam("busStatus")Short busStatus,@RequestParam("remark")String remark, @AuthUserParam AuthUser authUser);


    /**
     * 查询运营机构列表，应为数量少，不带分页信息
     * @param queryDTO 查询条件
     */
    @ApiOperation("查询运营机构列表")
    @PostMapping("findTradeOrgList")
    ResultDTO<Pagination<OrganizationBaseInfoListDTO>> findTradeOrgList(OrganizationBaseInfoQueryDTO queryDTO,@AuthUserParam AuthUser authUser);

    /**
     * 通过主键查询交易机构
     * @param guid 主键
     */
    @ApiOperation("通过主键查询交易机构")
    @GetMapping("findTradeOrgByGuid")
    ResultDTO<OrganizationBaseInfoShowDTO> getTradeOrgByGuid(@RequestParam("guid") String guid,@AuthUserParam AuthUser authUser);

    /**
     *  更新交易机构业务状态
     *  @param
     */
    @ApiOperation("更新交易机构业务状态")
    @PostMapping("updateTradeBusStatus")
    ResultDTO<Void> updateTradeBusStatus(@RequestParam("guid") String guid,@RequestParam("busStatus")Short busStatus,@RequestParam("remark")String remark,@AuthUserParam AuthUser authUser);

}
