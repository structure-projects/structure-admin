package cn.structured.sa.service.impl;

import cn.structured.mybatis.plus.starter.base.BaseServiceImpl;
import cn.structured.sa.entity.Space;
import cn.structured.sa.mapper.SpaceMapper;
import cn.structured.sa.service.ISpaceService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * 空间管理
 *
 * @author chuck
 * @since JDK1.8
 */
@Slf4j
@Service
public class SpaceServiceImpl extends BaseServiceImpl<SpaceMapper, Space> implements ISpaceService {

    @Override
    public void enable(Long spaceId) {

    }

    @Override
    public void disable(Long spaceId) {

    }
}
