package com.zhulong.business.system.baseservice.repository.subsystem;

import com.zhulong.business.system.baseservice.entity.subsystem.HomePageConfigEntity;
import com.zhulong.framework.common.jpa.BaseRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * Created by shanb on 2019-2-28.
 */
public interface HomePageConfigRepository extends BaseRepository<HomePageConfigEntity,String> {

/*    @Query("update HomePageConfigEntity set deleted=true,deleteTime=:deleteTime where guid=:guid")
    @Modifying
    int deleteByGuid(@Param("guid") String guid, @Param("deleteTime") Long deleteTime);*/

    List<HomePageConfigEntity> findBySubSystemGuidAndBusModelAndStatus(String subSystemGuid,Short busModel,Short status);
}
