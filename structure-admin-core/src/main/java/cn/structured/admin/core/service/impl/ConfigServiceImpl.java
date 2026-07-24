package cn.structured.admin.core.service.impl;

import cn.structured.admin.core.mapper.ConfigMapper;
import cn.structured.admin.core.service.IConfigService;
import cn.structured.admin.core.entity.Config;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * 系统配置
 *
 * @author chuck
 * @since JDK1.8
 */
@Slf4j
@Service
public class ConfigServiceImpl extends ServiceImpl<ConfigMapper, Config> implements IConfigService {

}
