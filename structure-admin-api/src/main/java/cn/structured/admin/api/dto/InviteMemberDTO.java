/*
Copyright 2025 Structure Projects

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

	http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
*/
package cn.structured.admin.api.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * 邀请成员
 *
 * @author chuck
 * @version 1.0
 * @since 1.8
 * @since 2025/12/18-下午10:14
 */
@Data
@ApiModel(description = "邀请成员")
public class InviteMemberDTO {

    /**
     * 成员id
     */
    @NotNull
    @ApiModelProperty(value = "用户ID")
    private Long uid;

    /**
     * 成员名
     */
    @ApiModelProperty(value = "用户名")
    private String name;

    /**
     * 部门id
     */
    @ApiModelProperty(value = "部门ID")
    private Long deptId;

    /**
     * 是否直接加入
     */
    @ApiModelProperty(value = "是否直接加入")
    private Boolean invite;

}
