package cn.structured.admin.endpoint.assembler;

import cn.structured.admin.api.dto.AnnouncementDTO;
import cn.structured.admin.api.vo.AnnouncementVO;
import cn.structured.admin.api.vo.AppAnnouncementVO;
import cn.structured.admin.entity.Announcement;

public class AnnouncementAssembler {

    private AnnouncementAssembler() {
    }


    /**
     * assembler
     *
     * @param announcement announcement
     * @return AppAnnouncementVO
     */
    public static AppAnnouncementVO assembler(Announcement announcement) {
        AppAnnouncementVO appAnnouncementVO = new AppAnnouncementVO();
        appAnnouncementVO.setId(announcement.getId());
        appAnnouncementVO.setSubject(announcement.getSubject());
        appAnnouncementVO.setContent(announcement.getContent());
        appAnnouncementVO.setUpdateTime(announcement.getUpdateTime());
        return appAnnouncementVO;
    }


    /**
     * assembler
     *
     * @param announcement announcement
     * @return AnnouncementVO
     */
    public static AnnouncementVO assemblerAnnouncementVO(Announcement announcement) {
        AnnouncementVO announcementVO = new AnnouncementVO();
        announcementVO.setId(announcement.getId());
        announcementVO.setSubject(announcement.getSubject());
        announcementVO.setContent(announcement.getContent());
        announcementVO.setScope(announcement.getScope());
        announcementVO.setType(announcement.getType());
        announcementVO.setTop(announcement.getTop());
        announcementVO.setExpirationTime(announcement.getExpirationTime());
        announcementVO.setState(announcement.getState());
        announcementVO.setUpdateTime(announcement.getUpdateTime());
        return announcementVO;
    }

    /**
     * assembler
     *
     * @param announcementDTO announcementDTO
     * @return Announcement
     */
    public static Announcement assembler(AnnouncementDTO announcementDTO) {
        Announcement announcement = new Announcement();
        announcement.setSubject(announcementDTO.getSubject());
        announcement.setContent(announcementDTO.getContent());
        announcement.setScope(announcementDTO.getScope());
        announcement.setType(announcementDTO.getType());
        announcement.setTop(announcementDTO.getTop());
        announcement.setExpirationTime(announcementDTO.getExpirationTime());
        announcement.setState(announcementDTO.getState());
        return announcement;
    }

}
