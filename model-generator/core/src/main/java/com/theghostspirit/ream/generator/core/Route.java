package com.theghostspirit.ream.generator.core;

import java.util.ArrayList;

public class Route {

    private String path;
    private String method;
    private String description;
    private Query query;
    private ArrayList<Response> responses;

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getMethod() {
        return method;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public Query getQuery() {
        return query;
    }

    public void setQuery(Query query) {
        this.query = query;
    }

    public ArrayList<Response> getResponses() {
        return responses;
    }

    public void setResponses(ArrayList<Response> responses) {
        this.responses = responses;
    }

    public void addResponse(Response response){
        responses.add(response);
    }
}
