package com.zhulong.business.system.baseservice.repository.user;

import com.zhulong.business.system.baseservice.entity.user.UserEntity;
import com.zhulong.framework.common.jpa.BaseRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 * 用户管理，基础服务类
 * Created by shanb on 2019-1-16.
 */
public interface UserRepository extends BaseRepository<UserEntity,String> {

    /**
     * 通过guid逻辑删除用户
     * @param guid 用户主键
     * @return 返回影响行数
     */
/*    @Modifying
    @Query("UPDATE UserEntity SET isDeleted=true where guid=:guid")
    int deleteByGuid(@Param("guid") String guid);*/

    /**
     * 通过账号获取账户信息
     * @param account 账号
     * @return 用户实体信息
     */
    UserEntity getByAccount(String account);
}
