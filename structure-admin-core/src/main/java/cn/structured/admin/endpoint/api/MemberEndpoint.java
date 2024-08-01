package cn.structured.admin.endpoint.api;

import cn.hutool.core.util.StrUtil;
import cn.structure.common.entity.ResResultVO;
import cn.structure.common.enums.NumberEnum;
import cn.structure.common.utils.ResultUtilSimpleImpl;
import cn.structured.admin.endpoint.assembler.MemberAssembler;
import cn.structured.admin.entity.Member;
import cn.structured.admin.manager.IUserManager;
import cn.structured.admin.service.IDeptService;
import cn.structured.admin.service.IMemberService;
import cn.structured.admin.dto.MemberDto;
import cn.structured.admin.dto.RestMemberPasswordDTO;
import cn.structured.admin.entity.Dept;
import cn.structured.admin.vo.MemberVO;
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
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
/**
 * 成员管理
 *
 * @author cqliut
 * @version 2023.0705
 * @since 1.0.1
 */
@Api(tags = "成员管理")
@RestController
@RequestMapping(value = "/api/member")
public class MemberEndpoint {
    @Resource
    private IMemberService service;
    @Resource
    private IUserManager userManager;
    @Resource
    private IDeptService deptService;

    @ApiOperation(value = "新增成员")
    @PostMapping(value = "/")
    public ResResultVO<Long> add(@RequestBody @Validated MemberDto create) {
        Member member = MemberAssembler.assembler(create);
        service.save(member);
        return ResultUtilSimpleImpl.success(member.getId());
    }

    @ApiOperation(value = "修改成员")
    @PutMapping(value = "/{id}")
    public ResResultVO<Void> update(@ApiParam(value = "成员ID", example = "1645717015337684992")
                                    @PathVariable("id") Long id,
                                    @RequestBody @Validated MemberDto update) {
        Member member = MemberAssembler.assembler(update);
        member.setId(id);
        service.updateById(member);
        return ResultUtilSimpleImpl.success(null);
    }

    @ApiOperation(value = "成员列表")
    @GetMapping(value = "/{page}/{pageSize}/page")
    public ResResultVO<ResPage<MemberVO>> page(@ApiParam(value = "关键字", example = "成员名词")
                                               @RequestParam(required = false) String keywords,
                                               @ApiParam(value = "是否启用", example = "1")
                                               @RequestParam(required = false) Integer state,
                                               @ApiParam(value = "部门ID", example = "1")
                                               @RequestParam(required = false) Long deptId,
                                               @ApiParam(value = "页码", example = "1")
                                               @PathVariable(value = "page") Long page,
                                               @ApiParam(value = "页大小", example = "10")
                                               @PathVariable(value = "pageSize") Long pageSize) {

        LambdaQueryWrapper<Member> queryWrapper = Wrappers.<Member>lambdaQuery()
                .eq(null != state, Member::getState, state)
                .eq(null != deptId, Member::getDeptId, deptId)
                .like(StrUtil.isNotBlank(keywords), Member::getName, StringPool.PERCENT + keywords + StringPool.PERCENT)
                .or()
                .like(StrUtil.isNotBlank(keywords), Member::getPhone, StringPool.PERCENT + keywords + StringPool.PERCENT);

        Page<Member> memberPage = service.page(new Page<>(page, pageSize), queryWrapper);
        ResPage<MemberVO> resultPage = ResPage.convert(memberPage, MemberAssembler::assembler);
        //有返回条目则需要对部门名称进行处理
        if (resultPage.getTotal() > NumberEnum.ZERO.getValue()) {
            Set<Long> deptIds = resultPage.getRecords().stream().map(MemberVO::getDeptId).collect(Collectors.toSet());
            Map<Long, String> deptMap = deptService.listByIds(deptIds).stream().collect(Collectors.toMap(Dept::getId, Dept::getName));
            resultPage.getRecords().forEach(memberVO ->memberVO.setDeptName(deptMap.get(memberVO.getDeptId())));
        }
        return ResultUtilSimpleImpl.success(resultPage);
    }


    @ApiOperation(value = "查看成员详情")
    @GetMapping(value = "/{id}")
    public ResResultVO<MemberVO> get(@ApiParam(value = "成员ID", example = "1645717015337684992")
                                     @PathVariable("id")
                                     Long id) {
        Member member = service.getById(id);
        List<Long> userRole = userManager.getUserRoleIds(member.getUserId());
        MemberVO memberVo = MemberAssembler.assembler(member);
        memberVo.setRoleIds(userRole);
        return ResultUtilSimpleImpl.success(memberVo);
    }

    @ApiOperation(value = "删除成员")
    @DeleteMapping(value = "/{id}")
    public ResResultVO<Void> remove(@ApiParam(value = "成员ID", example = "1645717015337684992")
                                    @PathVariable("id") Long id) {
        service.removeById(id);
        return ResultUtilSimpleImpl.success(null);
    }


    @ApiOperation(value = "成员状态变更")
    @PutMapping(value = "/{id}/{state}/changeState")
    public ResResultVO<Void> changeState(@ApiParam(value = "成员项ID", example = "1645717015337684992")
                                         @PathVariable("id") Long id,
                                         @ApiParam(value = "成员状态", example = "1")
                                         @PathVariable Integer state) {
        service.changeState(id, state);
        return ResultUtilSimpleImpl.success(null);
    }

    @ApiOperation(value = "重置成员密码")
    @PutMapping(value = "/resetPassword")
    public ResResultVO<Void> resetPassword(@Validated @RequestBody RestMemberPasswordDTO restMemberPassword) {
        service.resetPassword(restMemberPassword.getMemberId(), restMemberPassword.getPassword());
        return ResultUtilSimpleImpl.success(null);
    }


    @ApiOperation(value = "查看当前成员详情")
    @GetMapping(value = "/getMemberDeptTree")
    public ResResultVO<String> getMemberDeptTree(@ApiParam(value = "用户ID", example = "1")
                                                 @RequestParam(value = "userId")
                                                 Long userId) {

        return ResultUtilSimpleImpl.success(service.getMemberDeptTree(userId));
    }

}
