package com.zhulong.framework.zhulonggenerate.typeconvert;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by shanb on 2019-3-25.
 */
public class RegisterConvert {

    public static List<Convert> convertList = new ArrayList<>();

    static{
        convertList.add(new VarcharConvert());
        convertList.add(new SmallintConvert());
        convertList.add(new NumericConvert());
        convertList.add(new IntConvert());
        convertList.add(new BoolConvert());
        convertList.add(new BigintConvert());
    }
}