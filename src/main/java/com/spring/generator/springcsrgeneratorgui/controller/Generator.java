package com.spring.generator.springcsrgeneratorgui.controller;

import com.spring.generator.springcsrgeneratorgui.model.PatternFile;
import com.spring.generator.springcsrgeneratorgui.service.ModelService;
import com.spring.generator.springcsrgeneratorgui.service.ProjectService;
import com.spring.generator.springcsrgeneratorgui.service.SaveService;
import com.spring.generator.springcsrgeneratorgui.utils.TextTool;
import lombok.SneakyThrows;

import java.io.File;
import java.io.FileWriter;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Scanner;

import static com.spring.generator.springcsrgeneratorgui.utils.Const.JAVA_SOURCE;

public class Generator {

    private final String modelRepoPath = new SaveService().getLastDirectoryModelPath();

    private final ProjectService projectService;


    public Generator() {

        this.projectService = new ProjectService(this.modelRepoPath);

    }


    public void generate(List<PatternFile> patternFileList, Map<String, Boolean> map) {

        for (PatternFile pf:
             patternFileList) {

            if(Boolean.TRUE.equals(map.get(pf.getPName()))) {
                generateFilesFromPattern(pf);
            }
        }
    }

    private void generateFilesFromPattern(final PatternFile patternFile) {

        var modelRepo = new ModelService().getModels(
                this.modelRepoPath
        );

        if(modelRepo == null || !modelRepo.isDirectory() || modelRepo.listFiles() == null) return;

        for (File f:
                Objects.requireNonNull(modelRepo.listFiles())) {

            if (f.isDirectory())
                filesFromPattern(f,patternFile);
            else if (f.getAbsolutePath().contains(JAVA_SOURCE))
                createFileFromPattern(f,patternFile);
        }

    }

    private void filesFromPattern(File file, PatternFile patternFile) {

        for (File f:
                Objects.requireNonNull(file.listFiles())) {

            if (f.isDirectory())
                filesFromPattern(f,patternFile);
            else if (f.getAbsolutePath().contains(JAVA_SOURCE))
                createFileFromPattern(f,patternFile);
        }
    }

    @SneakyThrows
    private void createFileFromPattern(File f, PatternFile patternFile) {

        var newPath = f.getAbsolutePath()
                .replace("\\model\\",'\\'+ patternFile.getPName().toLowerCase() +'\\')
                .replace(
                        f.getName().split("\\.")[0] + JAVA_SOURCE,
                        f.getName().split("\\.")[0] + patternFile.getPName() + JAVA_SOURCE
                );

        var newFile = new File(newPath);

        String pathWithoutSource = newPath.replace(JAVA_SOURCE,"").split(this.projectService.getBasePackage())[1];

        String[] s = pathWithoutSource.split("\\\\");
        String packagePath = this.getPackage(s);
        String modelName = s[s.length-1].replace(patternFile.getPName(),"");
        String objPackage = f.getAbsolutePath().replace(this.modelRepoPath,"").substring(1).replace(JAVA_SOURCE,"");

        if(!newFile.getParentFile().mkdirs() && !newFile.createNewFile()) return;

        try (
                Scanner scan = new Scanner(new File(patternFile.getAbsolutePath()));
                FileWriter fw = new FileWriter(newPath)
                )
        {

            String content = scan.useDelimiter("\\Z").next();
            content = TextTool.changeVariable(content, this.projectService.getBasePackage(), packagePath ,objPackage, modelName);

            fw.write(content);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private String getPackage(String[] strings){
        StringBuilder s = new StringBuilder();

        for (int i = 2; i < strings.length-1; i++) {
            if(i > 2)
                s.append(".");
            s.append(strings[i]);
        }

        return s.toString();
    }

}
