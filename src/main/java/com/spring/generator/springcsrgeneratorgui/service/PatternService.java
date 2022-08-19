package com.spring.generator.springcsrgeneratorgui.service;

import com.spring.generator.springcsrgeneratorgui.model.PatternList;
import lombok.SneakyThrows;

import java.io.File;
import java.util.List;

import static com.spring.generator.springcsrgeneratorgui.utils.Const.APPLICATION_PROPERTIES;
import static com.spring.generator.springcsrgeneratorgui.utils.Const.PATTERN_FILE_PATH;


public class PatternService {

    private final PatternList patternList;

    @SneakyThrows
    public PatternService() {

        this.patternList = new PatternList(
                new PropertiesReaderService(APPLICATION_PROPERTIES).getProperty(PATTERN_FILE_PATH)
        );

        this.patternList.run();
    }

    @SneakyThrows
    public List<File> getPatternSystemFiles() {

        var thread = new Thread(this.patternList);
        thread.start();
        thread.join();

        return this.patternList.getPatternSystemFiles();
    }
}
