package ru.otus.homeworkApp.service;

import org.springframework.core.io.Resource;

import java.util.List;

public interface ResourceService {
    public List<String[]> getListDataFromResources(Resource res);
}
