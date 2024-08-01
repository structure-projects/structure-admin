package cn.structured.admin.vo;

import cn.structured.admin.enums.MenuTypeEnum;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 菜单详情VO
 *
 * @author cqliut
 * @version 2023.0707
 * @since 1.0.1
 */
@Data
@ApiModel(value = "菜单详情VO")
public class MenuDetailsVO {

    @ApiModelProperty(value = "菜单ID")
    private Long id;

    @ApiModelProperty(value = "功能编号")
    private String code;

    @ApiModelProperty(value = "父菜单ID")
    private Long parentId;

    @ApiModelProperty(value = "菜单名称")
    private String name;

    @ApiModelProperty(value = "菜单类型(1-菜单；2-目录；3-外链；4-按钮权限)")
    private MenuTypeEnum type;

    @ApiModelProperty(value = "路由路径")
    private String path;

    @ApiModelProperty(value = "组件路径(vue页面完整路径，省略.vue后缀)")
    private String component;

    @ApiModelProperty(value = "权限标识")
    private String perm;

    @ApiModelProperty(value = "显示状态")
    private Boolean visible;

    @ApiModelProperty(value = "排序(数字越小排名越靠前)")
    private Integer sort;

    @ApiModelProperty(value = "菜单图标")
    private String icon;

    @ApiModelProperty(value = "跳转路径")
    private String redirect;

    @ApiModelProperty(value = "【菜单】是否开启页面缓存", example = "1")
    private Boolean keepAlive;

    @ApiModelProperty(value = "【目录】只有一个子路由是否始终显示", example = "1")
    private Boolean alwaysShow;
}
