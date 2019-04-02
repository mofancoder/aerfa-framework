package com.zhulong.business.system.busservice.api.commonbussetting.impl;

import com.zhulong.business.system.baseservice.api.commonbussetting.IndustryTypeSettingBaseApi;
import com.zhulong.business.system.baseservice.dto.commonbussetting.IndustryTypeSettingDTO;
import com.zhulong.business.system.baseservice.dto.commonbussetting.IndustryTypeSettingQueryDTO;
import com.zhulong.business.system.busservice.api.commonbussetting.IndustryTypeSettingBusApi;
import com.zhulong.business.system.busservice.dto.commonbussetting.IndustryTypeSettingListDTO;
import com.zhulong.business.system.busservice.dto.commonbussetting.IndustryTypeSettingSaveDTO;
import com.zhulong.business.system.busservice.dto.commonbussetting.IndustryTypeSettingShowDTO;
import com.zhulong.business.system.busservice.dto.commonbussetting.IndustryTypeSettingUpdateDTO;
import com.zhulong.framework.auth.common.AuthUser;
import com.zhulong.framework.auth.common.mvc.AuthUserParam;
import com.zhulong.framework.common.annotion.Module;
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
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * 描述：国民经济行业分类 BusApiImpl实现
 *
 * @author 初。
 * @date 2019-03-28 09:18
 */
@RestController
@Module("COMMONBUSSETTING")
public class IndustryTypeSettingBusApiImpl implements IndustryTypeSettingBusApi {

    @Autowired
    private IndustryTypeSettingBaseApi industryTypeSettingBaseApi;

    @Override
    public ResultDTO<IndustryTypeSettingDTO> save(@Valid @RequestBody IndustryTypeSettingSaveDTO dto, @AuthUserParam AuthUser user) {

        IndustryTypeSettingDTO industryTypeSettingDTO = POJOConvertUtil.convert(dto, IndustryTypeSettingDTO.class);
        industryTypeSettingDTO.setGuid(UUID.randomUUID().toString());
        industryTypeSettingDTO.setCreateInfo(user);
        industryTypeSettingBaseApi.save(industryTypeSettingDTO);

        return ResultDTO.of(industryTypeSettingDTO);
    }

    @Override
    public ResultDTO<IndustryTypeSettingDTO> update(@Valid @RequestBody IndustryTypeSettingUpdateDTO dto, @AuthUserParam AuthUser user) {

        IndustryTypeSettingDTO industryTypeSettingDTO = POJOConvertUtil.convert(dto, IndustryTypeSettingDTO.class);
        industryTypeSettingDTO.setModifyInfo(user);
        industryTypeSettingDTO = industryTypeSettingBaseApi.update(industryTypeSettingDTO);

        return ResultDTO.of(industryTypeSettingDTO);
    }

    @Override
    public ResultDTO<Pagination<IndustryTypeSettingListDTO>> findPage(@RequestBody IndustryTypeSettingQueryDTO queryDTO, @AuthUserParam AuthUser user) {

        Pagination<IndustryTypeSettingDTO> industryTypeSettingDTOPage = industryTypeSettingBaseApi.findPage(queryDTO);
        List<IndustryTypeSettingDTO> itemDTOList = industryTypeSettingDTOPage.getList();
        List<IndustryTypeSettingListDTO> listDTOList = getIndustryTypeSettingListDTOs(itemDTOList);

        return ResultDTO.of(industryTypeSettingDTOPage.of(listDTOList));
    }

    private List<IndustryTypeSettingListDTO> getIndustryTypeSettingListDTOs(List<IndustryTypeSettingDTO> itemDTOList) {
        return POJOConvertUtil.convertList(itemDTOList, IndustryTypeSettingListDTO.class);
    }

    @Override
    public ResultDTO<IndustryTypeSettingShowDTO> getByGuid(@RequestParam("guid") String guid, @AuthUserParam AuthUser user) {

        IndustryTypeSettingDTO industryTypeSettingDTO = industryTypeSettingBaseApi.getByGuid(guid);
        IndustryTypeSettingShowDTO showDTO = POJOConvertUtil.convert(industryTypeSettingDTO, IndustryTypeSettingShowDTO.class);

        return ResultDTO.of(showDTO);
    }

    @Override
    public ResultDTO<Void> deleteById(@RequestParam("guid") String guid) {
        industryTypeSettingBaseApi.deleteById(guid);
        return ResultDTO.of(null);
    }

    @Override
    public ResultDTO<List<TreeDTO>> getTree() {

        List<IndustryTypeSettingDTO> industryTypeSettingDTOList = industryTypeSettingBaseApi.findList(new IndustryTypeSettingQueryDTO());
        //进行数据组装
        List<TreeDTO> treeDTOList = buildTreeDTO(industryTypeSettingDTOList, null);

        return ResultDTO.of(treeDTOList);
    }

    private List<TreeDTO> buildTreeDTO(List<IndustryTypeSettingDTO> industryTypeSettingDTOS, String parentGuid) {
        List<TreeDTO> treeDTOList = new ArrayList<>();
        if (industryTypeSettingDTOS != null && !industryTypeSettingDTOS.isEmpty()) {
            for (IndustryTypeSettingDTO industryTypeSettingDTO : industryTypeSettingDTOS) {
                if ((parentGuid == null && StringUtils.isEmpty(industryTypeSettingDTO.getParentGuid())) || (parentGuid != null && parentGuid.equals(industryTypeSettingDTO.getParentGuid()))) {
                    TreeDTO treeDTO = new TreeDTO(industryTypeSettingDTO.getGuid(), industryTypeSettingDTO.getFullNameZh());
                    treeDTO.setChildren(buildTreeDTO(industryTypeSettingDTOS, industryTypeSettingDTO.getGuid()));
                    treeDTOList.add(treeDTO);
                }
            }
        }

        return treeDTOList;
    }

}

