package com.example.service.notification.dto;

public class CreateNotificationTypeDto {

    private String name;

    private String params;

    private String podaci;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getParams() {
        return params;
    }

    public void setParams(String params) {
        this.params = params;
    }

    public String getPodaci() {
        return podaci;
    }

    public void setPodaci(String podaci) {
        this.podaci = podaci;
    }
}
