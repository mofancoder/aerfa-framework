package com.zhulong.business.system.baseservice.api.systemconfig.impl;

import com.zhulong.business.system.baseservice.enums.DayTypeEnum;
import com.zhulong.business.system.baseservice.api.systemconfig.DayTypeConfigBaseApi;
import com.zhulong.business.system.baseservice.dto.systemconfig.DayTypeConfigDTO;
import com.zhulong.business.system.baseservice.dto.systemconfig.DayTypeConfigSaveUpdateDTO;
import com.zhulong.business.system.baseservice.entity.systemconfig.DayTypeConfigEntity;
import com.zhulong.business.system.baseservice.repository.systemconfig.DayTypeConfigRepository;
import com.zhulong.framework.common.jpa.BaseDao;
import com.zhulong.framework.common.jpa.Finder;
import com.zhulong.framework.common.util.POJOConvertUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.EntityManager;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * 描述：日期类型配置 BaseApiImpl实现
 *
 * @author 初。
 * @date 2019-03-20 10:00:44
 */
@Transactional
@RestController
public class DayTypeConfigBaseApiImpl extends BaseDao implements DayTypeConfigBaseApi {

    @Autowired
    private DayTypeConfigRepository dayTypeConfigRepository;

    @Override
    public List<DayTypeConfigDTO> findAndGenerateByYear(@RequestParam("year") Short year) throws Exception {

        String hql = "select d from " + DayTypeConfigEntity.class.getSimpleName() + " d where year = :year order by month asc, day asc ";

        Finder finder = Finder.create(hql);
        finder.setParam("year", year);

        List<DayTypeConfigEntity> dayTypeConfigEntities = this.find(finder);
        if (dayTypeConfigEntities == null || dayTypeConfigEntities.size() == 0) {
            // 执行生成操作
            dayTypeConfigEntities = this.generateDataByYear(year);
        }

        return POJOConvertUtil.convertList(dayTypeConfigEntities, DayTypeConfigDTO.class);
    }

    /**
     * 生成指定年份的数据
     * @param year
     * @return
     * @throws Exception
     */
    private List<DayTypeConfigEntity> generateDataByYear(Short year) throws Exception {

        // 年份判断：1970 - 2999
        if (year == null || year < 1970 || year > 2999) {
            return  null;
        }

        String startDate = year + "0101";
        String endDate = year + "1231";
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        Date dBegin = sdf.parse(startDate);
        Date dEnd = sdf.parse(endDate);

        List<DayTypeConfigEntity> dayTypeConfigEntities = new ArrayList<>(365);
        DayTypeConfigEntity dayTypeConfigEntity = new DayTypeConfigEntity();
        dayTypeConfigEntity.setGuid(UUID.randomUUID().toString());
        dayTypeConfigEntity.setCreateTime(System.currentTimeMillis());
        dayTypeConfigEntity.setYear(Short.valueOf(year + ""));
        dayTypeConfigEntity.setMonth(Short.valueOf("1"));
        dayTypeConfigEntity.setDay(Short.valueOf("1"));
        dayTypeConfigEntity.setDayType(DayTypeEnum.UN_WORK.getType());
        dayTypeConfigEntities.add(dayTypeConfigEntity);

        Calendar calBegin = Calendar.getInstance();
        calBegin.setTime(dBegin);
        Calendar calEnd = Calendar.getInstance();
        calEnd.setTime(dEnd);

        // 此日期是否在指定日期之后
        while (dEnd.after(calBegin.getTime())) {

            // 根据日历的规则，为给定的日历字段添加或减去指定的时间量
            calBegin.add(Calendar.DAY_OF_MONTH, 1);

            dayTypeConfigEntity = new DayTypeConfigEntity();
            dayTypeConfigEntity.setGuid(UUID.randomUUID().toString());
            dayTypeConfigEntity.setCreateTime(System.currentTimeMillis());
            dayTypeConfigEntity.setYear(Short.valueOf(year + ""));
            dayTypeConfigEntity.setMonth(Short.valueOf((calBegin.get(Calendar.MONTH) + 1) + ""));
            dayTypeConfigEntity.setDay(Short.valueOf(calBegin.get(Calendar.DAY_OF_MONTH) + ""));
            dayTypeConfigEntity.setDayType(DayTypeEnum.UN_WORK.getType());
            dayTypeConfigEntities.add(dayTypeConfigEntity);

        }

        dayTypeConfigRepository.saveAll(dayTypeConfigEntities);

        return dayTypeConfigEntities;
    }

    @Override
    public void update(@RequestBody DayTypeConfigSaveUpdateDTO saveUpdateDTO) {

        List<DayTypeConfigDTO> dayTypeConfigDTOList = saveUpdateDTO.getDayTypeConfigDTOList();
        if (dayTypeConfigDTOList != null && dayTypeConfigDTOList.size() > 0) {
            List<DayTypeConfigEntity> dayTypeConfigEntities = POJOConvertUtil.convertList(dayTypeConfigDTOList, DayTypeConfigEntity.class);
            EntityManager entityManager = this.getEntityManager();
            for (DayTypeConfigEntity dayTypeConfigEntity : dayTypeConfigEntities) {
                entityManager.merge(dayTypeConfigEntity);
            }
            // TODO 会不会出问题？？？
            entityManager.flush();
        }

    }

}

















