package com.app.api.dto;

import java.io.Serializable;
import java.util.Date;

public class ComentarioDto implements Serializable {

    private static final long serialVersionUID = 1L;


    private String text;
    private Date data;
    private AutorDto autor;


    public ComentarioDto() {

    }

    public ComentarioDto(String text, Date data, AutorDto autor) {
        this.autor = autor;
        this.data = data;
        this.text = text;
    }

    public String getText() {
        return text;
    }


    public Date getData() {
        return data;
    }


    public AutorDto getAutor() {
        return autor;
    }


}
