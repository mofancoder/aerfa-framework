package com.zhulong.framework.common.util;

import java.util.Random;
/**
 * 数字工具类
 * @author xxc
 * @time 2019-1-7 14:16
 */
public class RandomUtil {

    /**
     * 随机生成6位验证码
     * @author xxc
     * @return java.lang.Integer
     * @param
     * @time 2019-1-7 14:15
     */
    public static Integer getNum(){
        Random r = new Random();
        int x = r.nextInt(899999);
        x = x + 100000;
        return x;
    }

    public static void main(String[] args) {
        System.out.println(getNum());
    }
}
