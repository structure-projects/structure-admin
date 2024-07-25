package cn.structured.admin.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

@Data
@ApiModel(value = "路由-VO")
public class RouteVo {

    @ApiModelProperty(value = "路由ID", example = "1")

    private Long id;
    @ApiModelProperty(value = "路由父ID", example = "0")
    private Long parentId;

    @ApiModelProperty(value = "路由路径", example = "user")
    private String path;

    @ApiModelProperty(value = "组件路径", example = "system/user/index")
    private String component;

    @ApiModelProperty(value = "跳转链接", example = "https://www.youlai.tech")
    private String redirect;

    @ApiModelProperty(value = "路由名称")
    private String name;

    @ApiModelProperty(value = "路由属性")
    private Meta meta;

    @ApiModel(value = "路由属性类型")
    @Data
    public static class Meta {

        @ApiModelProperty(value = "路由title")
        private String title;

        @ApiModelProperty(value = "ICON")
        private String icon;

        @ApiModelProperty(value = "是否隐藏(true-是 false-否)", example = "true")
        private Boolean hidden;

        @ApiModelProperty(value = "拥有路由权限的角色编码", example = "['ADMIN','ROOT']")
        private List<String> roles;

        @ApiModelProperty(value = "【菜单】是否开启页面缓存", example = "true")
        private Boolean keepAlive;

        @ApiModelProperty(value = "【目录】只有一个子路由是否始终显示", example = "true")
        private Boolean alwaysShow;
    }

    @ApiModelProperty(value = "子菜单")
    private List<RouteVo> children;

}
