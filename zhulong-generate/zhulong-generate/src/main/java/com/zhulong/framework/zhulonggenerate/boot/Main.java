package com.zhulong.framework.zhulonggenerate.boot;

import com.zhulong.framework.zhulonggenerate.util.CodeGenerateUtils;

/**
 * 入口启动类
 * 注意：启动之前， 先修改ConstantInterface中对应的值，
 * generate()方法有2中方法，dataBase和excel，根据需要修改，
 * 并且如果是excel方式，还得编辑table.xlsx中的属性
 * @author xxc
 * @time 2019-2-28 9:34
 */
public class Main {

    public static void main(String[] args) throws Exception {

        CodeGenerateUtils.generate(GenerateType.excel);

    }
}
