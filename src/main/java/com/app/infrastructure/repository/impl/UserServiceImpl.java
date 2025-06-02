package com.app.infrastructure.repository.impl;

import com.app.api.dto.UserDto;
import com.app.infrastructure.repository.UserRepository;
import com.app.infrastructure.repository.entity.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl {

    @Autowired
    private UserRepository repository;

    public List<UserEntity> findAll() {
        return repository.findAll();
    }

    public UserEntity findById(String id) {
        UserEntity user = repository.findById(id).orElse(null);
        if (user == null) {
            throw new RuntimeException("User not found with id: " + id);
        }
        return user;
    }

    public UserEntity Insert(UserEntity objeto) {
        if (objeto.getId() != null) {
            UserEntity naoExisteUsuario = repository.findById(objeto.getId()).orElse(null);
        }
        return repository.save(objeto);
    }

    public void excluir(String id) {
        findById(id); // Verifica se o usuário existe
        repository.deleteById(id); // Exclui o usuário pelo ID
    }

    public UserEntity update(UserEntity objeto) {
        UserEntity newObjeto = repository.findById(objeto.getId())
                .orElseThrow(() -> new RuntimeException("USUARIO NAO ENCONTADO COM ID : " + objeto.getId()));
        updateData(newObjeto, objeto);
        return repository.save(newObjeto);
    }

    public UserEntity updateData(UserEntity newObjeto, UserEntity objeto) {
        newObjeto.setName(objeto.getName());
        newObjeto.setEmail(objeto.getEmail());
        return newObjeto;
    }

    public UserEntity fromDto(UserDto objetoDto) {
        return new UserEntity(objetoDto.getId(), objetoDto.getName(), objetoDto.getEmail());
    }


}



