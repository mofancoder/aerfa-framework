package com.zhulong.framework.zhulonggenerate.boot;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 接口常量
 * @author xxc
 * @time 2019-2-28 9:41
 */
public interface ConstantInterface {

    //jdbc连接信息
    String url = "jdbc:mysql://zlWorkStation:3306/test?useUnicode=true&characterEncoding=UTF-8";
    String userName = "root";
    String password = "root";
    String driver = "com.mysql.jdbc.Driver";


    /******* 代码生成相关信息 start *********/

    String excelFileName = "AGREEMENT_SETTING";

    //注释作者
    String author = "MOFAN889";

    //数据库表名，使用小写
    String tableName = "AGREEMENT_SETTING";

    //表名注释
    String tableAnnotation = "协议管理";

    //包名
    String packageName = "com.zhulong.business.system.baseservice.entity.commonbussetting";

    //模块名
    String moduleName = "commonbussetting";

    //基础服务层名称
    String baseServiceName = "";

    //基础服务层常量名称   与基础服务层名称二选一。常量优先使用
    String baseServiceNameConstant = "com.zhulong.business.system.busservice.feign.FeignConstance.BASE_SERVICE_SYSTEM_NAME";

    //生成文件位置
    String diskPath = "F:\\cloudplatform-workspace\\generate\\agreementSetting";

    /******* 代码生成相关信息 end *********/

}
