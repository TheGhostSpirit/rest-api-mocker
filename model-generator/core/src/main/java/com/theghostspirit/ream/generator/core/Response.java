package com.theghostspirit.ream.generator.core;

import java.util.ArrayList;

public class Response {
    private String status;
    private String description;

    private ArrayList<Field> fieldList;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public ArrayList<Field> getFieldList() {
        return fieldList;
    }

    public void setFieldList(ArrayList<Field> fieldList) {
        this.fieldList = fieldList;
    }

    public void addField(String name, String type, Boolean required){
        Field field = new Field(name,type,required);
        fieldList.add(field);
    }
}
