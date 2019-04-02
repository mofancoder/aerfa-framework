package com.zhulong.business.system.baseservice.api.systemconfig;

import com.zhulong.business.system.baseservice.dto.systemconfig.DataDictionaryTypeDTO;
import com.zhulong.business.system.baseservice.dto.systemconfig.DataDictionaryTypeQueryDTO;
import com.zhulong.framework.common.page.Pagination;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * 描述：数据字典类型 BaseApi接口
 *
 * @author 初。
 * @date 2019-03-20 10:00:06
 */
public interface DataDictionaryTypeBaseApi {

    //url前缀
    String URL_PRE = "/dataDictionaryType";

    /**
     * 描述：根据Id获取DTO
     */
    @GetMapping(URL_PRE + "/getByGuid")
    DataDictionaryTypeDTO getById(@RequestParam("guid") String guid);

    /**
     * 描述：查询所有
     */
    @GetMapping(URL_PRE + "/findAll")
    List<DataDictionaryTypeDTO> findAll();

    /**
     * 描述：查询分页
     */
    @PostMapping(URL_PRE + "/findPageByCondition")
    Pagination<DataDictionaryTypeDTO> findPageByCondition(@RequestBody DataDictionaryTypeQueryDTO queryDTO);

    /**
     * 描述：根据DTO保存
     */
    @PostMapping(URL_PRE + "/save")
    void save(@RequestBody DataDictionaryTypeDTO dataDictionaryTypeDTO);

    /**
     * 描述：根据DTO更新
     */
    @PostMapping(URL_PRE + "/update")
    void update(@RequestBody DataDictionaryTypeDTO dataDictionaryTypeDTO);

    /**
     * 描述：根据Id删除
     */
    @PostMapping(URL_PRE + "/deleteById")
    void deleteById(@RequestParam("guid") String guid);

}
