package ru.otus.homeworkApp.dao;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import ru.otus.homeworkApp.domain.Answer;
import ru.otus.homeworkApp.domain.Question;

import java.util.*;


@Service
public class QuestionDaoImpl implements QuestionDao {
    private ResourceDao resourceDao;
    private List<Question> questions;

    public QuestionDaoImpl(ResourceDao resourceDao) {
        this.resourceDao = resourceDao;
        questions = new ArrayList<>();
    }

    @Override
    public List<Question> getAllQuestionsWithAnswers() {
        List<String[]> questionList = resourceDao.getQuestionList();

        Objects.requireNonNull(questionList).forEach(row -> {
            Arrays.stream(row).forEach(question -> questions.add(new Question(question)));
        });

        List<String[]> answerList = resourceDao.getAnswersList();
        for (int i = 0; i < answerList.size(); i++) {
            List<Answer> answersList = new ArrayList<>();
            for (String answer : answerList.get(i)) {
                Objects.requireNonNull(answersList).add(new Answer(answer));
            }
            questions.get(i).setAnswers(answersList);
        }
        markRightAnswers();
        return questions;
    }

    private void markRightAnswers(){
        List<String[]> rightAnswerList = resourceDao.getRightAnswersList();
        for (int i = 0; i < rightAnswerList.size(); i++) {
            List<Answer> answers =  questions.get(i).getAnswers();
            for (Answer answer:answers) {
                if(answer.getAnswer().equals(rightAnswerList.get(i)[0])){
                    answer.setRight(true);
                }
            }
        }
    }
}
