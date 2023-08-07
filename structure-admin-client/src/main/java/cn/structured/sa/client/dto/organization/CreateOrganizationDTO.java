package cn.structured.sa.client.dto.organization;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Map;

/**
 * 创建机构
 *
 * @author cqliut
 * @version 2023.0706
 * @since 1.0.1
 */
@Data
@ApiModel(description = "创建机构")
public class CreateOrganizationDTO {

    @NotNull
    @ApiModelProperty(value = "机构ID", example = "1645717015337684992", required = true)
    private Long id;

    @NotBlank
    @ApiModelProperty(value = "机构名称", example = "企业一站式解决方案", required = true)
    private String name;

    @NotBlank
    @ApiModelProperty(value = "机构地址", example = "北京省北京市", required = true)
    private String address;

    @NotBlank
    @ApiModelProperty(value = "域账号", example = "structured.cn", required = true)
    private String domain;

    @NotBlank
    @ApiModelProperty(value = "机构介绍", example = "一个专注于企业解决方案的团队", required = true)
    private String introduce;

    @ApiModelProperty(value = "管理员名称", example = "管理员", required = true)
    private String adminName;

    @ApiModelProperty(value = "管理员电话", example = "1888888888")
    private String adminPhone;

    @NotBlank
    @ApiModelProperty(value = "平台机构ID", example = "1645717015337684992", required = true)
    private Long organizationId;

    @ApiModelProperty(value = "系统配置", example = "{'themeColors':'#1890FF'}")
    private Map<String, String> config;
}
