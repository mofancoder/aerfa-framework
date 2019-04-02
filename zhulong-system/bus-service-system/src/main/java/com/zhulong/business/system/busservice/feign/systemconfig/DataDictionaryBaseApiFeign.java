package com.zhulong.business.system.busservice.feign.systemconfig;

import com.zhulong.business.system.baseservice.api.systemconfig.DataDictionaryBaseApi;
import com.zhulong.business.system.busservice.feign.FeignConstance;
import org.springframework.cloud.openfeign.FeignClient;

/**
 * 描述：数据字典 Feign接口
 *
 * @author 初。
 * @date 2019-03-19 02:01:42
 */
@FeignClient(FeignConstance.BASE_SERVICE_SYSTEM_NAME)
public interface DataDictionaryBaseApiFeign extends DataDictionaryBaseApi {

}