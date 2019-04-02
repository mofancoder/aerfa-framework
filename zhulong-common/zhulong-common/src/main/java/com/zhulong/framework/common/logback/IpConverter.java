package com.zhulong.framework.common.logback;

import ch.qos.logback.classic.pattern.ClassicConverter;
import ch.qos.logback.classic.spi.ILoggingEvent;
import com.zhulong.framework.common.util.IpUtils;
import lombok.extern.slf4j.Slf4j;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.util.Enumeration;

/**
 * 用于logback中转化ip的打印。
 * Created by shanb on 2019-2-13.
 */
public class IpConverter extends ClassicConverter {

    private String ip;

    @Override
    public void start() {
        super.start();
        //获取ip进行设置
        ip = IpUtils.getLocalIpAddr().getHostAddress();
    }

    @Override
    public String convert(ILoggingEvent iLoggingEvent) {
        return ip;
    }

}