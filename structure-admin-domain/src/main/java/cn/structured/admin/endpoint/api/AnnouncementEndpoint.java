package cn.structured.admin.endpoint.api;

import cn.hutool.core.text.CharSequenceUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.structure.common.entity.ResResultVO;
import cn.structure.common.utils.ResultUtilSimpleImpl;
import cn.structure.common.vo.ReqPage;
import cn.structure.common.vo.ResPage;
import cn.structured.admin.api.aop.OperationLog;
import cn.structured.admin.api.dto.AnnouncementDTO;
import cn.structured.admin.api.dto.AnnouncementQuery;
import cn.structured.admin.api.vo.AnnouncementVO;
import cn.structured.admin.api.vo.AppAnnouncementVO;
import cn.structured.admin.endpoint.assembler.AnnouncementAssembler;
import cn.structured.admin.entity.Announcement;
import cn.structured.admin.service.IAnnouncementService;
import cn.structured.mybatis.plus.starter.convert.ResPageConvert;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Slf4j
@Api(tags = "系统公告")
@RestController
@AllArgsConstructor
@RequestMapping("/api/announcement")
public class AnnouncementEndpoint {

    private final IAnnouncementService announcementService;

    @PostMapping("/")
    @ApiOperation(value = "添加系统公告")
    @OperationLog(value = "添加系统公告", module = "announcement")
    public ResResultVO<Long> add(@Validated @RequestBody AnnouncementDTO announcementDTO) {
        Announcement announcement = AnnouncementAssembler.assembler(announcementDTO);
        announcementService.save(announcement);
        return ResultUtilSimpleImpl.success(announcement.getId());
    }

    @PutMapping("/{id}")
    @ApiOperation(value = "修改系统公告")
    @OperationLog(value = "修改系统公告", module = "announcement")
    public ResResultVO<Long> update(@PathVariable Long id, @RequestBody AnnouncementDTO announcementDTO) {
        Announcement announcement = AnnouncementAssembler.assembler(announcementDTO);
        announcement.setId(id);
        announcementService.updateById(announcement);
        return ResultUtilSimpleImpl.success(announcement.getId());
    }

    @DeleteMapping("/{id}")
    @ApiOperation(value = "删除系统公告")
    @OperationLog(value = "删除系统公告", module = "announcement")
    public ResResultVO<Long> delete(@PathVariable Long id) {
        announcementService.removeById(id);
        return ResultUtilSimpleImpl.success(id);
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "查询系统公告")
    public ResResultVO<AnnouncementVO> query(@PathVariable Long id) {
        Announcement announcement = announcementService.getById(id);
        if (ObjectUtil.isNull(announcement)) {
            return ResultUtilSimpleImpl.success(new AnnouncementVO());
        }
        return ResultUtilSimpleImpl.success(AnnouncementAssembler.assemblerAnnouncementVO(announcement));
    }

    @GetMapping("/list")
    @ApiOperation(value = "查询系统公告列表")
    public ResResultVO<ResPage<AnnouncementVO>> list(ReqPage reqPage, AnnouncementQuery announcementQuery) {
        Page<Announcement> page = announcementService.page(new Page<>(reqPage.getCurrentPage(), reqPage.getPageSize()),
                Wrappers.<Announcement>lambdaQuery()
                        .eq(ObjectUtil.isNotNull(announcementQuery.getScope()), Announcement::getScope, announcementQuery.getScope())
                        .eq(ObjectUtil.isNotNull(announcementQuery.getType()), Announcement::getType, announcementQuery.getType())
                        .eq(ObjectUtil.isNotNull(announcementQuery.getState()), Announcement::getState, announcementQuery.getState())
                        .eq(ObjectUtil.isNotNull(announcementQuery.getTop()), Announcement::getTop, announcementQuery.getTop())
                        .ge(ObjectUtil.isNotNull(announcementQuery.getBeginTime()), Announcement::getCreateTime, announcementQuery.getBeginTime())
                        .le(ObjectUtil.isNotNull(announcementQuery.getEndTime()), Announcement::getCreateTime, announcementQuery.getEndTime())
                        .like(CharSequenceUtil.isNotBlank(reqPage.getKeyword()), Announcement::getSubject, reqPage.getKeyword())
                        .orderByDesc(Announcement::getCreateTime)
        );
        return ResultUtilSimpleImpl.success(ResPageConvert.convert(page, AnnouncementAssembler::assemblerAnnouncementVO));
    }

    @GetMapping("/listByScope")
    @ApiOperation(value = "根据系统类型，查询系统公告列表")
    public ResResultVO<ResPage<AppAnnouncementVO>> listByScope(ReqPage reqPage, Integer scope) {
        Page<Announcement> page = announcementService.page(new Page<>(reqPage.getCurrentPage(), reqPage.getPageSize()),
                Wrappers.<Announcement>lambdaQuery()
                        .eq(ObjectUtil.isNotNull(scope), Announcement::getScope, scope)
                        .eq(Announcement::getState, 1)
                        .orderByDesc(Announcement::getTop, Announcement::getCreateTime)
        );
        return ResultUtilSimpleImpl.success(ResPageConvert.convert(page, AnnouncementAssembler::assembler));
    }
}
