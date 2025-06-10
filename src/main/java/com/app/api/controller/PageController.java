package com.app.api.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PageController {
    @GetMapping(value = "/")
    public String index() {
        return "index.html";
    }

    @GetMapping(value = "/posts")
    public String posts() {
        return "post.html";
    }

    @GetMapping(value = "/inserir")
    public String inserir() {
        return "inserir.html/Servico_page.html";
    }

    @GetMapping(value = "/login")
    public String login() {
        return "forward:/index.html";
    }

    @GetMapping(value = "/register")
    public String register() {
        return "forward:/index.html";
    }

    @GetMapping(value = "/profile")
    public String profile() {
        return "forward:/index.html";
    }

    @GetMapping(value = "/admin")
    public String admin() {
        return "forward:/index.html";
    }


}
