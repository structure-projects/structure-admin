package cn.structured.sa.controller.assembler;

import cn.hutool.json.JSONObject;
import cn.structure.common.enums.NumberEnum;
import cn.structure.starter.oauth.common.util.UserUtil;
import cn.structured.sa.client.dto.config.ConfigDTO;
import cn.structured.sa.entity.Config;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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
     * 装配成系统配置-PO
     * @param sysConfig 系统配置
     * @return
     */
    public static List<Config> assemblerSysConfig(JSONObject sysConfig) {
        return sysConfig.entrySet()
                .stream()
                .map(ConfigAssembler::assemblerSysConfig)
                .collect(Collectors.toList());
    }

    /**
     * 装配成系统配置 JSON
     * @param sysConfig PO
     * @return
     */
    public static JSONObject assemblerSysConfig(List<Config> sysConfig) {
        JSONObject configObject = new JSONObject();
        sysConfig.forEach(config -> configObject.put(config.getCode(), config.getValue()));
        return configObject;
    }

    /**
     * 装配用户配置DTO
     * @param userConfig  用户配置PO
     * @return
     */
    public static ConfigDTO assembler(Config userConfig) {
        ConfigDTO config = new ConfigDTO();
        config.setKey(userConfig.getCode());
        config.setValue(userConfig.getValue());
        return config;
    }

    public static Config assembler(ConfigDTO userConfig) {
        Config config = new Config();
        config.setCode(userConfig.getKey());
        config.setValue(userConfig.getValue());
        config.setOrganizationId(UserUtil.getOrganizationId());
        config.setUserId(UserUtil.getUserId());
        config.setType(NumberEnum.ONE.getValue());
        return config;
    }

    /**
     * 转配为系统配置 解析JSON key value
     * @param entry key value 包装体
     * @return
     */
    private static Config assemblerSysConfig(Map.Entry entry) {
        Config config = new Config();
        config.setCode(entry.getKey().toString());
        config.setValue(entry.getValue().toString());
        config.setOrganizationId(UserUtil.getOrganizationId());
        config.setUserId(UserUtil.getUserId());
        config.setType(NumberEnum.ZERO.getValue());
        return config;
    }

}
