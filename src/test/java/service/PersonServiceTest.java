package service;

import org.assertj.core.api.Assert;
import org.junit.Test;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import ru.otus.homeworkApp.service.ResourceService;
import ru.otus.homeworkApp.service.ResourceServiseCsvImpl;
import utils.PropertyLoader;

import java.io.IOException;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class PersonServiceTest {
    Resource questions;
    ResourceService resourceService;

    @Test
    public void checkListDataFromResources(){
        questions = new FileSystemResource(PropertyLoader.loadProperty("questionsTestFolder"));
        resourceService = new ResourceServiseCsvImpl();

        List<String[]> resourceData = resourceService.getListDataFromResources(questions);
        assertThat(resourceData.contains("test1"));
        assertThat(resourceData.contains("test2"));
    }
}
