package cn.structured.admin.service;

import cn.structured.admin.entity.Member;
import cn.structured.mybatis.plus.starter.base.IBaseService;

public interface IMemberService extends IBaseService<Member> {

    /**
     * 成员状态变更
     *
     * @param id    成员ID
     * @param state 状态
     */
    void changeState(Long id, Integer state);


    /**
     * 重置成员密码
     *
     * @param id       成员ID
     * @param password 密码
     */
    void resetPassword(Long id, String password);

    /**
     * 获取当前成员部门信息
     * @return userId
     */
    String getMemberDeptTree(Long userId);
}
