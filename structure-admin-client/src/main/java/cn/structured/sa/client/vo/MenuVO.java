package cn.structured.sa.client.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * 菜单VO
 *
 * @author cqliut
 * @version 2023.0707
 * @since 1.0.1
 */
@Data
@ApiModel(description = "菜单VO")
public class MenuVO {

    @ApiModelProperty(value = "ID", example = "1645717015337684992")
    private Long id;

    @ApiModelProperty(value = "父ID", example = "1645717015337684992")
    private Long pid;

    @ApiModelProperty(value = "名称", example = "用户管理")
    private String name;

    @ApiModelProperty(value = "编码", example = "User")
    private String code;

    @ApiModelProperty(value = "权限表示", example = "admin")
    private String permission;

    @ApiModelProperty(value = "菜单类型", example = "0", notes = "0目录 1菜单 2按钮 3 功能")
    private Integer type;

    @ApiModelProperty(value = "图标", example = "user")
    private String icon;

    @ApiModelProperty(value = "打开方式", example = "1",notes = " 1组件 2内链 3外链")
    private Integer openType;

    @ApiModelProperty(value = "权重顺序", example = "0", notes = "用于菜单的排序")
    private Integer sequence;

    @ApiModelProperty(value = "路由地址", example = "user")
    private String router;

    @ApiModelProperty(value = "菜单类型", example = "1", notes = "1系统 2业务")
    private Integer menuType;

    @ApiModelProperty(value = "描述", example = "系统管理模块其中包括用户的管理")
    private String remark;

    @ApiModelProperty(value = "启用/停用", example = "true", notes = "启用 true,停用 false")
    private Boolean enabled;

    @ApiModelProperty(value = "操作人", example = "张三")
    private String operator;

    @ApiModelProperty(value = "操作时间", example = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private LocalDateTime operatorTime;

    @ApiModelProperty(value = "组织ID", example = "1645717015337684992")
    private Long organizationId;

    @ApiModelProperty(value = "存在子集", example = "true")
    private Boolean hasChildren;

}
