package com.sulitsa.dev.accountant.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sulitsa.dev.accountant.model.Stage;
import lombok.RequiredArgsConstructor;

import java.io.File;
import java.io.IOException;
import java.util.List;

@RequiredArgsConstructor
public class DataReaderService {

    private final ObjectMapper mapper;

    public List<Stage> readFromFile(File jsonFile) {
        List<Stage> stageList;

        try {
            stageList = mapper.readValue(jsonFile, new TypeReference<>() {});
        } catch (IOException e) {
            throw new RuntimeException("You have an error in the DataReader.readDataFromFile(): ", e);
        }

        return stageList;
    }
}
