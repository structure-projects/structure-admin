package cn.structured.sa.controller.api;

import cn.structure.common.entity.ResResultVO;
import cn.structure.common.utils.ResultUtilSimpleImpl;
import cn.structure.starter.oauth.common.util.UserUtil;
import cn.structured.sa.client.dto.organization.ChangeCurrentOrganizationDTO;
import cn.structured.sa.client.vo.OrganizationVO;
import cn.structured.sa.controller.assembler.OrganizationAssembler;
import cn.structured.sa.entity.Organization;
import cn.structured.sa.manager.IOrganizationManager;
import cn.structured.sa.service.IOrganizationService;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * 机构信息
 * todo 开放一些openApi 接口使用特殊的验证特殊的请求头，初始化机构信息和一些管理接口，初始化过程需要进行验证数据或调用远程接口完成初始化,底座是免费的没必要高特别复杂
 * todo openApi 需要可以对接口管理但是要验证身份
 *
 * @author cqliut
 * @version 2023.0705
 * @since 1.0.1
 */
@RestController
@Api(tags = "机构模块")
@RequestMapping(value = "/org")
@RequiredArgsConstructor
public class OrganizationController {

    private final IOrganizationManager organizationManager;

    private final IOrganizationService organizationService;

    @ApiOperation(value = "获取当前机构信息")
    @GetMapping("/current")
    public ResResultVO<OrganizationVO> current() {
        Long organizationId = UserUtil.getOrganizationId();
        Organization organization = organizationManager.getOrganizationByOrganizationId(organizationId);
        return ResultUtilSimpleImpl.success(OrganizationAssembler.assembler(organization));
    }

    @ApiOperation(value = "获取租户信息")
    @GetMapping("/getTenant")
    public ResResultVO<OrganizationVO> getTenant(@ApiParam(value = "host") @RequestParam("host") String host) {
        Organization organization = organizationManager.getOrganizationByHost(host);
        return ResultUtilSimpleImpl.success(OrganizationAssembler.assembler(organization));
    }

    @ApiOperation(value = "更改当前机构信息")
    @PutMapping("/changCurrent")
    public ResResultVO<Void> changCurrent(@RequestBody @Validated ChangeCurrentOrganizationDTO currentOrganization) {
        Organization organization = OrganizationAssembler.assembler(currentOrganization);
        organizationService.update(organization, Wrappers.<Organization>lambdaUpdate()
                .eq(Organization::getOrganizationId, organization.getOrganizationId()));
        return ResultUtilSimpleImpl.success(null);
    }
}
