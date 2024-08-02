package cn.structured.admin.api.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * 部门对象
 *
 * @author chuck
 * @version 2024/07/13 下午4:37
 * @since 1.8
 */
@Data
@ApiModel(description = "部门对象")
public class DeptDTO {

    @ApiModelProperty(value = "部门名称")
    private String name;

    @ApiModelProperty(value = "父部门ID")
    @NotNull(message = "父部门ID不能为空")
    private Long parentId;

    @ApiModelProperty(value = "父节点id路径")
    private String treePath;

    @ApiModelProperty(value = "排序")
    private Integer sort;

    @ApiModelProperty(value = "是否启用 1:  启用 0:未启用")
    private Boolean enabled;
}
