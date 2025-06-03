package com.app.api.controller;

import com.app.api.controller.URL.URLController;
import com.app.infrastructure.repository.entity.PostEntity;
import com.app.infrastructure.service.impl.PostServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping(value = "/posts")

public class PostResorceController {

    @Autowired
    private PostServiceImpl postServiceImpl;

    // buscar usuário por id
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<PostEntity> findById(@PathVariable("id") String id) {
        PostEntity obj = postServiceImpl.findById(id);

        return ResponseEntity.ok().body(obj);
    }

    @GetMapping(value = "/titlesearch")
    public ResponseEntity<List<PostEntity>> findByTitle(@RequestParam(value = "text", defaultValue = "") String text) {
        text = URLController.decodeParameter(text);
        List<PostEntity> list = postServiceImpl.findByTitle(text);
        return ResponseEntity.ok().body(list);
    }

}

