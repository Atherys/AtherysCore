package com.atherys.core.web;

public class AtherysWebServer {

    private static final AtherysWebServer instance = new AtherysWebServer();

    private AtherysWebServer() {
        // TODO
    }

    public static AtherysWebServer getInstance() {
        return instance;
    }
}
