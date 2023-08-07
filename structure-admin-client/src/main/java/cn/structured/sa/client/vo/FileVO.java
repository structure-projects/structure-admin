package cn.structured.sa.client.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 文件上传后的VO对象
 *
 * @author chuck
 * @since JDK1.8
 */
@Data
@ApiModel(description = "文件上传后的VO对象")
public class FileVO {

    @ApiModelProperty(value = "文件名")
    private String name;

    @ApiModelProperty(value = "文件路径")
    private String url;
}
