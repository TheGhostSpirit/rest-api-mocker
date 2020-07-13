package com.theghostspirit.ream.generator.core;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class ImportModel {

    public void ImportJsonFile (String path){

        String content = "";

        try
        {
            content = new String ( Files.readAllBytes( Paths.get(path) ) );

            System.out.println(content);

            try{

            ObjectMapper objectMapper = new ObjectMapper();

            Api api = objectMapper.readValue (content, Api.class);


            } catch (IOException e) {
                e.printStackTrace ();
            }

        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

}
