package com.theghostspirit.ream.generator.core;

import java.util.ArrayList;

public class Response {
    private String status;
    private String description;
    private ArrayList<FieldParameter> fieldParameterList = new ArrayList<FieldParameter>();

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

    public ArrayList<FieldParameter> getFieldParameterList() {
        return fieldParameterList;
    }

    public void setFieldParameterList(ArrayList<FieldParameter> fieldParameterList) {
        this.fieldParameterList = fieldParameterList;
    }

}
