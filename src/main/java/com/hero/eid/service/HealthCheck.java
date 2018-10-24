package com.hero.eid.service;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(".well-known/health")
public class HealthCheck {


    @GetMapping
    public static String healthy(){
        return "OK";
    }
}
