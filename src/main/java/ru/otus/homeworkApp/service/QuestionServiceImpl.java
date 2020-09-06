package ru.otus.homeworkApp.service;

import ru.otus.homeworkApp.dao.QuestionDao;
import ru.otus.homeworkApp.domain.Question;

import java.util.List;

public class QuestionServiceImpl implements QuestionService{

    private QuestionDao dao;

    public QuestionServiceImpl(QuestionDao dao) {
        this.dao = dao;
    }

    @Override
    public List<Question> getAllQuestionsWithAnswers() {
        return dao.getAllQuestionsWithAnswers();
    }
}
