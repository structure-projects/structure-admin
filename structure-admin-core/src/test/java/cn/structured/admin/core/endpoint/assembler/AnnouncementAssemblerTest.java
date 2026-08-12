package cn.structured.admin.core.endpoint.assembler;

import cn.structured.admin.common.dto.AnnouncementDTO;
import cn.structured.admin.common.vo.AnnouncementVO;
import cn.structured.admin.common.vo.AppAnnouncementVO;
import cn.structured.admin.core.entity.Announcement;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("AnnouncementAssembler 单元测试")
class AnnouncementAssemblerTest {

    @Test
    @DisplayName("assembler(Announcement) -> AppAnnouncementVO: 正常转换")
    void assemblerToAppAnnouncementVOSShouldConvert() {
        LocalDateTime now = LocalDateTime.now();
        Announcement announcement = new Announcement();
        announcement.setId(1L);
        announcement.setSubject("系统升级");
        announcement.setContent("升级内容");
        announcement.setUpdateTime(now);

        AppAnnouncementVO vo = AnnouncementAssembler.assembler(announcement);

        assertNotNull(vo);
        assertEquals(1L, vo.getId());
        assertEquals("系统升级", vo.getSubject());
        assertEquals("升级内容", vo.getContent());
        assertEquals(now, vo.getUpdateTime());
    }

    @Test
    @DisplayName("assembler(Announcement) -> AppAnnouncementVO: null 输入可能 NPE")
    void assemblerToAppVONullInputMayThrow() {
        assertThrows(NullPointerException.class,
                () -> AnnouncementAssembler.assembler((Announcement) null));
    }

    @Test
    @DisplayName("assemblerAnnouncementVO: 正常转换所有字段")
    void assemblerAnnouncementVOShouldConvertAllFields() {
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime expiration = LocalDateTime.now().plusDays(7);

        Announcement announcement = new Announcement();
        announcement.setId(1L);
        announcement.setSubject("通知标题");
        announcement.setContent("通知内容");
        announcement.setScope(2);
        announcement.setType(1);
        announcement.setTop(true);
        announcement.setExpirationTime(expiration);
        announcement.setState(1);
        announcement.setUpdateTime(now);

        AnnouncementVO vo = AnnouncementAssembler.assemblerAnnouncementVO(announcement);

        assertEquals(1L, vo.getId());
        assertEquals("通知标题", vo.getSubject());
        assertEquals("通知内容", vo.getContent());
        assertEquals(2, vo.getScope());
        assertEquals(1, vo.getType());
        assertTrue(vo.getTop());
        assertEquals(expiration, vo.getExpirationTime());
        assertEquals(1, vo.getState());
        assertEquals(now, vo.getUpdateTime());
    }

    @Test
    @DisplayName("assemblerAnnouncementVO: null 输入可能 NPE")
    void assemblerAnnouncementVONullInputMayThrow() {
        assertThrows(NullPointerException.class,
                () -> AnnouncementAssembler.assemblerAnnouncementVO(null));
    }

    @Test
    @DisplayName("assembler(AnnouncementDTO) -> Announcement: 正常转换")
    void assemblerFromDtoShouldConvert() {
        LocalDateTime expiration = LocalDateTime.now().plusDays(30);

        AnnouncementDTO dto = new AnnouncementDTO();
        dto.setSubject("新公告");
        dto.setContent("公告内容");
        dto.setScope(1);
        dto.setType(2);
        dto.setTop(false);
        dto.setExpirationTime(expiration);
        dto.setState(1);

        Announcement entity = AnnouncementAssembler.assembler(dto);

        assertNotNull(entity);
        assertEquals("新公告", entity.getSubject());
        assertEquals("公告内容", entity.getContent());
        assertEquals(1, entity.getScope());
        assertEquals(2, entity.getType());
        assertFalse(entity.getTop());
        assertEquals(expiration, entity.getExpirationTime());
        assertEquals(1, entity.getState());
    }

    @Test
    @DisplayName("assembler(AnnouncementDTO) -> Announcement: null 输入可能 NPE")
    void assemblerFromDtoNullInputMayThrow() {
        assertThrows(NullPointerException.class,
                () -> AnnouncementAssembler.assembler((AnnouncementDTO) null));
    }
}
