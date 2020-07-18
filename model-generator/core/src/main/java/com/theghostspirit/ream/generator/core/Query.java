package com.theghostspirit.ream.generator.core;

import java.util.ArrayList;

public class Query {

    private ArrayList<FieldParameter> fieldParameterList = new ArrayList<FieldParameter>();

    public void setFieldParameterList(ArrayList<FieldParameter> fieldParameterList) {
        this.fieldParameterList = fieldParameterList;
    }

    public ArrayList<FieldParameter> getFieldParameterList() {
        return fieldParameterList;
    }

}
