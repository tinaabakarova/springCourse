package ru.otus.homeworkApp.dao;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvException;
import org.springframework.core.io.Resource;
import ru.otus.homeworkApp.domain.Answer;
import ru.otus.homeworkApp.domain.Question;
import ru.otus.homeworkApp.service.ResourceService;
import ru.otus.homeworkApp.service.ResourceServiseCsvImpl;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class QuestionDaoImpl implements QuestionDao {
    private Resource questionsResource;
    private Resource answersResource;
    private Resource rightAnswerResource;
    private ResourceService resourceService;

    private List<Question> questions;

    public QuestionDaoImpl(Resource questionsResource, Resource answersResource, Resource rightAnswerResource,
                           ResourceService resourceService) {
        this.questionsResource = questionsResource;
        this.answersResource = answersResource;
        this.rightAnswerResource = rightAnswerResource;
        this.resourceService = resourceService;
        questions = new ArrayList<>();
    }

    @Override
    public List<Question> getAllQuestionsWithAnswers() {
        List<String[]> questionList = resourceService.getListDataFromResources(questionsResource);

        Objects.requireNonNull(questionList).forEach(row -> {
            Arrays.stream(row).forEach(question -> questions.add(new Question(question)));
        });

        List<String[]> answerList = resourceService.getListDataFromResources(answersResource);
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
        List<String[]> rightAnswerList = resourceService.getListDataFromResources(rightAnswerResource);
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
