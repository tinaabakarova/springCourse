package ru.otus.homeworkApp.service;

import org.springframework.stereotype.Service;
import org.w3c.dom.ls.LSOutput;
import ru.otus.homeworkApp.dao.QuestionDao;
import ru.otus.homeworkApp.domain.Answer;
import ru.otus.homeworkApp.domain.Question;
import ru.otus.utils.PropertyLoader;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

@Service
public class QuestionServiceImpl implements QuestionService{

    private final QuestionDao dao;
    private final PersonService personService;

    public QuestionServiceImpl(QuestionDao dao, PersonService personService) {
        this.dao = dao;
        this.personService = personService;
    }

    @Override
    public List<Question> getAllQuestionsWithAnswers() {
        return dao.getAllQuestionsWithAnswers();
    }

    @Override
    public void testUserAndPrintResult(){
        System.out.println("Let's do some tests!");
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
            System.out.println("You pass!");
        } else {
            System.out.println("You fail!");
        }

    }

    private boolean isOffset(List<Boolean> results){
       long count = results.stream().filter(res -> res.equals(true)).count();
       return count >= Integer.parseInt(PropertyLoader.loadProperty("answersCountToPass"));
    }

}
