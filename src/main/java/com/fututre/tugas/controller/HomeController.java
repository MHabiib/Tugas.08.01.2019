package com.fututre.tugas.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping(value = "/login")
    public String doLogin() {
        return "login";
    }

    @GetMapping(value = "/")
    public String indexPage() {
        return "index";
    }

}