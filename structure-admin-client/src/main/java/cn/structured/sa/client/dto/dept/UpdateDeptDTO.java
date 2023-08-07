package cn.structured.sa.client.dto.dept;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * 更新部门
 *
 * @author cqliut
 * @version 2023.0706
 * @since 1.0.1
 */
@Data
@ApiModel(description = "更新部门")
public class UpdateDeptDTO {

    @NotNull
    @ApiModelProperty(value = "部门ID", example = "1645717015337684992", required = true)
    private Long id;

    @ApiModelProperty(value = "部门名称", example = "管理员")
    private String name;

    @ApiModelProperty(value = "部门编码", example = "ADMIN")
    private String code;

    @ApiModelProperty(value = "描述", example = "这个部门是一个运营部门，其中包括3个子部门")
    private String remark;

    @ApiModelProperty(value = "部门父ID", example = "1645717015337684993")
    private Long pid;

    @ApiModelProperty(value = "权重顺序", example = "0", notes = "部门的排序")
    private Integer sequence;
}
