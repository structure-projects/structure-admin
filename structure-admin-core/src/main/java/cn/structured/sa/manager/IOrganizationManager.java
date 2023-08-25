package cn.structured.sa.manager;

import cn.structured.sa.entity.Organization;

/**
 * 组织manager 机构可在全局上使用，所以这个位置抽取了一个抽象manager层可在所有业务上调用
 *
 * @author cqliut
 * @version 2023.0711
 * @since 1.0.1
 */
public interface IOrganizationManager {

    /**
     * 通过ID获取机构信息
     *
     * @param organizationId 机构ID
     * @return 机构信息
     */
    Organization getOrganizationByOrganizationId(Long organizationId);


    /**
     * 通过host获取机构信息
     * @param host 域名
     * @return
     */
    Organization getOrganizationByHost(String host);

}
