package com.zhulong.business.system.baseservice.repository.organization;

import com.zhulong.framework.common.jpa.BaseRepository;
import com.zhulong.business.system.baseservice.entity.organization.OrganizationBaseInfoEntity;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;

/**
* 描述：组织管理 Repository接口
* @author shanb
* @date 2019-03-27 10:14
*/
@Repository
public interface OrganizationBaseInfoRepository extends BaseRepository<OrganizationBaseInfoEntity,String> {


    /**
     * 更新序号
     * @param guid 主键
     * @param sortNum 排序号
     */
    @Modifying
    @Query("update OrganizationBaseInfoEntity set sortNum=:sortNum where guid=:guid")
    void updateSortNum(@Param("guid") String guid, @Param("sortNum") BigDecimal sortNum);

}
