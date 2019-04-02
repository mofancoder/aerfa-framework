package com.zhulong.business.system.baseservice.repository.commonbussetting;

import com.zhulong.framework.common.jpa.BaseRepository;
import com.zhulong.business.system.baseservice.entity.commonbussetting.AreaSettingEntity;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;

/**
 * 描述：行政区域 Repository接口
 * @author 初。
 * @date 2019-03-28 09:23
 */
@Repository
public interface AreaSettingRepository extends BaseRepository<AreaSettingEntity, String> {

    @Modifying
    @Query("update AreaSettingEntity set sortNum = :sortNum where guid = :guid")
    void updateSortNum(@Param("guid") String guid, @Param("sortNum") BigDecimal sortNum);

}
