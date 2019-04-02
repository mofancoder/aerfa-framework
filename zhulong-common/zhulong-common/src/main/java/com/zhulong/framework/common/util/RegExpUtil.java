package com.zhulong.framework.common.util;

import java.util.regex.Pattern;
/**
 * 正则工具类
 * @author xxc
 * @time 2019-1-7 14:10
 */
public class RegExpUtil {

    public static final String EMAIL = "\\w+@\\w+\\.[a-z]+(\\.[a-z]+)?";

    public static final String IDCARD = "[1-9]\\d{13,16}[a-zA-Z0-9]{1}";

    public static final String MOBILE = "(\\+\\d+)?1[34578]\\d{9}$";

    /**
     * 根据输入规则匹配
     * @author xxc
     * @return boolean
     * @param regexp
     * @param input
     * @time 2019-1-7 14:13
     */
    public static boolean matches(String regexp,String input){
        return Pattern.matches( regexp , input );
    }


    /**
    * 验证邮箱
    * @author xxc
    * @return boolean
    * @param email
    * @time 2019-1-7 14:11
    */
    public static boolean checkEmail(String email) {
        return Pattern.matches(EMAIL, email);
    }

    /**
     * 验证身份证号码
     * @author xxc
     * @return boolean
     * @param idCard
     * @time 2019-1-7 14:11
     */
    public static boolean checkIdCard(String idCard) {
        return Pattern.matches(IDCARD,idCard);
    }

    /**
     * 验证手机号码
     * @author xxc
     * @return boolean
     * @param mobile
     * @time 2019-1-7 14:11
     */
    public static boolean checkMobile(String mobile) {
        return Pattern.matches(MOBILE,mobile);
    }


}