package com.zhulong.business.system.baseservice.repository.commonbussetting;

import com.zhulong.framework.common.jpa.BaseRepository;
import com.zhulong.business.system.baseservice.entity.commonbussetting.CodeRuleSettingEntity;
import org.springframework.stereotype.Repository;

/**
 * 描述：编码生成规则 Repository接口
 *
 * @author 初。
 * @date 2019-03-28 10:21
 */
@Repository
public interface CodeRuleSettingRepository extends BaseRepository<CodeRuleSettingEntity, String> {

}
