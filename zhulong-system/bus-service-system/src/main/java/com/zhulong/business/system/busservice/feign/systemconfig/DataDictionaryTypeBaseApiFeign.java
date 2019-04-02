package com.zhulong.business.system.busservice.feign.systemconfig;

import com.zhulong.business.system.baseservice.api.systemconfig.DataDictionaryTypeBaseApi;
import com.zhulong.business.system.busservice.feign.FeignConstance;
import org.springframework.cloud.openfeign.FeignClient;

/**
 * 描述：数据字典类型 Feign接口
 *
 * @author 初。
 * @date 2019-03-20 10:00:06
 */
@FeignClient(FeignConstance.BASE_SERVICE_SYSTEM_NAME)
public interface DataDictionaryTypeBaseApiFeign extends DataDictionaryTypeBaseApi {

}