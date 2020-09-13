package ru.otus.homeworkApp.dao;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvException;
import org.springframework.core.io.Resource;
import ru.otus.homeworkApp.domain.Answer;
import ru.otus.homeworkApp.domain.Question;

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

    private List<Question> questions;

    public QuestionDaoImpl(Resource questionsResource, Resource answersResource) {
        this.questionsResource = questionsResource;
        this.answersResource = answersResource;
        questions = new ArrayList<>();
    }

    @Override
    public List<Question> getAllQuestionsWithAnswers() {
        List<String[]> questionList = getListDataFromResources(questionsResource);

        Objects.requireNonNull(questionList).forEach(row -> {
            Arrays.stream(row).forEach(question -> questions.add(new Question(question)));
        });

        List<String[]> answerList = getListDataFromResources(answersResource);
        for (int i = 0; i < answerList.size(); i++) {
            List<Answer> answersList = new ArrayList<>();
            for (String answer : answerList.get(i)) {
                Objects.requireNonNull(answersList).add(new Answer(answer));
            }
            questions.get(i).setAnswers(answersList);
        }
        return questions;
    }

    public List<String[]> getListDataFromResources(Resource res){
        List<String[]> allRows = null;

        try (CSVReader reader = new CSVReader(new FileReader(Objects.requireNonNull(res.getFile())));) {
            allRows = reader.readAll();
        } catch (IOException | CsvException fileNotFoundException) {
            fileNotFoundException.printStackTrace();
        }
        return allRows;
    }
}
