package cn.structured.admin.core.service.impl;

import cn.structured.admin.core.entity.Announcement;
import cn.structured.admin.core.mapper.AnnouncementMapper;
import cn.structured.admin.core.service.IAnnouncementService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@AllArgsConstructor
public class AnnouncementServiceImpl extends ServiceImpl<AnnouncementMapper, Announcement> implements IAnnouncementService {
}
