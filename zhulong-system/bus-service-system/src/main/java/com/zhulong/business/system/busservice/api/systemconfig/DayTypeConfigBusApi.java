package com.zhulong.business.system.busservice.api.systemconfig;

import com.zhulong.business.system.baseservice.dto.systemconfig.DayTypeConfigDTO;
import com.zhulong.business.system.baseservice.dto.systemconfig.DayTypeConfigSaveUpdateDTO;
import com.zhulong.framework.common.dto.ResultDTO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * 描述：工作日配置 BusApi接口
 *
 * @author 初。
 * @date 2019-03-20 10:00:44
 */
@RequestMapping("/dayTypeConfig")
@Api(value = "系统配置 - 工作日配置", description = "系统配置 - 工作日配置")
public interface DayTypeConfigBusApi {

    /**
     * 根据年份找个该年份的所有日期类型配置
     *
     * @param year
     * @return
     */
    @GetMapping("findAllByYear")
    @ApiOperation("根据年份找个该年份的所有日期类型配置")
    ResultDTO<List<DayTypeConfigDTO>> findAllByYear(Short year);

    /**
     * 更新数据
     *
     * @param saveUpdateDTO
     * @return
     */
    @PostMapping("update")
    @ApiOperation("更新数据")
    ResultDTO<Object> update(@RequestBody DayTypeConfigSaveUpdateDTO saveUpdateDTO);

}






















