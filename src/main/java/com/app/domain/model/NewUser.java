package com.app.domain.model;

public class NewUser {
    private String email;
    private String name;



    public NewUser( String email , String name) {
        this.email = email;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }


}
