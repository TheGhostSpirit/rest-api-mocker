package com.theghostspirit.ream.generator.core;

public class ApiWrapper {

    private String version;

    private Api api = new Api();

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public Api getApi() {
        return api;
    }

    public void setApi(Api api) {
        this.api = api;
    }
}
