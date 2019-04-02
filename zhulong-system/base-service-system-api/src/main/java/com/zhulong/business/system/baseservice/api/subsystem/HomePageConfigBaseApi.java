package com.zhulong.business.system.baseservice.api.subsystem;

import com.zhulong.business.system.baseservice.dto.subsystem.HomePageConfigQueryDTO;
import com.zhulong.framework.common.page.Pagination;
import com.zhulong.business.system.baseservice.dto.subsystem.HomePageConfigDTO;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * 首页配置-基础服务层接口
 * Created by shanb on 2019-2-26.
 */
public interface HomePageConfigBaseApi {

    String URL_PRE = "homePageConfig/";

    /**
     * 保存首页配置信息
     * @param dto 要保存的信息
     * @return 无
     */
    @PostMapping(URL_PRE+"save")
    void save(@RequestBody HomePageConfigDTO dto);

    /**
     * 更新首页配置信息
     * @param dto 要更新的信息
     * @return 是否更新到数据
     */
    @PostMapping(URL_PRE+"update")
    Boolean update(@RequestBody HomePageConfigDTO dto);

    /**
     * 根据主键删除信息
     * @param guid 主键
     * @return 是否删除到数据
     */
    @PostMapping(URL_PRE+"deleteByGuid")
    Boolean deleteByGuid(@RequestParam("guid") String guid);

    /**
     * 根据查询参数查询带分页数据
     * @param queryDTO 查询参数
     * @return 查询到的分页信息数据
     */
    @PostMapping(URL_PRE+"findPageByCondition")
    Pagination<HomePageConfigDTO> findPageByCondition(@RequestBody HomePageConfigQueryDTO queryDTO);

    /**
     * 根据查询参数查询数据
     * @param queryDTO 查询参数
     * @return 查询到的数据列表
     */
    @PostMapping(URL_PRE+"findListByCondition")
    List<HomePageConfigDTO> findListByCondition(@RequestBody HomePageConfigQueryDTO queryDTO);

    /**
     * 根据主键获取首页配置信息
     * @param guid 主键
     * @return 首页配置信息，查询不到返回null
     */
    @GetMapping(URL_PRE+"getByGuid")
    HomePageConfigDTO getByGuid(@RequestParam("guid") String guid);

    /**
     * 根据子系统主键和业务模式，获取可用的首页配置
     * @param subSystemGuid 子系统主键
     * @param busModel 业务模式
     * @return 返回可用的首页配置
     */
    @GetMapping(URL_PRE+"getCanUseConfig")
    HomePageConfigDTO getCanUseConfig(@RequestParam("subSystemGuid") String subSystemGuid,@RequestParam("busModel") Short busModel);
}