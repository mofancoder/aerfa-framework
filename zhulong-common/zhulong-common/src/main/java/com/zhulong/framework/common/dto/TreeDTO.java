package com.zhulong.framework.common.dto;

import com.zhulong.framework.common.enums.BaseEnum;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * 树形机构
 * Created by shanb on 2019-3-25.
 */
@RequiredArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ApiModel("树形结构")
public class TreeDTO implements Serializable {

    @ApiModelProperty("唯一键")
    @NonNull
    private String id;

    @ApiModelProperty("显示值")
    @NonNull
    private String label;

    @ApiModelProperty("展开状态,如果为异步树，若有下级节点，值为closed，children为null")
    private String state;

    @ApiModelProperty("下级节点,同步树传入")
    private List<TreeDTO> children;

    @ApiModelProperty("扩展属性，可用于页面进行判断，具体包含值可根据业务进行")
    private Map<String,Object> extInfo;

    public enum  TreeStateEnum implements BaseEnum<String>{
        CLOSED("closed","关闭状态"),
        OPEN("open","开启状态");

        TreeStateEnum(String code,String description){
            this.code = code;
            this.description = description;
        }

        private String code;

        private String description;

        @Override
        public String getCode() {
            return null;
        }

        @Override
        public String getDescription() {
            return null;
        }
    }
}