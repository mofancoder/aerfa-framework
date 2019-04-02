package com.zhulong.business.system.busservice.api.organization.impl;

import com.zhulong.business.system.baseservice.api.organization.OrganizationBaseInfoBaseApi;
import com.zhulong.business.system.baseservice.dto.common.DataModifyRecordDTO;
import com.zhulong.business.system.baseservice.dto.organization.OrganizationBaseInfoDTO;
import com.zhulong.business.system.baseservice.dto.organization.OrganizationBaseInfoQueryDTO;
import com.zhulong.business.system.baseservice.dto.organization.OrganizationUpdateBusStatusDTO;
import com.zhulong.business.system.busservice.api.organization.OrganizationBusManageBusApi;
import com.zhulong.business.system.busservice.dto.organization.OrganizationBaseInfoListDTO;
import com.zhulong.business.system.busservice.dto.organization.OrganizationBaseInfoShowDTO;
import com.zhulong.framework.auth.common.AuthUser;
import com.zhulong.framework.auth.common.mvc.AuthUserParam;
import com.zhulong.framework.common.annotion.Module;
import com.zhulong.framework.common.dto.ResultDTO;
import com.zhulong.framework.common.enums.UserTypeCode;
import com.zhulong.framework.common.page.Pagination;
import com.zhulong.framework.common.util.POJOConvertUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

/**
 * 描述：机构业务管理 BusApi接口
 *
 * @author shanb
 * @date 2019-03-27 16:11
 */
@RestController
@Module("ORGANIZATION")
public class OrganizationBusManageBusApiImpl implements OrganizationBusManageBusApi {

    @Autowired
    private OrganizationBaseInfoBaseApi organizationBaseInfoBaseApi;

    @Override
    public ResultDTO<List<OrganizationBaseInfoListDTO>> findManageOrgList(OrganizationBaseInfoQueryDTO queryDTO, @AuthUserParam AuthUser authUser) {
        queryDTO.setTypeCode(UserTypeCode.MANAGE_ORGANIZATION.getCode());
        List<OrganizationBaseInfoDTO> dtoList = organizationBaseInfoBaseApi.findList(queryDTO);
        return ResultDTO.of(POJOConvertUtil.convertList(dtoList, OrganizationBaseInfoListDTO.class));
    }

    @Override
    public ResultDTO<OrganizationBaseInfoShowDTO> getManageOrgByGuid(@RequestParam("guid") String guid, @AuthUserParam AuthUser authUser) {
        OrganizationBaseInfoDTO dto = organizationBaseInfoBaseApi.getByGuid(guid);
        if (dto != null && UserTypeCode.MANAGE_ORGANIZATION.getCode().equals(dto.getTypeCode())) {
            OrganizationBaseInfoShowDTO showDTO = POJOConvertUtil.convert(dto, OrganizationBaseInfoShowDTO.class);
            if (!StringUtils.isEmpty(dto.getParentGuid())) {
                OrganizationBaseInfoDTO pdto = organizationBaseInfoBaseApi.getByGuid(dto.getParentGuid());
                showDTO.setParentName(pdto.getName());
            }
            return ResultDTO.of(showDTO);
        } else {
            return ResultDTO.of(null);
        }
    }

    @Override
    public ResultDTO<Void> updateManageBusStatus(@RequestParam("guid") String guid, @RequestParam("busStatus") Short busStatus, @RequestParam("remark") String remark, @AuthUserParam AuthUser authUser) {
        //查询数据,看是否为运营机构
        OrganizationBaseInfoDTO infoDTO = organizationBaseInfoBaseApi.getByGuid(guid);
        if (infoDTO.getGuid() != null && UserTypeCode.MANAGE_ORGANIZATION.getCode().equals(infoDTO.getTypeCode())) {
            updateBusStatusDTO(guid, busStatus, remark, infoDTO);
        }
        return ResultDTO.of(null);
    }

    @Override
    public ResultDTO<Pagination<OrganizationBaseInfoListDTO>> findTradeOrgList(OrganizationBaseInfoQueryDTO queryDTO, @AuthUserParam AuthUser authUser) {
        queryDTO.setTypeCode(UserTypeCode.TRADE_ORGANIZATION.getCode());
        Pagination<OrganizationBaseInfoDTO> dtoPage = organizationBaseInfoBaseApi.findPage(queryDTO);
        Pagination<OrganizationBaseInfoListDTO> resultPage = dtoPage.of(POJOConvertUtil.convertList(dtoPage.getList(), OrganizationBaseInfoListDTO.class));
        return ResultDTO.of(resultPage);
    }

    @Override
    public ResultDTO<OrganizationBaseInfoShowDTO> getTradeOrgByGuid(@RequestParam("guid") String guid, @AuthUserParam AuthUser authUser) {
        OrganizationBaseInfoDTO dto = organizationBaseInfoBaseApi.getByGuid(guid);
        if (dto != null && UserTypeCode.TRADE_ORGANIZATION.getCode().equals(dto.getTypeCode())) {
            OrganizationBaseInfoShowDTO showDTO = POJOConvertUtil.convert(dto, OrganizationBaseInfoShowDTO.class);
            if (!StringUtils.isEmpty(dto.getParentGuid())) {
                OrganizationBaseInfoDTO pdto = organizationBaseInfoBaseApi.getByGuid(dto.getParentGuid());
                showDTO.setParentName(pdto.getName());
            }
            return ResultDTO.of(showDTO);
        } else {
            return ResultDTO.of(null);
        }
    }

    @Override
    public ResultDTO<Void> updateTradeBusStatus(@RequestParam("guid") String guid, @RequestParam("busStatus") Short busStatus, @RequestParam("remark") String remark, @AuthUserParam AuthUser authUser) {
        //查询数据,看是否为交易机构
        OrganizationBaseInfoDTO infoDTO = organizationBaseInfoBaseApi.getByGuid(guid);
        if (infoDTO.getGuid() != null && UserTypeCode.TRADE_ORGANIZATION.getCode().equals(infoDTO.getTypeCode())) {
            updateBusStatusDTO(guid, busStatus, remark, infoDTO);
        }
        return ResultDTO.of(null);
    }

    private void updateBusStatusDTO(@RequestParam("guid") String guid, @RequestParam("busStatus") Short busStatus, @RequestParam("remark") String remark, OrganizationBaseInfoDTO infoDTO) {
        OrganizationUpdateBusStatusDTO updateDTO = new OrganizationUpdateBusStatusDTO();
        updateDTO.setGuid(guid);
        updateDTO.setBusStatus(busStatus);

        DataModifyRecordDTO recordDTO = new DataModifyRecordDTO();
        recordDTO.setGuid(UUID.randomUUID().toString());
        recordDTO.setDataGuid(guid);
        recordDTO.setDataType(DataModifyRecordDTO.DataTypeEnum.ORG_MODIFY_BUS_STATUS.getCode());
        recordDTO.setOldValue(infoDTO.getBusStatus().toString());
        recordDTO.setNewValue(busStatus.toString());
        recordDTO.setRemark(remark);
        updateDTO.setModifyRecordDTO(recordDTO);
        organizationBaseInfoBaseApi.updateBusStatus(updateDTO);
    }
}