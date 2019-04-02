package com.zhulong.framework.zhulonggenerate.util;

import com.zhulong.framework.zhulonggenerate.boot.ConstantInterface;
import com.zhulong.framework.zhulonggenerate.excel.ExcelInfo;
import com.zhulong.framework.zhulonggenerate.typeconvert.Convert;
import com.zhulong.framework.zhulonggenerate.typeconvert.RegisterConvert;
import lombok.Getter;
import lombok.Setter;
import org.springframework.util.StringUtils;

import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Created by shanb on 2019-3-25.
 */
@Getter
@Setter
public class GenerateRequiredData {

    private String baseServiceAppName;//基础服务层名称


    private String table_name_small;
    private String table_name;
    private String author;
    private String date;
    private String package_name;
    private String table_annotation;
    private String module_name;
    private String moduleNameAnnotion;

    private Set<String> entityDTOImport = new HashSet<>(); //实体类和DTO需要导入
    private Set<String> queryDTOImport = new HashSet<>(); //查询DTO需要导入
    private Set<String> saveDTOImport = new HashSet<>();//保存需要导入
    private Set<String> updateDTOImport = new HashSet<>();//更新需要导入
    private Set<String> listDTOImport = new HashSet<>();//列表需要导入
    private Set<String> showDTOImport = new HashSet<>();//展示需要导入

    private List<GenerateRequiredColumnData> columnDataList;

    public static GenerateRequiredData getGenerateRequiredData(List<ExcelInfo> excelInfoList){

        String[] saveUpdateDTOIgnorProperty = new String[]{"createTime","creatorGuid","creatorName","modifierGuid","modifierName","modifyTime"};
        GenerateRequiredData data = new GenerateRequiredData();
        if(!StringUtils.isEmpty(ConstantInterface.baseServiceNameConstant)){
            data.setBaseServiceAppName(ConstantInterface.baseServiceNameConstant);
        }else{
            data.setBaseServiceAppName("\""+ConstantInterface.baseServiceName+"\"");
        }
        data.setTable_name_small(ConstantInterface.tableName.toUpperCase());
        data.setTable_name(replaceUnderLineAndUpperCase(ConstantInterface.tableName));
        data.setAuthor(ConstantInterface.author);
        data.setDate( new SimpleDateFormat("yyyy-MM-dd HH:mm").format(new Date()));
        data.setPackage_name(ConstantInterface.packageName);
        data.setTable_annotation(ConstantInterface.tableAnnotation);
        if(!StringUtils.isEmpty(ConstantInterface.moduleName) ) {
            data.setModule_name("."+ConstantInterface.moduleName);
            data.setModuleNameAnnotion(ConstantInterface.moduleName.toUpperCase());
        }else{
            data.setModule_name("");
            data.setModuleNameAnnotion("");
        }
        List<GenerateRequiredColumnData> columnDataList = excelInfoList.stream().map(excelInfo -> {
            GenerateRequiredColumnData columnData = new GenerateRequiredColumnData();
            columnData.setColumnName(excelInfo.getCloumnName().toUpperCase());
            columnData.setPropertyName(StringUtils.uncapitalize(replaceUnderLineAndUpperCase(excelInfo.getCloumnName())));

            columnData.setRemarks(excelInfo.getRemarks());
            if(StringUtils.isEmpty(excelInfo.getIsRequired())) {
                columnData.setIsRequired(false);
            }else{
                columnData.setIsRequired(true);
                columnData.getValidateAnnotion().add("@NotNull");
            }

            if(StringUtils.isEmpty(excelInfo.getIsQueryParam())) {
                columnData.setIsQueryParam(false); //是否查询参数
            }else{
                columnData.setIsQueryParam(true);
            }

            if(StringUtils.isEmpty(excelInfo.getIsSaveData())) {
                columnData.setIsSaveData(true);//是否保存的数据
            }else{
                columnData.setIsSaveData(false);
            }

            if(StringUtils.isEmpty(excelInfo.getIsUpdateData())) {
                columnData.setIsUpdateData(true);//是否更新的数据
            }else{
                columnData.setIsUpdateData(false);
            }

            if(StringUtils.isEmpty(excelInfo.getIsListData())) {
                columnData.setIsListData(true);//是否列表显示的数据
            }else{
                columnData.setIsListData(false);
            }

            if(StringUtils.isEmpty(excelInfo.getIsShowData())) {
                columnData.setIsShowData(true);//是否查看页面展示的数据
            }else{
                columnData.setIsShowData(false);
            }

            boolean getType = false;
            for (Convert convert : RegisterConvert.convertList) {
                String typeName = excelInfo.getTypeName().toLowerCase();
                if(convert.support(typeName)){
                    String convertJavaType = convert.convertJavaType(typeName);
                    if(convertJavaType.indexOf(".")>0){
                        String simpleName = convertJavaType.substring(convertJavaType.lastIndexOf(".")+1,convertJavaType.length());
                        columnData.setJavaTypeName(simpleName);
                        String pck = convertJavaType;
                        data.getEntityDTOImport().add(pck);
                        if(columnData.getIsQueryParam()){
                            data.getQueryDTOImport().add(pck);
                        }
                        if(columnData.getIsListData()){
                            data.getListDTOImport().add(pck);
                        }
                        if(columnData.getIsSaveData()){
                            data.getSaveDTOImport().add(pck);
                        }
                        if(columnData.getIsShowData()){
                            data.getShowDTOImport().add(pck);
                        }
                        if(columnData.getIsUpdateData()){
                            data.getUpdateDTOImport().add(pck);
                        }
                    }else{
                        columnData.setJavaTypeName(convertJavaType);
                    }
                    columnData.getValidateAnnotion().addAll(convert.getAnnotionList(typeName,columnData.getIsRequired()));
                    getType = true;
                    break;
                }
            }
            if(!getType){
                columnData.setJavaTypeName("UNKOWN");
            }
            return columnData;
        }).collect(Collectors.toList());
        data.setColumnDataList(columnDataList);
        return data;
    }

    private static String replaceUnderLineAndUpperCase(String str){

        str=str.toLowerCase();//统一转为小写
        StringBuffer sb = new StringBuffer();
        sb.append(str);
        int count = sb.indexOf("_");
        while(count!=0){
            int num = sb.indexOf("_",count);
            count = num + 1;
            if(num != -1){
                char ss = sb.charAt(count);
                char ia = (char) (ss - 32);
                sb.replace(count , count + 1,ia + "");
            }
        }
        String result = sb.toString().replaceAll("_","");
        if(result.startsWith("is")){//如果是is开头，则去掉is
            result=result.substring(2,result.length());
        }
        return StringUtils.capitalize(result);
    }
}