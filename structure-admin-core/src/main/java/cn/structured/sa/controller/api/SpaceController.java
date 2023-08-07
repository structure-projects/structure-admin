package cn.structured.sa.controller.api;

import cn.structure.common.entity.ResResultVO;
import cn.structure.common.utils.ResultUtilSimpleImpl;
import cn.structure.starter.oauth.common.util.UserUtil;
import cn.structured.mybatis.plus.starter.core.QueryJoinPageListWrapper;
import cn.structured.mybatis.plus.starter.vo.ResPage;
import cn.structured.sa.client.dto.space.CreateSpaceDTO;
import cn.structured.sa.client.dto.space.SearchSpaceDTO;
import cn.structured.sa.client.dto.space.UpdateSpaceDTO;
import cn.structured.sa.client.vo.SpaceVO;
import cn.structured.sa.controller.assembler.SpaceAssembler;
import cn.structured.sa.entity.Space;
import cn.structured.sa.group.SearchGroup;
import cn.structured.sa.service.ISpaceService;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * 空间管理
 * todo 完善空间管理并迁移出管理底座
 *
 * @author cqliut
 * @version 2023.0705
 * @since 1.0.1
 */
@RestController
@Api(tags = "空间管理")
@RequestMapping(value = "/space")
@RequiredArgsConstructor
public class SpaceController {

    private final ISpaceService spaceService;

    @ApiOperation(value = "新增空间")
    @PostMapping(value = "/")
    public ResResultVO<Long> add(@RequestBody @Validated CreateSpaceDTO createSpace) {
        Space space = SpaceAssembler.assembler(createSpace);
        spaceService.save(space);
        return ResultUtilSimpleImpl.success(space.getId());
    }

    @ApiOperation(value = "修改空间")
    @PutMapping(value = "/")
    public ResResultVO<Void> update(@RequestBody @Validated UpdateSpaceDTO updateSpace) {
        spaceService.updateById(SpaceAssembler.assembler(updateSpace));
        return ResultUtilSimpleImpl.success(null);
    }

    @ApiOperation(value = "空间列表")
    @GetMapping(value = "/list/{page}/{pageSize}/")
    public ResResultVO<ResPage<SpaceVO>> page(@ApiParam(value = "页码", required = true, example = "1")
                                              @PathVariable("page") Long page,
                                              @ApiParam(value = "页大小", required = true, example = "20")
                                              @PathVariable("pageSize") Long pageSize,
                                              SearchSpaceDTO searchSpace) {
        Space space = new Space();
        space.setName(searchSpace.getName());
        space.setCode(searchSpace.getCode());
        space.setOrganizationId(UserUtil.getOrganizationId());
        space.setEnabled(searchSpace.getEnabled());
        QueryJoinPageListWrapper<Space> queryWrapper = new QueryJoinPageListWrapper<>(space);
        queryWrapper.setJoinGroup(SearchGroup.class);
        queryWrapper.setIsJoin(true);

        Page<Space> pageParam = new Page<>(page, pageSize);
        pageParam.setOptimizeCountSql(false);

        IPage<Space> pageResult = spaceService.page(pageParam, queryWrapper);
        return ResultUtilSimpleImpl.success(ResPage.convert(pageResult, SpaceAssembler::assembler));
    }

    @ApiOperation(value = "删除空间")
    @DeleteMapping(value = "/{spaceId}")
    public ResResultVO<Void> remove(@ApiParam(value = "空间ID", example = "1645717015337684992")
                                    @PathVariable("spaceId") Long spaceId) {
        spaceService.removeById(spaceId);
        return ResultUtilSimpleImpl.success(null);
    }

    @ApiOperation(value = "启用")
    @PutMapping(value = "/enable/{spaceId}")
    public ResResultVO<Void> enable(@ApiParam(value = "空间ID", example = "1645717015337684992")
                                    @PathVariable("spaceId") Long spaceId) {
        spaceService.enable(spaceId);
        return ResultUtilSimpleImpl.success(null);
    }

    @ApiOperation(value = "停用")
    @PutMapping(value = "/disable/{spaceId}")
    public ResResultVO<Void> disable(@ApiParam(value = "空间ID", example = "1645717015337684992")
                                     @PathVariable("spaceId") Long spaceId) {
        spaceService.disable(spaceId);
        return ResultUtilSimpleImpl.success(null);
    }

}
