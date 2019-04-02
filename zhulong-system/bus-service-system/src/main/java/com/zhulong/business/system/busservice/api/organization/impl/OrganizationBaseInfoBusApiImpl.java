package com.zhulong.business.system.busservice.api.organization.impl;

import com.zhulong.business.system.baseservice.api.organization.OrganizationBaseInfoBaseApi;
import com.zhulong.business.system.baseservice.dto.organization.OrganizationBaseInfoDTO;
import com.zhulong.business.system.baseservice.dto.organization.OrganizationBaseInfoQueryDTO;
import com.zhulong.business.system.busservice.api.organization.OrganizationBaseInfoBusApi;
import com.zhulong.business.system.busservice.dto.organization.OrganizationBaseInfoListDTO;
import com.zhulong.business.system.busservice.dto.organization.OrganizationBaseInfoSaveDTO;
import com.zhulong.business.system.busservice.dto.organization.OrganizationBaseInfoShowDTO;
import com.zhulong.business.system.busservice.dto.organization.OrganizationBaseInfoUpdateDTO;
import com.zhulong.framework.auth.common.AuthUser;
import com.zhulong.framework.auth.common.mvc.AuthUserParam;
import com.zhulong.framework.common.annotion.Module;
import com.zhulong.framework.common.dto.KeyValueDTO;
import com.zhulong.framework.common.dto.ResultDTO;
import com.zhulong.framework.common.dto.TreeDTO;
import com.zhulong.framework.common.enums.UserTypeCode;
import com.zhulong.framework.common.page.Pagination;
import com.zhulong.framework.common.util.POJOConvertUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

/**
 * 描述：机构管理 BusApiImpl实现
 * @author shanb
 * @date 2019-03-27 10:14
 */
@RestController
@Module("ORGANIZATION")
public class OrganizationBaseInfoBusApiImpl implements OrganizationBaseInfoBusApi {

    @Autowired
    private OrganizationBaseInfoBaseApi organizationBaseInfoBaseApi;

    @Override
    public ResultDTO<OrganizationBaseInfoDTO> save(@RequestBody @Valid OrganizationBaseInfoSaveDTO dto, @AuthUserParam AuthUser user) {
        List<String> organizationUserCodeList = UserTypeCode.getOrganizationCodeList();
        if(!organizationUserCodeList.contains(dto.getTypeCode())){
            return ResultDTO.error("USERTYPE.NOT.SUPPORT");
        }
        //TODO:做进一步校验，社会信用号不能重复
        OrganizationBaseInfoDTO organizationBaseInfoDTO = POJOConvertUtil.convert(dto,OrganizationBaseInfoDTO.class);
        organizationBaseInfoDTO.setGuid(UUID.randomUUID().toString());
        organizationBaseInfoDTO.setCode(genaratorCode());
        organizationBaseInfoDTO.setCreateInfo(user);
        organizationBaseInfoDTO.setBusStatus(OrganizationBaseInfoDTO.BusStatusEnum.INIT.getCode());
        organizationBaseInfoBaseApi.save(organizationBaseInfoDTO);
        return ResultDTO.of(organizationBaseInfoDTO);
    }

    @Override
    public ResultDTO<OrganizationBaseInfoDTO> update(@RequestBody @Valid OrganizationBaseInfoUpdateDTO dto, @AuthUserParam AuthUser user) {
        OrganizationBaseInfoDTO organizationBaseInfoDTO = POJOConvertUtil.convert(dto,OrganizationBaseInfoDTO.class);
        organizationBaseInfoDTO.setModifyInfo(user);
        organizationBaseInfoDTO = organizationBaseInfoBaseApi.update(organizationBaseInfoDTO);
        return ResultDTO.of(organizationBaseInfoDTO);
    }

    @Override
    public ResultDTO<Pagination<OrganizationBaseInfoListDTO>> findPage(@RequestBody OrganizationBaseInfoQueryDTO queryDTO, @AuthUserParam AuthUser user) {
        Pagination<OrganizationBaseInfoDTO> organizationBaseInfoDTOPage = organizationBaseInfoBaseApi.findPage(queryDTO);

        List<OrganizationBaseInfoDTO> itemDTOList = organizationBaseInfoDTOPage.getList();
        List<OrganizationBaseInfoListDTO> listDTOList = getOgranizationBaseInfoListDTOs(itemDTOList);
        return ResultDTO.of(organizationBaseInfoDTOPage.of(listDTOList));
    }

