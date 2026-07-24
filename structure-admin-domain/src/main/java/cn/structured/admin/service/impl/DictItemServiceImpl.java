package cn.structured.admin.service.impl;

import cn.structured.admin.mapper.DictItemMapper;
import cn.structured.admin.service.IDictService;
import cn.structured.admin.entity.DictItem;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * 字典项
 *
 * @author chuck
 * @version 2024/07/13 下午4:37
 * @since 1.8
 */
@Service
public class DictItemServiceImpl extends ServiceImpl<DictItemMapper, DictItem> implements IDictService {


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
