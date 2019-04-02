package com.zhulong.framework.common.dto;

import com.zhulong.framework.common.spring.bean.ConfigValueBean;
import com.zhulong.framework.common.util.ModuleUtils;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Data;

import java.io.Serializable;
import java.util.Enumeration;

/**
 * dto统一返回
 * @author xxc
 * @time 2019-1-7 11:48
 */
@Data
@Builder
@ApiModel
public class ResultDTO<T> implements Serializable {

    private static final long serialVersionUID = -1L;

    private static final Integer DODULE_FOUND_DEEP = 10;


    @ApiModelProperty("是否成功")
    private Boolean success; //是否成功

    @ApiModelProperty("子系统名称")
    private String appName; //子系统名称

    @ApiModelProperty("模块")
    private String module; //模块

    @ApiModelProperty("编码")
    private String code; //编码

    @ApiModelProperty("返回结果数据")
    private T result;   //成功返回结果

    @ApiModelProperty("返回信息")
    private String message; //返回信息

    public static <T> ResultDTO<T> of(T result){
        return ResultDTO.<T>builder().result(result).build();
    }

    public static <T> ResultDTO<T> error(String code){
        return ResultDTO.<T>builder().success(false).code(code).build();
    }

    public static <T> ResultDTO<T> error(String code,String message){
        return ResultDTO.<T>builder().success(false).code(code).message(message).build();
    }

    public static <T> ResultDTO.ResultDTOBuilder<T> builder() {
        return new ResultDTO.ResultDTOBuilder();
    }

    public static class ResultDTOBuilder<T> {
        //默认为true
        private Boolean success = true;
        private String code;
        private T result;
        private String message;

        ResultDTOBuilder() {
        }

        public ResultDTO.ResultDTOBuilder<T> success(Boolean success) {
            this.success = success;
            return this;
        }

        public ResultDTO.ResultDTOBuilder<T> code(String code) {
            this.code = code;
            return this;
        }

        public ResultDTO.ResultDTOBuilder<T> result(T result) {
            this.result = result;
            return this;
        }

        public ResultDTO.ResultDTOBuilder<T> message(String message) {
            this.message = message;
            return this;
        }

        public ResultDTO<T> build() {
            //获取APPName和module
            String appName = ConfigValueBean.applicationName;
            String module = ModuleUtils.getModuleName();
            return new ResultDTO(this.success, appName, module, this.code, this.result, this.message);
        }

        public String toString() {
            return "ResultDTO.ResultDTOBuilder(success=" + this.success + ", appName=" + this.appName + ", module=" + this.module + ", code=" + this.code + ", result=" + this.result + ", message=" + this.message + ")";
        }
    }
}
