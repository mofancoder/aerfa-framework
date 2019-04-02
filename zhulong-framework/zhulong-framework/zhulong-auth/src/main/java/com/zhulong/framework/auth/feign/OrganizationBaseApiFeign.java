package com.zhulong.framework.auth.feign;

import com.zhulong.framework.auth.dto.AccountInfoDTO;
import com.zhulong.framework.auth.dto.AccountInfoQueryDTO;
import com.zhulong.framework.auth.dto.OrganizationBaseInfoDTO;
import com.zhulong.framework.auth.dto.OrganizationPersonDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * 调用组织架构的feign接口
 * Created by shanb on 2019-3-28.
 */
@FeignClient("base-service-system")
public interface OrganizationBaseApiFeign {

    /**
     * 描述：按条件查询列表
     *
     * @param queryDTO 查询的对象
     * @return 返回查询到的列表数据，未查询到数据返回空列表
     */
    @PostMapping("/accountInfoBaseApi/findList")
    List<AccountInfoDTO> findAccountList(@RequestBody AccountInfoQueryDTO queryDTO);

    /**
     * 更新账号信息
     */
    @PostMapping("accountInfoBaseApi/update")
    AccountInfoDTO updateAccountInfo(@RequestBody AccountInfoDTO accountInfoDTO);

    /**
     * 描述：根据Id获取组织下的人员信息
     * @param guid 主键
     * @return 查询到的数据，未查询到返回null
     */
    @GetMapping("/organizationPersonBaseApi/getByGuid")
    OrganizationPersonDTO getOrgPersonByGuid(@RequestParam("guid") String guid);


    /**
     * 描述：根据Id获取组织架构基本信息
     *
     * @param guid 主键
     * @return 查询到的数据，未查询到返回null
     */
    @GetMapping("/organizationBaseInfoBaseApi/getByGuid")
    OrganizationBaseInfoDTO getOrgBaseInfoByGuid(@RequestParam("guid") String guid);


}