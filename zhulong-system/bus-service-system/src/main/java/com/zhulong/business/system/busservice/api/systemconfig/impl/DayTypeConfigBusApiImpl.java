package com.zhulong.business.system.busservice.api.systemconfig.impl;

import com.zhulong.business.system.baseservice.api.systemconfig.DayTypeConfigBaseApi;
import com.zhulong.business.system.baseservice.dto.systemconfig.DayTypeConfigDTO;
import com.zhulong.business.system.baseservice.dto.systemconfig.DayTypeConfigSaveUpdateDTO;
import com.zhulong.business.system.busservice.api.systemconfig.DayTypeConfigBusApi;
import com.zhulong.framework.common.annotion.Module;
import com.zhulong.framework.common.dto.ResultDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 描述：日期类型配置 BusApiImpl实现
 *
 * @author 初。
 * @date 2019-03-20 10:00:44
 */
@RestController
@Module("系统配置 - 日期类型配置业务层接口")
public class DayTypeConfigBusApiImpl implements DayTypeConfigBusApi {

    @Autowired
    private DayTypeConfigBaseApi dayTypeConfigBaseApi;

    @Override
    public ResultDTO<List<DayTypeConfigDTO>> findAllByYear(Short year) {

        List<DayTypeConfigDTO> dayTypeConfigDTOS = null;
        try {
            dayTypeConfigDTOS = dayTypeConfigBaseApi.findAndGenerateByYear(year);
        } catch (Exception e) {
            e.printStackTrace();
            // TODO 错误码！！！
            return  ResultDTO.error("", "生成该年份的数据失败！");
        }

        return ResultDTO.of(dayTypeConfigDTOS);
    }

    @Override
    public ResultDTO<Object> update(@RequestBody DayTypeConfigSaveUpdateDTO saveUpdateDTO) {

        dayTypeConfigBaseApi.update(saveUpdateDTO);

        return ResultDTO.of(null);
    }

}






















