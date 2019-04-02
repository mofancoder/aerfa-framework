package com.zhulong.business.system.busservice.api.commonbussetting.impl;

import com.zhulong.business.system.baseservice.api.commonbussetting.TransTypeSettingBaseApi;
import com.zhulong.business.system.baseservice.dto.commonbussetting.TransTypeSettingDTO;
import com.zhulong.business.system.baseservice.dto.commonbussetting.TransTypeSettingQueryDTO;
import com.zhulong.business.system.busservice.api.commonbussetting.TransTypeSettingBusApi;
import com.zhulong.business.system.busservice.dto.commonbussetting.TransTypeSettingListDTO;
import com.zhulong.business.system.busservice.dto.commonbussetting.TransTypeSettingSaveDTO;
import com.zhulong.business.system.busservice.dto.commonbussetting.TransTypeSettingShowDTO;
import com.zhulong.business.system.busservice.dto.commonbussetting.TransTypeSettingUpdateDTO;
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
 * 描述：交易类型 BusApiImpl实现
 * @author 初。
 * @date 2019-03-28 09:17
 */
@RestController
@Module("COMMONBUSSETTING")
public class TransTypeSettingBusApiImpl implements TransTypeSettingBusApi {

    @Autowired
    private  TransTypeSettingBaseApi transTypeSettingBaseApi;

    @Override
    public ResultDTO<TransTypeSettingDTO> save(@Valid @RequestBody TransTypeSettingSaveDTO dto, @AuthUserParam AuthUser user) {

        TransTypeSettingDTO transTypeSettingDTO = POJOConvertUtil.convert(dto,TransTypeSettingDTO.class);
        transTypeSettingDTO.setGuid(UUID.randomUUID().toString());
        transTypeSettingDTO.setCreateInfo(user);
        transTypeSettingBaseApi.save(transTypeSettingDTO);

        return ResultDTO.of(transTypeSettingDTO);
    }

    @Override
    public ResultDTO<TransTypeSettingDTO> update(@Valid @RequestBody TransTypeSettingUpdateDTO dto, @AuthUserParam AuthUser user) {

        TransTypeSettingDTO transTypeSettingDTO = POJOConvertUtil.convert(dto,TransTypeSettingDTO.class);
        transTypeSettingDTO.setModifyInfo(user);
        transTypeSettingDTO = transTypeSettingBaseApi.update(transTypeSettingDTO);

        return ResultDTO.of(transTypeSettingDTO);
    }

    @Override
    public ResultDTO<Pagination<TransTypeSettingListDTO>> findPage(@RequestBody TransTypeSettingQueryDTO queryDTO, @AuthUserParam AuthUser user) {

        Pagination<TransTypeSettingDTO> transTypeSettingDTOPage = transTypeSettingBaseApi.findPage(queryDTO);
        List<TransTypeSettingDTO> itemDTOList = transTypeSettingDTOPage.getList();
        List<TransTypeSettingListDTO> listDTOList = getTransTypeSettingListDTOs(itemDTOList);

        return ResultDTO.of(transTypeSettingDTOPage.of(listDTOList));
    }

    private List<TransTypeSettingListDTO> getTransTypeSettingListDTOs(List<TransTypeSettingDTO> itemDTOList) {
        return POJOConvertUtil.convertList(itemDTOList, TransTypeSettingListDTO.class);
    }

    @Override
    public ResultDTO<TransTypeSettingShowDTO> getByGuid(@RequestParam("guid") String guid, @AuthUserParam AuthUser user) {

        TransTypeSettingDTO transTypeSettingDTO = transTypeSettingBaseApi.getByGuid(guid);
        TransTypeSettingShowDTO showDTO = POJOConvertUtil.convert(transTypeSettingDTO, TransTypeSettingShowDTO.class);

        return ResultDTO.of(showDTO);
    }

    @Override
    public ResultDTO<Void> deleteById(@RequestParam("guid") String guid) {
        transTypeSettingBaseApi.deleteById(guid);
        return ResultDTO.of(null);
    }

    @Override
    public ResultDTO<Void> updateSortNum(@RequestBody List<KeyValueDTO<String, BigDecimal>> keyValueDTOList, @AuthUserParam AuthUser user) {
        transTypeSettingBaseApi.updateSortNum(keyValueDTOList);
        return ResultDTO.of(null);
    }

    @Override
    public ResultDTO<List<TreeDTO>> getTree() {

        List<TransTypeSettingDTO> transTypeSettingDTOList = transTypeSettingBaseApi.findList(new TransTypeSettingQueryDTO());
        //进行数据组装
        List<TreeDTO> treeDTOList = buildTreeDTO(transTypeSettingDTOList, null);

        return ResultDTO.of(treeDTOList);
    }

    private List<TreeDTO> buildTreeDTO(List<TransTypeSettingDTO> transTypeSettingDTOList, String parentGuid) {
        List<TreeDTO> treeDTOList = new ArrayList<>();
        if (transTypeSettingDTOList != null && !transTypeSettingDTOList.isEmpty()) {
            for (TransTypeSettingDTO transTypeSettingDTO : transTypeSettingDTOList) {
                if ((parentGuid == null && StringUtils.isEmpty(transTypeSettingDTO.getParentGuid())) || (parentGuid != null && parentGuid.equals(transTypeSettingDTO.getParentGuid()))) {
                    TreeDTO treeDTO = new TreeDTO(transTypeSettingDTO.getGuid(), transTypeSettingDTO.getFullNameZh());
                    treeDTO.setChildren(buildTreeDTO(transTypeSettingDTOList, transTypeSettingDTO.getGuid()));
                    treeDTOList.add(treeDTO);
                }
            }
        }

        return treeDTOList;
    }

}

