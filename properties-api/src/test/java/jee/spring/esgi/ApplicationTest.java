package jee.spring.esgi;

import io.github.cdimascio.dotenv.Dotenv;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;

@SpringBootTest(classes = EsgiApplication.class)
class ApplicationTest {

    private static final Dotenv dotenv = Dotenv.load();

    @DynamicPropertySource
    static void registerPgProperties(DynamicPropertyRegistry registry) {
        String url = String.format(
                "jdbc:postgresql://%s:%s/%s",
                dotenv.get("DB_HOST"),
                dotenv.get("DB_PORT"),
                dotenv.get("DB_NAME")
        );

        registry.add("spring.datasource.url",           () -> url);
        registry.add("spring.datasource.username",      () -> dotenv.get("DB_USERNAME"));
        registry.add("spring.datasource.password",      () -> dotenv.get("DB_PASSWORD"));
        registry.add("spring.datasource.driver-class-name",
                () -> "org.postgresql.Driver");
    }

    @Test
    void contextLoads() {
    }
}