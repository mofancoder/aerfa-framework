package com.zhulong.business.system.busservice.feign.systemconfig;

import com.zhulong.business.system.baseservice.api.systemconfig.WorkingTimeConfigBaseApi;
import com.zhulong.business.system.busservice.feign.FeignConstance;
import org.springframework.cloud.openfeign.FeignClient;

/**
 * 描述：工作时间管理 Feign接口
 *
 * @author 初。
 * @date 2019-03-21 04:05:52
 */
@FeignClient(FeignConstance.BASE_SERVICE_SYSTEM_NAME)
public interface WorkingTimeConfigBaseApiFeign extends WorkingTimeConfigBaseApi {

}