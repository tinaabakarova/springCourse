package ru.otus.homeworkApp.dao;

import org.springframework.core.io.Resource;

import java.util.List;
import java.util.Map;

public interface ResourceDao {
    public  List<String[]> getQuestionList();

    public  List<String[]> getAnswersList();

    public  List<String[]> getRightAnswersList();
}
