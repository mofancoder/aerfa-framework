package com.zhulong.business.system.baseservice.api.commonbussetting;

import com.zhulong.business.system.baseservice.dto.commonbussetting.AgreementSettingDTO;
import com.zhulong.business.system.baseservice.dto.commonbussetting.AgreementSettingQueryDTO;
import com.zhulong.framework.common.page.Pagination;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
* 描述：协议管理基础服务层接口API
* @author MOFAN889
* @date 2019-04-01 17:59
*/
public interface AgreementSettingBaseApi {

    //url前缀
    String URL_PRE="/agreementSettingBaseApi";

    /**
    * 描述：根据Id获取DTO
    * @param guid 主键
    * @return 查询到的数据，未查询到返回null
    */
    @GetMapping(URL_PRE+"/getByGuid")
    AgreementSettingDTO getByGuid(@RequestParam("guid") String guid);

    /**
    * 根据主键列表获取数据
    * @param guidList 主键列表
    * @return 查询到的数据列表，无数据返回空列表
    */
    @PostMapping(URL_PRE+"/findByGuidList")
    List<AgreementSettingDTO> findByGuidList(@RequestBody List<String> guidList);

    /**
    * 描述：按条件查询列表
    * @param queryDTO 查询的对象
    * @return 返回查询到的列表数据，未查询到数据返回空列表
    */
    @PostMapping(URL_PRE+"/findList")
    List<AgreementSettingDTO> findList(@RequestBody AgreementSettingQueryDTO queryDTO);

    /**
     * 描述：查询分页
     * @param queryDTO 查询的对象
     * @return 返回查询到的分页数据
     */
     @PostMapping(URL_PRE+"/findPage")
     Pagination<AgreementSettingDTO> findPage(@RequestBody AgreementSettingQueryDTO queryDTO);

     /**
      * 描述：根据DTO保存数据
      * @param dto 要保存的数据
      * @return 无
      */
      @PostMapping(URL_PRE+"/save")
      void save(@RequestBody AgreementSettingDTO dto);

      /**
       * 描述：根据DTO更新
       * @param dto 要更新的数据
       * @return 更新后的数据
       */
       @PostMapping(URL_PRE+"/update")
       AgreementSettingDTO update(@RequestBody AgreementSettingDTO dto);

       /**
        * 描述：根据Id删除
        * @param guid 要删除数据的主键
        * @return 无-无论是否删除到数据，都表示成功。
        */
       @PostMapping(URL_PRE+"/deleteById")
       void deleteById(@RequestParam("guid") String guid);

}
