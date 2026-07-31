package cn.structured.admin.core.entity;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("DictItem 实体单元测试")
class DictItemTest {

    @Test
    @DisplayName("set/get 所有字段正确")
    void allFieldsShouldWork() {
        LocalDateTime now = LocalDateTime.now();

        DictItem dictItem = new DictItem();
        dictItem.setId(1L);
        dictItem.setName("男");
        dictItem.setCode("male");
        dictItem.setValue("1");
        dictItem.setSort(1);
        dictItem.setEnabled(true);
        dictItem.setOrganizationId(1000L);
        dictItem.setCreateTime(now);
        dictItem.setUpdateTime(now);
        dictItem.setCreateBy(1L);
        dictItem.setUpdateBy(2L);
        dictItem.setDeleted(false);

        assertEquals(1L, dictItem.getId());
        assertEquals("男", dictItem.getName());
        assertEquals("male", dictItem.getCode());
        assertEquals("1", dictItem.getValue());
        assertEquals(1, dictItem.getSort());
        assertTrue(dictItem.getEnabled());
        assertEquals(1000L, dictItem.getOrganizationId());
        assertEquals(now, dictItem.getCreateTime());
        assertEquals(now, dictItem.getUpdateTime());
        assertEquals(1L, dictItem.getCreateBy());
        assertEquals(2L, dictItem.getUpdateBy());
        assertFalse(dictItem.getDeleted());
    }

    @Test
    @DisplayName("sort 排序字段默认为 null")
    void sortShouldDefaultToNull() {
        DictItem dictItem = new DictItem();
        assertNull(dictItem.getSort());
    }

    @Test
    @DisplayName("enabled 控制项的启用状态")
    void enabledControlsActiveState() {
        DictItem enabled = new DictItem();
        enabled.setEnabled(true);
        assertTrue(enabled.getEnabled());

        DictItem disabled = new DictItem();
        disabled.setEnabled(false);
        assertFalse(disabled.getEnabled());
    }
}
