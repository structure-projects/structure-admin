package cn.structured.sa.service.impl;

import cn.structured.mybatis.plus.starter.base.BaseServiceImpl;
import cn.structured.sa.entity.Organization;
import cn.structured.sa.mapper.OrganizationMapper;
import cn.structured.sa.service.IOrganizationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * 组织管理
 *
 * @author cqliut
 * @version 2023.0711
 * @since 1.0.1
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class OrganizationServiceImpl extends BaseServiceImpl<OrganizationMapper, Organization> implements IOrganizationService {

}
