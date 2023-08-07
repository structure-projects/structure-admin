package cn.structured.sa.controller.assembler;

import cn.structure.starter.oauth.common.util.UserUtil;
import cn.structured.sa.client.dto.organization.ChangeCurrentOrganizationDTO;
import cn.structured.sa.client.vo.OrganizationVO;
import cn.structured.sa.entity.Organization;
import com.alibaba.fastjson.JSON;

/**
 * 机构装配器
 *
 * @author cqliut
 * @version 2023.0713
 * @since 1.0.1
 */
public class OrganizationAssembler {

    private OrganizationAssembler() {
    }

    /**
     * 将当前机构信息构建成组织信息实体
     *
     * @param currentOrganization 当前机构信息
     * @return 机构信息实体
     */
    public static Organization assembler(ChangeCurrentOrganizationDTO currentOrganization) {
        Organization organization = new Organization();
        organization.setName(currentOrganization.getName());
        organization.setAddress(currentOrganization.getAddress());
        organization.setDomain(currentOrganization.getDomain());
        organization.setIntroduce(currentOrganization.getIntroduce());
        organization.setAdminName(currentOrganization.getAdminName());
        organization.setAdminPhone(currentOrganization.getAdminPhone());
        organization.setOrganizationId(UserUtil.getOrganizationId());
        return organization;
    }

    /**
     * 将机构信息构建成机构VO
     *
     * @param organization 机构实体
     * @return 机构VO
     */
    public static OrganizationVO assembler(Organization organization) {
        OrganizationVO organizationVO = new OrganizationVO();
        organizationVO.setId(organization.getId());
        organizationVO.setName(organization.getName());
        organizationVO.setAddress(organization.getAddress());
        organizationVO.setDomain(organization.getDomain());
        organizationVO.setIntroduce(organization.getIntroduce());
        organizationVO.setAdminName(organization.getAdminName());
        organizationVO.setAdminPhone(organization.getAdminPhone());
        organizationVO.setOrganizationId(organization.getOrganizationId());
        return organizationVO;
    }

}
