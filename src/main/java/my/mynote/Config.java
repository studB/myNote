package my.mynote;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import my.mynote.repository.JdbcTemplateTextRepository;
import my.mynote.repository.JdbcTemplateUserRepository;
import my.mynote.repository.TextRepository;
import my.mynote.repository.UserRepository;
import my.mynote.service.AuthService;
import my.mynote.service.TextService;

@Configuration
public class Config {

    private final DataSource dataSource;

    @Autowired
    public Config(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Bean
    public AuthService authService() {
        return new AuthService(userRepository());
    }

    @Bean
    public UserRepository userRepository() {
        return new JdbcTemplateUserRepository(dataSource);
    }

    @Bean
    public TextService textService() {
        return new TextService(textRepository());
    }

    @Bean
    public TextRepository textRepository() {
        return new JdbcTemplateTextRepository(dataSource);
    }
}
