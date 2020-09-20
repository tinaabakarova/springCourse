package ru.otus.homeworkApp.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.io.Resource;
import ru.otus.homeworkApp.dao.ResourceDao;
import ru.otus.homeworkApp.dao.ResourceDaoCsv;

@PropertySource("application.properties")
@Configuration
public class ResourceConfig {
    @Value("${questions}")
    private Resource questionsResource;
    @Value("${answers}")
    private Resource answersResource;
    @Value("${rightAnswers}")
    private Resource rightAnswerResource;

    @Bean
    public ResourceDao resourceDao() {
        return new ResourceDaoCsv(questionsResource, answersResource, rightAnswerResource);
    }

}
