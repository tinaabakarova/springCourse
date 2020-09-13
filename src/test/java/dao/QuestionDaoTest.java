package dao;

import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import ru.otus.homeworkApp.dao.QuestionDao;
import ru.otus.homeworkApp.dao.QuestionDaoImpl;
import ru.otus.homeworkApp.domain.Answer;
import ru.otus.homeworkApp.domain.Question;
import utils.PropertyLoader;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class QuestionDaoTest {
    QuestionDao questionDao;
    Resource questions;
    Resource answers;

    @BeforeEach
    void setUp() {
    }

    @Test
    public void getQuestionWithAnswer() throws IOException {
        // тестовые данные
        List<Question> expected = new ArrayList<>();
        List<Answer> answersList = new ArrayList<>();
        answersList.add(new Answer("1"));
        answersList.add(new Answer("2"));
        Question question = new Question("test1");
        question.setAnswers(answersList);
        expected.add(question);
        answersList = new ArrayList<>();
        answersList.add(new Answer("2"));
        answersList.add(new Answer("4"));
        question = new Question("test2");
        question.setAnswers(answersList);
        expected.add(question);
        questions = new FileSystemResource(PropertyLoader.loadProperty("questionsTestFolder"));
        answers = new FileSystemResource(PropertyLoader.loadProperty("answersTestFolder"));

        //тест
        questionDao = new QuestionDaoImpl(questions, answers);
        List<Question> result = questionDao.getAllQuestionsWithAnswers();

        //проверка
        assertEquals(result,expected);
    }
}
