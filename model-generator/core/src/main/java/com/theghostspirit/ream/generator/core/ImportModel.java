package com.theghostspirit.ream.generator.core;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class ImportModel {

    public Api ImportJsonFile (String path){

        String content = "";
        Api api = new Api();

        String extension = checkExtension(path);

        if(!(extension.equals("yaml") || extension.equals(("json")))) {

            try {
                content = new String(Files.readAllBytes(Paths.get(path)));
                System.out.println(content);

                if (extension.equals("yaml")) {
                    try {
                        com.fasterxml.jackson.databind.ObjectMapper objectMapper = new com.fasterxml.jackson.databind.ObjectMapper(new YAMLFactory());

                        api = objectMapper.readValue(content, Api.class);


                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                } else {
                    try {
                        ObjectMapper objectMapper = new ObjectMapper();

                        api = objectMapper.readValue(content, Api.class);


                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return api;

    }

    private String checkExtension(String path){

        System.out.println("Verification path : " + path);

        String[] arrayPath = path.split("\\.");

        System.out.println("Size : " + arrayPath.length);

        String extension = arrayPath[arrayPath.length-1];

        System.out.println("Check extension : " + extension);

        return extension;
    }

}
