package service;

import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.shell.jline.InteractiveShellApplicationRunner;
import org.springframework.shell.jline.ScriptShellApplicationRunner;
import ru.otus.homeworkApp.dao.ResourceDao;
import ru.otus.homeworkApp.dao.ResourceDaoCsv;
import utils.PropertyLoader;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(properties = {
        InteractiveShellApplicationRunner.SPRING_SHELL_INTERACTIVE_ENABLED + "=false",
        ScriptShellApplicationRunner.SPRING_SHELL_SCRIPT_ENABLED + "=false"
})
public class ResourceDaoTest {
    Resource questions;
    Resource answers;
    Resource rightAnswers;
    ResourceDao resourceDao;

    @Test
    public void checkListDataFromResources(){
        questions = new FileSystemResource(PropertyLoader.loadProperty("questionsTestFolder"));
        answers = new FileSystemResource(PropertyLoader.loadProperty("answersTestFolder"));
        rightAnswers = new FileSystemResource(PropertyLoader.loadProperty("rightAnswersTestFolder"));
        resourceDao = new ResourceDaoCsv(questions,answers,rightAnswers);

        List<String[]> resourceData = resourceDao.getQuestionList();
        assertThat(resourceData.contains("test1"));
        assertThat(resourceData.contains("test2"));

        resourceData = resourceDao.getAnswersList();
        assertThat(resourceData.contains("1"));
        assertThat(resourceData.contains("4"));

        resourceData = resourceDao.getRightAnswersList();
        assertThat(resourceData.contains("1"));
        assertThat(resourceData.contains("2"));
    }
}
