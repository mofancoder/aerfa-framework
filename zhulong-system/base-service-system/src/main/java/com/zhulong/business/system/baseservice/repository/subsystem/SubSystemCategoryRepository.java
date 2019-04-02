package com.zhulong.business.system.baseservice.repository.subsystem;

import com.zhulong.framework.common.jpa.BaseRepository;
import com.zhulong.business.system.baseservice.entity.subsystem.SubSystemCategoryEntity;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.math.BigDecimal;

/**
 * Created by shanb on 2019-2-25.
 */
public interface SubSystemCategoryRepository extends BaseRepository<SubSystemCategoryEntity,String>{

    @Modifying
    @Query("update SubSystemCategoryEntity set sortNum=:sortNum where guid = :guid")
    void updateSortNum(@Param("guid") String guid,@Param("sortNum") BigDecimal sortNum);

/*    @Modifying
    @Query("update SubSystemCategoryEntity set deleted=true,deleteTime=:deleteTime where guid=:guid")
    int deleteByGuid(@Param("guid") String guid,@Param("deleteTime") Long deleteTime);*/

    @Modifying
    @Query("delete from SubSystemCategoryEntity where name like :nameLike")
    void deleteByNameLike(@Param("nameLike")String nameLike);
}
