package com.app.infrastructure.repository.entity;

import com.app.api.dto.AutorDto;
import com.app.api.dto.ComentarioDto;
import jakarta.persistence.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@Document(collection = "posts")
public class PostEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    private String id;
    private Date data;
    private String title;
    private String conteudo;
    private AutorDto autor;

    private List<ComentarioDto> comenatrios = new ArrayList<>(); // Lista de comentários direto do dto


    public PostEntity() {

    }

    public PostEntity(String id, Date data, String title, String conteudo, AutorDto autor) {
        this.id = id;
        this.data = data;
        this.title = title;
        this.conteudo = conteudo;
        this.autor = autor;
    }

    public String getConteudo() {
        return conteudo;
    }

    public String getTitle() {
        return title;
    }

    public String getId() {
        return id;
    }

    public Date getData() {
        return data;
    }

    public AutorDto getAutor() {
        return autor;
    }

    public List<ComentarioDto> getComenatrios() {
        return comenatrios;
    }

    public void setComenatrios(List<ComentarioDto> comenatrios) {
        this.comenatrios = comenatrios;
    }


    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        PostEntity that = (PostEntity) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }


}
