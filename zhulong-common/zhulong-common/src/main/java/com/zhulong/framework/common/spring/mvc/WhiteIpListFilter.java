package com.zhulong.framework.common.spring.mvc;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;

/**
 * Created by shanb on 2019-3-8.
 */
@Slf4j
public class WhiteIpListFilter extends GenericFilterBean {

    private List<Pattern> whiteIpPatternList = new ArrayList<>();

    private static final List<String> LOCAL_IP = Arrays.asList("127.0.0.1","0:0:0:0:0:0:0:1");

    public WhiteIpListFilter(String ips){
        if(!StringUtils.isEmpty(ips)){
            for (String ip : ips.split(",")) {
                Pattern p = Pattern.compile(ip);
                whiteIpPatternList.add(p);
            }
        }
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain) throws IOException, ServletException {
        //获取请求ip,此IP一定要为调用者的IP,各个请求的调用，都应该携带正确的IP信息
        String ip = request.getRemoteAddr();
        log.debug("the invoke ip:{}",ip);
        if(LOCAL_IP.contains(ip)){
            filterChain.doFilter(request,response);
            return;
        }else{
            if(!whiteIpPatternList.isEmpty() ){
                if(whiteIpPatternList.stream().anyMatch(p->  p.matcher(ip).matches())) {
                    filterChain.doFilter(request, response);
                    return;
                }
            }
        }
        if(response instanceof HttpServletResponse) {
            ((HttpServletResponse) response).setStatus(HttpStatus.BAD_REQUEST.value());
        }
        PrintWriter printWriter = response.getWriter();
        printWriter.write("not white ip allow,confuse invoke!");
        printWriter.flush();
        printWriter.close();

    }
}