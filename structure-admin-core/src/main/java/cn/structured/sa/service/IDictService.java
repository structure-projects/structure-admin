package cn.structured.sa.service;

import cn.structured.mybatis.plus.starter.base.IBaseService;
import cn.structured.sa.entity.DictCategory;
import cn.structured.sa.entity.DictItem;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;

import java.util.List;

/**
 * 字典Service
 *
 * @author cqliut
 * @version 2023.0711
 * @since 1.0.1
 */
public interface IDictService extends IBaseService<DictCategory> {

    /**
     * 启用
     *
     * @param dictCategoryId 字典类ID
     */
    void enable(Long dictCategoryId);

    /**
     * 停用
     *
     * @param dictCategoryId 字典类ID
     */
    void disable(Long dictCategoryId);

    /**
     * 保存字典项
     *
     * @param dictItem 字典项
     */
    void saveDictItem(DictItem dictItem);

    /**
     * 修改字典项
     *
     * @param dictItem 字典项
     */
    void updateDictItemById(DictItem dictItem);

    /**
     * 移除字典项
     *
     * @param dictItemId 字典项ID
     */
    void removeDictItemById(Long dictItemId);

    /**
     * 查询字典项列表
     *
     * @param dictItem 字典项
     * @return 返回字典项ID
     */
    List<DictItem> getDictItemList(DictItem dictItem);

    /**
     * 获取字典项列表
     *
     * @param queryWrapper 字典项
     * @return 返回字典项ID
     */
    List<DictItem> getDictItemList(LambdaQueryWrapper<DictItem> queryWrapper);


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
