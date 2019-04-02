package com.zhulong.framework.tcc;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import feign.RequestInterceptor;
import feign.RequestTemplate;
import org.mengyun.tcctransaction.api.TransactionContext;

/**
 * feign的拦截器，在feign调用之前，加入tcc xid信息到头中。
 * Created by shanb on 2019-1-25.
 */
public class AddTccContextInterceptor implements RequestInterceptor {

    @Override
    public void apply(RequestTemplate requestTemplate) {
        TransactionContext context = TccContextStore.get();
        if(context!=null){
            ObjectMapper objectMapper = new ObjectMapper();
            try {
                String contextJson = objectMapper.writeValueAsString(context);
                requestTemplate.header(TccConstant.TCC_HEAD_TOKEN_KEY,contextJson);
            } catch (JsonProcessingException e) {
                //需要抛出异常
                throw new RuntimeException(e);
            }finally {
                //删除当前值，否则并行调用出现问题
                TccContextStore.remove();
            }
        }
    }
}