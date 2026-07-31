package cn.structured.admin.core.entity;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Config 实体单元测试")
class ConfigTest {

    @Test
    @DisplayName("set/get 所有字段正确")
    void allFieldsShouldWork() {
        Config config = new Config();
        config.setId(1L);
        config.setCode("login_mode");
        config.setValue("STANDALONE");
        config.setRemark("登录模式配置");
        config.setOrganizationId(1000L);

        assertEquals(1L, config.getId());
        assertEquals("login_mode", config.getCode());
        assertEquals("STANDALONE", config.getValue());
        assertEquals("登录模式配置", config.getRemark());
        assertEquals(1000L, config.getOrganizationId());
    }

    @Test
    @DisplayName("无参构造创建的实体 hashcode 和 equals 基于 Data 注解")
    void dataAnnotationShouldGenerateEqualsAndHashCode() {
        Config config1 = new Config();
        config1.setId(1L);
        config1.setCode("key");
        config1.setValue("val");

        Config config2 = new Config();
        config2.setId(1L);
        config2.setCode("key");
        config2.setValue("val");

        assertEquals(config1, config2);
        assertEquals(config1.hashCode(), config2.hashCode());
    }

    @Test
    @DisplayName("不同字段创建的实体不相等")
    void differentConfigsShouldNotBeEqual() {
        Config config1 = new Config();
        config1.setCode("key1");

        Config config2 = new Config();
        config2.setCode("key2");

        assertNotEquals(config1, config2);
    }

    @Test
    @DisplayName("toString 包含 code 和 value")
    void toStringShouldContainFields() {
        Config config = new Config();
        config.setCode("test_key");
        config.setValue("test_value");

        String str = config.toString();
        assertTrue(str.contains("test_key"));
        assertTrue(str.contains("test_value"));
    }
}
