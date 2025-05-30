package jee.spring.esgi.monitor.health;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class VelibStationsApiHealthIndicator implements HealthIndicator {

    private final RestTemplate restTemplate = new RestTemplate();

    @Value("${velib.api.url}")
    private String velibApiUrl;

    @Override
    public Health health() {
        try {
            restTemplate.getForEntity(velibApiUrl, String.class);
            return Health.up()
                    .withDetail("url", velibApiUrl)
                    .withDetail("message", "API Velib accessible")
                    .build();
        } catch (Exception e) {
            return Health.down()
                    .withDetail("url", velibApiUrl)
                    .withDetail("error", e.getMessage())
                    .build();
        }
    }
}