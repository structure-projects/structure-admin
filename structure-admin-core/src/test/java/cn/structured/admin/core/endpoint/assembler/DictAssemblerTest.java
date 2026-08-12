package cn.structured.admin.core.endpoint.assembler;

import cn.structured.admin.common.dto.DictCategoryDTO;
import cn.structured.admin.common.dto.DictItemDTO;
import cn.structured.admin.common.vo.DictCategoryVO;
import cn.structured.admin.common.vo.DictItemVO;
import cn.structured.admin.core.entity.DictCategory;
import cn.structured.admin.core.entity.DictItem;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("DictAssembler 单元测试")
class DictAssemblerTest {

    // ========== DictCategory ==========

    @Test
    @DisplayName("assemblerDictCategory(DTO) -> DictCategory: 正常转换")
    void assemblerDictCategoryFromDtoShouldConvert() {
        DictCategoryDTO dto = new DictCategoryDTO();
        dto.setName("性别");
        dto.setCode("gender");
        dto.setEnabled(true);
        dto.setRemark("性别字典");

        DictCategory entity = DictAssembler.assemblerDictCategory(dto);

        assertNotNull(entity);
        assertEquals("性别", entity.getName());
        assertEquals("gender", entity.getCode());
        assertTrue(entity.getEnabled());
        assertEquals("性别字典", entity.getRemark());
    }

    @Test
    @DisplayName("assemblerDictCategory(DTO) -> DictCategory: null 输入可能 NPE")
    void assemblerDictCategoryFromDtoNullInputMayThrow() {
        assertThrows(NullPointerException.class,
                () -> DictAssembler.assemblerDictCategory((DictCategoryDTO) null));
    }

    @Test
    @DisplayName("assemblerDictCategory(Entity) -> DictCategoryVO: 正常转换，operatorTime=updateTime")
    void assemblerDictCategoryToVOSShouldMapOperatorTime() {
        LocalDateTime now = LocalDateTime.now();
        DictCategory entity = new DictCategory();
        entity.setId(1L);
        entity.setName("状态");
        entity.setCode("status");
        entity.setRemark("状态字典");
        entity.setEnabled(true);
        entity.setUpdateTime(now);
        entity.setOrganizationId(1000L);

        DictCategoryVO vo = DictAssembler.assemblerDictCategory(entity);

        assertEquals(1L, vo.getId());
        assertEquals("状态", vo.getName());
        assertEquals("status", vo.getCode());
        assertEquals("状态字典", vo.getRemark());
        assertTrue(vo.getEnabled());
        assertEquals(now, vo.getOperatorTime());
        assertEquals(1000L, vo.getOrganizationId());
    }

    // ========== DictItem ==========

    @Test
    @DisplayName("assemblerDictItem(DTO) -> DictItem: 正常转换")
    void assemblerDictItemFromDtoShouldConvert() {
        DictItemDTO dto = new DictItemDTO();
        dto.setName("男");
        dto.setCode("male");
        dto.setValue("1");
        dto.setSort(10);
        dto.setEnabled(true);

        DictItem entity = DictAssembler.assemblerDictItem(dto);

        assertNotNull(entity);
        assertEquals("男", entity.getName());
        assertEquals("male", entity.getCode());
        assertEquals("1", entity.getValue());
        assertEquals(10, entity.getSort());
        assertTrue(entity.getEnabled());
    }

    @Test
    @DisplayName("assemblerDictItem(DTO) -> DictItem: null 输入可能 NPE")
    void assemblerDictItemFromDtoNullInputMayThrow() {
        assertThrows(NullPointerException.class,
                () -> DictAssembler.assemblerDictItem((DictItemDTO) null));
    }

    @Test
    @DisplayName("assemblerDictItem(Entity) -> DictItemVO: 正常转换，operatorTime=updateTime")
    void assemblerDictItemToVOSShouldMapOperatorTime() {
        LocalDateTime now = LocalDateTime.now();
        DictItem entity = new DictItem();
        entity.setId(1L);
        entity.setName("男");
        entity.setCode("male");
        entity.setValue("1");
        entity.setSort(1);
        entity.setEnabled(true);
        entity.setUpdateTime(now);

        DictItemVO vo = DictAssembler.assemblerDictItem(entity);

        assertEquals(1L, vo.getId());
        assertEquals("男", vo.getName());
        assertEquals("male", vo.getCode());
        assertEquals("1", vo.getValue());
        assertEquals(1, vo.getSort());
        assertTrue(vo.getEnabled());
        assertEquals(now, vo.getOperatorTime());
    }
}
