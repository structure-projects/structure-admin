package cn.structured.sa.client.dto.organization;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.util.Map;

/**
 * 更新机构
 *
 * @author cqliut
 * @version 2023.0706
 * @since 1.0.1
 */
@Data
@ApiModel(description = "更新机构")
public class ChangeOrganizationDTO {

    @NotNull
    @ApiModelProperty(value = "机构ID", example = "1645717015337684992", required = true)
    private Long id;

    @ApiModelProperty(value = "机构名称", example = "企业一站式解决方案")
    private String name;

    @ApiModelProperty(value = "机构地址", example = "北京省北京市")
    private String address;

    @ApiModelProperty(value = "域账号", example = "structured.cn")
    private String domain;

    @ApiModelProperty(value = "机构介绍", example = "一个专注于企业解决方案的团队")
    private String introduce;

    @ApiModelProperty(value = "管理员名称", example = "管理员")
    private String adminName;

    @ApiModelProperty(value = "管理员电话", example = "1888888888")
    private String adminPhone;

    @ApiModelProperty(value = "系统配置", example = "{'themeColors':'#1890FF'}")
    private Map<String, String> config;
}
