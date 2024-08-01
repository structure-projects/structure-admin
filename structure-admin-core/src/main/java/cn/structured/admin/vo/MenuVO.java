package cn.structured.admin.vo;

import cn.structured.admin.enums.MenuTypeEnum;
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
@ApiModel(value = "菜单VO")
public class MenuVO {

    @ApiModelProperty(value = "菜单ID")
    private Long id;

    @ApiModelProperty(value = "功能编号")
    private String code;

    @ApiModelProperty(value = "父菜单ID")
    private Long parentId;

    @ApiModelProperty(value = "菜单名称")
    private String name;

    @ApiModelProperty(value = "菜单类型")
    private MenuTypeEnum type;

    @ApiModelProperty(value = "路由路径")
    private String path;

    @ApiModelProperty(value = "组件路径")
    private String component;

    @ApiModelProperty(value = "菜单排序(数字越小排名越靠前)")
    private Integer sort;

    @ApiModelProperty(value = "菜单是否可见")
    private Boolean visible;

    @ApiModelProperty(value = "ICON")
    private String icon;

    @ApiModelProperty(value = "跳转路径")
    private String redirect;

    @ApiModelProperty(value = "按钮权限标识")
    private String perm;

    @ApiModelProperty(value = "子菜单")
    private List<MenuVO> children;

}
