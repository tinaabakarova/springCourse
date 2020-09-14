package ru.otus.homeworkApp.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.otus.homeworkApp.domain.Person;

@Configuration
public class DomainConfig {

    @Bean
    public Person person() {
        return new Person();
    }
}
