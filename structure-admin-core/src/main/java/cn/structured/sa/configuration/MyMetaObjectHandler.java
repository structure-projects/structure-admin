package cn.structured.sa.configuration;

import cn.structure.starter.oauth.common.util.UserUtil;
import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

/**
 * @author cqliut
 * @version 2022.0726
 * @since 1.0.1
 */
@Slf4j
@Component
@RequiredArgsConstructor
public class MyMetaObjectHandler implements MetaObjectHandler {

    private final TenantProperty tenantProperty;

    @Override
    public void insertFill(MetaObject metaObject) {
        this.setFieldValByName("delFlag", Boolean.FALSE, metaObject);
        this.setFieldValByName("enabled", Boolean.TRUE, metaObject);
        this.setFieldValByName("deleted", Boolean.FALSE, metaObject);
        this.setFieldValByName("createDate", LocalDateTime.now(), metaObject);
        this.setFieldValByName("createTime", LocalDateTime.now(), metaObject);
        this.setFieldValByName("createBy", getUserId(), metaObject);
        this.setFieldValByName("updateDate", LocalDateTime.now(), metaObject);
        this.setFieldValByName("updateTime", LocalDateTime.now(), metaObject);
        this.setFieldValByName("updateBy", getUserId(), metaObject);
        this.setFieldValByName("organizationId", getOrganizationId(), metaObject);
    }

    @Override
    public void updateFill(MetaObject metaObject) {
        this.setFieldValByName("updateDate", LocalDateTime.now(), metaObject);
        this.setFieldValByName("updateTime", LocalDateTime.now(), metaObject);
        this.setFieldValByName("updateBy", getUserId(), metaObject);
    }

    /**
     * 获取用户ID
     *
     * @return 用户ID
     */
    private Object getUserId() {
        try {
            return UserUtil.getUserId();
        } catch (Exception e) {
            log.debug("get user id is error -> message = {}", e.getMessage());
        }
        return null;
    }

    /**
     * 获取组织ID
     *
     * @return 组织ID
     */
    private Object getOrganizationId() {
        try {
            return UserUtil.getOrganizationId();
        } catch (Exception e) {
            log.debug("get organization id is error -> message = {}", e.getMessage());
        }
        return Long.parseLong(tenantProperty.getDefaultTenantId());
    }
}
