package com.theghostspirit.ream.generator.core;

import java.util.ArrayList;

public class Api {
    private String name;
    private String description;
    private String serverPath;
    private String license;
    private String version;
    private Contact contact;
    private ArrayList<Route> routes;


    public Api() {
        this.name = "";
        this.description = "";
        this.license = "";
        this.version = "";
        this.serverPath = "";
        this.contact = new Contact();
        this.routes = new ArrayList<Route>();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getServerPath() {
        return serverPath;
    }

    public void setServerPath(String serverPath) {
        this.serverPath = serverPath;
    }

    public String getLicense() {
        return license;
    }

    public void setLicense(String license) {
        this.license = license;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public Contact getContact() {
        return contact;
    }

    public void setContact(Contact contact) {
        this.contact = contact;
    }

    public ArrayList<Route> getRoutes() {
        return routes;
    }

    public void setRoutes(ArrayList<Route> routes) {
        this.routes = routes;
    }

    public String toString(){
        return "Api description :  " + getDescription();
    }

    public void addRoute(Route route){
        this.routes.add(route);
    }
}
