package my.mynote;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import my.mynote.service.AuthService;

@Configuration
public class Config {

    @Bean
    public AuthService authService() {
        return new AuthService();
    }
}
