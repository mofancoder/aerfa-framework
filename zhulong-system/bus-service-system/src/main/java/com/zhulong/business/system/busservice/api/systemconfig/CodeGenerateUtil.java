package com.zhulong.business.system.busservice.api.systemconfig;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

/**
 * 说明：这是一个临时的生成编码的类，后期会统一编码生成规则
 * <p>
 * Author: 初。
 * Date: 2019-3-19
 * Time: 15:58
 */
public class CodeGenerateUtil {

    /**
     * 生成编码
     *
     * @return  yyyyMMddHHmmss + 6位随机数
     */
    public static String generateCode() {

        StringBuilder sb = new StringBuilder();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
        sb.append(sdf.format(new Date()));
        Random random = new Random();
        Integer sequence = random.nextInt(999999);

        return sb.append(String.format("%06d", sequence)).toString();
    }

}
