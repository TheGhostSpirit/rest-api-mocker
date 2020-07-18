package com.theghostspirit.ream.generator.core;

import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import com.fasterxml.jackson.dataformat.yaml.YAMLMapper;
import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.map.ObjectMapper;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class ExportModel {

    public void getJsonModel(Api api, String path){

        ObjectMapper map = new ObjectMapper();

        try{
            String jsonModel = map.writeValueAsString(api);
            System.out.println(jsonModel);

            try {
                File myObj = new File(path);
                if (myObj.createNewFile()) {
                    System.out.println("File created: " + myObj.getName());
                    writeJsonModel(jsonModel,path);

                } else {
                    System.out.println("File already exists.");
                }
            } catch (IOException e) {
                System.out.println("An error occurred.");
                e.printStackTrace();
            }

        }

        catch (IOException e){
            e.printStackTrace();
        }

    }

    public void getYamlModel(Api api, String path){

        com.fasterxml.jackson.databind.ObjectMapper mapper = new com.fasterxml.jackson.databind.ObjectMapper(new YAMLFactory());

        try{
            String yaml = mapper.writeValueAsString(api);

            try {
                File myObj = new File(path);
                if (myObj.createNewFile()) {
                    System.out.println("File created: " + myObj.getName());
                    writeJsonModel(yaml,path);

                } else {
                    System.out.println("File already exists.");
                }
            } catch (IOException e) {
                System.out.println("An error occurred.");
                e.printStackTrace();
            }

        }

        catch (IOException e){
            e.printStackTrace();
        }

    }

    public void writeJsonModel(String jsonModel,String path){
        try {
            FileWriter myWriter = new FileWriter(path);
            myWriter.write(jsonModel);
            myWriter.close();
            System.out.println("Successfully wrote to the file.");
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

}
