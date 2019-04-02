package com.zhulong.business.system.baseservice.api.systemconfig;

import com.zhulong.business.system.baseservice.dto.systemconfig.DataDictionaryDTO;
import com.zhulong.business.system.baseservice.dto.systemconfig.DataDictionaryQueryDTO;
import com.zhulong.framework.common.page.Pagination;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 描述：数据字典 BaseApi接口
 *
 * @author 初。
 * @date 2019-03-19 02:01:42
 */
public interface DataDictionaryBaseApi {

    // url前缀
    String URL_PRE = "/dataDictionary";

    /**
     * 描述：根据Id获取DTO
     */
    @GetMapping(URL_PRE + "/getByGuid")
    DataDictionaryDTO getById(@RequestParam("guid") String guid);

    /**
     * 描述：查询所有
     */
    @GetMapping(URL_PRE + "/findAll")
    List<DataDictionaryDTO> findAll();

    /**
     * 描述：查询分页
     */
    @PostMapping(URL_PRE + "/findPageByCondition")
    Pagination<DataDictionaryDTO> findPageByCondition(@RequestBody DataDictionaryQueryDTO queryDTO);

    /**
     * 描述：根据DTO保存
     */
    @PostMapping(URL_PRE + "/save")
    void save(@RequestBody DataDictionaryDTO dataDictionaryDTO);

    /**
     * 描述：根据DTO更新
     */
    @PostMapping(URL_PRE + "/update")
    Boolean update(@RequestBody DataDictionaryDTO dataDictionaryDTO);

    /**
     * 描述：根据Id删除
     */
    @PostMapping(URL_PRE + "/deleteById")
    void deleteById(@RequestParam("guid") String guid);

    /**
     * 根据数据字典分类的guid查出数据字典总数
     */
    @GetMapping(URL_PRE + "/countDataDictionaryByTypeGuid")
    Long countDataDictionaryByTypeGuid(@RequestParam("typeGuid") String typeGuid);

}
