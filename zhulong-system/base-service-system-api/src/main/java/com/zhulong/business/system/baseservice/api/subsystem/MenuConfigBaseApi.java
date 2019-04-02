package com.zhulong.business.system.baseservice.api.subsystem;

import com.zhulong.business.system.baseservice.dto.subsystem.MenuConfigQueryDTO;
import com.zhulong.framework.common.dto.KeyValueDTO;
import com.zhulong.framework.common.page.Pagination;
import com.zhulong.business.system.baseservice.dto.subsystem.MenuConfigDTO;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

/**
 * 菜单管理-基础服务层接口
 * Created by shanb on 2019-2-27.
 */
public interface MenuConfigBaseApi {

    String URL_PRE = "menuConfigBase/";
    /**
     * 保存
     * @param dto 要保存的信息
     * @return 无
     */
    @PostMapping(URL_PRE+"save")
    void save(@RequestBody MenuConfigDTO dto);

    /**
     * 更新
     * @param dto 更新数据-不为null的进行更新
     * @return 是否更新到数据
     */
    @PostMapping(URL_PRE+"update")
    Boolean update(@RequestBody MenuConfigDTO dto);

    /**
     * 删除数据
     * @param guid 输出数据
     * @return 是否删除到数据
     */
    @PostMapping(URL_PRE+"deleteByGuid")
    Boolean deleteByGuid(@RequestParam("guid") String guid);

    /**
     * 获取数据
     * @param guid 获取数据
     * @return 查询到的数据
     */
    @GetMapping(URL_PRE+"getByGuid")
    MenuConfigDTO getByGuid(@RequestParam("guid") String guid);

    /**
     * 根据主键列表，批量查询数据
     * @param guidList 主键列表
     * @return 查询到的数据
     */
    @PostMapping(URL_PRE+"findByGuidList")
    List<MenuConfigDTO> findByGuidList(@RequestBody List<String> guidList);

    /**
     * 查询数据分页列表
     * @param dto 查询对象
     * @return 查询到的分页数据
     */
    @PostMapping(URL_PRE+"findPageByCondition")
    Pagination<MenuConfigDTO> findPageByCondition(@RequestBody MenuConfigQueryDTO dto);

    /**
     * 查询数据列表
     * @param dto 查询对象
     * @return 查询到的列表数据
     */
    @PostMapping(URL_PRE+"findListByCondition")
    List<MenuConfigDTO> findListByCondition(@RequestBody MenuConfigQueryDTO dto);

    /**
     * 更新排序信息
     * @param sortNumList 要更新的排序信息
     * @return 无
     */
    @PostMapping(URL_PRE+"updateSortNum")
    void updateSortNum(@RequestBody List<KeyValueDTO<String,BigDecimal>> sortNumList);
}
