package com.clinic.msdentistservice.controller;

import lombok.AllArgsConstructor;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test")
@AllArgsConstructor
public class TestController {

    private Environment environment;

    @GetMapping("/life")
    public String life() {
        String msg = environment.getProperty("message");
        return "I'm alive; "+msg;
    }

}
