package ru.otus.homeworkApp.dao;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvException;
import org.springframework.core.io.Resource;

import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import java.util.Objects;

public class ResourceDaoCsv implements ResourceDao {
    private final Resource questionsResource;
    private final Resource answersResource;
    private final Resource rightAnswerResource;

    public ResourceDaoCsv(Resource questionsResource, Resource answersResource, Resource rightAnswerResource) {
        this.questionsResource = questionsResource;
        this.answersResource = answersResource;
        this.rightAnswerResource = rightAnswerResource;
    }

    public  List<String[]> getQuestionList(){
        return getListDataFromResources(questionsResource);
    }

    public  List<String[]> getAnswersList(){
        return getListDataFromResources(answersResource);
    }

    public  List<String[]> getRightAnswersList(){ return getListDataFromResources(rightAnswerResource); }


    private List<String[]> getListDataFromResources(Resource res) {
        List<String[]> allRows = null;

        try (CSVReader reader = new CSVReader(new FileReader(Objects.requireNonNull(res.getFile())));) {
            allRows = reader.readAll();
        } catch (IOException | CsvException fileNotFoundException) {
            fileNotFoundException.printStackTrace();
        }
        return allRows;
    }
}
