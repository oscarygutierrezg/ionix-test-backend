package cl.com.ionix.testbackend.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

/**
 * The Class WebConfig.
 */
@Configuration
public class WebConfig{

	@Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
}