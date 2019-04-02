package com.zhulong.business.system.baseservice.repository.systemconfig;

import com.zhulong.business.system.baseservice.dto.systemconfig.DataDictionaryDTO;
import com.zhulong.business.system.baseservice.entity.systemconfig.DataDictionaryValueEntity;
import com.zhulong.framework.common.jpa.BaseRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 * 描述：数据字典值 Repository接口
 *
 * @author 初。
 * @date 2019-03-20 09:59:31
 */
@Repository
public interface DataDictionaryValueRepository extends BaseRepository<DataDictionaryValueEntity, String> {

    @Query("update DataDictionaryValueEntity set status = :#{#dataDictionaryDTO.status}, modifyTime = :#{#dataDictionaryDTO.modifyTime}, modifierGuid = :#{#dataDictionaryDTO.modifierGuid} , modifierName = :#{#dataDictionaryDTO.modifierName} where dictionaryGuid = :#{#dataDictionaryDTO.guid} ")
    @Modifying
    int updateStatusByDataDictionary(@Param("dataDictionaryDTO") DataDictionaryDTO dataDictionaryDTO);

    @Query("delete from DataDictionaryValueEntity where dictionaryGuid = :dictionaryGuid ")
    @Modifying
    int deleteByDictionaryGuid(@Param("dictionaryGuid") String dictionaryGuid);

}


















