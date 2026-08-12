package cn.structured.admin;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.ActiveProfiles;

/**
 * 应用上下文加载测试
 *
 * @author structure
 */
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE, classes = ApplicationContextLoadTest.SimpleTestApplication.class)
@ActiveProfiles("test")
class ApplicationContextLoadTest {

    @Test
    void contextLoads() {
    }

    @Configuration
    static class SimpleTestApplication {
    }
}
