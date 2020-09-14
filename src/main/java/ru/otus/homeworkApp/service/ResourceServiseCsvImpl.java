package ru.otus.homeworkApp.service;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvException;
import org.springframework.core.io.Resource;

import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import java.util.Objects;

public class ResourceServiseCsvImpl implements ResourceService{
    @Override
    public List<String[]> getListDataFromResources(Resource res) {
        List<String[]> allRows = null;

        try (CSVReader reader = new CSVReader(new FileReader(Objects.requireNonNull(res.getFile())));) {
            allRows = reader.readAll();
        } catch (IOException | CsvException fileNotFoundException) {
            fileNotFoundException.printStackTrace();
        }
        return allRows;
    }
}
