package ru.otus.homeworkApp.service;

import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.stereotype.Service;
import ru.otus.homeworkApp.config.ApplicationPropertiesConfig;
import ru.otus.homeworkApp.dao.QuestionDao;
import ru.otus.homeworkApp.domain.Question;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

@ShellComponent
public class QuestionServiceImpl implements QuestionService{

    private final QuestionDao dao;
    private final ApplicationPropertiesConfig applicationProperties;
    private IoService ioService;

    public QuestionServiceImpl(QuestionDao dao, ApplicationPropertiesConfig applicationProperties,
                               IoService ioService) {
        this.dao = dao;
        this.applicationProperties = applicationProperties;
        this.ioService = ioService;
    }

    @Override
    public List<Question> getAllQuestionsWithAnswers() {
        return dao.getAllQuestionsWithAnswers();
    }

    @ShellMethod("Start test")
    @Override
    public void testUserAndPrintResult(){
        ioService.out(applicationProperties.getMessageSource().getMessage("startMessage",
                new String[]{}, applicationProperties.getLocale()));
        ioService.out(applicationProperties.getMessageSource().getMessage("greetingPart1",
                new String[]{}, applicationProperties.getLocale()));
        String credentials = ioService.readString();
        ioService.out(applicationProperties.getMessageSource().getMessage("greetingPart2",
                new String[]{credentials}, applicationProperties.getLocale()));
        List<Boolean> results = new ArrayList<>();
        dao.getAllQuestionsWithAnswers().forEach(question -> {
            ioService.out(question.toString());
            String answer = ioService.readString();
            question.getAnswers().forEach(answer1 -> {
                if (answer1.getAnswer().equals(answer)){
                    results.add(answer1.isRight());
                }
            });
        });

        if (isOffset(results)) {
            ioService.out(applicationProperties.getMessageSource().getMessage("pass",
                    new String[]{}, applicationProperties.getLocale()));
        } else {
            ioService.out(applicationProperties.getMessageSource().getMessage("fail",
                    new String[]{}, applicationProperties.getLocale()));
        }

    }

    private boolean isOffset(List<Boolean> results){
       long count = results.stream().filter(res -> res.equals(true)).count();
       return count >= applicationProperties.getAnswersCountToPass();
    }

}
