package com.spring.generator.springcsrgeneratorgui.utils;

import lombok.SneakyThrows;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.regex.Pattern;

public class Analyser {

    private Analyser(){}

    @SneakyThrows
    public static String searchInFile(File file, String regex){
        String target = "";
        try (
                Scanner scan1 = new Scanner( file )
                ) {

            String content = scan1.useDelimiter("\\A").next();

            var scan2 = new Scanner(content);
            target = scan2.findInLine(Pattern.compile(regex));
            scan2.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return target;
    }
}
