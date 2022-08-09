package com.spring.generator.springcsrgeneratorgui.service;




import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertiesReaderService {

    private final Properties properties;

    public PropertiesReaderService(String propertyFileName) throws IOException {
        InputStream is = getClass().getClassLoader()
                .getResourceAsStream(propertyFileName);
        this.properties = new Properties();
        this.properties.load(is);
    }

    public String getProperty(String propertyName) {
        return this.properties.getProperty(propertyName);
    }
}
