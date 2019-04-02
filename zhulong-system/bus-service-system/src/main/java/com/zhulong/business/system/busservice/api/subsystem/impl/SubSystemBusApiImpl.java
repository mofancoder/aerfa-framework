package com.zhulong.business.system.busservice.api.subsystem.impl;

import com.zhulong.business.system.busservice.api.subsystem.SubSystemBusApi;
import com.zhulong.business.system.busservice.dto.subsystem.SubSystemUpdateDTO;
import com.zhulong.framework.auth.common.AuthUser;
import com.zhulong.framework.auth.common.mvc.AuthUserParam;
import com.zhulong.framework.common.annotion.Module;
import com.zhulong.framework.common.dto.KeyValueDTO;
import com.zhulong.framework.common.dto.ResultDTO;
import com.zhulong.framework.common.dto.ZhEnglishDispayDTO;
import com.zhulong.framework.common.page.Pagination;
import com.zhulong.framework.common.util.POJOConvertUtil;
import com.zhulong.business.system.baseservice.api.subsystem.SubSystemBaseApi;
import com.zhulong.business.system.baseservice.api.subsystem.SubSystemCategoryBaseApi;
import com.zhulong.business.system.baseservice.dto.subsystem.SubSystemCategoryDTO;
import com.zhulong.business.system.baseservice.dto.subsystem.SubSystemDTO;
import com.zhulong.business.system.baseservice.dto.subsystem.SubSystemQueryDTO;
import com.zhulong.business.system.busservice.dto.subsystem.SubSystemListDTO;
import com.zhulong.business.system.busservice.dto.subsystem.SubSystemSaveDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

/**
 * 子系统管理-业务层接口
 * Created by shanb on 2019-2-26.
 */
@RestController
@Module("SUBSYSTEM")
public class SubSystemBusApiImpl implements SubSystemBusApi {

    @Autowired
    private SubSystemBaseApi subSystemBaseApi;

    @Autowired
    private SubSystemCategoryBaseApi subSystemCategoryBaseApi;

    @Override
    public ResultDTO<SubSystemDTO> save(@Valid @RequestBody SubSystemSaveDTO dto, @AuthUserParam AuthUser authUser) {
        SubSystemDTO subSystemDTO = POJOConvertUtil.convert(dto, SubSystemDTO.class);
        //一些业务的设定
        //计算编号
        subSystemDTO.setCode(genaratorCode());
        subSystemDTO.setGuid(UUID.randomUUID().toString());
        subSystemDTO.setCreateInfo(authUser);
        subSystemBaseApi.save(subSystemDTO);
        return ResultDTO.of(subSystemDTO);
    }

    @Override
    public ResultDTO<SubSystemDTO> update(@RequestBody SubSystemUpdateDTO dto, @AuthUserParam AuthUser authUser) {
        SubSystemDTO subSystemDTO = POJOConvertUtil.convert(dto, SubSystemDTO.class);
        subSystemDTO.setModifyInfo(authUser);
        subSystemBaseApi.update(subSystemDTO);
        return ResultDTO.of(subSystemDTO);
    }

    @Override
    public ResultDTO<Boolean> deleteByGuid(String guid, @AuthUserParam AuthUser authUser) {
        Boolean deleted = subSystemBaseApi.deleteByGuid(guid);
        return  ResultDTO.of(deleted);
    }

    @Override
    public ResultDTO<Pagination<SubSystemListDTO>> findPage(@RequestBody SubSystemQueryDTO queryDTO, @AuthUserParam AuthUser authUser) {
        Pagination<SubSystemDTO> pagination = subSystemBaseApi.queryPageByCondition(queryDTO);
        List<SubSystemListDTO> listDtoList = getSubSystemListDTOs(pagination.getList());
        return ResultDTO.of(pagination.of(listDtoList));
    }

    @Override
    public ResultDTO<List<SubSystemListDTO>> findList(@RequestBody SubSystemQueryDTO queryDTO, @AuthUserParam AuthUser authUser) {
        List<SubSystemDTO> dtoList = subSystemBaseApi.queryListByConditon(queryDTO);
        List<SubSystemListDTO> listDtoList = getSubSystemListDTOs(dtoList);
        return ResultDTO.of(listDtoList);
    }

