package com.zhulong.business.system.baseservice.repository.systemconfig;

import com.zhulong.business.system.baseservice.entity.systemconfig.DataDictionaryTypeEntity;
import com.zhulong.framework.common.jpa.BaseRepository;
import org.springframework.stereotype.Repository;

/**
 * 描述：数据字典类型 Repository接口
 *
 * @author 初。
 * @date 2019-03-20 10:00:06
 */
@Repository
public interface DataDictionaryTypeRepository extends BaseRepository<DataDictionaryTypeEntity, String> {

}
