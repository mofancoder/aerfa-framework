package com.zhulong.business.system.baseservice.repository.subsystem;

import com.zhulong.business.system.baseservice.entity.subsystem.SubSystemEntity;
import com.zhulong.framework.common.jpa.BaseRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.math.BigDecimal;

/**
 * Created by shanb on 2019-2-26.
 */
public interface SubSystemRepository extends BaseRepository<SubSystemEntity,String> {

/*    @Query("update SubSystemEntity set deleted=true,deleteTime=:deleteTime where guid=:guid")
    @Modifying
    int deleteByGuid(@Param("guid") String guid,@Param("deleteTime") Long deleteTime);*/

    @Query("update SubSystemEntity set sortNum=:sortNum where guid=:guid")
    @Modifying
    void updateSortNum(@Param("guid") String guid,@Param("sortNum") BigDecimal sortNum);

    @Query("delete from SubSystemEntity where categoryGuid in (select guid from SubSystemCategoryEntity where code =:subSystemCode)")
    @Modifying
    void deleteBySubSystemCode(@Param("subSystemCode") String subSystemCode);
}
