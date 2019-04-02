package com.zhulong.business.system.busservice.feign.basesystem;

import com.zhulong.business.system.baseservice.api.subsystem.HomePageConfigBaseApi;
import com.zhulong.business.system.busservice.feign.FeignConstance;
import org.springframework.cloud.openfeign.FeignClient;

/**
 * 首页管理-feign调用对象。
 * Created by shanb on 2019-2-27.
 */
@FeignClient(FeignConstance.BASE_SERVICE_SYSTEM_NAME)
public interface HomePageConfigBaseApiFeign extends HomePageConfigBaseApi {

}