package cn.structured.sa.service.impl;

import cn.structured.mybatis.plus.starter.base.BaseServiceImpl;
import cn.structured.sa.entity.Dept;
import cn.structured.sa.mapper.DeptMapper;
import cn.structured.sa.service.IDeptService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.io.Serializable;

/**
 * 部门管理
 *
 * @author cqliut
 * @version 2023.0711
 * @since 1.0.1
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class DeptServiceImpl extends BaseServiceImpl<DeptMapper, Dept> implements IDeptService {

    @Override
    public void enable(Long deptId) {
        Dept dept = new Dept();
        dept.setId(deptId);
        dept.setEnabled(true);
        //todo 启用过
        baseMapper.updateById(dept);
    }

    @Override
    public void disable(Long deptId) {
        Dept dept = new Dept();
        dept.setId(deptId);
        dept.setEnabled(false);
        baseMapper.updateById(dept);
    }

    @Override
    public boolean removeById(Serializable id) {
        //todo 如果已经启用过则不能在删除
        return super.removeById(id);
    }
}
