package cn.structured.sa.service;

import cn.structured.mybatis.plus.starter.base.IBaseService;
import cn.structured.sa.entity.Config;

import java.util.List;

/**
 * 系统配置
 *
 * @author chuck
 * @since JDK1.8
 */
public interface IConfigService extends IBaseService<Config> {

    /**
     * 保存系统配置
     *
     * @param configList     配置列表
     * @param organizationId 机构ID
     */
    void saveSysConfig(List<Config> configList, Long organizationId);
}
