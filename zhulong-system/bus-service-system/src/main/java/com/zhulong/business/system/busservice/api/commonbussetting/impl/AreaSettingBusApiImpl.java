package com.zhulong.business.system.busservice.api.commonbussetting.impl;

import com.zhulong.business.system.baseservice.api.commonbussetting.AreaSettingBaseApi;
import com.zhulong.business.system.baseservice.dto.commonbussetting.AreaSettingDTO;
import com.zhulong.business.system.baseservice.dto.commonbussetting.AreaSettingQueryDTO;
import com.zhulong.business.system.busservice.api.commonbussetting.AreaSettingBusApi;
import com.zhulong.business.system.busservice.dto.commonbussetting.AreaSettingListDTO;
import com.zhulong.business.system.busservice.dto.commonbussetting.AreaSettingSaveDTO;
import com.zhulong.business.system.busservice.dto.commonbussetting.AreaSettingShowDTO;
import com.zhulong.business.system.busservice.dto.commonbussetting.AreaSettingUpdateDTO;
import com.zhulong.framework.auth.common.AuthUser;
import com.zhulong.framework.auth.common.mvc.AuthUserParam;
import com.zhulong.framework.common.annotion.Module;
import com.zhulong.framework.common.dto.KeyValueDTO;
import com.zhulong.framework.common.dto.ResultDTO;
import com.zhulong.framework.common.dto.TreeDTO;
import com.zhulong.framework.common.page.Pagination;
import com.zhulong.framework.common.util.POJOConvertUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * 描述：行政区域 BusApiImpl实现
 * @author 初。
 * @date 2019-03-28 09:23
 */
@RestController
@Module("COMMONBUSSETTING")
public class AreaSettingBusApiImpl implements AreaSettingBusApi {

    @Autowired
    private  AreaSettingBaseApi areaSettingBaseApi;

    @Override
    public ResultDTO<AreaSettingDTO> save(@Valid @RequestBody AreaSettingSaveDTO dto, @AuthUserParam AuthUser user) {

        AreaSettingDTO areaSettingDTO = POJOConvertUtil.convert(dto,AreaSettingDTO.class);
        areaSettingDTO.setGuid(UUID.randomUUID().toString());
        areaSettingDTO.setCreateInfo(user);
        areaSettingBaseApi.save(areaSettingDTO);

        return ResultDTO.of(areaSettingDTO);
    }

    @Override
    public ResultDTO<AreaSettingDTO> update(@Valid @RequestBody AreaSettingUpdateDTO dto, @AuthUserParam AuthUser user) {

        AreaSettingDTO areaSettingDTO = POJOConvertUtil.convert(dto,AreaSettingDTO.class);
        areaSettingDTO.setModifyInfo(user);
        areaSettingDTO = areaSettingBaseApi.update(areaSettingDTO);

        return ResultDTO.of(areaSettingDTO);
    }

    @Override
    public ResultDTO<Pagination<AreaSettingListDTO>> findPage(@RequestBody AreaSettingQueryDTO queryDTO, @AuthUserParam AuthUser user) {

        Pagination<AreaSettingDTO> areaSettingDTOPage = areaSettingBaseApi.findPage(queryDTO);
        List<AreaSettingDTO> itemDTOList = areaSettingDTOPage.getList();
        List<AreaSettingListDTO> listDTOList = getAreaSettingListDTOs(itemDTOList);

        return ResultDTO.of(areaSettingDTOPage.of(listDTOList));
    }

    private List<AreaSettingListDTO> getAreaSettingListDTOs(List<AreaSettingDTO> itemDTOList) {
        return POJOConvertUtil.convertList(itemDTOList, AreaSettingListDTO.class);
    }

    @Override
    public ResultDTO<AreaSettingShowDTO> getByGuid(@RequestParam("guid") String guid, @AuthUserParam AuthUser user) {

        AreaSettingDTO areaSettingDTO = areaSettingBaseApi.getByGuid(guid);
        AreaSettingShowDTO showDTO = POJOConvertUtil.convert(areaSettingDTO, AreaSettingShowDTO.class);

        return ResultDTO.of(showDTO);
    }

    @Override
    public ResultDTO<Void> deleteById(@RequestParam("guid") String guid) {
        areaSettingBaseApi.deleteById(guid);
        return ResultDTO.of(null);
    }

    @Override
    public ResultDTO<Void> updateSortNum(@RequestBody List<KeyValueDTO<String, BigDecimal>> keyValueDTOList, @AuthUserParam AuthUser user) {
        areaSettingBaseApi.updateSortNum(keyValueDTOList);
        return ResultDTO.of(null);
    }

    @Override
    public ResultDTO<List<TreeDTO>> getTree() {

        List<AreaSettingDTO> areaSettingDTOList = areaSettingBaseApi.findList(new AreaSettingQueryDTO());
        //进行数据组装
        List<TreeDTO> treeDTOList = buildTreeDTO(areaSettingDTOList, null);

        return ResultDTO.of(treeDTOList);
    }

    private List<TreeDTO> buildTreeDTO(List<AreaSettingDTO> areaSettingDTOList, String parentGuid) {
        List<TreeDTO> treeDTOList = new ArrayList<>();
        if (areaSettingDTOList != null && !areaSettingDTOList.isEmpty()) {
            for (AreaSettingDTO areaSettingDTO : areaSettingDTOList) {
                if ((parentGuid == null && StringUtils.isEmpty(areaSettingDTO.getParentGuid())) || (parentGuid != null && parentGuid.equals(areaSettingDTO.getParentGuid()))) {
                    TreeDTO treeDTO = new TreeDTO(areaSettingDTO.getGuid(), areaSettingDTO.getFullNameZh());
                    treeDTO.setChildren(buildTreeDTO(areaSettingDTOList, areaSettingDTO.getGuid()));
                    treeDTOList.add(treeDTO);
                }
            }
        }

        return treeDTOList;
    }

}

