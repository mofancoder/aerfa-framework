package com.zhulong.test;

import com.alibaba.fastjson.JSON;
import com.zhulong.business.system.baseservice.entity.user.UserEntity;

/**
 * Author: 初。
 * Date: 2019-3-25
 * Time: 14:03
 */
public class UserTest {

    public static void main(String[] args) {

        UserEntity userEntity = new UserEntity();
        userEntity.setAccount("shanb222333");
        userEntity.setUserName("shanb1231232");
        userEntity.setPassword("$2a$10$HKm7RXL7VOkNkYtzCfFhpOQ.xNW.xbdPfA.NJ.xzqNmYNK.p3MbVO");
        userEntity.setDeptGuid("11");
        userEntity.setDeptName("1233211");
        userEntity.setIdType(Short.valueOf("1"));
        userEntity.setIdNum("4302231234567");
        userEntity.setPhoneNum("18515620548");
        userEntity.setTelNum("010-1233");
        userEntity.setEmail("shanb@qq.com");
        userEntity.setValidate(true);
        userEntity.setChangeStatusReason("");
        userEntity.setLocked(false);
        userEntity.setLockedTime(null);
        userEntity.setSortNum(1);
        userEntity.setDescription("");

        String s = JSON.toJSONString(userEntity);
        System.out.println(s);

    }

}




















