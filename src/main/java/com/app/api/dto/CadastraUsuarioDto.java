package com.app.api.dto;

import java.io.Serializable;

public class CadastraUsuarioDto implements Serializable {
    private static final long serialVersionUID = 1L;

    private String name;
    private String email;


    public String getEmail() {
        return email;
    }

    public String getName() {
        return name;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public void setName(String name) {
        this.name = name;
    }
}
