package com.zhulong.business.system.busservice.feign.basesystem;

import com.zhulong.business.system.baseservice.api.user.UserBaseApi;
import com.zhulong.business.system.busservice.feign.FeignConstance;
import org.springframework.cloud.openfeign.FeignClient;

/**
 * Created by shanb on 2019-1-16.
 */
@FeignClient(FeignConstance.BASE_SERVICE_SYSTEM_NAME)
public interface UserBaseApiFeign extends UserBaseApi {

}