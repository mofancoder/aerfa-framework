package com.zhulong.business.system.baseservice.repository.subsystem;

import com.zhulong.business.system.baseservice.entity.subsystem.MenuConfigEntity;
import com.zhulong.framework.common.jpa.BaseRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.math.BigDecimal;

/**
 * 菜单管理-JPA查询对象
 * Created by shanb on 2019-2-28.
 */
public interface MenuConfigRepository extends BaseRepository<MenuConfigEntity,String> {

/*    @Query("update MenuConfigEntity set deleted=true,deleteTime=:deleteTime where guid=:guid")
    @Modifying
    int deleteByGuid(@Param("guid") String guid, @Param("deleteTime") Long deleteTime);*/

    @Query("update MenuConfigEntity set sortNum=:sortNum where guid=:guid")
    @Modifying
    void updateSortNum(@Param("guid") String guid,@Param("sortNum") BigDecimal sortNum);
}
