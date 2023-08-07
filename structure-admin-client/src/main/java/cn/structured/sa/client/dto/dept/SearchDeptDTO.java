package cn.structured.sa.client.dto.dept;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 搜索部门
 *
 * @author cqliut
 * @version 2023.0707
 * @since 1.0.1
 */
@Data
@ApiModel(description = "搜索部门")
public class SearchDeptDTO {

    @ApiModelProperty(value = "部门编号", example = "0001")
    private String code;

    @ApiModelProperty(value = "部门名称", example = "运营一部")
    private String name;

    @ApiModelProperty(value = "启用/停用", example = "true", notes = "启用 true,停用 false")
    private Boolean enabled;

}
