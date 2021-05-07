package com.example.myapp.entity;

public class UserDetail {
    public String uname;
    public String password;

    public UserDetail(){

    }

    public UserDetail(String username, String password) {
        this.uname = username;
        this.password = password;
    }

    public String getUname() {
        return uname;
    }

    public void setUname(String uname) {
        this.uname = uname;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}
