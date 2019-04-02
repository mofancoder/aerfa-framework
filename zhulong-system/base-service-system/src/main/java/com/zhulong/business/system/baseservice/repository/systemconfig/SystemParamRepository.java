package com.zhulong.business.system.baseservice.repository.systemconfig;

import com.zhulong.business.system.baseservice.entity.systemconfig.SystemParamEntity;
import com.zhulong.framework.common.jpa.BaseRepository;
import org.springframework.stereotype.Repository;

/**
 * 描述：系统参数 Repository接口
 *
 * @author 初。
 * @date 2019-03-20 10:01:09
 */
@Repository
public interface SystemParamRepository extends BaseRepository<SystemParamEntity, String> {

}
