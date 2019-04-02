package com.zhulong.business.system.busservice.feign.systemconfig;

import com.zhulong.business.system.baseservice.api.systemconfig.SystemParamBaseApi;
import com.zhulong.business.system.busservice.feign.FeignConstance;
import org.springframework.cloud.openfeign.FeignClient;

/**
* 描述：系统参数 Feign接口
* @author 初。
* @date 2019-03-20 10:01:09
*/
@FeignClient(FeignConstance.BASE_SERVICE_SYSTEM_NAME)
public interface SystemParamBaseApiFeign extends SystemParamBaseApi {

}