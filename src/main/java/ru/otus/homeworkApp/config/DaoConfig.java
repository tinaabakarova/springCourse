package ru.otus.homeworkApp.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.io.Resource;
import ru.otus.homeworkApp.dao.QuestionDao;
import ru.otus.homeworkApp.dao.QuestionDaoImpl;
import ru.otus.homeworkApp.service.ResourceService;

@PropertySource("application.properties")
@Configuration
public class DaoConfig {
    @Value("${questions}")
    Resource questions;
    @Value("${answers}")
    Resource answers;
    @Value("${rightAnswers}")
    Resource rightAnswers;

    @Bean
    public QuestionDao questionDao(ResourceService resourceService) {
        return new QuestionDaoImpl(questions, answers, rightAnswers, resourceService);
    }
}