    @Override
    public ResultDTO<SubSystemDTO> getByGuid(String guid, @AuthUserParam AuthUser authUser) {
        SubSystemDTO subSystemDTO = subSystemBaseApi.getByGuid(guid);
        return ResultDTO.of(subSystemDTO);
    }

    @Override
    public ResultDTO<Void> updateSortNum(@RequestBody List<KeyValueDTO<String, BigDecimal>> sortNumList, @AuthUserParam AuthUser authUser) {
        subSystemBaseApi.updateSortNumList(sortNumList);
        return ResultDTO.of(null);
    }

    private List<SubSystemListDTO> getSubSystemListDTOs(List<SubSystemDTO> subSystemDtoList) {
        //用户类型，此数据不多，查询出全部，然后挨个对比
        List<ZhEnglishDispayDTO> zhEnglishDispayDTOList = getUserTypeList();
        //查询要用的所属分类
        Set<String> categoryGuidSet = subSystemDtoList.stream().map(dto -> dto.getCategoryGuid()).collect(Collectors.toSet());
        List<SubSystemCategoryDTO> categoryDTOList = subSystemCategoryBaseApi.findListByGuidList(new ArrayList<>(categoryGuidSet));

        //组装页面需要展示的数据
        return subSystemDtoList.stream().map(dto -> {
            SubSystemListDTO listDTO = POJOConvertUtil.convert(dto,SubSystemListDTO.class);
            //获取用户类型
            if(!StringUtils.isEmpty(dto.getUserType())){
                StringBuilder sbGuid = new StringBuilder();
                StringBuilder sbZh = new StringBuilder();
                StringBuilder sbEnglish = new StringBuilder();
                //TODO:是否可以修改lambda表达式的方式
                List<String> userTypeGuidList = Arrays.asList(dto.getUserType().split(","));
                for (ZhEnglishDispayDTO zhEnglishDispayDTO : zhEnglishDispayDTOList) {

                    if(userTypeGuidList.contains(zhEnglishDispayDTO.getGuid())){
                        sbGuid.append(","+zhEnglishDispayDTO.getGuid());
                        sbZh.append(","+zhEnglishDispayDTO.getDispayNameZh());
                        sbEnglish.append(","+zhEnglishDispayDTO.getDispayNameEnglish());
                    }
                }
                if(sbGuid.length()>0){
                    sbGuid.deleteCharAt(0);
                    sbZh.deleteCharAt(0);
                    sbEnglish.deleteCharAt(0);
                    listDTO.setUserTypeInfo(ZhEnglishDispayDTO.builder().guid(sbGuid.toString()).dispayNameZh(sbZh.toString()).dispayNameEnglish(sbEnglish.toString()).build());
                }
            }

            if(!StringUtils.isEmpty(dto.getCategoryGuid())) {
                Optional<SubSystemCategoryDTO> categoryDTO = categoryDTOList.stream().filter(categoryDto -> categoryDto.getGuid().equals(dto.getCategoryGuid())).findFirst();
                listDTO.setCategoryName(categoryDTO.isPresent()?categoryDTO.get().getName():null);
            }
            return listDTO;
        }).collect(Collectors.toList());
    }

    @Override
    public ResultDTO<List<ZhEnglishDispayDTO>> findAllUserTypeList() {
        return ResultDTO.of(getUserTypeList());
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

    private List<ZhEnglishDispayDTO> getUserTypeList(){
        List<ZhEnglishDispayDTO> list = new ArrayList<>();
        list.add(ZhEnglishDispayDTO.builder().guid("1").dispayNameZh("平台运营运营用户").dispayNameEnglish("平台运营运营用户english").build());
        list.add(ZhEnglishDispayDTO.builder().guid("2").dispayNameZh("普通招标人").dispayNameEnglish("普通招标人english").build());
        list.add(ZhEnglishDispayDTO.builder().guid("3").dispayNameZh("普通投标人").dispayNameEnglish("普通投标人english").build());
        list.add(ZhEnglishDispayDTO.builder().guid("4").dispayNameZh("普通招标代理机构").dispayNameEnglish("普通招标代理机构english").build());
        list.add(ZhEnglishDispayDTO.builder().guid("5").dispayNameZh("专家").dispayNameEnglish("专家english").build());
        list.add(ZhEnglishDispayDTO.builder().guid("6").dispayNameZh("机构用户").dispayNameEnglish("机构用户english").build());
        return list;
    }
}