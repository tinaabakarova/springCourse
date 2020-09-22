package dao;

import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import ru.otus.homeworkApp.dao.QuestionDao;
import ru.otus.homeworkApp.dao.QuestionDaoImpl;
import ru.otus.homeworkApp.domain.Answer;
import ru.otus.homeworkApp.domain.Question;
import ru.otus.homeworkApp.dao.ResourceDao;
import ru.otus.homeworkApp.dao.ResourceDaoCsv;
import utils.PropertyLoader;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
public class QuestionDaoTest {
    QuestionDao questionDao;
    Question question;
    ResourceDao resourceDao;

    @BeforeEach
    public void beforeClass() {
    }

    @Test
    public void getQuestionWithAnswer() throws IOException {
        List<String[]> questionList = new ArrayList<>();
        List<String[]> answerList = new ArrayList<>();
        List<String[]> rightAnswerList = new ArrayList<>();
        String[] testQuestions = new String[]{"test1", "test2"};
        String[] testAnswers1 = new String[]{"2","4"};
        String[] testAnswers2 = new String[]{"3","5"};
        String[] rightAnswers1 = new String[]{"2"};
        String[] rightAnswers2 = new String[]{"5"};
        questionList.add(testQuestions);
        answerList.add(testAnswers1);
        answerList.add(testAnswers2);
        rightAnswerList.add(rightAnswers1);
        rightAnswerList.add(rightAnswers2);

        resourceDao = mock(ResourceDao.class);

        when(resourceDao.getQuestionList()).thenReturn(questionList);
        when(resourceDao.getAnswersList()).thenReturn(answerList);
        when(resourceDao.getRightAnswersList()).thenReturn(rightAnswerList);

        questionDao = new QuestionDaoImpl(resourceDao);
        List<Question> result = questionDao.getAllQuestionsWithAnswers();

        assertThat(result.get(0).getQuestion().equals("test1"));
        assertThat(result.get(1).getQuestion().equals("test2"));
        assertThat(result.get(0).getAnswers().get(0).getAnswer().equals("2"));
        assertThat(result.get(0).getAnswers().get(1).getAnswer().equals("4"));
        assertThat(result.get(1).getAnswers().get(0).getAnswer().equals("3"));
        assertThat(result.get(1).getAnswers().get(1).getAnswer().equals("5"));
        assertTrue(result.get(0).getAnswers().get(0).isRight());
        assertTrue(result.get(1).getAnswers().get(1).isRight());
        assertFalse(result.get(0).getAnswers().get(1).isRight());
        assertFalse(result.get(1).getAnswers().get(0).isRight());
    }


}
