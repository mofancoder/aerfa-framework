package com.zhulong.framework.tcc;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.mengyun.tcctransaction.api.TransactionContext;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * Created by shanb on 2019-1-25.
 */
public class SetTccContextFilter extends GenericFilterBean {

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        String tccContext = ((HttpServletRequest)servletRequest).getHeader(TccConstant.TCC_HEAD_TOKEN_KEY);
        if(!StringUtils.isEmpty(tccContext)){
            ObjectMapper mapper = new ObjectMapper();
            TransactionContext context = mapper.readValue(tccContext, TransactionContext.class);
            TccContextStore.set(context);
        }
        try {
            filterChain.doFilter(servletRequest, servletResponse);
        }finally {
            TccContextStore.remove();
        }
    }
}