package ru.otus.homeworkApp.dao;

import org.springframework.core.io.Resource;
import ru.otus.homeworkApp.domain.Question;

import java.util.List;

public interface QuestionDao {

    public List<Question> getAllQuestionsWithAnswers();
}
