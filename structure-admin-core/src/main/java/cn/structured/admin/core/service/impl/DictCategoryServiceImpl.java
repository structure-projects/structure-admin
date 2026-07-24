package cn.structured.admin.core.service.impl;

import cn.structured.admin.core.entity.DictCategory;
import cn.structured.admin.core.mapper.DictCategoryMapper;
import cn.structured.admin.core.service.IDictCategoryService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
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
public class DictCategoryServiceImpl extends ServiceImpl<DictCategoryMapper, DictCategory> implements IDictCategoryService {

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
