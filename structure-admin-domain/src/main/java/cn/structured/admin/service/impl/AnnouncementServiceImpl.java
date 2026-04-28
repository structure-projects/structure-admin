package cn.structured.admin.service.impl;

import cn.structured.admin.entity.Announcement;
import cn.structured.admin.mapper.AnnouncementMapper;
import cn.structured.admin.service.IAnnouncementService;
import cn.structured.mybatis.plus.starter.base.BaseServiceImpl;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@AllArgsConstructor
public class AnnouncementServiceImpl extends BaseServiceImpl<AnnouncementMapper, Announcement> implements IAnnouncementService {
}
