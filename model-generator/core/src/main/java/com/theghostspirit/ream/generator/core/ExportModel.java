package com.theghostspirit.ream.generator.core;

import org.codehaus.jackson.map.ObjectMapper;

import java.io.IOException;

public class ExportModel {

    public void getJsonModel(Api api){

        ObjectMapper map = new ObjectMapper();

        try{
            String jsonModel = map.writeValueAsString(api);

            System.out.println(jsonModel);

        }

        catch (IOException e){
            e.printStackTrace();
        }

    }

}
