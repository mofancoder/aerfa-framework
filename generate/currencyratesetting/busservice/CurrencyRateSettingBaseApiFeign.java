package com.zhulong.business.system.baseservice.entity.commonbussetting.busservice.feign;

import com.zhulong.business.system.baseservice.entity.commonbussetting.baseservice.api.commonbussetting.CurrencyRateSettingBaseApi;
import org.springframework.cloud.openfeign.FeignClient;

/**
 * 描述：汇率管理 Feign接口
 * @author MOFAN889
 * @date 2019-04-01 16:21
 */
@FeignClient(com.zhulong.business.system.busservice.feign.FeignConstance.BASE_SERVICE_SYSTEM_NAME)
public interface CurrencyRateSettingBaseApiFeign extends CurrencyRateSettingBaseApi {

}