    private List<OrganizationBaseInfoListDTO> getOgranizationBaseInfoListDTOs(List<OrganizationBaseInfoDTO> itemDTOList) {

        Set<String> parentGuidSet = itemDTOList.stream().filter(dto->!StringUtils.isEmpty(dto.getParentGuid()))
                .map(dto->dto.getParentGuid()).collect(Collectors.toSet());
        List<OrganizationBaseInfoDTO> parentDTOList = null;
        if(parentGuidSet!=null){
            parentDTOList = organizationBaseInfoBaseApi.findByGuidList(new ArrayList<>(parentGuidSet));
        }else{
            parentDTOList = Collections.emptyList();
        }

        List<OrganizationBaseInfoDTO> useParentDTOList = parentDTOList;
        return itemDTOList.stream().map(dto->{
            OrganizationBaseInfoListDTO listDTO = POJOConvertUtil.convert(dto,OrganizationBaseInfoListDTO.class);
            if(!StringUtils.isEmpty(listDTO.getParentGuid())){
                Optional<OrganizationBaseInfoDTO> parentDTO = useParentDTOList.stream().filter(pdto->pdto.getGuid().equals(listDTO.getParentGuid())).findFirst();
                if(parentDTO.isPresent()){
                    listDTO.setParentName(parentDTO.get().getName());
                }
            }
            return listDTO;
        }).collect(Collectors.toList());
    }

    @Override
    public ResultDTO<List<OrganizationBaseInfoListDTO>> findList(@RequestBody OrganizationBaseInfoQueryDTO queryDTO, @AuthUserParam AuthUser user) {
        List<OrganizationBaseInfoDTO> itemDTOList = organizationBaseInfoBaseApi.findList(queryDTO);
        List<OrganizationBaseInfoListDTO> listDTOList = getOgranizationBaseInfoListDTOs(itemDTOList);
        return ResultDTO.of(listDTOList);
    }

    @Override
    public ResultDTO<OrganizationBaseInfoShowDTO> getByGuid(@RequestParam("guid") String guid, @AuthUserParam AuthUser user) {
        OrganizationBaseInfoDTO organizationBaseInfoDTO = organizationBaseInfoBaseApi.getByGuid(guid);
        OrganizationBaseInfoShowDTO showDTO = POJOConvertUtil.convert(organizationBaseInfoDTO,OrganizationBaseInfoShowDTO.class);
        if(StringUtils.isEmpty(organizationBaseInfoDTO.getParentGuid())) {
            OrganizationBaseInfoDTO parentDTO = organizationBaseInfoBaseApi.getByGuid(organizationBaseInfoDTO.getParentGuid());
            if(parentDTO!=null){
                showDTO.setParentName(parentDTO.getName());
            }
        }
        return ResultDTO.of(showDTO);
    }

    @Override
    public ResultDTO<Void> deleteById(@RequestParam("guid") String guid) {
        OrganizationBaseInfoDTO dto = organizationBaseInfoBaseApi.getByGuid(guid);
        if(!OrganizationBaseInfoDTO.BusStatusEnum.INIT.equals(dto.getBusStatus())){
            return ResultDTO.error("RECORD.CANNOT.DELETE");
        }
        organizationBaseInfoBaseApi.deleteById(guid);
        return ResultDTO.of(null);
    }

    @Override
    public ResultDTO<Void> updateSortNum(@RequestBody List<KeyValueDTO<String,BigDecimal>> guidSortNumList) {
        organizationBaseInfoBaseApi.updateSortNum(guidSortNumList);
        return ResultDTO.of(null);
    }

    @Override
    public ResultDTO<List<TreeDTO>> getTreeListSyn(@RequestParam("parentGuid") String parentGuid) {
        OrganizationBaseInfoQueryDTO queryDTO = new OrganizationBaseInfoQueryDTO();
        List<OrganizationBaseInfoDTO> dtoList = organizationBaseInfoBaseApi.findList(queryDTO);
        List<TreeDTO> treeDTOList = buildTreeDTO(dtoList,null);
        return ResultDTO.of(treeDTOList);
    }

    private List<TreeDTO> buildTreeDTO(List<OrganizationBaseInfoDTO> organizationDTOList,String parentGuid){
        List<TreeDTO> treeDTOList = new ArrayList<>();
        if(organizationDTOList!=null && !organizationDTOList.isEmpty()){
            for (OrganizationBaseInfoDTO dto : organizationDTOList) {
                if((parentGuid==null && StringUtils.isEmpty(dto.getParentGuid())) || (parentGuid!=null && parentGuid.equals(dto.getParentGuid()))){
                    TreeDTO treeDTO = new TreeDTO(dto.getGuid(),dto.getName());
                    treeDTO.setChildren(buildTreeDTO(organizationDTOList,dto.getGuid()));
                    treeDTOList.add(treeDTO);
                }
            }
        }
        return treeDTOList;
    }

    //TODO:修改按照编码规则进行
    private String genaratorCode(){
        StringBuilder sb = new StringBuilder();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
        sb.append(sdf.format(new Date()));
        Random r = new Random();
        Integer sequence = r.nextInt(9999);
        return sb.append(String.format("%04d",sequence)).toString();
    }

}

