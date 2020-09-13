package ru.otus.homeworkApp.service;

import ru.otus.homeworkApp.domain.Question;

import java.util.List;

public interface QuestionService {
    public List<Question> getAllQuestionsWithAnswers();
}
