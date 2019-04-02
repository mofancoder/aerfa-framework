package com.zhulong.business.system.busservice.feign.basesystem;

import com.zhulong.business.system.baseservice.api.organization.OrganizationBaseInfoBaseApi;
import org.springframework.cloud.openfeign.FeignClient;

/**
 * 描述：组织管理 Feign接口
 * @author shanb
 * @date 2019-03-27 10:14
 */
@FeignClient(com.zhulong.business.system.busservice.feign.FeignConstance.BASE_SERVICE_SYSTEM_NAME)
public interface OrganizationBaseInfoBaseApiFeign extends OrganizationBaseInfoBaseApi {

}