package ru.otus.homeworkApp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.ConfigurableApplicationContext;
import ru.otus.homeworkApp.config.ApplicationPropertiesConfig;
import ru.otus.homeworkApp.service.QuestionService;

@SpringBootApplication
@EnableConfigurationProperties(ApplicationPropertiesConfig.class)
public class Main {
    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(Main.class, args);
        QuestionService questionService = context.getBean(QuestionService.class);
        questionService.testUserAndPrintResult();
    }
}
