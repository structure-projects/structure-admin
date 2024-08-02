package cn.structured.admin.endpoint.api;

import cn.hutool.core.util.StrUtil;
import cn.structure.common.entity.ResResultVO;
import cn.structure.common.utils.ResultUtilSimpleImpl;
import cn.structured.admin.api.dto.DictCategoryDTO;
import cn.structured.admin.endpoint.assembler.DictAssembler;
import cn.structured.admin.entity.DictCategory;
import cn.structured.admin.service.IDictCategoryService;
import cn.structured.admin.api.vo.DictCategoryVO;
import cn.structured.mybatis.plus.starter.vo.ResPage;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
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

/**
 * 字典管理
 *
 * @author chuck
 * @since JDK1.8
 */
@Api(tags = "字典管理")
@RestController
@RequestMapping(value = "/api/dict")
public class DictCategoryEndpoint {

    @Resource
    private IDictCategoryService dictService;

    @ApiOperation(value = "新增字典类")
    @PostMapping(value = "/category/")
    public ResResultVO<Long> add(@RequestBody @Validated DictCategoryDTO dictCategoryDto) {
        DictCategory dictCategory = DictAssembler.assemblerDictCategory(dictCategoryDto);
        dictService.save(dictCategory);
        return ResultUtilSimpleImpl.success(dictCategory.getId());
    }

    @ApiOperation(value = "修改字典类")
    @PutMapping(value = "/category/{dictCategoryId}")
    public ResResultVO<Void> update(@ApiParam(value = "字典类ID", example = "1645717015337684992")
                                    @PathVariable("dictCategoryId") Long dictCategoryId,
                                    @RequestBody @Validated DictCategoryDTO dictCategoryDto) {
        DictCategory dictCategory = DictAssembler.assemblerDictCategory(dictCategoryDto);
        dictCategory.setId(dictCategoryId);
        dictService.updateById(dictCategory);
        return ResultUtilSimpleImpl.success(null);
    }

    @ApiOperation(value = "字典类分页列表")
    @GetMapping(value = "/category/{page}/{pageSize}/page")
    public ResResultVO<ResPage<DictCategoryVO>> page(@ApiParam(value = "页码", required = true, example = "1")
                                                     @PathVariable(value = "page") Long page,
                                                     @ApiParam(value = "页大小", required = true, example = "20")
                                                     @PathVariable(value = "pageSize") Long pageSize,
                                                     @ApiParam(value = "关键字", example = "test")
                                                     @RequestParam(value = "keywords", required = false) String keywords) {
        LambdaQueryWrapper<DictCategory> queryWrapper = Wrappers.<DictCategory>lambdaQuery()
                .like(StrUtil.isNotBlank(keywords), DictCategory::getCode, StringPool.PERCENT + keywords + StringPool.PERCENT)
                .or()
                .like(StrUtil.isNotBlank(keywords), DictCategory::getName, StringPool.PERCENT + keywords + StringPool.PERCENT);

        IPage<DictCategory> pageResult = dictService.page(new Page<>(page, pageSize), queryWrapper);
        return ResultUtilSimpleImpl.success(ResPage.convert(pageResult, DictAssembler::assemblerDictCategory));
    }

    @GetMapping(value = "/category/{dictCategoryId}")
    public ResResultVO<DictCategoryVO> getCategory(@ApiParam(value = "字典类ID", example = "1645717015337684992")
                                                   @PathVariable("dictCategoryId") Long dictCategoryId) {
        DictCategory dictCategory = dictService.getById(dictCategoryId);
        return ResultUtilSimpleImpl.success(DictAssembler.assemblerDictCategory(dictCategory));
    }

    @ApiOperation(value = "删除字典类")
    @DeleteMapping(value = "/category/{ids}")
    public ResResultVO<Void> remove(@ApiParam(value = "字典类ID", example = "1645717015337684992")
                                    @PathVariable("ids") List<Long> ids) {
        dictService.removeByIds(ids);
        return ResultUtilSimpleImpl.success(null);
    }

    @ApiOperation(value = "启用")
    @PutMapping(value = "/category/enable/{dictCategoryId}")
    public ResResultVO<Void> enable(@ApiParam(value = "字典类ID", example = "1645717015337684992")
                                    @PathVariable("dictCategoryId") Long dictCategoryId) {
        dictService.enable(dictCategoryId);
        return ResultUtilSimpleImpl.success(null);
    }

    @ApiOperation(value = "停用")
    @PutMapping(value = "/category/disable/{dictCategoryId}")
    public ResResultVO<Void> disable(@ApiParam(value = "字典类ID", example = "1645717015337684992")
                                     @PathVariable("dictCategoryId") Long dictCategoryId) {
        dictService.disable(dictCategoryId);
        return ResultUtilSimpleImpl.success(null);
    }
}
