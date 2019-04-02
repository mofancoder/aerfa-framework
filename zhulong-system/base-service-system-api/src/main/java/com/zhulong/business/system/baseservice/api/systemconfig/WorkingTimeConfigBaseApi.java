package com.zhulong.business.system.baseservice.api.systemconfig;

import com.zhulong.business.system.baseservice.dto.systemconfig.WorkingTimeConfigDTO;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * 描述：工作时间管理 BaseApi接口
 *
 * @author 初。
 * @date 2019-03-21 04:05:52
 */
public interface WorkingTimeConfigBaseApi {

    //url前缀
    String URL_PRE = "/workingTimeConfig";

    /**
     * 描述：根据Id获取DTO
     */
    @GetMapping(URL_PRE + "/getByGuid")
    WorkingTimeConfigDTO getById(@RequestParam("guid") String guid);

    /**
     * 描述：查询所有
     */
    @GetMapping(URL_PRE + "/findAll")
    List<WorkingTimeConfigDTO> findAll();

    /**
     * 描述：根据DTO保存
     */
    @PostMapping(URL_PRE + "/save")
    void save(@RequestBody WorkingTimeConfigDTO workingTimeConfigDTO);

    /**
     * 描述：根据DTO更新
     */
    @PostMapping(URL_PRE + "/update")
    void update(@RequestBody WorkingTimeConfigDTO workingTimeConfigDTO);

    /**
     * 描述：根据Id删除
     */
    @PostMapping(URL_PRE + "/deleteById")
    void deleteById(@RequestParam("guid") String guid);

}
