package cn.structured.sa.manager.impl;

import cn.hutool.core.util.ObjectUtil;
import cn.structure.common.exception.CommonException;
import cn.structured.sa.entity.Organization;
import cn.structured.sa.manager.IOrganizationManager;
import cn.structured.sa.mapper.OrganizationMapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * 组织Manager
 *
 * @author cqliut
 * @version 2023.0711
 * @since 1.0.1
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class OrganizationManagerImpl implements IOrganizationManager {

    private final OrganizationMapper organizationMapper;

    @Override
    public Organization getOrganizationByOrganizationId(Long organizationId) {
        //查询机构信息
        Organization organization = organizationMapper.selectOne(Wrappers.<Organization>lambdaQuery()
                .eq(ObjectUtil.isNotNull(organizationId),Organization::getOrganizationId, organizationId));
        //如果不存在组织信息则抛出异常信息
        if (null == organization) {
            //todo 不存在机构信息抛出业务异常！
            throw new CommonException();
        }
        return organization;
    }

    @Override
    public Organization getOrganizationByHost(String host) {
        //查询机构信息
        Organization organization = organizationMapper.selectOne(Wrappers.<Organization>lambdaQuery()
                .eq(ObjectUtil.isNotEmpty(host),Organization::getDomain, host));
        //如果不存在组织信息则抛出异常信息
        if (null == organization) {
            //todo 不存在机构信息抛出业务异常！
            throw new CommonException();
        }
        return organization;
    }
}
