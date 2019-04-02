package com.zhulong.business.system.busservice.feign.commonbussetting;

import com.zhulong.business.system.baseservice.api.commonbussetting.CodeRuleSettingBaseApi;
import com.zhulong.business.system.busservice.feign.FeignConstance;
import org.springframework.cloud.openfeign.FeignClient;

/**
 * 描述：编码生成规则 Feign接口
 *
 * @author 初。
 * @date 2019-03-28 10:21
 */
@FeignClient(FeignConstance.BASE_SERVICE_SYSTEM_NAME)
public interface CodeRuleSettingBaseApiFeign extends CodeRuleSettingBaseApi {

}