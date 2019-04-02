package com.zhulong.business.system.baseservice.repository.systemconfig;

import com.zhulong.business.system.baseservice.entity.systemconfig.DataDictionaryEntity;
import com.zhulong.framework.common.jpa.BaseRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 * 描述：数据字典 Repository接口
 *
 * @author 初。
 * @date 2019-03-19 02:01:42
 */
@Repository
public interface DataDictionaryRepository extends BaseRepository<DataDictionaryEntity, String> {

    @Query("select count(d) from DataDictionaryEntity d where d.typeGuid = :typeGuid ")
    long countDataDictionaryByTypeGuid(@Param("typeGuid") String typeGuid) ;

}
