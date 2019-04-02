package com.zhulong.business.system.busservice.feign.systemconfig;

import com.zhulong.business.system.baseservice.api.systemconfig.DataDictionaryValueBaseApi;
import com.zhulong.business.system.busservice.feign.FeignConstance;
import org.springframework.cloud.openfeign.FeignClient;

/**
 * 描述：数据字典值 Feign接口
 *
 * @author 初。
 * @date 2019-03-20 09:59:31
 */
@FeignClient(FeignConstance.BASE_SERVICE_SYSTEM_NAME)
public interface DataDictionaryValueBaseApiFeign extends DataDictionaryValueBaseApi {

}