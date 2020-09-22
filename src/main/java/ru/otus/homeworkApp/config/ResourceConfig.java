package ru.otus.homeworkApp.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import ru.otus.homeworkApp.dao.ResourceDao;
import ru.otus.homeworkApp.dao.ResourceDaoCsv;

@Configuration
public class ResourceConfig {

    @Profile("EN")
    @Bean
    public ResourceDao resourceDaoEn(ApplicationPropertiesConfig applicationProperties) {
        return new ResourceDaoCsv(applicationProperties.getQuestions(), applicationProperties.getAnswers(), applicationProperties.getRightAnswers());
    }

    @Profile("RU")
    @Bean
    public ResourceDao resourceDaoRu(ApplicationPropertiesConfig applicationProperties) {
        return new ResourceDaoCsv(applicationProperties.getQuestionsRu(), applicationProperties.getAnswersRu(), applicationProperties.getRightAnswersRu());
    }

}
