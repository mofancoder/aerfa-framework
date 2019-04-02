package com.zhulong.business.system.baseservice.repository.systemconfig;

import com.zhulong.business.system.baseservice.entity.systemconfig.SystemParamTypeEntity;
import com.zhulong.framework.common.jpa.BaseRepository;
import org.springframework.stereotype.Repository;

/**
 * 描述：系统参数分类 Repository接口
 *
 * @author 初。
 * @date 2019-03-20 10:01:25
 */
@Repository
public interface SystemParamTypeRepository extends BaseRepository<SystemParamTypeEntity, String> {

}
