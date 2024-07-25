package cn.structured.admin.service.impl;

import cn.hutool.core.util.StrUtil;
import cn.structured.admin.entity.Dept;
import cn.structured.admin.mapper.DeptMapper;
import cn.structured.admin.service.IDeptService;
import cn.structured.mybatis.plus.starter.base.BaseServiceImpl;
import org.springframework.stereotype.Service;

@Service
public class DeptServiceImpl extends BaseServiceImpl<DeptMapper, Dept> implements IDeptService {

    @Override
    public boolean save(Dept entity) {
        entity.setTreePath(getTreePath(entity.getParentId()));
        return super.save(entity);
    }

    @Override
    public boolean updateById(Dept entity) {
        entity.setTreePath(getTreePath(entity.getParentId()));
        return super.updateById(entity);
    }

    private String getTreePath(Long id) {
        Dept dept = baseMapper.selectById(id);
        if (null != dept) {
            return dept.getTreePath() + StrUtil.COMMA + id;
        } else {
            return id.toString();
        }
    }
}
