package com.zhulong.business.system.baseservice.api.organization;

import com.zhulong.business.system.baseservice.dto.organization.OrganizationBaseInfoDTO;
import com.zhulong.business.system.baseservice.dto.organization.OrganizationBaseInfoQueryDTO;
import com.zhulong.business.system.baseservice.dto.organization.OrganizationUpdateBusStatusDTO;
import com.zhulong.framework.common.dto.KeyValueDTO;
import com.zhulong.framework.common.page.Pagination;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

/**
 * 描述：机构管理基础服务层接口API
 *
 * @author shanb
 * @date 2019-03-27 10:14
 */
public interface OrganizationBaseInfoBaseApi {

    //url前缀
    String URL_PRE = "/organizationBaseInfoBaseApi";

    /**
     * 描述：根据Id获取DTO
     *
     * @param guid 主键
     * @return 查询到的数据，未查询到返回null
     */
    @GetMapping(URL_PRE + "/getByGuid")
    OrganizationBaseInfoDTO getByGuid(@RequestParam("guid") String guid);

    /**
     * 根据主键列表获取数据
     *
     * @param guidList 主键列表
     * @return 查询到的数据列表，无数据返回空列表
     */
    @PostMapping(URL_PRE + "/findByGuidList")
    List<OrganizationBaseInfoDTO> findByGuidList(@RequestBody List<String> guidList);

    /**
     * 描述：按条件查询列表
     *
     * @param queryDTO 查询的对象
     * @return 返回查询到的列表数据，未查询到数据返回空列表
     */
    @PostMapping(URL_PRE + "/findList")
    List<OrganizationBaseInfoDTO> findList(@RequestBody OrganizationBaseInfoQueryDTO queryDTO);

    /**
     * 描述：查询分页
     *
     * @param queryDTO 查询的对象
     * @return 返回查询到的分页数据
     */
    @PostMapping(URL_PRE + "/findPage")
    Pagination<OrganizationBaseInfoDTO> findPage(@RequestBody OrganizationBaseInfoQueryDTO queryDTO);

    /**
     * 描述：根据DTO保存数据
     *
     * @param dto 要保存的数据
     */
    @PostMapping(URL_PRE + "/save")
    void save(@RequestBody OrganizationBaseInfoDTO dto);

    /**
     * 描述：根据DTO更新
     *
     * @param dto 要更新的数据
     * @return 更新后的数据
     */
    @PostMapping(URL_PRE + "/update")
    OrganizationBaseInfoDTO update(@RequestBody OrganizationBaseInfoDTO dto);

    /**
     * 描述：根据Id删除
     *
     * @param guid 要删除数据的主键
     */
    @PostMapping(URL_PRE + "/deleteById")
    void deleteById(@RequestParam("guid") String guid);

    /**
     * 更新序号
     * @param guidSortNumList 主键和要更新的序号
     */
    @PostMapping(URL_PRE + "/updateSortNum")
    void updateSortNum(@RequestBody List<KeyValueDTO<String, BigDecimal>> guidSortNumList);

    /**
     * 更新业务状态，并记录更新记录
     * @param updateDTO 更新的数据
     */
    @PostMapping(URL_PRE+"/updateBusStatus")
    void updateBusStatus(@RequestBody OrganizationUpdateBusStatusDTO updateDTO);
}
