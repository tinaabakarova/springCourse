package ru.otus.homeworkApp;

import org.springframework.context.support.ClassPathXmlApplicationContext;
import ru.otus.homeworkApp.domain.Question;
import ru.otus.homeworkApp.service.QuestionService;
import ru.otus.utils.PropertyLoader;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        ClassPathXmlApplicationContext context =
                new ClassPathXmlApplicationContext(PropertyLoader.loadProperty("xmlContext"));
        QuestionService questionService = context.getBean(QuestionService.class);
        List<Question> questionList = questionService.getAllQuestionsWithAnswers();
        questionList.forEach(System.out::println);
    }
}
