package cn.structured.admin.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * 菜单VO
 *
 * @author cqliut
 * @version 2023.0707
 * @since 1.0.1
 */
@Data
@ApiModel(description = "菜单VO")
public class UserMenuVO {


    @ApiModelProperty(value = "ID", example = "1645717015337684992")
    private Long id;

    @ApiModelProperty(value = "名称", example = "用户管理")
    private String name;

    @ApiModelProperty(value = "编码", example = "User")
    private String code;

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

    @ApiModelProperty(value = "打开方式", example = "0", notes = "0无 1组件 2内链 3外链")
    private Integer openType;

    @ApiModelProperty(value = "是否可见", example = "true", notes = "可见为true,不可见为false 指这个按钮或者这个菜单是否可以显示的")
    private Boolean visible;

    @ApiModelProperty(value = "链接地址", example = "https://www.structured.cn", notes = "链接到外部")
    private String link;

    @ApiModelProperty(value = "重定向地址", example = "/index")
    private String redirect;

    @ApiModelProperty(value = "子集", example = "[]")
    private List<UserMenuVO> children;

}
