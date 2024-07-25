package cn.structured.admin.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * 字典类VO
 *
 * @author cqliut
 * @version 2023.0707
 * @since 1.0.1
 */
@Data
@ApiModel(description = "字典类VO")
public class DictCategoryVo {

    @ApiModelProperty(value = "字典类ID", example = "1645717015337684994")
    private Long id;

    @ApiModelProperty(value = "字典类名称", example = "性别")
    private String name;

    @ApiModelProperty(value = "字典类编码", example = "SEX")
    private String code;

    @ApiModelProperty(value = "描述", example = "这个是姓别字典类,其中包括三个字典项目：N 未知，F 女,M 男")
    private String remark;

    @ApiModelProperty(value = "字典类型", example = "1", notes = "系统:1 ,用户:2")
    private Integer type;

    @ApiModelProperty(value = "启用/停用", example = "true", notes = "启用 true,停用 false")
    private Boolean enabled;

    @ApiModelProperty(value = "操作时间", example = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private LocalDateTime operatorTime;

    @ApiModelProperty(value = "组织ID", example = "1645717015337684992")
    private Long organizationId;
}
