package com.theghostspirit.ream.generator.core;


import java.util.ArrayList;

public class Model {
    private String name;
    private String tag;
    private String scope;
    private String version;
    private String format;
   // ArrayList ObjectList = new ArrayList();

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public String getScope() {
        return scope;
    }

    public void setScope(String scope) {
        this.scope = scope;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getFormat() {
        return format;
    }

    public void setFormat(String format) {
        this.format = format;
    }

    /*
    public ArrayList getObjectList() {
        return ObjectList;
    }

    public void setObjectList(ArrayList objectList) {
        ObjectList = objectList;
    }

    */
}
