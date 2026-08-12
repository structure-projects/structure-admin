package cn.structured.admin.core.endpoint.assembler;

import cn.structured.admin.common.dto.ConfigDTO;
import cn.structured.admin.common.vo.ConfigVO;
import cn.structured.admin.core.entity.Config;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("ConfigAssembler 单元测试")
class ConfigAssemblerTest {

    @Test
    @DisplayName("assembler(Config) -> ConfigVO: 正常转换，key=code")
    void assemblerConfigToVOSShouldMapCodeToKey() {
        Config config = new Config();
        config.setId(1L);
        config.setCode("login_mode");
        config.setValue("STANDALONE");
        config.setRemark("登录模式");

        ConfigVO vo = ConfigAssembler.assembler(config);

        assertNotNull(vo);
        assertEquals(1L, vo.getId());
        assertEquals("login_mode", vo.getKey());
        assertEquals("STANDALONE", vo.getValue());
        assertEquals("登录模式", vo.getRemark());
    }

    @Test
    @DisplayName("assembler(Config) -> ConfigVO: null 输入可能 NPE")
    void assemblerConfigToVONullInputMayThrow() {
        assertThrows(NullPointerException.class,
                () -> ConfigAssembler.assembler((Config) null));
    }

    @Test
    @DisplayName("assembler(ConfigDTO) -> Config: 正常转换，key->code")
    void assemblerFromDtoShouldMapKeyToCode() {
        ConfigDTO dto = new ConfigDTO();
        dto.setKey("site_name");
        dto.setValue("IAM系统");
        dto.setRemark("站点名称");

        Config config = ConfigAssembler.assembler(dto);

        assertNotNull(config);
        assertEquals("site_name", config.getCode());
        assertEquals("IAM系统", config.getValue());
        assertEquals("站点名称", config.getRemark());
    }

    @Test
    @DisplayName("assembler(ConfigDTO) -> Config: null 输入可能 NPE")
    void assemblerFromDtoNullInputMayThrow() {
        assertThrows(NullPointerException.class,
                () -> ConfigAssembler.assembler((ConfigDTO) null));
    }

    @Test
    @DisplayName("assembler(Config) -> ConfigVO: value 为空时正常处理")
    void assemblerConfigToVOShouldHandleEmptyValue() {
        Config config = new Config();
        config.setCode("empty_key");
        config.setValue("");

        ConfigVO vo = ConfigAssembler.assembler(config);
        assertEquals("", vo.getValue());
    }
}
