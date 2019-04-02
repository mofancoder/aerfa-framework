package com.zhulong.business.system.baseservice.api.subsystem;

import com.zhulong.business.system.baseservice.dto.subsystem.SubSystemDTO;
import com.zhulong.framework.common.dto.KeyValueDTO;
import com.zhulong.framework.common.page.Pagination;
import com.zhulong.business.system.baseservice.dto.subsystem.SubSystemQueryDTO;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.math.BigDecimal;
import java.util.List;

/**
 * 子系统管理-基础服务层接口
 * Created by shanb on 2019-2-26.
 */
public interface SubSystemBaseApi {

    String URL_PRE = "subSystemBase/";

    /**
     * 保存
     * @param dto 保存的信息
     * @return 无
     */
    @PostMapping(URL_PRE+"save")
    void save(@RequestBody SubSystemDTO dto);

    /**
     * 更新接口
     * @param dto 更新的信息-不为null值的进行更新
     * @return 是否更新到数据
     */
    @PostMapping(URL_PRE+"update")
    Boolean update(@RequestBody SubSystemDTO dto);

    /**
     * 删除
     * @param guid 主键
     * @return 是否更新到数据
     */
    @PostMapping(URL_PRE+"deleteByGuid")
    Boolean deleteByGuid(@RequestParam("guid") String guid);

    /**
     * 查询分页信息
     * @param queryDTO 查询信息
     * @return 返回查询到的数据
     */
    @PostMapping(URL_PRE+"queryPageByCondition")
    Pagination<SubSystemDTO> queryPageByCondition(@RequestBody SubSystemQueryDTO queryDTO);

    /**
     * 查询列表信息
     * @param queryDTO 查询信息
     * @return 返回查询到的数据
     */
    @PostMapping(URL_PRE+"queryListByCondition")
    List<SubSystemDTO> queryListByConditon(@RequestBody SubSystemQueryDTO queryDTO);


    /**
     * 根据主键查询子系统
     * @param guid 主键
     * @return 返回查询到的数据，如未查询到，返回null
     */
    @GetMapping(URL_PRE+"getByGuid")
    SubSystemDTO getByGuid(@RequestParam("guid") String guid);

    /**
     *  根据主键列表查询主键
     *  @param guidList 主键列表
     *  @return 返回查询到的数据
     */
    @PostMapping(URL_PRE+"findByGuidList")
    List<SubSystemDTO> findByGuidList(@RequestBody List<String> guidList);

    /**
     * 更新排序信息
     * @param sortNumList 要排序信息
     * @return 无
     */
    @PostMapping(URL_PRE+"updateSortNumList")
    void updateSortNumList(@RequestBody List<KeyValueDTO<String,BigDecimal>> sortNumList);

    @PostMapping(URL_PRE+"/deleteByCategoryCode")
    void deleteByCategoryCode(@RequestParam("categoryCode") String categoryCode);
}