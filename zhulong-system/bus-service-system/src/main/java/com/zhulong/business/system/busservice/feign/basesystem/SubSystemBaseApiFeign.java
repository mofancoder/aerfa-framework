package com.zhulong.business.system.busservice.feign.basesystem;

import com.zhulong.business.system.baseservice.api.subsystem.SubSystemBaseApi;
import com.zhulong.business.system.busservice.feign.FeignConstance;
import org.springframework.cloud.openfeign.FeignClient;

/**
 * Created by shanb on 2019-2-26.
 */
@FeignClient(FeignConstance.BASE_SERVICE_SYSTEM_NAME)
public interface SubSystemBaseApiFeign extends SubSystemBaseApi{

}