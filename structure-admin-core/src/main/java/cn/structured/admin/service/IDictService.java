package cn.structured.admin.service;

import cn.structured.admin.entity.DictItem;
import cn.structured.mybatis.plus.starter.base.IBaseService;

public interface IDictService extends IBaseService<DictItem> {

    /**
     * 启用
     *
     * @param dictItemId 字典项ID
     */
    void enableItem(Long dictItemId);

    /**
     * 停用
     *
     * @param dictItemId 字典项ID
     */
    void disableItem(Long dictItemId);
}
