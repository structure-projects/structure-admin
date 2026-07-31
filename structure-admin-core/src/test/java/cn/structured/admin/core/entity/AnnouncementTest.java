package cn.structured.admin.core.entity;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Announcement 实体单元测试")
class AnnouncementTest {

    @Test
    @DisplayName("set/get 所有字段正确")
    void allFieldsShouldWork() {
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime expiration = LocalDateTime.now().plusDays(7);

        Announcement announcement = new Announcement();
        announcement.setId(1L);
        announcement.setSubject("系统升级通知");
        announcement.setContent("系统将于周五升级");
        announcement.setScope(2);
        announcement.setType(1);
        announcement.setTop(true);
        announcement.setExpirationTime(expiration);
        announcement.setState(1);
        announcement.setTenantId(1000L);
        announcement.setCreateTime(now);
        announcement.setUpdateTime(now);
        announcement.setCreateBy(1L);
        announcement.setUpdateBy(2L);
        announcement.setDeleted(false);

        assertEquals(1L, announcement.getId());
        assertEquals("系统升级通知", announcement.getSubject());
        assertEquals("系统将于周五升级", announcement.getContent());
        assertEquals(2, announcement.getScope());
        assertEquals(1, announcement.getType());
        assertTrue(announcement.getTop());
        assertEquals(expiration, announcement.getExpirationTime());
        assertEquals(1, announcement.getState());
        assertEquals(1000L, announcement.getTenantId());
        assertEquals(now, announcement.getCreateTime());
        assertEquals(now, announcement.getUpdateTime());
        assertEquals(1L, announcement.getCreateBy());
        assertEquals(2L, announcement.getUpdateBy());
        assertFalse(announcement.getDeleted());
    }

    @Test
    @DisplayName("scope 区分 app(1) 和管理端(2)")
    void scopeShouldDistinguishAppAndAdmin() {
        Announcement appAnnouncement = new Announcement();
        appAnnouncement.setScope(1);
        assertEquals(1, appAnnouncement.getScope());

        Announcement adminAnnouncement = new Announcement();
        adminAnnouncement.setScope(2);
        assertEquals(2, adminAnnouncement.getScope());
    }

    @Test
    @DisplayName("type 区分长期(1)和短期(2)")
    void typeShouldDistinguishLongTermAndShortTerm() {
        Announcement longTerm = new Announcement();
        longTerm.setType(1);
        assertEquals(1, longTerm.getType());

        Announcement shortTerm = new Announcement();
        shortTerm.setType(2);
        assertEquals(2, shortTerm.getType());
    }

    @Test
    @DisplayName("state 区分正常(1)和失效(2)")
    void stateShouldDistinguishActiveAndInactive() {
        Announcement active = new Announcement();
        active.setState(1);
        assertEquals(1, active.getState());

        Announcement inactive = new Announcement();
        inactive.setState(2);
        assertEquals(2, inactive.getState());
    }

    @Test
    @DisplayName("deleted 逻辑删除默认为 false")
    void deletedShouldDefaultToFalse() {
        Announcement announcement = new Announcement();
        assertNull(announcement.getDeleted());
    }

    @Test
    @DisplayName("top 置顶标识")
    void topShouldBeTrueWhenPinned() {
        Announcement announcement = new Announcement();
        announcement.setTop(true);
        assertTrue(announcement.getTop());

        announcement.setTop(false);
        assertFalse(announcement.getTop());
    }
}
