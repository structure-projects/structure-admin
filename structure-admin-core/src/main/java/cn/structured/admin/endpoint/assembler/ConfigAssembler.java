package cn.structured.admin.endpoint.assembler;

import cn.structured.admin.dto.ConfigDTO;
import cn.structured.admin.entity.Config;
import cn.structured.admin.vo.ConfigVO;

/**
 * 配置装配器
 *
 * @author chuck
 * @since JDK1.8
 */
public class ConfigAssembler {
    private ConfigAssembler() {
    }


    /**
     * 装配用户配置VO
     *
     * @param config 用户配置PO
     * @return ConfigVO
     */
    public static ConfigVO assembler(Config config) {
        ConfigVO configVo = new ConfigVO();
        configVo.setId(config.getId());
        configVo.setKey(config.getCode());
        configVo.setValue(config.getValue());
        configVo.setRemark(config.getRemark());
        return configVo;
    }

    public static Config assembler(ConfigDTO userConfig) {
        Config config = new Config();
        config.setCode(userConfig.getKey());
        config.setValue(userConfig.getValue());
        config.setRemark(userConfig.getRemark());
        return config;
    }

}
