package com.zhulong.business.system.busservice.feign.basesystem;

import com.zhulong.business.system.baseservice.api.subsystem.FunctionConfigBaseApi;
import org.springframework.cloud.openfeign.FeignClient;

/**
 * 描述：功能管理 Feign接口
 * @author shanb
 * @date 2019-03-26 15:52
 */
@FeignClient(com.zhulong.business.system.busservice.feign.FeignConstance.BASE_SERVICE_SYSTEM_NAME)
public interface FunctionConfigBaseApiFeign extends FunctionConfigBaseApi {

}