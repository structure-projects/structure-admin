package cn.structured.sa.client.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

/**
 * 部门VO
 *
 * @author cqliut
 * @version 2023.0707
 * @since 1.0.1
 */
@Data
@ApiModel(description = "部门VO")
public class DeptVO {

    @ApiModelProperty(value = "部门ID", example = "1645717015337684994")
    private Long id;

    @ApiModelProperty(value = "部门父ID", example = "1645717015337684993")
    private Long pid;

    @ApiModelProperty(value = "部门层级父ID", example = "1645717015337684992,1645717015337684993")
    private String pids;

    @ApiModelProperty(value = "权重顺序", example = "0", notes = "部门的排序")
    private Integer sequence;

    @ApiModelProperty(value = "部门名称", example = "管理员")
    private String name;

    @ApiModelProperty(value = "部门编码", example = "ADMIN")
    private String code;

    @ApiModelProperty(value = "描述", example = "这个部门是一个运营部门，其中包括3个子部门")
    private String remark;

    @ApiModelProperty(value = "启用/停用", example = "true", notes = "启用 true,停用 false")
    private Boolean enabled;

    @ApiModelProperty(value = "存在子集", example = "true")
    private Boolean hasChildren;

    @ApiModelProperty(value = "操作人", example = "张三")
    private String operator;

    @ApiModelProperty(value = "操作时间", example = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private LocalDateTime operatorTime;

    @ApiModelProperty(value = "组织ID", example = "1645717015337684992")
    private Long organizationId;

}
