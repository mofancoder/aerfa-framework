package com.zhulong.framework.localmessage.jpa.dao;

import com.zhulong.framework.common.jpa.BaseRepository;
import com.zhulong.framework.localmessage.jpa.entity.LocalMessage;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * Created by shanb on 2018-12-18.
 */
public interface LocalMessageDao extends BaseRepository<LocalMessage,String> {

    @Query("select t from LocalMessage t where t.status=:status order by t.lastPushTime asc")
    List<LocalMessage> queryListByStatus(@Param("status") short status, Pageable pageable);

}