package cn.structured.admin.endpoint.assembler;

import cn.structured.admin.entity.Member;
import cn.structured.admin.dto.MemberDto;
import cn.structured.admin.vo.MemberVO;
import cn.structured.basic.core.utils.SystemUtil;
/**
 * 成员装配器
 * @author chuck
 * @version 2024/07/19 下午11:40
 * @since 1.8
 */
public class MemberAssembler {

    public static MemberVO assembler(Member member) {
        MemberVO memberVo = new MemberVO();
        memberVo.setId(member.getId());
        memberVo.setPhone(member.getPhone());
        memberVo.setName(member.getName());
        memberVo.setSex(member.getSex());
        memberVo.setDeptId(member.getDeptId());
        memberVo.setState(member.getState());
        memberVo.setCreateTime(member.getCreateTime());
        return memberVo;
    }

    public static Member assembler(MemberDto memberDto) {
        Member member = new Member();
        member.setPhone(memberDto.getPhone());
        member.setName(memberDto.getName());
        member.setSex(memberDto.getSex());
        member.setDeptId(memberDto.getDeptId());
        member.setState(memberDto.getState());
        member.setOrganizationId(SystemUtil.getOrganizationId());
        member.setRoleIds(memberDto.getRoleIds());
        return member;
    }
}
