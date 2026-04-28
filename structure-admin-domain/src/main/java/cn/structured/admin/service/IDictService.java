package cn.structured.admin.service;

import cn.structured.admin.entity.DictItem;
import cn.structured.mybatis.plus.starter.base.IBaseService;
/**
 * 字典管理
 *
 * @author chuck
 * @version 2024/07/13 下午4:37
 * @since 1.8
 */
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
