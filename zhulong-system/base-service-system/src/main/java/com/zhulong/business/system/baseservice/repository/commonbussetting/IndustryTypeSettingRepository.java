package com.zhulong.business.system.baseservice.repository.commonbussetting;

import com.zhulong.framework.common.jpa.BaseRepository;
import com.zhulong.business.system.baseservice.entity.commonbussetting.IndustryTypeSettingEntity;
import org.springframework.stereotype.Repository;

/**
 * 描述：国民经济行业分类 Repository接口
 * @author 初。
 * @date 2019-03-28 09:18
 */
@Repository
public interface IndustryTypeSettingRepository extends BaseRepository<IndustryTypeSettingEntity, String> {

}
