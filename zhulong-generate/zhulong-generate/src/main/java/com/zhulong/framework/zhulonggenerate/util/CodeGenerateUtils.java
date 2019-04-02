package com.zhulong.framework.zhulonggenerate.util;

import com.alibaba.excel.ExcelReader;
import com.alibaba.excel.metadata.Sheet;
import com.alibaba.excel.support.ExcelTypeEnum;
import com.zhulong.framework.zhulonggenerate.boot.ConstantInterface;
import com.zhulong.framework.zhulonggenerate.boot.GenerateType;
import com.zhulong.framework.zhulonggenerate.excel.ExcelInfo;
import com.zhulong.framework.zhulonggenerate.excel.ExcelListener;
import freemarker.template.Template;

import java.io.*;
import java.util.Iterator;
import java.util.List;

/**
 * 代码生成工具
 * @author xxc
 * @time 2019-2-28 9:27
 */
public class CodeGenerateUtils {


    public static void generate(GenerateType generateType) throws Exception{
        if(generateType==GenerateType.dataBase){
            System.out.println("暂不支持");
        }else{
            System.out.println("解析数据===================");
            InputStream inputStream = CodeGenerateUtils.class.getClassLoader().getResourceAsStream("files/"+ConstantInterface.excelFileName+".xlsx");

            // 解析每行结果在listener中处理
            ExcelListener listener = new ExcelListener();

            ExcelReader excelReader = new ExcelReader(inputStream, ExcelTypeEnum.XLSX, null, listener);

            excelReader.read(new Sheet(1, 0, ExcelInfo.class));

            excelReader.finish();

            inputStream.close();

            //得到excel中表格数据
            List<ExcelInfo> excelInfoList = listener.getDatas();
            // 剔除多余的无用的数据
            Iterator<ExcelInfo> excelInfoIterator = excelInfoList.iterator();
            while (excelInfoIterator.hasNext()) {
                String columnName = excelInfoIterator.next().getCloumnName();
                if (columnName == null || "" == columnName) {
                    excelInfoIterator.remove();
                }
            }

            GenerateRequiredData requiredData = GenerateRequiredData.getGenerateRequiredData(excelInfoList);
            System.out.println("数据解析完毕=====================");

            System.out.println("开始基础服务层接口项目文件的生成================");
            generateBaseApiFile(requiredData);

            System.out.println("完成基础服务层接口项目文件的生成================");

            System.out.println("开始基础服务层实现项目文件的生成================");

            generateBaseServiceFile(requiredData);

            System.out.println("完成基础服务层实现项目文件的生成================");

            System.out.println("开始业务层项目文件的生成========================");

            generateBusServiceFile(requiredData);

            System.out.println("开始业务层项目文件的生成========================");
        }

    }

    private static void generateBusServiceFile(GenerateRequiredData requiredData) throws Exception {

        String[] templateNameArray = new String[]{"BaseApiFeign","BusApi","BusApiImpl","ListDTO","SaveDTO","ShowDTO","UpdateDTO"};
        File baseDirFile = new File(ConstantInterface.diskPath+File.separator+"busservice");
        if(!baseDirFile.exists() || !baseDirFile.isDirectory()){
            baseDirFile.mkdirs();
        }
        for (String templateName : templateNameArray) {
            File outFile = new File(baseDirFile,requiredData.getTable_name()+templateName+".java");
            generateFileByTemplate("busservice/"+templateName+".ftl",outFile,requiredData);
        }
    }

    private static void generateBaseApiFile(GenerateRequiredData requiredData) throws Exception {

        String[] templateNameArray = new String[]{"BaseApi","DTO","QueryDTO"};
        File baseDirFile = new File(ConstantInterface.diskPath+File.separator+"baseserviceapi");
        if(!baseDirFile.exists() || !baseDirFile.isDirectory()){
            baseDirFile.mkdirs();
        }
        for (String templateName : templateNameArray) {
            File outFile = new File(baseDirFile,requiredData.getTable_name()+templateName+".java");
            generateFileByTemplate("baseserviceapi/"+templateName+".ftl",outFile,requiredData);
        }
    }

    private static void generateBaseServiceFile(GenerateRequiredData requiredData) throws Exception {

        String[] templateNameArray = new String[]{"BaseApiImpl","Entity","Repository"};
        File baseDirFile = new File(ConstantInterface.diskPath+File.separator+"baseservice");
        if(!baseDirFile.exists() || !baseDirFile.isDirectory()){
            baseDirFile.mkdirs();
        }
        for (String templateName : templateNameArray) {
            File outFile = new File(baseDirFile,requiredData.getTable_name()+templateName+".java");
            generateFileByTemplate("baseservice/"+templateName+".ftl",outFile,requiredData);
        }
    }

    private static void generateFileByTemplate(final String templateName, File file, GenerateRequiredData data) throws Exception{
        Template template = FreeMarkerTemplateUtils.getTemplate(templateName);
        FileOutputStream fos = new FileOutputStream(file);
        Writer out = new BufferedWriter(new OutputStreamWriter(fos, "utf-8"),10240);
        template.process(data,out);
    }

}
