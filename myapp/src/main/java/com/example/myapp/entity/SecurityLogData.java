package com.example.myapp.entity;

import java.util.Map;

public class SecurityLogData {

    private Map<String,String> data;

    public Map<String, String> getData() {
        return data;
    }

    public void setData(Map<String, String> data) {
        this.data = data;
    }
    public SecurityLogData(Map<String, String> data) {
        this.data = data;
    }


}
