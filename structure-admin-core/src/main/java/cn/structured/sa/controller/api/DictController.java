package cn.structured.sa.controller.api;

import cn.structure.common.entity.ResResultVO;
import cn.structure.common.utils.ResultUtilSimpleImpl;
import cn.structure.starter.oauth.common.util.UserUtil;
import cn.structured.mybatis.plus.starter.core.QueryJoinPageListWrapper;
import cn.structured.mybatis.plus.starter.vo.ResPage;
import cn.structured.sa.client.dto.dict.CreateDictCategoryDTO;
import cn.structured.sa.client.dto.dict.DictItemDTO;
import cn.structured.sa.client.dto.dict.SearchDictCategoryDTO;
import cn.structured.sa.client.dto.dict.UpdateDictCategoryDTO;
import cn.structured.sa.client.groups.Create;
import cn.structured.sa.client.groups.Update;
import cn.structured.sa.client.vo.DictCategoryVO;
import cn.structured.sa.client.vo.DictItemVO;
import cn.structured.sa.client.vo.OptionVO;
import cn.structured.sa.controller.assembler.DictAssembler;
import cn.structured.sa.controller.assembler.OptionAssembler;
import cn.structured.sa.entity.DictCategory;
import cn.structured.sa.entity.DictItem;
import cn.structured.sa.group.SearchGroup;
import cn.structured.sa.service.IDictService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

/**
 * 字典管理
 * todo 分离字典类和字典项目为两个模块
 *
 * @author cqliut
 * @version 2023.0705
 * @since 1.0.1
 */
@RestController
@Api(tags = "字典管理")
@RequestMapping(value = "/dict")
@RequiredArgsConstructor
public class DictController {

    private final IDictService dictService;

    @ApiOperation(value = "新增字典类")
    @PostMapping(value = "/category/")
    public ResResultVO<Long> add(@RequestBody @Validated CreateDictCategoryDTO createDictCategory) {
        DictCategory dictCategory = DictAssembler.assemblerDictCategory(createDictCategory);
        dictService.save(dictCategory);
        return ResultUtilSimpleImpl.success(dictCategory.getId());
    }

    @ApiOperation(value = "修改字典类")
    @PutMapping(value = "/category/")
    public ResResultVO<Void> update(@RequestBody @Validated UpdateDictCategoryDTO updateDictCategory) {
        dictService.updateById(DictAssembler.assemblerDictCategory(updateDictCategory));
        return ResultUtilSimpleImpl.success(null);
    }

    @ApiOperation(value = "字典类分页列表")
    @GetMapping(value = "/category/list/{page}/{pageSize}/")
    public ResResultVO<ResPage<DictCategoryVO>> page(@ApiParam(value = "页码", required = true, example = "1")
                                                     @PathVariable("page") Long page,
                                                     @ApiParam(value = "页大小", required = true, example = "20")
                                                     @PathVariable("pageSize") Long pageSize,
                                                     SearchDictCategoryDTO searchDictCategory) {
        DictCategory dictCategory = new DictCategory();
        dictCategory.setName(searchDictCategory.getName());
        dictCategory.setCode(searchDictCategory.getCode());
        dictCategory.setEnabled(searchDictCategory.getEnabled());
        dictCategory.setOrganizationId(UserUtil.getOrganizationId());
        QueryJoinPageListWrapper<DictCategory> queryWrapper = new QueryJoinPageListWrapper<>(dictCategory);
        queryWrapper.setJoinGroup(SearchGroup.class);
        queryWrapper.setIsJoin(true);

        Page<DictCategory> pageParam = new Page<>(page, pageSize);
        pageParam.setOptimizeCountSql(false);

        IPage<DictCategory> pageResult = dictService.page(pageParam, queryWrapper);
        return ResultUtilSimpleImpl.success(ResPage.convert(pageResult, DictAssembler::assemblerDictCategory));
    }

