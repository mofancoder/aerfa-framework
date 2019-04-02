package com.zhulong.framework.common.util;

import com.google.common.collect.Lists;
import com.zhulong.framework.common.constant.ResultStatus;
import com.zhulong.framework.common.dto.ResultDTO;
import com.zhulong.framework.common.entity.BaseEntity;
import com.zhulong.framework.common.page.Pagination;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * web工具类
 * @author xxc
 * @time 2019-1-7 16:15
 */
public class WebUtils {

    private WebUtils() {}

    /**
     * 将返回数据封装成ResultDTO返回给外部接口
     * @author xxc
     * @return com.zhulong.framework.common.dto.ResultDTO
     * @param object
     * @time 2019-1-10 11:34
     */
    public static ResultDTO dataToWeb(Object object){

        if(object instanceof  Pagination){

            //转为为Pagination对象
            Pagination page=(Pagination)object;

            //page对象转为map，赋值给object
            object=pageToMap(page);
        }

        return ResultDTO.builder().success(true).code(String.valueOf(ResultStatus.OK)).result(object).message("").build();

    }

    /**
     * pagination对象转为map
     * @author xxc
     * @return java.util.Map
     * @param pagination
     * @time 2019-1-28 9:40
     */
    public static Map pageToMap(Pagination pagination) {

        Map<String, Object> map = new HashMap<>();
        map.put("total", pagination.getTotalCount());  //总数量
        map.put("rows", pagination.getList());   //结果
        map.put("totalPage", pagination.getTotalPage()); //总页数
        return map;
    }

    /**
     * 是否是ajax请求
     * @author xxc
     * @return java.lang.Boolean
     * @param request
     * @time 2018-11-1 10:41
     */
    public static Boolean isAjaxRequest(HttpServletRequest request){
        if ("XMLHttpRequest".equalsIgnoreCase(request.getHeader("X-Requested-With"))) {

            return true;
        }
        return false;
    }

    public static void main(String[] args) {

        BaseEntity baseEntity=new BaseEntity();
        baseEntity.setGuid("111");

        System.out.println(dataToWeb(baseEntity));

        List<BaseEntity> list = Lists.newArrayList();
        list.add(baseEntity);

        System.out.println(dataToWeb(list));

        Pagination page=new Pagination(1,10,10,list);

        System.out.println(dataToWeb(page));

    }
}
