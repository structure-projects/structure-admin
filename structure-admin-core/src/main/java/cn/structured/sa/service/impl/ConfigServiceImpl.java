package cn.structured.sa.service.impl;

import cn.structure.common.enums.NumberEnum;
import cn.structured.mybatis.plus.starter.base.BaseServiceImpl;
import cn.structured.sa.entity.Config;
import cn.structured.sa.mapper.ConfigMapper;
import cn.structured.sa.service.IConfigService;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 系统配置
 *
 * @author chuck
 * @since JDK1.8
 */
@Slf4j
@Service
public class ConfigServiceImpl extends BaseServiceImpl<ConfigMapper, Config> implements IConfigService {
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void saveSysConfig(List<Config> configList, Long organizationId) {
        baseMapper.delete(Wrappers.<Config>lambdaQuery()
                .eq(Config::getType, NumberEnum.ZERO.getValue())
                .eq(Config::getOrganizationId, organizationId));
        baseMapper.insertList(configList);
    }
}
