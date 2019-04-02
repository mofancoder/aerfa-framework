package com.zhulong.business.system.baseservice.entity.commonbussetting.busservice.feign;

import com.zhulong.business.system.baseservice.entity.commonbussetting.baseservice.api.commonbussetting.CurrencyDictionaryBaseApi;
import org.springframework.cloud.openfeign.FeignClient;

/**
 * 描述：币种管理 Feign接口
 * @author MOFAN889
 * @date 2019-04-01 11:37
 */
@FeignClient(com.zhulong.business.system.busservice.feign.FeignConstance.BASE_SERVICE_SYSTEM_NAME)
public interface CurrencyDictionaryBaseApiFeign extends CurrencyDictionaryBaseApi {

}