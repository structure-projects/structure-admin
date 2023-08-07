package cn.structured.sa.client.dto.employee;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 搜索职工
 *
 * @author cqliut
 * @version 2023.0707
 * @since 1.0.1
 */
@Data
@ApiModel(description = "搜索职工")
public class SearchEmployeeDTO {

    @ApiModelProperty(value = "职工名称", example = "韦小宝")
    private String name;

    @ApiModelProperty(value = "工号", example = "100001")
    private String empNo;

    @ApiModelProperty(value = "职工状态", example = "1", notes = "存储的是字典表里的字典项字典类编码为：EMP_STATE")
    private String state;

    @ApiModelProperty(value = "部门ID", example = "1645717015337684992")
    private Long deptId;

}
