package com.app.domain.model;

public class User {
    private String id;
    private String email;
    private String name;



    public User(String id, String email , String name) {
        this.id = id;
        this.email = email;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }


    public String getId() {
        return id;
    }

}
