package com.zhulong.business.system.baseservice.api.subsystem;

import com.zhulong.business.system.baseservice.dto.subsystem.SubSystemCategoryDTO;
import com.zhulong.business.system.baseservice.dto.subsystem.SubSystemCategoryQueryDTO;
import com.zhulong.framework.common.dto.KeyValueDTO;
import com.zhulong.framework.common.page.Pagination;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.math.BigDecimal;
import java.util.List;

/**
 * 子系统分类-基础接口。
 * Created by shanb on 2019-2-25.
 */
public interface SubSystemCategoryBaseApi {

    String FIX_URL_PRE = "moduleCategoryBase/";

    /**
     * 保存子系统分类
     * @param dto 要保存的数据
     * @return 无
     */
    @PostMapping(FIX_URL_PRE+"save")
    void save(@RequestBody SubSystemCategoryDTO dto);

    /**
     * 更新子系统分类-非空字段更新
     * @param dto 要更新的数据，null值不更新
     * @return 是否更新到数据
     */
    @PostMapping(FIX_URL_PRE+"update")
    Boolean update(@RequestBody SubSystemCategoryDTO dto);


    /**
     * 更新子系统分类排序信息
     */
    @PostMapping(FIX_URL_PRE+"updateSortNums")
    void updateSortNums(@RequestBody List<KeyValueDTO<String,BigDecimal>> sortNumList);

    /**
     * 根据主键删除子系统分类
     * @param guid 要删除的主键
     * @return 是否删除到数据
     */
    @PostMapping(FIX_URL_PRE+"delete")
    Boolean deleteByGuid(@RequestParam("guid") String guid);

    /**
     * 根据主键查询子系统分类信息
     */
    @GetMapping(FIX_URL_PRE+"getByGuid")
    SubSystemCategoryDTO getByGuid(@RequestParam("guid") String guid);

    /**
     * 根据查询条件获取子系统分类
     * @param queryDto 查询条件信息
     * @return 返回查询数据的分页信息
     */
    @PostMapping(FIX_URL_PRE+"findPage")
    Pagination<SubSystemCategoryDTO> findPageByCondition(@RequestBody SubSystemCategoryQueryDTO queryDto);

    /**
     * 根据查询条件获取列表信息
     * @param queryDTO 查询条件信息
     * @return 返回查询到的列表信息
     */
    @PostMapping(FIX_URL_PRE+"findList")
    List<SubSystemCategoryDTO> findListByCondition(@RequestBody SubSystemCategoryQueryDTO queryDTO);

    /**
     * 根据主键列表查询子系统分类信息
     * @param guidList 主键列表
     * @return 查询到的列表信息
     */
    @PostMapping(FIX_URL_PRE+"findListByGuidList")
    List<SubSystemCategoryDTO> findListByGuidList(@RequestBody List<String> guidList);

    @PostMapping(FIX_URL_PRE+"findPageByJpql")
    List<SubSystemCategoryDTO> findPageByJpql(@RequestBody SubSystemCategoryDTO categoryDTO);
}