package com.zhulong.business.system.busservice.api.commonbussetting.impl;

import com.zhulong.business.system.baseservice.api.commonbussetting.PublicSourceTransSettingBaseApi;
import com.zhulong.business.system.baseservice.dto.commonbussetting.PublicSourceTransSettingDTO;
import com.zhulong.business.system.baseservice.dto.commonbussetting.PublicSourceTransSettingQueryDTO;
import com.zhulong.business.system.busservice.api.commonbussetting.PublicSourceTransSettingBusApi;
import com.zhulong.business.system.busservice.dto.commonbussetting.PublicSourceTransSettingListDTO;
import com.zhulong.business.system.busservice.dto.commonbussetting.PublicSourceTransSettingSaveDTO;
import com.zhulong.business.system.busservice.dto.commonbussetting.PublicSourceTransSettingShowDTO;
import com.zhulong.business.system.busservice.dto.commonbussetting.PublicSourceTransSettingUpdateDTO;
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
 * 描述：公共资源分类 BusApiImpl实现
 * @author 初。
 * @date 2019-03-28 09:21
 */
@RestController
@Module("COMMONBUSSETTING")
public class PublicSourceTransSettingBusApiImpl implements PublicSourceTransSettingBusApi {

    @Autowired
    private  PublicSourceTransSettingBaseApi publicSourceTransSettingBaseApi;

    @Override
    public ResultDTO<PublicSourceTransSettingDTO> save(@Valid @RequestBody PublicSourceTransSettingSaveDTO dto, @AuthUserParam AuthUser user) {

        PublicSourceTransSettingDTO publicSourceTransSettingDTO = POJOConvertUtil.convert(dto,PublicSourceTransSettingDTO.class);
        publicSourceTransSettingDTO.setGuid(UUID.randomUUID().toString());
        publicSourceTransSettingDTO.setCreateInfo(user);
        publicSourceTransSettingBaseApi.save(publicSourceTransSettingDTO);

        return ResultDTO.of(publicSourceTransSettingDTO);
    }

    @Override
    public ResultDTO<PublicSourceTransSettingDTO> update(@Valid @RequestBody PublicSourceTransSettingUpdateDTO dto, @AuthUserParam AuthUser user) {

        PublicSourceTransSettingDTO publicSourceTransSettingDTO = POJOConvertUtil.convert(dto,PublicSourceTransSettingDTO.class);
        publicSourceTransSettingDTO.setModifyInfo(user);
        publicSourceTransSettingDTO = publicSourceTransSettingBaseApi.update(publicSourceTransSettingDTO);

        return ResultDTO.of(publicSourceTransSettingDTO);
    }

    @Override
    public ResultDTO<Pagination<PublicSourceTransSettingListDTO>> findPage(@RequestBody PublicSourceTransSettingQueryDTO queryDTO, @AuthUserParam AuthUser user) {

        Pagination<PublicSourceTransSettingDTO> publicSourceTransSettingDTOPage = publicSourceTransSettingBaseApi.findPage(queryDTO);
        List<PublicSourceTransSettingDTO> itemDTOList = publicSourceTransSettingDTOPage.getList();
        List<PublicSourceTransSettingListDTO> listDTOList = getPublicSourceTransSettingListDTOs(itemDTOList);

        return ResultDTO.of(publicSourceTransSettingDTOPage.of(listDTOList));
    }

    private List<PublicSourceTransSettingListDTO> getPublicSourceTransSettingListDTOs(List<PublicSourceTransSettingDTO> itemDTOList) {
        return POJOConvertUtil.convertList(itemDTOList, PublicSourceTransSettingListDTO.class);
    }

    @Override
    public ResultDTO<PublicSourceTransSettingShowDTO> getByGuid(@RequestParam("guid") String guid, @AuthUserParam AuthUser user) {

        PublicSourceTransSettingDTO publicSourceTransSettingDTO = publicSourceTransSettingBaseApi.getByGuid(guid);
        PublicSourceTransSettingShowDTO showDTO = POJOConvertUtil.convert(publicSourceTransSettingDTO, PublicSourceTransSettingShowDTO.class);

        return ResultDTO.of(showDTO);
    }

    @Override
    public ResultDTO<Void> deleteById(@RequestParam("guid") String guid) {
        publicSourceTransSettingBaseApi.deleteById(guid);
        return ResultDTO.of(null);
    }

    @Override
    public ResultDTO<List<TreeDTO>> getTree() {

        List<PublicSourceTransSettingDTO> publicSourceTransSettingDTOList = publicSourceTransSettingBaseApi.findList(new PublicSourceTransSettingQueryDTO());
        //进行数据组装
        List<TreeDTO> treeDTOList = buildTreeDTO(publicSourceTransSettingDTOList, null);

        return ResultDTO.of(treeDTOList);
    }

    private List<TreeDTO> buildTreeDTO(List<PublicSourceTransSettingDTO> publicSourceTransSettingDTOList, String parentGuid) {
        List<TreeDTO> treeDTOList = new ArrayList<>();
        if (publicSourceTransSettingDTOList != null && !publicSourceTransSettingDTOList.isEmpty()) {
            for (PublicSourceTransSettingDTO publicSourceTransSettingDTO : publicSourceTransSettingDTOList) {
                if ((parentGuid == null && StringUtils.isEmpty(publicSourceTransSettingDTO.getParentGuid())) || (parentGuid != null && parentGuid.equals(publicSourceTransSettingDTO.getParentGuid()))) {
                    TreeDTO treeDTO = new TreeDTO(publicSourceTransSettingDTO.getGuid(), publicSourceTransSettingDTO.getFullNameZh());
                    treeDTO.setChildren(buildTreeDTO(publicSourceTransSettingDTOList, publicSourceTransSettingDTO.getGuid()));
                    treeDTOList.add(treeDTO);
                }
            }
        }

        return treeDTOList;
    }

}

