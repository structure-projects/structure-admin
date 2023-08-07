package cn.structured.sa.service.impl;

import cn.structured.mybatis.plus.starter.base.BaseServiceImpl;
import cn.structured.mybatis.plus.starter.core.JoinHelper;
import cn.structured.mybatis.plus.starter.core.QueryJoinPageListWrapper;
import cn.structured.sa.entity.DictCategory;
import cn.structured.sa.entity.DictItem;
import cn.structured.sa.group.ListGroup;
import cn.structured.sa.mapper.DictCategoryMapper;
import cn.structured.sa.mapper.DictItemMapper;
import cn.structured.sa.service.IDictService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

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
public class DictServiceImpl extends BaseServiceImpl<DictCategoryMapper, DictCategory> implements IDictService {

    private final DictItemMapper dictItemMapper;

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

    @Override
    public void saveDictItem(DictItem dictItem) {
        dictItemMapper.insert(dictItem);
    }

    @Override
    public void updateDictItemById(DictItem dictItem) {
        dictItemMapper.updateById(dictItem);
    }

    @Override
    public void removeDictItemById(Long dictItemId) {
        dictItemMapper.deleteById(dictItemId);
    }

    @Override
    public List<DictItem> getDictItemList(DictItem dictItem) {
        QueryJoinPageListWrapper<DictItem> queryWrapper = new QueryJoinPageListWrapper<>(dictItem);
        queryWrapper.setJoinGroup(ListGroup.class);
        queryWrapper.setIsJoin(true);
        List<HashMap<String, Object>> resultSet = dictItemMapper.selectJoinList(queryWrapper);
        return JoinHelper.getList(resultSet, DictItem.class);
    }

    @Override
    public List<DictItem> getDictItemList(LambdaQueryWrapper<DictItem> queryWrapper) {
        return dictItemMapper.selectList(queryWrapper);
    }

    @Override
    public void enableItem(Long dictItemId) {
        DictItem dictItem = new DictItem();
        dictItem.setId(dictItemId);
        dictItem.setEnabled(true);
        dictItemMapper.updateById(dictItem);
    }

    @Override
    public void disableItem(Long dictItemId) {
        DictItem dictItem = new DictItem();
        dictItem.setId(dictItemId);
        dictItem.setEnabled(false);
        dictItemMapper.updateById(dictItem);
    }
}
