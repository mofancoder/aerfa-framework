package com.zhulong.business.system.baseservice.api.subsystem;

import com.zhulong.business.system.baseservice.dto.subsystem.FunctionConfigDTO;
import com.zhulong.business.system.baseservice.dto.subsystem.FunctionConfigQueryDTO;
import com.zhulong.framework.common.page.Pagination;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
* 描述：功能管理基础服务层接口API
* @author shanb
* @date 2019-03-26 11:38
*/
public interface FunctionConfigBaseApi {

    //url前缀
    String URL_PRE="/functionConfigBaseApi";

    /**
    * 描述：根据Id获取DTO
    * @param guid 主键
    * @return 查询到的数据，未查询到返回null
    */
    @GetMapping(URL_PRE+"/getByGuid")
    FunctionConfigDTO getByGuid(@RequestParam("guid") String guid);

    /**
    * 根据主键列表获取数据
    * @param guidList 主键列表
    * @return 查询到的数据列表，无数据返回空列表
    */
    @PostMapping(URL_PRE+"/findByGuidList")
    List<FunctionConfigDTO> findByGuidList(@RequestBody List<String> guidList);

    /**
    * 描述：按条件查询列表
    * @param queryDTO 查询的对象
    * @return 返回查询到的列表数据，未查询到数据返回空列表
    */
    @PostMapping(URL_PRE+"/findList")
    List<FunctionConfigDTO> findList(@RequestBody FunctionConfigQueryDTO queryDTO);

    /**
     * 描述：查询分页
     * @param queryDTO 查询的对象
     * @return 返回查询到的分页数据
     */
     @PostMapping(URL_PRE+"/findPage")
     Pagination<FunctionConfigDTO> findPage(@RequestBody FunctionConfigQueryDTO queryDTO);

     /**
      * 描述：根据DTO保存数据
      * @param dto 要保存的数据
      * @return 无
      */
      @PostMapping(URL_PRE+"/save")
      void save(@RequestBody FunctionConfigDTO dto);

      /**
       * 描述：根据DTO更新
       * @param dto 要更新的数据
       * @return 更新后的数据
       */
       @PostMapping(URL_PRE+"/update")
       FunctionConfigDTO update(@RequestBody FunctionConfigDTO dto);

       /**
        * 描述：根据Id删除
        */
       @PostMapping(URL_PRE+"/deleteById")
       void deleteById(@RequestParam("guid") String guid);

}
