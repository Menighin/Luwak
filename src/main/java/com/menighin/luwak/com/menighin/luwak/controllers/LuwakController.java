package com.menighin.luwak.com.menighin.luwak.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LuwakController {

    @RequestMapping("/luwak/{wat}")
    public String index(String wat) {
        return "It is working D: " + wat;
    }

}
