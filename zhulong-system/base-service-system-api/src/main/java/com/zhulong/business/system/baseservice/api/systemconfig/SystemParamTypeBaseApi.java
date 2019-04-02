package com.zhulong.business.system.baseservice.api.systemconfig;

import com.zhulong.business.system.baseservice.dto.systemconfig.SystemParamTypeDTO;
import com.zhulong.business.system.baseservice.dto.systemconfig.SystemParamTypeQueryDTO;
import com.zhulong.framework.common.page.Pagination;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * 描述：系统参数分类 BaseApi接口
 *
 * @author 初。
 * @date 2019-03-20 10:01:25
 */
public interface SystemParamTypeBaseApi {

    //url前缀
    String URL_PRE = "/systemParamType";

    /**
     * 描述：根据Id获取DTO
     */
    @GetMapping(URL_PRE + "/getByGuid")
    SystemParamTypeDTO getById(@RequestParam("guid") String guid);

    /**
     * 描述：查询所有
     */
    @GetMapping(URL_PRE + "/findAll")
    List<SystemParamTypeDTO> findAll();

    /**
     * 描述：查询分页
     */
    @PostMapping(URL_PRE + "/findPageByCondition")
    Pagination<SystemParamTypeDTO> findPageByCondition(@RequestBody SystemParamTypeQueryDTO queryDTO);

    /**
     * 描述：根据DTO保存
     */
    @PostMapping(URL_PRE + "/save")
    void save(@RequestBody SystemParamTypeDTO systemParamTypeDTO);

    /**
     * 描述：根据DTO更新
     */
    @PostMapping(URL_PRE + "/update")
    void update(@RequestBody SystemParamTypeDTO systemParamTypeDTO);

    /**
     * 描述：根据Id删除
     */
    @PostMapping(URL_PRE + "/deleteById")
    void deleteById(@RequestParam("guid") String guid);

}
