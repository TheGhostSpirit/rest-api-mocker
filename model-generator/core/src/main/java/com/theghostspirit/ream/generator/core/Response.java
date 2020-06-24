package com.theghostspirit.ream.generator.core;

import java.util.ArrayList;

public class Response {
    private String status;

    private ArrayList fieldList;


    public ArrayList getFieldList() {
        return fieldList;
    }

    public void setFieldList(ArrayList fieldList) {
        this.fieldList = fieldList;
    }

    public void addField(String name, String type, Boolean required){
        Field field = new Field(name,type,required);
        fieldList.add(field);
    }
}
