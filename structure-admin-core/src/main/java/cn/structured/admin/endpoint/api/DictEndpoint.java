package cn.structured.admin.endpoint.api;

import cn.hutool.core.util.StrUtil;
import cn.structure.common.entity.ResResultVO;
import cn.structure.common.utils.ResultUtilSimpleImpl;
import cn.structured.admin.dto.DictItemDTO;
import cn.structured.admin.endpoint.assembler.DictAssembler;
import cn.structured.admin.endpoint.assembler.OptionAssembler;
import cn.structured.admin.entity.DictItem;
import cn.structured.admin.service.IDictService;
import cn.structured.admin.vo.DictItemVO;
import cn.structured.admin.vo.OptionVO;
import cn.structured.basic.api.groups.Create;
import cn.structured.basic.api.groups.Update;
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
import java.util.stream.Collectors;

@Api(tags = "字典管理")
@RestController
@RequestMapping(value = "/api/dict")
public class DictEndpoint {

    @Resource
    private IDictService dictService;


    @ApiOperation(value = "字典项列表")
    @GetMapping(value = "/{page}/{pageSize}/page")
    public ResResultVO<ResPage<DictItemVO>> itemList(@ApiParam(value = "字典类CODE", example = "SEX")
                                                     @RequestParam(value = "code") String code,
                                                     @ApiParam(value = "关键字", example = "部门key")
                                                     @RequestParam(required = false)
                                                     String keywords,
                                                     @ApiParam(value = "是否启用", example = "TRUE")
                                                     @RequestParam(required = false) Boolean enabled,
                                                     @ApiParam(value = "页码", required = true, example = "1")
                                                     @PathVariable(value = "page") Long page,
                                                     @ApiParam(value = "页大小", required = true, example = "20")
                                                     @PathVariable(value = "pageSize") Long pageSize) {

        LambdaQueryWrapper<DictItem> queryWrapper = Wrappers.<DictItem>lambdaQuery()
                .eq(DictItem::getCode, code)
                .eq(null != enabled, DictItem::getEnabled, enabled)
                .like(StrUtil.isNotBlank(keywords), DictItem::getName, "%" + keywords + "%")
                .orderByAsc(DictItem::getSort);
        Page<DictItem> pageResult = dictService.page(new Page<>(page, pageSize), queryWrapper);
        ResPage<DictItemVO> result = ResPage.convert(pageResult, DictAssembler::assemblerDictItem);
        return ResultUtilSimpleImpl.success(result);
    }

    @ApiOperation(value = "创建字典项")
    @PostMapping(value = "/")
    public ResResultVO<Long> addItem(@RequestBody @Validated(value = Create.class) DictItemDTO create) {
        DictItem item = DictAssembler.assemblerDictItem(create);
        dictService.save(item);
        return ResultUtilSimpleImpl.success(item.getId());
    }

    @ApiOperation(value = "获取字典项详情")
    @GetMapping(value = "/{id}")
    public ResResultVO<DictItemVO> get(@PathVariable(value = "id") Long id) {
        DictItem dictItem = dictService.getById(id);
        return ResultUtilSimpleImpl.success(DictAssembler.assemblerDictItem(dictItem));
    }


    @ApiOperation(value = "更新字典项")
    @PutMapping(value = "/{id}")
    public ResResultVO<Void> editItem(@PathVariable(value = "id") Long id, @RequestBody @Validated(value = Update.class) DictItemDTO update) {
        DictItem dictItem = DictAssembler.assemblerDictItem(update);
        dictItem.setId(id);
        dictService.updateById(dictItem);
        return ResultUtilSimpleImpl.success(null);
    }

    @ApiOperation(value = "删除字典项")
    @DeleteMapping(value = "/{ids}")
    public ResResultVO<Void> removeItem(@ApiParam(value = "字典项ID", example = "1645717015337684992")
                                        @PathVariable("ids") List<Long> ids) {
        dictService.removeByIds(ids);
        return ResultUtilSimpleImpl.success(null);
    }


    @ApiOperation(value = "启用字典项")
    @PutMapping(value = "/enable/{id}")
    public ResResultVO<Void> enableItem(@ApiParam(value = "字典项ID", example = "1645717015337684992")
                                        @PathVariable("id") Long id) {
        dictService.enableItem(id);
        return ResultUtilSimpleImpl.success(null);
    }

    @ApiOperation(value = "停用字典项")
    @PutMapping(value = "/disable/{id}")
    public ResResultVO<Void> disableItem(@ApiParam(value = "字典项ID", example = "1645717015337684992")
                                         @PathVariable("id") Long id) {
        dictService.disableItem(id);
        return ResultUtilSimpleImpl.success(null);
    }


    @ApiOperation(value = "下拉选", notes = "当前组织某个字典类的全部启用字典项")
    @GetMapping(value = "/{codeType}/options")
    public ResResultVO<List<OptionVO>> option(@ApiParam(value = "字典类CODE", example = "SEX")
                                              @PathVariable(value = "codeType") String dictCategoryCode,
                                              @ApiParam(value = "关键字", example = "SEX")
                                              @RequestParam(value = "keywords", required = false) String keywords
    ) {
        LambdaQueryWrapper<DictItem> queryWrapper = Wrappers.<DictItem>lambdaQuery()
                .eq(DictItem::getCode, dictCategoryCode)
                .eq(DictItem::getEnabled, Boolean.TRUE)
                .like(StrUtil.isNotBlank(keywords), DictItem::getName, StringPool.PERCENT + keywords + StringPool.PERCENT)
                .select(DictItem::getId, DictItem::getCode, DictItem::getName, DictItem::getValue)
                .orderByAsc(DictItem::getSort);
        List<DictItem> dictItems = dictService.list(queryWrapper);
        return ResultUtilSimpleImpl.success(dictItems.stream()
                .map(OptionAssembler::assemblerOption)
                .collect(Collectors.toList()));
    }
}


