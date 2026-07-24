package cn.structured.admin.core.endpoint.api;

import cn.hutool.core.util.StrUtil;
import cn.structure.common.entity.ResResultVO;
import cn.structure.common.utils.ResultUtilSimpleImpl;
import cn.structure.common.vo.ResPage;
import cn.structured.admin.common.dto.ConfigDTO;
import cn.structured.admin.core.endpoint.assembler.ConfigAssembler;
import cn.structured.admin.core.entity.Config;
import cn.structured.admin.core.service.IConfigService;
import cn.structured.admin.common.vo.ConfigVO;
import cn.structured.mybatis.plus.starter.convert.ResPageConvert;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.StringPool;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import jakarta.annotation.Resource;
import java.util.List;
/**
 * 配置管理
 * @author chuck
 * @version 2024/07/19 下午11:40
 * @since 1.8
 */
@Tag(name = "配置管理")
@RestController
@RequestMapping(value = "/api/config")
public class ConfigEndpoint {

    @Resource
    private IConfigService service;

    @Operation(summary = "新增配置")
    @PostMapping(value = "/")
    public ResResultVO<Long> add(@RequestBody @Validated ConfigDTO create) {
        Config config = ConfigAssembler.assembler(create);
        service.save(config);
        return ResultUtilSimpleImpl.success(config.getId());
    }

    @Operation(summary = "修改配置")
    @PutMapping(value = "/{id}")
    public ResResultVO<Void> update(@Parameter(description = "配置ID", example = "1645717015337684992")
                                    @PathVariable("id") Long id,
                                    @RequestBody @Validated ConfigDTO update) {
        Config config = ConfigAssembler.assembler(update);
        config.setId(id);
        service.updateById(config);
        return ResultUtilSimpleImpl.success(null);
    }

    @Operation(summary = "配置列表")
    @GetMapping(value = "/{page}/{pageSize}/page")
    public ResResultVO<ResPage<ConfigVO>> page(@Parameter(description = "关键字", example = "配置key") @RequestParam(required = false) String keywords,
                                               @Parameter(description = "页码", example = "1") @PathVariable(value = "page") Long page,
                                               @Parameter(description = "页大小", example = "10") @PathVariable(value = "pageSize") Long pageSize) {
        LambdaQueryWrapper<Config> queryWrapper = Wrappers.<Config>lambdaQuery()
                .like(StrUtil.isNotBlank(keywords), Config::getCode, StringPool.PERCENT + keywords + StringPool.PERCENT);
        Page<Config> pageResult = service.page(new Page<>(page, pageSize), queryWrapper);
        return ResultUtilSimpleImpl.success(ResPageConvert.convert(pageResult, ConfigAssembler::assembler));
    }


    @Operation(summary = "查看配置详情")
    @GetMapping(value = "/{id}")
    public ResResultVO<ConfigVO> get(@Parameter(description = "配置ID", example = "1645717015337684992")
                                     @PathVariable("id")
                                     Long id) {
        Config config = service.getById(id);
        return ResultUtilSimpleImpl.success(ConfigAssembler.assembler(config));
    }

    @Operation(summary = "删除配置")
    @DeleteMapping(value = "/{ids}")
    public ResResultVO<Void> remove(@Parameter(description = "配置ID", example = "1645717015337684992")
                                    @PathVariable("ids") List<Long> ids) {
        service.removeByIds(ids);
        return ResultUtilSimpleImpl.success(null);
    }

}
