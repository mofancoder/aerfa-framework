package com.zhulong.business.system.baseservice.repository.common;

import com.zhulong.framework.common.jpa.BaseRepository;
import com.zhulong.business.system.baseservice.entity.common.DataModifyRecordEntity;
import org.springframework.stereotype.Repository;

/**
* 描述：数据修改记录 Repository接口
* @author shanb
* @date 2019-03-27 17:41
*/
@Repository
public interface DataModifyRecordRepository extends BaseRepository<DataModifyRecordEntity,String> {


}
