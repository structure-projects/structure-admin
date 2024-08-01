package cn.structured.admin.endpoint.api;

import cn.hutool.core.util.StrUtil;
import cn.structure.common.entity.ResResultVO;
import cn.structure.common.utils.ResultUtilSimpleImpl;
import cn.structured.admin.dto.ConfigDTO;
import cn.structured.admin.endpoint.assembler.ConfigAssembler;
import cn.structured.admin.entity.Config;
import cn.structured.admin.service.IConfigService;
import cn.structured.admin.vo.ConfigVO;
import cn.structured.mybatis.plus.starter.vo.ResPage;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.StringPool;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@Api(tags = "配置管理")
@RestController
@RequestMapping(value = "/api/config")
public class ConfigEndpoint {

    @Resource
    private IConfigService service;

    @ApiOperation(value = "新增配置")
    @PostMapping(value = "/")
    public ResResultVO<Long> add(@RequestBody @Validated ConfigDTO create) {
        Config config = ConfigAssembler.assembler(create);
        service.save(config);
        return ResultUtilSimpleImpl.success(config.getId());
    }

    @ApiOperation(value = "修改配置")
    @PutMapping(value = "/{id}")
    public ResResultVO<Void> update(@ApiParam(value = "配置ID", example = "1645717015337684992")
                                    @PathVariable("id") Long id,
                                    @RequestBody @Validated ConfigDTO update) {
        Config config = ConfigAssembler.assembler(update);
        config.setId(id);
        service.updateById(config);
        return ResultUtilSimpleImpl.success(null);
    }

    @ApiOperation(value = "配置列表")
    @GetMapping(value = "/{page}/{pageSize}/page")
    public ResResultVO<ResPage<ConfigVO>> page(@ApiParam(value = "关键字", example = "配置key") @RequestParam(required = false) String keywords,
                                               @ApiParam(value = "页码", example = "1") @PathVariable(value = "page") Long page,
                                               @ApiParam(value = "页大小", example = "10") @PathVariable(value = "pageSize") Long pageSize) {
        LambdaQueryWrapper<Config> queryWrapper = Wrappers.<Config>lambdaQuery()
                .like(StrUtil.isNotBlank(keywords), Config::getCode, StringPool.PERCENT + keywords + StringPool.PERCENT);
        Page<Config> pageResult = service.page(new Page<>(page, pageSize), queryWrapper);
        return ResultUtilSimpleImpl.success(ResPage.convert(pageResult, ConfigAssembler::assembler));
    }


    @ApiOperation(value = "查看配置详情")
    @GetMapping(value = "/{id}")
    public ResResultVO<ConfigVO> get(@ApiParam(value = "配置ID", example = "1645717015337684992")
                                     @PathVariable("id")
                                     Long id) {
        Config config = service.getById(id);
        return ResultUtilSimpleImpl.success(ConfigAssembler.assembler(config));
    }

    @ApiOperation(value = "删除配置")
    @DeleteMapping(value = "/{ids}")
    public ResResultVO<Void> remove(@ApiParam(value = "配置ID", example = "1645717015337684992")
                                    @PathVariable("ids") List<Long> ids) {
        service.removeByIds(ids);
        return ResultUtilSimpleImpl.success(null);
    }

}
