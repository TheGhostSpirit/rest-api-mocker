package com.theghostspirit.ream.generator.core;

import java.util.ArrayList;

public class Route {

    private String path;
    private String method;
    private String description;
    private String operationId;
    private ArrayList<Query> query = new ArrayList<Query>();
    private ArrayList<Response> response = new ArrayList<Response>();
    private ArrayList<Query> body = new ArrayList<Query>();

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getOperationId() {
        return operationId;
    }

    public void setOperationId(String operationId) {
        this.operationId = operationId;
    }


    public ArrayList<Query> getQuery() {
        return query;
    }

    public void setQuery(ArrayList<Query> query) {
        this.query = query;
    }

    public ArrayList<Response> getResponse() {
        return response;
    }

    public void setResponse(ArrayList<Response> responses) {
        this.response = responses;
    }

    public ArrayList<Query> getBody() {
        return body;
    }

    public void setBody(ArrayList<Query> body) {
        this.body = body;
    }
}
