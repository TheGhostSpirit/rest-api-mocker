package com.theghostspirit.ream.generator.cli;

import com.theghostspirit.ream.generator.core.Api;
import com.theghostspirit.ream.generator.core.ExportModel;

public class Main {

    public static void main(String[] args){
        Api apiInstance = new Api();
        apiInstance.setName("Test");
        apiInstance.setDescription("Petite description");
        ExportModel export = new ExportModel();
        export.getJsonModel(apiInstance);
    }
}
