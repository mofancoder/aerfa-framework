package com.zhulong.business.system.baseservice.entity.commonbussetting.baseservice.api.commonbussetting;

import com.zhulong.business.system.baseservice.entity.commonbussetting.baseservice.dto.commonbussetting.CurrencyDictionaryDTO;
import com.zhulong.business.system.baseservice.entity.commonbussetting.baseservice.dto.commonbussetting.CurrencyDictionaryQueryDTO;
import com.zhulong.framework.common.page.Pagination;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
* 描述：币种管理基础服务层接口API
* @author MOFAN889
* @date 2019-04-01 11:37
*/
public interface CurrencyDictionaryBaseApi {

    //url前缀
    String URL_PRE="/currencyDictionaryBaseApi";

    /**
    * 描述：根据Id获取DTO
    * @param guid 主键
    * @return 查询到的数据，未查询到返回null
    */
    @GetMapping(URL_PRE+"/getByGuid")
    CurrencyDictionaryDTO getByGuid(@RequestParam("guid") String guid);

    /**
    * 根据主键列表获取数据
    * @param guidList 主键列表
    * @return 查询到的数据列表，无数据返回空列表
    */
    @PostMapping(URL_PRE+"/findByGuidList")
    List<CurrencyDictionaryDTO> findByGuidList(@RequestBody List<String> guidList);

    /**
    * 描述：按条件查询列表
    * @param queryDTO 查询的对象
    * @return 返回查询到的列表数据，未查询到数据返回空列表
    */
    @PostMapping(URL_PRE+"/findList")
    List<CurrencyDictionaryDTO> findList(@RequestBody CurrencyDictionaryQueryDTO queryDTO);

    /**
     * 描述：查询分页
     * @param queryDTO 查询的对象
     * @return 返回查询到的分页数据
     */
     @PostMapping(URL_PRE+"/findPage")
     Pagination<CurrencyDictionaryDTO> findPage(@RequestBody CurrencyDictionaryQueryDTO queryDTO);

     /**
      * 描述：根据DTO保存数据
      * @param dto 要保存的数据
      * @return 无
      */
      @PostMapping(URL_PRE+"/save")
      void save(@RequestBody CurrencyDictionaryDTO dto);

      /**
       * 描述：根据DTO更新
       * @param dto 要更新的数据
       * @return 更新后的数据
       */
       @PostMapping(URL_PRE+"/update")
       CurrencyDictionaryDTO update(@RequestBody CurrencyDictionaryDTO dto);

       /**
        * 描述：根据Id删除
        * @param guid 要删除数据的主键
        * @return 无-无论是否删除到数据，都表示成功。
        */
       @PostMapping(URL_PRE+"/deleteById")
       void deleteById(@RequestParam("guid") String guid);

}
