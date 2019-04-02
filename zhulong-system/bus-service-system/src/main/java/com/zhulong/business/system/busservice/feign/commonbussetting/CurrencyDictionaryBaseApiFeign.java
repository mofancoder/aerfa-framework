package com.zhulong.business.system.busservice.feign.commonbussetting;

import com.zhulong.business.system.baseservice.api.commonbussetting.CurrencyDictionaryBaseApi;
import com.zhulong.business.system.busservice.feign.FeignConstance;
import org.springframework.cloud.openfeign.FeignClient;

/**
 * 描述：币种管理 Feign接口
 * @author MOFAN889
 * @date 2019-04-01 11:37
 */
@FeignClient(FeignConstance.BASE_SERVICE_SYSTEM_NAME)
public interface CurrencyDictionaryBaseApiFeign extends CurrencyDictionaryBaseApi {

}