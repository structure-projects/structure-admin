package cn.structured.admin.common.vo;

import io.swagger.v3.oas.annotations.media.Schema;
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
@Schema(description = "菜单VO")
public class UserMenuVO {


    @Schema(description = "ID", example = "1645717015337684992")
    private Long id;

    @Schema(description = "名称", example = "用户管理")
    private String name;

    @Schema(description = "编码", example = "User")
    private String code;

    @Schema(description = "菜单类型，0目录 1菜单 2按钮 3 功能", example = "0")
    private Integer type;

    @Schema(description = "图标", example = "user")
    private String icon;

    @Schema(description = "路由地址", example = "user")
    private String router;

    @Schema(description = "组件地址", example = "/views/user/index")
    private String component;

    @Schema(description = "权限标识", example = "ADMIN")
    private String permission;

    @Schema(description = "打开方式，0无 1组件 2内链 3外链", example = "0")
    private Integer openType;

    @Schema(description = "是否可见，可见为true,不可见为false 指这个按钮或者这个菜单是否可以显示的", example = "true")
    private Boolean visible;

    @Schema(description = "链接地址，链接到外部", example = "https://www.structured.cn")
    private String link;

    @Schema(description = "重定向地址", example = "/index")
    private String redirect;

    @Schema(description = "子集", example = "[]")
    private List<UserMenuVO> children;

}
