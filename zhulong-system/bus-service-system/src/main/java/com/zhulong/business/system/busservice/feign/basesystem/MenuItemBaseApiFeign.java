package com.zhulong.business.system.busservice.feign.basesystem;

import com.zhulong.business.system.baseservice.api.subsystem.MenuItemBaseApi;
import com.zhulong.business.system.busservice.feign.FeignConstance;
import org.springframework.cloud.openfeign.FeignClient;

/**
* 描述：菜单项管理 Feign接口
* @author shanb
* @date 2019-03-18 11:44:45
*/
@FeignClient(FeignConstance.BASE_SERVICE_SYSTEM_NAME)
public interface MenuItemBaseApiFeign extends MenuItemBaseApi {


}