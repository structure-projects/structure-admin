package cn.structured.sa.client.dto.menu;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

/**
 * 更新功能权限
 *
 * @author cqliut
 * @version 2023.0706
 * @since 1.0.1
 */
@Data
@ApiModel(description = "更新功能权限")
public class UpdateMenuDTO {

    @ApiModelProperty(value = "ID", example = "1645717015337684992")
    private Long id;

    @ApiModelProperty(value = "父ID", example = "1645717015337684992")
    private Long pid;

    @ApiModelProperty(value = "名称", example = "用户管理", required = true)
    private String name;

    @ApiModelProperty(value = "编码", example = "User", required = true)
    private String code;

    @Min(value = 0, message = "菜单类型在0-3之间")
    @Max(value = 3, message = "菜单类型在0-3之间")
    @ApiModelProperty(value = "菜单类型", example = "0", notes = "0目录 1菜单 2按钮 3 功能")
    private Integer type;

    @ApiModelProperty(value = "图标", example = "user")
    private String icon;

    @ApiModelProperty(value = "路由地址", example = "user")
    private String router;

    @ApiModelProperty(value = "组件地址", example = "/views/user/index")
    private String component;

    @ApiModelProperty(value = "权限标识", example = "ADMIN")
    private String permission;

    @Min(value = 0, message = "打开方式在0-3之间")
    @Max(value = 3, message = "打开方式在0-3之间")
    @ApiModelProperty(value = "打开方式", example = "0", notes = "0无 1组件 2内链 3外链")
    private Integer openType;

    @ApiModelProperty(value = "是否可见", example = "true", notes = "可见为true,不可见为false 指这个按钮或者这个菜单是否可以显示的")
    private Boolean visible;

    @ApiModelProperty(value = "链接地址", example = "https://www.structured.cn", notes = "链接到外部")
    private String link;

    @ApiModelProperty(value = "重定向地址", example = "/index")
    private String redirect;

    @Min(value = 1, message = "菜单类型在1-2之间")
    @Max(value = 2, message = "菜单类型在1-2之间")
    @ApiModelProperty(value = "菜单类型", example = "1", notes = "1系统权重 2业务权重")
    private Integer menuType;

    @ApiModelProperty(value = "权重顺序", example = "0", notes = "用于菜单的排序")
    private Integer sequence;

    @ApiModelProperty(value = "描述", example = "系统管理模块其中包括用户的管理")
    private String remark;
}
