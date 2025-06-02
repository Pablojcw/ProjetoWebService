package com.app.api.dto;

import com.app.infrastructure.repository.entity.UserEntity;

import java.io.Serializable;

public class AutorDto implements Serializable {

    private static final long serialVersionUID = 1L;

    private String nome;
    private String id;

    public AutorDto() {
    }


    public AutorDto(UserEntity objetoAUtor) {
        nome = objetoAUtor.getName();
        id = objetoAUtor.getId();

    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
