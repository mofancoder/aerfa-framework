package com.zhulong.business.system.baseservice.entity.commonbussetting.busservice.feign;

import com.zhulong.business.system.baseservice.entity.commonbussetting.baseservice.api.commonbussetting.AgreementSettingBaseApi;
import org.springframework.cloud.openfeign.FeignClient;

/**
 * 描述：协议管理 Feign接口
 * @author MOFAN889
 * @date 2019-04-01 18:06
 */
@FeignClient(com.zhulong.business.system.busservice.feign.FeignConstance.BASE_SERVICE_SYSTEM_NAME)
public interface AgreementSettingBaseApiFeign extends AgreementSettingBaseApi {

}