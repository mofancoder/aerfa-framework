package com.zhulong.framework.common.util;

import com.zhulong.framework.common.exception.SystemException;
import lombok.extern.slf4j.Slf4j;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.util.Enumeration;

/**
 * Created by shanb on 2019-2-19.
 * 与Ip地址相关的工具类
 */
public class IpUtils {

    /**
     * 获取本机的IP地址
     */
    public static InetAddress getLocalIpAddr(){
        try {
            Enumeration<NetworkInterface> interfaces = NetworkInterface.getNetworkInterfaces();
            while (interfaces.hasMoreElements()){
                NetworkInterface netInterface = interfaces.nextElement();
                Enumeration<InetAddress> addres = netInterface.getInetAddresses();
                while (addres.hasMoreElements()){
                    InetAddress addr = addres.nextElement();
                    if(!addr.isLoopbackAddress() && !addr.isAnyLocalAddress() && !addr.isLinkLocalAddress()
                            && addr.isSiteLocalAddress()){
                        return addr;
                    }
                }
            }

        } catch (Exception e) {
            throw new SystemException(e);
        }
        //没有获取到ip地址
        throw new SystemException("can get local addr");
    }
}