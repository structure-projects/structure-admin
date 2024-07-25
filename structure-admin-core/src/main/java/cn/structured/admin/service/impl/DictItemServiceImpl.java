package cn.structured.admin.service.impl;

import cn.structured.admin.mapper.DictItemMapper;
import cn.structured.admin.service.IDictService;
import cn.structured.admin.entity.DictItem;
import cn.structured.mybatis.plus.starter.base.BaseServiceImpl;
import groovy.util.logging.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class DictItemServiceImpl extends BaseServiceImpl<DictItemMapper, DictItem> implements IDictService {


    @Override
    public void enableItem(Long dictItemId) {
        DictItem dictItem = new DictItem();
        dictItem.setId(dictItemId);
        dictItem.setEnabled(true);
        baseMapper.updateById(dictItem);
    }

    @Override
    public void disableItem(Long dictItemId) {
        DictItem dictItem = new DictItem();
        dictItem.setId(dictItemId);
        dictItem.setEnabled(false);
        baseMapper.updateById(dictItem);
    }
}
