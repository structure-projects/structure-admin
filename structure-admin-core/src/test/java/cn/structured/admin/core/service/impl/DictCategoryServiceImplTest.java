package cn.structured.admin.core.service.impl;

import cn.structured.admin.core.entity.DictCategory;
import cn.structured.admin.core.mapper.DictCategoryMapper;
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
@DisplayName("DictCategoryServiceImpl 单元测试")
class DictCategoryServiceImplTest {

    @Mock
    private DictCategoryMapper dictCategoryMapper;

    private DictCategoryServiceImpl dictCategoryService;

    @BeforeEach
    void setUp() {
        dictCategoryService = new DictCategoryServiceImpl();
        ReflectionTestUtils.setField(dictCategoryService, "baseMapper", dictCategoryMapper);
    }

    @Test
    @DisplayName("enable: 设置 enabled=true 并调用 mapper.updateById")
    void enableShouldSetEnabledTrueAndCallMapper() {
        dictCategoryService.enable(10L);

        ArgumentCaptor<DictCategory> captor = ArgumentCaptor.forClass(DictCategory.class);
        verify(dictCategoryMapper, times(1)).updateById(captor.capture());

        DictCategory updated = captor.getValue();
        assertEquals(10L, updated.getId());
        assertTrue(updated.getEnabled());
    }

    @Test
    @DisplayName("disable: 设置 enabled=false 并调用 mapper.updateById")
    void disableShouldSetEnabledFalseAndCallMapper() {
        dictCategoryService.disable(20L);

        ArgumentCaptor<DictCategory> captor = ArgumentCaptor.forClass(DictCategory.class);
        verify(dictCategoryMapper, times(1)).updateById(captor.capture());

        DictCategory updated = captor.getValue();
        assertEquals(20L, updated.getId());
        assertFalse(updated.getEnabled());
    }
}
