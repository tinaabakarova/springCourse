package service;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.shell.jline.InteractiveShellApplicationRunner;
import org.springframework.shell.jline.ScriptShellApplicationRunner;
import ru.otus.homeworkApp.config.ApplicationPropertiesConfig;
import ru.otus.homeworkApp.dao.QuestionDao;
import ru.otus.homeworkApp.dao.QuestionDaoImpl;
import ru.otus.homeworkApp.dao.ResourceDao;
import ru.otus.homeworkApp.dao.ResourceDaoCsv;
import ru.otus.homeworkApp.service.*;
import utils.PropertyLoader;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.List;
import java.util.Locale;

import static org.mockito.Mockito.*;

@SpringBootTest(properties = {
        InteractiveShellApplicationRunner.SPRING_SHELL_INTERACTIVE_ENABLED + "=false",
        ScriptShellApplicationRunner.SPRING_SHELL_SCRIPT_ENABLED + "=false"
})
public class QuestionServiceTest {
    private final Resource questions = new FileSystemResource(PropertyLoader.loadProperty("questionsTestFolder"));
    private final Resource answers = new FileSystemResource(PropertyLoader.loadProperty("answersTestFolder"));
    private final Resource rightAnswers = new FileSystemResource(PropertyLoader.loadProperty("rightAnswersTestFolder"));

    @Test
    public void testUserAndPrintResultPositive() throws IOException {
        String questionsAndAnswers = "Let's do some tests!\n" +
                "Dear user, please enter your first and last name:\n" +
                "Hi Tina Abakarova!\n" +
                "test1\n" +
                "[1, 2]\n" +
                "test2\n" +
                "[2, 4]\n" +
                "You pass!\n";
        String testAnswers = "Tina Abakarova\n1\n2";
        ResourceDao resourceDao = new ResourceDaoCsv(questions,answers,rightAnswers);
        QuestionDao questionDao = new QuestionDaoImpl(resourceDao);
        ByteArrayOutputStream bos = new ByteArrayOutputStream();

        ApplicationPropertiesConfig applicationPropertiesConfig = mock(ApplicationPropertiesConfig.class);
        var messageSource = new ReloadableResourceBundleMessageSource();
        messageSource.setBasename("classpath:/i18n/bundle");
        messageSource.setDefaultEncoding("UTF-8");
        when(applicationPropertiesConfig.getMessageSource()).thenReturn(messageSource);
        when(applicationPropertiesConfig.getLocale()).thenReturn(new Locale("EN"));
        when(applicationPropertiesConfig.getAnswersCountToPass()).thenReturn(2);
        IoService ioService = new ConsoleIoService(new ByteArrayInputStream(testAnswers.getBytes()), new PrintStream(bos));

        QuestionService questionService = new QuestionServiceImpl(questionDao, applicationPropertiesConfig, ioService);
        questionService.testUserAndPrintResult();
        Assert.assertArrayEquals(questionsAndAnswers.getBytes(), bos.toByteArray());
    }

    @Test
    public void testUserAndPrintResultNegative() throws IOException {
        String questionsAndAnswers = "Let's do some tests!\n" +
                "Dear user, please enter your first and last name:\n" +
                "Hi Tina Abakarova!\n" +
                "test1\n" +
                "[1, 2]\n" +
                "test2\n" +
                "[2, 4]\n" +
                "You fail!\n";
        String testAnswers = "Tina Abakarova\n1\n1";
        ResourceDao resourceDao = new ResourceDaoCsv(questions,answers,rightAnswers);
        QuestionDao questionDao = new QuestionDaoImpl(resourceDao);
        ByteArrayOutputStream bos = new ByteArrayOutputStream();

        ApplicationPropertiesConfig applicationPropertiesConfig = mock(ApplicationPropertiesConfig.class);
        var messageSource = new ReloadableResourceBundleMessageSource();
        messageSource.setBasename("classpath:/i18n/bundle");
        messageSource.setDefaultEncoding("UTF-8");
        when(applicationPropertiesConfig.getMessageSource()).thenReturn(messageSource);
        when(applicationPropertiesConfig.getLocale()).thenReturn(new Locale("EN"));
        when(applicationPropertiesConfig.getAnswersCountToPass()).thenReturn(2);
        IoService ioService = new ConsoleIoService(new ByteArrayInputStream(testAnswers.getBytes()), new PrintStream(bos));

        QuestionService questionService = new QuestionServiceImpl(questionDao, applicationPropertiesConfig, ioService);
        questionService.testUserAndPrintResult();
        Assert.assertArrayEquals(questionsAndAnswers.getBytes(), bos.toByteArray());
    }

}
