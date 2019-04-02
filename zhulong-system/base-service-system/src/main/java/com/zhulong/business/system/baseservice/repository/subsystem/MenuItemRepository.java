package com.zhulong.business.system.baseservice.repository.subsystem;

import com.zhulong.framework.common.dto.KeyValueDTO;
import com.zhulong.framework.common.jpa.BaseRepository;
import com.zhulong.business.system.baseservice.entity.subsystem.MenuItemEntity;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

/**
* 描述：菜单项管理 Repository接口
* @author shanb
* @date 2019-03-18 11:44:45
*/
@Repository
public interface MenuItemRepository extends BaseRepository<MenuItemEntity,String> {

    @Modifying
    @Query("update MenuItemEntity set sortNum=:sortNum where guid=:guid")
    void updateSortNum(@Param("guid") String guid, @Param("sortNum") BigDecimal sortNum);

}
