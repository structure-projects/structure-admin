package cn.structured.sa.client.dto.menu;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 搜索菜单
 *
 * @author cqliut
 * @version 2023.0707
 * @since 1.0.1
 */
@Data
@ApiModel(description = "搜索菜单")
public class SearchMenuDTO {

    @ApiModelProperty(value = "菜单编号", example = "SYS_ADMIN")
    private String code;

    @ApiModelProperty(value = "菜单名称", example = "系统管理")
    private String name;

    @ApiModelProperty(value = "启用/停用", example = "true", notes = "启用 true,停用 false")
    private Boolean enabled;

}
