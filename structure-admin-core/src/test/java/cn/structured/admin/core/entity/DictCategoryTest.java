package cn.structured.admin.core.entity;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("DictCategory 实体单元测试")
class DictCategoryTest {

    @Test
    @DisplayName("set/get 所有字段正确")
    void allFieldsShouldWork() {
        LocalDateTime now = LocalDateTime.now();

        DictCategory dictCategory = new DictCategory();
        dictCategory.setId(1L);
        dictCategory.setName("性别");
        dictCategory.setCode("gender");
        dictCategory.setEnabled(true);
        dictCategory.setRemark("用户性别字典");
        dictCategory.setOrganizationId(1000L);
        dictCategory.setCreateBy(1L);
        dictCategory.setCreateTime(now);
        dictCategory.setUpdateBy(2L);
        dictCategory.setUpdateTime(now);
        dictCategory.setDeleted(false);

        assertEquals(1L, dictCategory.getId());
        assertEquals("性别", dictCategory.getName());
        assertEquals("gender", dictCategory.getCode());
        assertTrue(dictCategory.getEnabled());
        assertEquals("用户性别字典", dictCategory.getRemark());
        assertEquals(1000L, dictCategory.getOrganizationId());
        assertEquals(1L, dictCategory.getCreateBy());
        assertEquals(2L, dictCategory.getUpdateBy());
        assertEquals(now, dictCategory.getCreateTime());
        assertEquals(now, dictCategory.getUpdateTime());
        assertFalse(dictCategory.getDeleted());
    }

    @Test
    @DisplayName("enabled 为 true 表示启用")
    void enabledTrueMeansActive() {
        DictCategory dictCategory = new DictCategory();
        dictCategory.setEnabled(true);
        assertTrue(dictCategory.getEnabled());
    }

    @Test
    @DisplayName("enabled 为 false 表示禁用")
    void enabledFalseMeansDisabled() {
        DictCategory dictCategory = new DictCategory();
        dictCategory.setEnabled(false);
        assertFalse(dictCategory.getEnabled());
    }
}
