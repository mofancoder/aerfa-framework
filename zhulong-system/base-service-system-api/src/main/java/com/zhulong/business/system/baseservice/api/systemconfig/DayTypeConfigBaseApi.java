package com.zhulong.business.system.baseservice.api.systemconfig;

import com.zhulong.business.system.baseservice.dto.systemconfig.DayTypeConfigDTO;
import com.zhulong.business.system.baseservice.dto.systemconfig.DayTypeConfigSaveUpdateDTO;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * 描述：日期类型配置 BaseApi接口
 *
 * @author 初。
 * @date 2019-03-20 10:00:44
 */
public interface DayTypeConfigBaseApi {

    //url前缀
    String URL_PRE = "/dayTypeConfig";

    /**
     * 根据指定的年份查询出当前的所有日期类型配置，若没有数据，则生成查询年的数据
     *
     * @param year
     * @return
     */
    @GetMapping(URL_PRE + "/findAndGenerateByYear")
    List<DayTypeConfigDTO> findAndGenerateByYear(@RequestParam("year") Short year) throws Exception;

    /**
     * 更新编辑的内容
     * @param saveUpdateDTO
     */
    @PostMapping(URL_PRE + "/generateDataByYear")
    void update(@RequestBody DayTypeConfigSaveUpdateDTO saveUpdateDTO);

}

























