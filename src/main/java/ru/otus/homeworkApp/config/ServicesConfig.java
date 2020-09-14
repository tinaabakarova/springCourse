package ru.otus.homeworkApp.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.otus.homeworkApp.dao.QuestionDao;
import ru.otus.homeworkApp.dao.QuestionDaoImpl;
import ru.otus.homeworkApp.domain.Person;
import ru.otus.homeworkApp.service.*;

@Configuration
public class ServicesConfig {

    @Bean
    public QuestionService questionService(QuestionDao dao) {
        return new QuestionServiceImpl(dao);
    }

    @Bean
    public PersonService personService(Person person) {
        return new PersonServiceImpl(person);
    }

    @Bean
    public ResourceService resourceService() {
        return new ResourceServiseCsvImpl();
    }
}
