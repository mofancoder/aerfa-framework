package com.zhulong.business.system.busservice.feign.commonbussetting;

import com.zhulong.business.system.baseservice.api.commonbussetting.AreaSettingBaseApi;
import com.zhulong.business.system.busservice.feign.FeignConstance;
import org.springframework.cloud.openfeign.FeignClient;

/**
 * 描述：行政区域 Feign接口
 * @author 初。
 * @date 2019-03-28 09:23
 */
@FeignClient(FeignConstance.BASE_SERVICE_SYSTEM_NAME)
public interface AreaSettingBaseApiFeign extends AreaSettingBaseApi {

}