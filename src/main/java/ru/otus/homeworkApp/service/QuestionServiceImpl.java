package ru.otus.homeworkApp.service;

import org.springframework.stereotype.Service;
import ru.otus.homeworkApp.config.ApplicationPropertiesConfig;
import ru.otus.homeworkApp.dao.QuestionDao;
import ru.otus.homeworkApp.domain.Question;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

@Service
public class QuestionServiceImpl implements QuestionService{

    private final QuestionDao dao;
    private final PersonService personService;
    private final ApplicationPropertiesConfig applicationProperties;

    public QuestionServiceImpl(QuestionDao dao, PersonService personService, ApplicationPropertiesConfig applicationProperties) {
        this.dao = dao;
        this.personService = personService;
        this.applicationProperties = applicationProperties;
    }

    @Override
    public List<Question> getAllQuestionsWithAnswers() {
        return dao.getAllQuestionsWithAnswers();
    }

    @Override
    public void testUserAndPrintResult(){
        System.out.println(applicationProperties.getMessageSource().getMessage("startMessage",
                new String[]{}, applicationProperties.getLocale()));
        personService.askPersonCredentials();
        personService.greeting();
        Scanner scanner = new Scanner(System.in);
        List<Boolean> results = new ArrayList<>();
        dao.getAllQuestionsWithAnswers().forEach(question -> {
            System.out.println(question);
            String answer = scanner.nextLine();
            question.getAnswers().forEach(answer1 -> {
                if (answer1.getAnswer().equals(answer)){
                    results.add(answer1.isRight());
                }
            });
        });

        if (isOffset(results)) {
            System.out.println(applicationProperties.getMessageSource().getMessage("pass",
                    new String[]{}, applicationProperties.getLocale()));
        } else {
            System.out.println(applicationProperties.getMessageSource().getMessage("fail",
                    new String[]{}, applicationProperties.getLocale()));
        }

    }

    private boolean isOffset(List<Boolean> results){
       long count = results.stream().filter(res -> res.equals(true)).count();
       return count >= applicationProperties.getAnswersCountToPass();
    }

}
