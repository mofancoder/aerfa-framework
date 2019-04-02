package com.zhulong.business.system.baseservice.repository.systemconfig;

import com.zhulong.business.system.baseservice.entity.systemconfig.WorkingTimeConfigEntity;
import com.zhulong.framework.common.jpa.BaseRepository;
import org.springframework.stereotype.Repository;

/**
 * 描述：工作时间管理 Repository接口
 *
 * @author 初。
 * @date 2019-03-21 04:05:52
 */
@Repository
public interface WorkingTimeConfigRepository extends BaseRepository<WorkingTimeConfigEntity, String> {

}
