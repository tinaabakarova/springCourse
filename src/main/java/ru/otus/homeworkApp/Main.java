package ru.otus.homeworkApp;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import ru.otus.homeworkApp.domain.Person;
import ru.otus.homeworkApp.domain.Question;
import ru.otus.homeworkApp.service.PersonService;
import ru.otus.homeworkApp.service.QuestionService;
import ru.otus.utils.PropertyLoader;

import java.util.List;

@Configuration
@ComponentScan
public class Main {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context =
                new AnnotationConfigApplicationContext(Main.class);
        QuestionService questionService = context.getBean(QuestionService.class);
        PersonService personService = context.getBean(PersonService.class);
        personService.askPersonCredentials();
        personService.greeting();
        questionService.testUserAndPrintResult();
    }
}
