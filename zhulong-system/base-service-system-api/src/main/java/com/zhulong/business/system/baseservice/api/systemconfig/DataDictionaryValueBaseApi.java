package com.zhulong.business.system.baseservice.api.systemconfig;

import com.zhulong.business.system.baseservice.dto.systemconfig.DataDictionaryValueDTO;
import com.zhulong.business.system.baseservice.dto.systemconfig.DataDictionaryValueQueryDTO;
import com.zhulong.framework.common.page.Pagination;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * 描述：数据字典值 BaseApi接口
 *
 * @author 初。
 * @date 2019-03-20 09:59:31
 */
public interface DataDictionaryValueBaseApi {

    //url前缀
    String URL_PRE = "/dataDictionaryValue";

    /**
     * 描述：根据Id获取DTO
     */
    @GetMapping(URL_PRE + "/getByGuid")
    DataDictionaryValueDTO getById(@RequestParam("guid") String guid);

    /**
     * 描述：查询所有
     */
    @GetMapping(URL_PRE + "/findAll")
    List<DataDictionaryValueDTO> findAll();

    /**
     * 描述：查询分页
     */
    @PostMapping(URL_PRE + "/findPageByCondition")
    Pagination<DataDictionaryValueDTO> findPageByCondition(@RequestBody DataDictionaryValueQueryDTO queryDTO);

    /**
     * 描述：根据DTO保存
     */
    @PostMapping(URL_PRE + "/save")
    void save(@RequestBody DataDictionaryValueDTO dataDictionaryValueDTO);

    /**
     * 描述：根据DTO更新
     */
    @PostMapping(URL_PRE + "/update")
    void update(@RequestBody DataDictionaryValueDTO dataDictionaryValueDTO);

    /**
     * 描述：根据Id删除
     */
    @PostMapping(URL_PRE + "/deleteById")
    void deleteById(@RequestParam("guid") String guid);

}
