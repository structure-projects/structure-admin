package cn.structured.sa.controller.api;

import cn.hutool.json.JSONObject;
import cn.structure.common.entity.ResResultVO;
import cn.structure.common.utils.ResultUtilSimpleImpl;
import cn.structure.starter.oauth.common.util.UserUtil;
import cn.structured.sa.client.dto.config.ConfigDTO;
import cn.structured.sa.controller.assembler.ConfigAssembler;
import cn.structured.sa.entity.Config;
import cn.structured.sa.service.IConfigService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 机构信息
 * todo 上传功能和加载用户自定义配置
 *
 * @author cqliut
 * @version 2023.0705
 * @since 1.0.1
 */
@RestController
@Api(tags = "系统配置")
@RequestMapping(value = "/config")
@RequiredArgsConstructor
public class ConfigController {

    private final IConfigService configService;

    @ApiOperation(value = "获取当前机构系统配置")
    @GetMapping("/getSysConfig")
    public ResResultVO<JSONObject> current() {
        Long organizationId = UserUtil.getOrganizationId();
        List<Config> list = configService.list(Wrappers.<Config>lambdaQuery().eq(Config::getOrganizationId, organizationId));
        return ResultUtilSimpleImpl.success(ConfigAssembler.assemblerSysConfig(list));
    }

    @ApiOperation(value = "更改当前机构系统配置")
    @PutMapping("/changSysConfig")
    public ResResultVO<Void> changCurrent(@RequestBody @Validated JSONObject sysConfig) {
        List<Config> configs = ConfigAssembler.assemblerSysConfig(sysConfig);
        Long organizationId = UserUtil.getOrganizationId();
        configService.saveSysConfig(configs, organizationId);
        return ResultUtilSimpleImpl.success(null);
    }

    @ApiOperation(value = "更改当前用户的个性化系统配置")
    @PutMapping("/changCurrentUserConfig")
    public ResResultVO<Void> changCurrentUserConfig(@RequestBody @Validated ConfigDTO userConfig) {
        Config config = ConfigAssembler.assembler(userConfig);
        configService.saveOrUpdate(config, Wrappers.<Config>lambdaUpdate()
                .eq(Config::getUserId, UserUtil.getUserId())
                .eq(Config::getOrganizationId, UserUtil.getOrganizationId())
        );
        return ResultUtilSimpleImpl.success(null);
    }

    @ApiOperation(value = "获取用户某一项配置")
    @PutMapping("/getUserConfigByCode")
    public ResResultVO<String> getUserConfigByCode(@ApiParam(value = "用户配置编码", required = true, example = "themeColors") @RequestParam("code") String code) {
        LambdaQueryWrapper<Config> queryWrapper = Wrappers.<Config>lambdaQuery()
                .eq(Config::getUserId, UserUtil.getUserId())
                .eq(Config::getCode, code)
                .eq(Config::getOrganizationId, UserUtil.getOrganizationId())
                .select(Config::getId, Config::getValue);
        Config config = configService.getOne(queryWrapper);
        return ResultUtilSimpleImpl.success(config.getValue());
    }
}
