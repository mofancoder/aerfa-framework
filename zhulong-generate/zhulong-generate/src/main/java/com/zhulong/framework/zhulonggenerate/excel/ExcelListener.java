package com.zhulong.framework.zhulonggenerate.excel;

import com.alibaba.excel.read.context.AnalysisContext;
import com.alibaba.excel.read.event.AnalysisEventListener;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
/**
 * excel处理监听
 * @author xxc
 * @time 2019-3-4 10:42
 */
@Setter
@Getter
public class  ExcelListener extends AnalysisEventListener {

    private List<ExcelInfo> datas = new ArrayList<ExcelInfo>();

    @Override
    public void invoke(Object object, AnalysisContext context) {
        //System.out.println("当前行序号："+context.getCurrentRowNum());
        //System.out.println("当前行数据："+object.toString());

        //第0行是表头，不用添加到数据中
        if(context.getCurrentRowNum()!=0){
            datas.add((ExcelInfo)object);//数据存储到list，供批量处理，或后续自己业务逻辑处理。
        }
    }


    @Override
    public void doAfterAllAnalysed(AnalysisContext analysisContext) {
        System.out.println("doAfterAllAnalysed");

    }
}
