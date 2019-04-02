package com.zhulong.business.system.busservice.feign.commonbussetting;

import com.zhulong.business.system.baseservice.api.commonbussetting.TransTypeSettingBaseApi;
import com.zhulong.business.system.busservice.feign.FeignConstance;
import org.springframework.cloud.openfeign.FeignClient;

/**
 * 描述：交易类型 Feign接口
 * @author 初。
 * @date 2019-03-28 09:17
 */
@FeignClient(FeignConstance.BASE_SERVICE_SYSTEM_NAME)
public interface TransTypeSettingBaseApiFeign extends TransTypeSettingBaseApi {

}