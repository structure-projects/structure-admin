package cn.structured.admin.core.endpoint.api;

import cn.hutool.core.util.StrUtil;
import cn.structure.common.entity.ResResultVO;
import cn.structure.common.utils.ResultUtilSimpleImpl;
import cn.structure.common.vo.ResPage;
import cn.structured.admin.common.dto.DictCategoryDTO;
import cn.structured.admin.core.endpoint.assembler.DictAssembler;
import cn.structured.admin.core.entity.DictCategory;
import cn.structured.admin.core.service.IDictCategoryService;
import cn.structured.admin.common.vo.DictCategoryVO;
import cn.structured.mybatis.plus.starter.convert.ResPageConvert;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.StringPool;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import jakarta.annotation.Resource;
import java.util.List;

/**
 * 字典管理
 *
 * @author chuck
 * @since JDK1.8
 */
@Tag(name = "字典管理")
@RestController
@RequestMapping(value = "/api/dict")
public class DictCategoryEndpoint {

    @Resource
    private IDictCategoryService dictService;

    @Operation(summary = "新增字典类")
    @PostMapping(value = "/category/")
    @PreAuthorize("hasAuthority('sys:dict_type:add')")
    public ResResultVO<Long> add(@RequestBody @Validated DictCategoryDTO dictCategoryDto) {
        DictCategory dictCategory = DictAssembler.assemblerDictCategory(dictCategoryDto);
        dictService.save(dictCategory);
        return ResultUtilSimpleImpl.success(dictCategory.getId());
    }

    @Operation(summary = "修改字典类")
    @PutMapping(value = "/category/{dictCategoryId}")
    @PreAuthorize("hasAuthority('sys:dict_type:edit')")
    public ResResultVO<Void> update(@Parameter(description = "字典类ID", example = "1645717015337684992")
                                    @PathVariable("dictCategoryId") Long dictCategoryId,
                                    @RequestBody @Validated DictCategoryDTO dictCategoryDto) {
        DictCategory dictCategory = DictAssembler.assemblerDictCategory(dictCategoryDto);
        dictCategory.setId(dictCategoryId);
        dictService.updateById(dictCategory);
        return ResultUtilSimpleImpl.success(null);
    }

    @Operation(summary = "字典类分页列表")
    @GetMapping(value = "/category/{page}/{pageSize}/page")
    @PreAuthorize("hasAuthority('sys:dict_type:read')")
    public ResResultVO<ResPage<DictCategoryVO>> page(@Parameter(description = "页码", required = true, example = "1")
                                                     @PathVariable(value = "page") Long page,
                                                     @Parameter(description = "页大小", required = true, example = "20")
                                                     @PathVariable(value = "pageSize") Long pageSize,
                                                     @Parameter(description = "关键字", example = "test")
                                                     @RequestParam(value = "keywords", required = false) String keywords) {
        LambdaQueryWrapper<DictCategory> queryWrapper = Wrappers.<DictCategory>lambdaQuery()
                .like(StrUtil.isNotBlank(keywords), DictCategory::getCode, StringPool.PERCENT + keywords + StringPool.PERCENT)
                .or()
                .like(StrUtil.isNotBlank(keywords), DictCategory::getName, StringPool.PERCENT + keywords + StringPool.PERCENT);

        IPage<DictCategory> pageResult = dictService.page(new Page<>(page, pageSize), queryWrapper);
        return ResultUtilSimpleImpl.success(ResPageConvert.convert(pageResult, DictAssembler::assemblerDictCategory));
    }

    @Operation(summary = "字典类详情")
    @GetMapping(value = "/category/{dictCategoryId}")
    @PreAuthorize("hasAuthority('sys:dict_type:read')")
    public ResResultVO<DictCategoryVO> getCategory(@Parameter(description = "字典类ID", example = "1645717015337684992")
                                                   @PathVariable("dictCategoryId") Long dictCategoryId) {
        DictCategory dictCategory = dictService.getById(dictCategoryId);
        return ResultUtilSimpleImpl.success(DictAssembler.assemblerDictCategory(dictCategory));
    }

    @Operation(summary = "删除字典类")
    @DeleteMapping(value = "/category/{ids}")
    @PreAuthorize("hasAuthority('sys:dict_type:del')")
    public ResResultVO<Void> remove(@Parameter(description = "字典类ID", example = "1645717015337684992")
                                    @PathVariable("ids") List<Long> ids) {
        dictService.removeByIds(ids);
        return ResultUtilSimpleImpl.success(null);
    }

    @Operation(summary = "启用")
    @PutMapping(value = "/category/enable/{dictCategoryId}")
    @PreAuthorize("hasAuthority('sys:dict_type:enable')")
    public ResResultVO<Void> enable(@Parameter(description = "字典类ID", example = "1645717015337684992")
                                    @PathVariable("dictCategoryId") Long dictCategoryId) {
        dictService.enable(dictCategoryId);
        return ResultUtilSimpleImpl.success(null);
    }

    @Operation(summary = "停用")
    @PutMapping(value = "/category/disable/{dictCategoryId}")
    @PreAuthorize("hasAuthority('sys:dict_type:disable')")
    public ResResultVO<Void> disable(@Parameter(description = "字典类ID", example = "1645717015337684992")
                                     @PathVariable("dictCategoryId") Long dictCategoryId) {
        dictService.disable(dictCategoryId);
        return ResultUtilSimpleImpl.success(null);
    }
}
