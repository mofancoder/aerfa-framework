package com.zhulong.framework.zhulonggenerate.excel;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.metadata.BaseRowModel;
import lombok.Data;

/**
 * excel表格属性类型
 * @author xxc
 * @time 2019-3-1 9:40
 */
@Data
public class ExcelInfo extends BaseRowModel {

    @ExcelProperty(index = 0)
    private String cloumnName;  //属性名

    @ExcelProperty(index = 1)
    private String typeName;   //类型

    @ExcelProperty(index = 2)
    private String condition;   //条件

    @ExcelProperty(index = 3)
    private String remarks;   //注释

    @ExcelProperty(index = 4)
    private String isRequired;

    @ExcelProperty(index = 5)
    private String isQueryParam; //是否查询参数

    @ExcelProperty(index = 6)
    private String isSaveData;//是否保存的数据

    @ExcelProperty(index = 7)
    private String isUpdateData;//是否更新的数据

    @ExcelProperty(index = 8)
    private String isListData;//是否列表显示的数据

    @ExcelProperty(index = 9)
    private String isShowData;//是否查看页面展示的数据
}
