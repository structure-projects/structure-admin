package cn.structured.admin.api.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.List;
/**
 * 部门视图对象
 *
 * @author cqliut
 * @version 2023.0706
 * @since 1.0.1
 */
@Data
@ApiModel(description = "部门视图对象")
public class DeptVO {


    @ApiModelProperty(value = "部门ID")
    private Long id;

    @ApiModelProperty(value = "部门名称")
    private String name;

    @ApiModelProperty(value = "父部门ID")
    @NotNull(message = "父部门ID不能为空")
    private Long parentId;

    @ApiModelProperty(value = "父节点id路径")
    private String treePath;

    @ApiModelProperty(value = "排序(数字越小排名越靠前)")
    private Integer sort;

    @ApiModelProperty(value = "是否启用 1:  启用 0:未启用")
    private Boolean enabled;

    @ApiModelProperty(value = "子部门")
    private List<DeptVO> children;

    @ApiModelProperty(value = "创建时间")
    private LocalDateTime createTime;

    @ApiModelProperty(value = "修改时间")
    private LocalDateTime updateTime;
}
