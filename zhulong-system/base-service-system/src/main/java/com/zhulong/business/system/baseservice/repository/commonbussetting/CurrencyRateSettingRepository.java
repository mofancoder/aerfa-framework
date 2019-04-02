package com.zhulong.business.system.baseservice.repository.commonbussetting;

import com.zhulong.business.system.baseservice.entity.commonbussetting.CurrencyRateSettingEntity;
import com.zhulong.framework.common.jpa.BaseRepository;
import org.springframework.stereotype.Repository;

/**
* 描述：汇率管理 Repository接口
* @author MOFAN889
* @date 2019-04-01 16:21
*/
@Repository
public interface CurrencyRateSettingRepository extends BaseRepository<CurrencyRateSettingEntity,String> {



}
