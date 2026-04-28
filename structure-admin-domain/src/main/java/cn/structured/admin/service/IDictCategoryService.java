package cn.structured.admin.service;

import cn.structured.admin.entity.DictCategory;
import cn.structured.mybatis.plus.starter.base.IBaseService;

/**
 * 字典管理
 *
 * @author chuck
 * @since JDK1.8
 */
public interface IDictCategoryService extends IBaseService<DictCategory> {

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

}
