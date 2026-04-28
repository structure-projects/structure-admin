package cn.structured.admin.service.impl;

import cn.structured.admin.entity.DictCategory;
import cn.structured.admin.mapper.DictCategoryMapper;
import cn.structured.admin.service.IDictCategoryService;
import cn.structured.mybatis.plus.starter.base.BaseServiceImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * 字典管理
 *
 * @author cqliut
 * @version 2023.0711
 * @since 1.0.1
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class DictCategoryServiceImpl extends BaseServiceImpl<DictCategoryMapper, DictCategory> implements IDictCategoryService {

    @Override
    public void enable(Long dictCategoryId) {
        DictCategory dictCategory = new DictCategory();
        dictCategory.setId(dictCategoryId);
        dictCategory.setEnabled(true);
        baseMapper.updateById(dictCategory);
    }

    @Override
    public void disable(Long dictCategoryId) {
        DictCategory dictCategory = new DictCategory();
        dictCategory.setId(dictCategoryId);
        dictCategory.setEnabled(false);
        baseMapper.updateById(dictCategory);
    }


}
