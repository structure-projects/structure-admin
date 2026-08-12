package cn.structured.admin.core.service;

import cn.structured.admin.core.entity.DictCategory;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * 字典管理
 *
 * @author chuck
 * @since JDK1.8
 */
public interface IDictCategoryService extends IService<DictCategory> {

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
