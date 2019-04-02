package com.zhulong.business.system.baseservice.api.systemconfig;

import com.zhulong.business.system.baseservice.dto.systemconfig.SystemParamDTO;
import com.zhulong.business.system.baseservice.dto.systemconfig.SystemParamQueryDTO;
import com.zhulong.framework.common.page.Pagination;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * 描述：系统参数 BaseApi接口
 *
 * @author 初。
 * @date 2019-03-20 10:01:09
 */
public interface SystemParamBaseApi {

    //url前缀
    String URL_PRE = "/systemParam";

    /**
     * 描述：根据Id获取DTO
     */
    @GetMapping(URL_PRE + "/getByGuid")
    SystemParamDTO getById(@RequestParam("guid") String guid);

    /**
     * 描述：查询所有
     */
    @GetMapping(URL_PRE + "/findAll")
    List<SystemParamDTO> findAll();

    /**
     * 描述：查询分页
     */
    @PostMapping(URL_PRE + "/findPageByCondition")
    Pagination<SystemParamDTO> findPageByCondition(@RequestBody SystemParamQueryDTO queryDTO);

    /**
     * 描述：根据DTO保存
     */
    @PostMapping(URL_PRE + "/save")
    void save(@RequestBody SystemParamDTO systemParamDTO);

    /**
     * 描述：根据DTO更新
     */
    @PostMapping(URL_PRE + "/update")
    void update(@RequestBody SystemParamDTO systemParamDTO);

    /**
     * 描述：根据Id删除
     */
    @PostMapping(URL_PRE + "/deleteById")
    void deleteById(@RequestParam("guid") String guid);

}
