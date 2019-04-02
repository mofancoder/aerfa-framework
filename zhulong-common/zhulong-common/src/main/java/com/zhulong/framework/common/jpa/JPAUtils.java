package com.zhulong.framework.common.jpa;

import com.zhulong.framework.common.dto.PageOrderDTO;
import com.zhulong.framework.common.dto.QueryPageDTO;
import com.zhulong.framework.common.page.Pagination;
import com.zhulong.framework.common.util.POJOConvertUtil;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.stream.Collectors;

/**
 * JPA一些工具类及转化
 * Created by shanb on 2019-2-26.
 */
public class JPAUtils {

    /**
     * 将传入的参数转化成jpa要用的pageable参数
     * @param queryPageDTO 查询传入的分页参数
     */
    public static Pageable toJpaPageable(QueryPageDTO queryPageDTO){
        return PageRequest.of(queryPageDTO.getPageNo()-1,queryPageDTO.getPageSize());
    }

    /**
     * 将传入的参数转化成jpa要用的pageable参数，且带分页效果
     * @param pageOrderDto 传入的分页和排序的参数
     * @param defaultSort 如传入的分页排序参数为空，默认的排序对象
     */
    public static Pageable toJpaPageableAndSort(PageOrderDTO pageOrderDto, Sort defaultSort){
        //构造sort
        Sort sort = defaultSort;
        if(!StringUtils.isEmpty(pageOrderDto.getProperties())){
            Sort.Direction direction = stringToDirection(pageOrderDto.getDirection());
            //固定加上guid,防止分页的时候，产生重复数据的问题。
            sort = Sort.by(direction,pageOrderDto.getProperties(),"guid");
        }
        Pageable pageable = PageRequest.of(pageOrderDto.getPageNo()-1,pageOrderDto.getPageSize(),sort);
        return pageable;
    }

    public static <S,T> Pagination<T> jpaPageToPagination(Page<S> page,Class<T> dataClass){
        List<S> sourceDataList = page.getContent();
        List<T> targetList = sourceDataList.stream().map((s)-> POJOConvertUtil.convert(s,dataClass))
                                .collect(Collectors.toList());
        Pagination<T> rtPage = new Pagination<>(page.getNumber()+1,page.getSize(),Long.valueOf(page.getTotalElements()).intValue(),targetList);
        return rtPage;
    }

    private static Sort.Direction stringToDirection(String directionStr){
        if(!StringUtils.isEmpty(directionStr)){
            return Sort.Direction.fromString(directionStr);
        }
        return Sort.Direction.ASC;
    }
}