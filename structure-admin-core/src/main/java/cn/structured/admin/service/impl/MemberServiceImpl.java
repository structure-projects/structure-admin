package cn.structured.admin.service.impl;

import cn.hutool.core.util.StrUtil;
import cn.structure.common.enums.NumberEnum;
import cn.structured.admin.entity.Dept;
import cn.structured.admin.entity.Member;
import cn.structured.admin.manager.IUserManager;
import cn.structured.admin.mapper.DeptMapper;
import cn.structured.admin.mapper.MemberMapper;
import cn.structured.admin.service.IMemberService;
import cn.structured.mybatis.plus.starter.base.BaseServiceImpl;
import cn.structured.user.api.dto.user.RegisterPlatformUserDTO;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.google.common.collect.Lists;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
/**
 * 成员管理
 *
 * @author chuck
 * @version 2024/07/13 下午4:37
 * @since 1.8
 */
@Service
public class MemberServiceImpl extends BaseServiceImpl<MemberMapper, Member> implements IMemberService {

    @Resource
    private IUserManager userManager;

    @Resource
    private DeptMapper deptMapper;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean save(Member entity) {
        RegisterPlatformUserDTO registerPlatformUserDto = new RegisterPlatformUserDTO();
        registerPlatformUserDto.setNickname(entity.getName());
        registerPlatformUserDto.setPlatformUserId(entity.getPhone());
        registerPlatformUserDto.setType("phone");
        Long userId = userManager.registerPlatformUser(registerPlatformUserDto);
        entity.setUserId(userId);
        userManager.resetPassword(userId, "123456");
        userManager.assigningRole(entity.getRoleIds(), userId);
        return super.save(entity);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean updateById(Member entity) {
        Member member = baseMapper.selectById(entity.getId());
        userManager.assigningRole(entity.getRoleIds(), member.getUserId());
        return IMemberService.super.updateById(entity);
    }


    @Override
    @Transactional(rollbackFor = Exception.class)
    public void changeState(Long id, Integer state) {
        Member member = baseMapper.selectById(id);
        if (NumberEnum.ONE.getValue() == state) {
            userManager.enable(member.getUserId());
        } else {
            userManager.disable(member.getUserId());
        }
        Member updateMember = new Member();
        updateMember.setId(id);
        updateMember.setState(state);
        baseMapper.updateById(updateMember);
    }

    @Override
    public void resetPassword(Long id, String password) {
        Member member = baseMapper.selectById(id);
        userManager.resetPassword(member.getUserId(), password);
    }

    @Override
    public String getMemberDeptTree(Long userId) {
        StringBuilder deptName = new StringBuilder();
        Member member = baseMapper.selectOne(Wrappers.<Member>lambdaQuery().eq(Member::getUserId, userId));
        if (null != member) {
            Dept currentDept = deptMapper.selectById(member.getDeptId());
            if (null != currentDept) {
                String treePath = currentDept.getTreePath();
                String[] treeArray = treePath.split(StrUtil.COMMA);
                List<Long> treeList = Lists.newArrayList();
                for (String s : treeArray) {
                    treeList.add(Long.parseLong(s));
                }
                if (!treeList.isEmpty()) {
                    List<Dept> depts = deptMapper.selectBatchIds(treeList);
                    Map<Long, Dept> deptMap = depts.stream().collect(Collectors.toMap(Dept::getId, e -> e));
                    for (Long deptId : treeList) {
                        Dept dept = deptMap.get(deptId);
                        if (null != dept) {
                            deptName.append(dept.getName());
                            deptName.append("-");
                        }
                    }
                }
                deptName.append(currentDept.getName());
            }
        }
        return deptName.toString();
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean removeById(Serializable id) {
        Member member = baseMapper.selectById(id);
        userManager.removeById(member.getUserId());
        return super.removeById(id);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean removeByIds(Collection<? extends Serializable> idList) {
        Set<Long> userIds = baseMapper.selectList(Wrappers.<Member>lambdaQuery().in(Member::getId, idList)
                        .select(Member::getId, Member::getUserId)).stream().map(Member::getUserId)
                .collect(Collectors.toSet());
        userManager.removeByIds(userIds);
        return super.removeByIds(idList);
    }
}
