package com.spring.generator.springcsrgeneratorgui.utils;

import java.util.Locale;

public class TextTool {

    private TextTool() {
    }

    public static String changeVariable(String text, String basePackage, String packagePath, String objectPackage, String modelName){
        return text
                .replace("${basePackage}", basePackage)
                .replace("${packagePath}", packagePath)
                .replace("${objectPackage}",objectPackage.replace("\\","."))
                .replace("${objectName}",modelName)
                .replace("${lowerObjectName}",toCamelCase(modelName));
    }

    /**
     * Warning ! Doesn't actually transform into a camelCase
     * ex : FirstCharacter --> firstCharacter
     * but if your string is Titlecase or lowercase
     * Firstcharacter --> firstcharacter
     * firstcharacter --> firstcharacter
     * @param s String
     * @return String
     */
    public static String toCamelCase(String s) {
        return String.valueOf(s.charAt(0)).toLowerCase(Locale.ROOT) + s.substring(1);
    }
}
