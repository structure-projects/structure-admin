package cn.structured.admin.core.service.impl;

import cn.structured.admin.core.entity.DictItem;
import cn.structured.admin.core.mapper.DictItemMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.util.ReflectionTestUtils;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@DisplayName("DictItemServiceImpl 单元测试")
class DictItemServiceImplTest {

    @Mock
    private DictItemMapper dictItemMapper;

    private DictItemServiceImpl dictItemService;

    @BeforeEach
    void setUp() {
        dictItemService = new DictItemServiceImpl();
        ReflectionTestUtils.setField(dictItemService, "baseMapper", dictItemMapper);
    }

    @Test
    @DisplayName("enableItem: 设置 enabled=true 并调用 mapper.updateById")
    void enableItemShouldSetEnabledTrueAndCallMapper() {
        dictItemService.enableItem(100L);

        ArgumentCaptor<DictItem> captor = ArgumentCaptor.forClass(DictItem.class);
        verify(dictItemMapper, times(1)).updateById(captor.capture());

        DictItem updated = captor.getValue();
        assertEquals(100L, updated.getId());
        assertTrue(updated.getEnabled());
    }

    @Test
    @DisplayName("disableItem: 设置 enabled=false 并调用 mapper.updateById")
    void disableItemShouldSetEnabledFalseAndCallMapper() {
        dictItemService.disableItem(200L);

        ArgumentCaptor<DictItem> captor = ArgumentCaptor.forClass(DictItem.class);
        verify(dictItemMapper, times(1)).updateById(captor.capture());

        DictItem updated = captor.getValue();
        assertEquals(200L, updated.getId());
        assertFalse(updated.getEnabled());
    }
}
