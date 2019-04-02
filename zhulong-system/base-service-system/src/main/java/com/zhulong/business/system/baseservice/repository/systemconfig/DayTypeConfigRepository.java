package com.zhulong.business.system.baseservice.repository.systemconfig;

import com.zhulong.business.system.baseservice.entity.systemconfig.DayTypeConfigEntity;
import com.zhulong.framework.common.jpa.BaseRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 * 描述：日期类型配置 Repository接口
 *
 * @author 初。
 * @date 2019-03-20 10:00:44
 */
@Repository
public interface DayTypeConfigRepository extends BaseRepository<DayTypeConfigEntity, String> {

    @Query("select count(d) from DayTypeConfigEntity d where d.year = :year ")
    long countDataByYear(@Param("year") Short year) ;

}