    @ApiOperation(value = "删除字典类")
    @DeleteMapping(value = "/category/{dictCategoryId}")
    public ResResultVO<Void> remove(@ApiParam(value = "字典类ID", example = "1645717015337684992")
                                    @PathVariable("dictCategoryId") Long dictCategoryId) {
        dictService.removeById(dictCategoryId);
        return ResultUtilSimpleImpl.success(null);
    }

    @ApiOperation(value = "字典项列表")
    @GetMapping(value = "/item/list")
    public ResResultVO<List<DictItemVO>> itemList(@ApiParam(value = "字典类CODE", example = "SEX")
                                                  @RequestParam String dictCategoryCode,
                                                  @ApiParam(value = "是否启用", example = "TRUE")
                                                  @RequestParam(required = false) Boolean enabled) {
        DictItem dictItem = new DictItem();
        dictItem.setCode(dictCategoryCode);
        dictItem.setEnabled(enabled);


        List<DictItem> dictItemList = dictService.getDictItemList(dictItem);
        return ResultUtilSimpleImpl.success(dictItemList
                .stream()
                .map(DictAssembler::assemblerDictItem)
                .collect(Collectors.toList()));
    }

    @ApiOperation(value = "创建字典项")
    @PostMapping(value = "/item/")
    public ResResultVO<Long> addItem(@RequestBody @Validated(value = Create.class) DictItemDTO dictItem) {
        DictItem item = DictAssembler.assemblerDictItem(dictItem);
        dictService.saveDictItem(item);
        return ResultUtilSimpleImpl.success(item.getId());
    }

    @ApiOperation(value = "更新字典项")
    @PutMapping(value = "/item/")
    public ResResultVO<Void> editItem(@RequestBody @Validated(value = Update.class) DictItemDTO dictItem) {
        dictService.updateDictItemById(DictAssembler.assemblerDictItem(dictItem));
        return ResultUtilSimpleImpl.success(null);
    }

    @ApiOperation(value = "删除字典项")
    @DeleteMapping(value = "/item/{dictItemId}")
    public ResResultVO<Void> removeItem(@ApiParam(value = "字典项ID", example = "1645717015337684992")
                                        @PathVariable("dictItemId") Long dictItemId) {
        dictService.removeDictItemById(dictItemId);
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

    @ApiOperation(value = "启用字典项")
    @PutMapping(value = "/item/enable/{dictItemId}")
    public ResResultVO<Void> enableItem(@ApiParam(value = "字典项ID", example = "1645717015337684992")
                                        @PathVariable("dictItemId") Long dictItemId) {
        dictService.enableItem(dictItemId);
        return ResultUtilSimpleImpl.success(null);
    }

    @ApiOperation(value = "停用字典项")
    @PutMapping(value = "/item/disable/{dictItemId}")
    public ResResultVO<Void> disableItem(@ApiParam(value = "字典项ID", example = "1645717015337684992")
                                         @PathVariable("dictItemId") Long dictItemId) {
        dictService.disableItem(dictItemId);
        return ResultUtilSimpleImpl.success(null);
    }


    @ApiOperation(value = "下拉选", notes = "当前组织某个字典类的全部启用字典项")
    @GetMapping(value = "/option")
    public ResResultVO<List<OptionVO>> option(@ApiParam(value = "字典类CODE", example = "SEX")
                                              @RequestParam String dictCategoryCode) {
        LambdaQueryWrapper<DictItem> queryWrapper = Wrappers.<DictItem>lambdaQuery()
                .eq(DictItem::getCode, dictCategoryCode)
                .eq(DictItem::getEnabled, Boolean.TRUE)
                .eq(DictItem::getOrganizationId, UserUtil.getOrganizationId())
                .select(DictItem::getId, DictItem::getCode, DictItem::getName, DictItem::getValue);
        List<DictItem> dictItems = dictService.getDictItemList(queryWrapper);
        return ResultUtilSimpleImpl.success(dictItems.stream()
                .map(OptionAssembler::assemblerOption)
                .collect(Collectors.toList()));
    }
    //todo 更新字典项排序,并验证所有的排序是否正常
}
