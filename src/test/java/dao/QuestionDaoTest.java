package dao;

import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.Mock;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import ru.otus.homeworkApp.dao.QuestionDao;
import ru.otus.homeworkApp.dao.QuestionDaoImpl;
import ru.otus.homeworkApp.domain.Answer;
import ru.otus.homeworkApp.domain.Question;
import ru.otus.homeworkApp.service.ResourceService;
import ru.otus.homeworkApp.service.ResourceServiseCsvImpl;
import utils.PropertyLoader;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class QuestionDaoTest {
    QuestionDao questionDao;
    Resource questions;
    Resource answers;
    Resource rightAnswers;
    List<Question> expected;
    List<Answer> answersList;
    Question question;
    ResourceService resourceService;

    @BeforeEach
    public void beforeClass() {
    }

    @Test
    public void getQuestionWithAnswer() throws IOException {
        //test data
        expected = new ArrayList<>();
        answersList = new ArrayList<>();
        answersList.add(new Answer("1"));
        answersList.add(new Answer("2"));
        answersList.get(0).setRight(true);
        answersList.get(1).setRight(false);
        question = new Question("test1");
        question.setAnswers(answersList);
        expected.add(question);
        answersList = new ArrayList<>();
        answersList.add(new Answer("2"));
        answersList.add(new Answer("4"));
        answersList.get(0).setRight(true);
        answersList.get(1).setRight(false);
        question = new Question("test2");
        question.setAnswers(answersList);
        expected.add(question);
        questions = new FileSystemResource(PropertyLoader.loadProperty("questionsTestFolder"));
        answers = new FileSystemResource(PropertyLoader.loadProperty("answersTestFolder"));
        rightAnswers = new FileSystemResource(PropertyLoader.loadProperty("rightAnswersTestFolder"));

        //test
        resourceService = new ResourceServiseCsvImpl();
        questionDao = new QuestionDaoImpl(questions, answers, rightAnswers, resourceService);
        List<Question> result = questionDao.getAllQuestionsWithAnswers();

        //assertion
        assertEquals(result,expected);
    }


}
