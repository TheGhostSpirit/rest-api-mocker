package com.theghostspirit.ream.generator.core;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class ImportModel {

    public Api ImportJsonFile (String path){

        String content = "";
        ApiWrapper apiWrapper = new ApiWrapper();
        String extension = checkExtension(path);

        if(extension.equals("yaml") || extension.equals("json")) {

            try {
                content = new String(Files.readAllBytes(Paths.get(path)));
                System.out.println(content);

                if (extension.equals("yaml")) {
                    try {
                        com.fasterxml.jackson.databind.ObjectMapper objectMapper = new com.fasterxml.jackson.databind.ObjectMapper(new YAMLFactory());

                        apiWrapper = objectMapper.readValue(content, ApiWrapper.class);


                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                } else {
                    try {
                        ObjectMapper objectMapper = new ObjectMapper();

                        apiWrapper = objectMapper.readValue(content, ApiWrapper.class);


                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return apiWrapper.getApi();
    }

    private String checkExtension(String path){
        String[] arrayPath = path.split("\\.");
        String extension = arrayPath[arrayPath.length-1];
        return extension;
    }

}
