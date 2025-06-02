package com.app.api.controller;

import com.app.api.dto.UserDto;
import com.app.infrastructure.repository.entity.PostEntity;
import com.app.infrastructure.repository.entity.UserEntity;
import com.app.infrastructure.repository.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;


@RestController
@RequestMapping("/api")
@CrossOrigin("*")// Permite acesso de qualquer origem
public class UserResorceController {
    @Autowired
    private UserServiceImpl userServiceImpl;

    @GetMapping(value = "/buscar-todos")
    public ResponseEntity<List<UserDto>> findAll() {
        List<UserEntity> list = userServiceImpl.findAll();

        List<UserDto> listDto = list.stream()
                .map(user -> new UserDto(user))
                .collect(Collectors.toList());
        return ResponseEntity.ok().body(listDto);
    }

    // buscar usuário por id
    @GetMapping(value = "/buscar")
    public ResponseEntity<UserDto> findById(@PathVariable String id) {
        UserEntity obj = userServiceImpl.findById(id);

        return ResponseEntity.ok().body(new UserDto(obj));
    }

    // inserir um novo usuário
    @PostMapping(value = "/inserir")
    public ResponseEntity<Void> insert(@RequestBody UserDto objetoDto) {
        UserEntity objeto = userServiceImpl.fromDto(objetoDto);
        objeto = userServiceImpl.Insert(objeto);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}").buildAndExpand(objeto.getId()).toUri();
        return ResponseEntity.created(null).build();

    }

    // excluir um usuário
    @DeleteMapping(value = "/delete")
    public ResponseEntity<Void> excluir(@PathVariable String id) {
        userServiceImpl.excluir(id);
        return ResponseEntity.noContent().build(); // 204 No Content
    }

    // atualizar um usuário
    @PostMapping(value = "/atualizar")
    public ResponseEntity<Void> update(@RequestBody UserDto objetoDto, @PathVariable String id) {
        UserEntity objeto = userServiceImpl.fromDto(objetoDto);
        objeto = userServiceImpl.update(objeto);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}").buildAndExpand(objeto.getId()).toUri();
        return ResponseEntity.created(location).build();
    }

    @PostMapping(value = "/Post")
    public ResponseEntity<List<PostEntity>> post(@PathVariable String id) {
        UserEntity objeto = userServiceImpl.findById(id);
        if (objeto == null) {
            return ResponseEntity.notFound().build();
        }
        objeto = userServiceImpl.update(objeto);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}").buildAndExpand(objeto.getId()).toUri();
        return ResponseEntity.ok().body(objeto.getPost());


    }

}

