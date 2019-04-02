package com.zhulong.business.system.busservice.feign.commonbussetting;

import com.zhulong.business.system.baseservice.api.commonbussetting.CurrencyRateSettingBaseApi;
import com.zhulong.business.system.busservice.feign.FeignConstance;
import org.springframework.cloud.openfeign.FeignClient;

/**
 * 描述：汇率管理 Feign接口
 * @author MOFAN889
 * @date 2019-04-01 16:21
 */
@FeignClient(FeignConstance.BASE_SERVICE_SYSTEM_NAME)
public interface CurrencyRateSettingBaseApiFeign extends CurrencyRateSettingBaseApi {

}