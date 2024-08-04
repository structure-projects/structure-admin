package cn.structured.admin.configuration;

import cn.structured.admin.api.utils.SystemUtil;
import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.core.metadata.TableFieldInfo;
import com.baomidou.mybatisplus.core.metadata.TableInfo;
import com.baomidou.mybatisplus.core.metadata.TableInfoHelper;
import com.baomidou.mybatisplus.extension.plugins.MybatisPlusInterceptor;
import com.baomidou.mybatisplus.extension.plugins.handler.TenantLineHandler;
import com.baomidou.mybatisplus.extension.plugins.inner.PaginationInnerInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.TenantLineInnerInterceptor;
import net.sf.jsqlparser.expression.Expression;
import net.sf.jsqlparser.expression.LongValue;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;

/**
 * @author chuck
 */
@MapperScan(basePackages = "cn.structured.admin.mapper.**")
public class AutoMybatisConfiguration {

    private final static String TENANT_ID = "organization_id";


    @Bean
    public MybatisPlusInterceptor mybatisPlusInterceptor() {

        MybatisPlusInterceptor interceptor = new MybatisPlusInterceptor();
        interceptor.addInnerInterceptor(new PaginationInnerInterceptor(DbType.MYSQL));
        interceptor.addInnerInterceptor(new TenantLineInnerInterceptor(new TenantLineHandler() {
            @Override
            public Expression getTenantId() {
                // 默认租户为1
                Long organizationId = SystemUtil.getOrganizationId();
                if (null == organizationId) {
                    return new LongValue("1");
                }
                return new LongValue(organizationId);
            }

            @Override
            public String getTenantIdColumn() {
                return TENANT_ID;
            }

            // 这是 default 方法,默认返回 false 表示所有表都需要拼多租户条件
            @Override
            public boolean ignoreTable(String tableName) {
                TableInfo tableInfo = TableInfoHelper.getTableInfos().
                        stream()
                        .filter(table -> table.getTableName().equals(tableName))
                        .findFirst().orElse(null);
                if (null == tableInfo) {
                    return true;
                }
                TableFieldInfo tableField = tableInfo.getFieldList().stream().filter(tableFieldInfo ->
                        tableFieldInfo.getColumn().equals(TENANT_ID)
                ).findFirst().orElse(null);
                return (null == tableField);
            }
        }));
        return interceptor;
    }

}
