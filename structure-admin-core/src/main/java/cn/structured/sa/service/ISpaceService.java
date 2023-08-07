package cn.structured.sa.service;

import cn.structured.mybatis.plus.starter.base.IBaseService;
import cn.structured.sa.entity.Space;

/**
 * 空间管理
 *
 * @author chuck
 * @since JDK1.8
 */
public interface ISpaceService extends IBaseService<Space> {
    /**
     * 启用
     *
     * @param spaceId 空间ID
     */
    void enable(Long spaceId);

    /**
     * 停用
     *
     * @param spaceId 空间ID
     */
    void disable(Long spaceId);
}
