package com.example.l7e1.controllers;


import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DemoController {

    @GetMapping("/demo")
    @PreAuthorize(value = "hasAuthority('read')")
    public String demo1(){
        return "demo1";
    }

    @GetMapping("/demo2")
    @PreAuthorize(value = "hasAnyAuthority('read','write')")
    public String demo2(){
        return "demo2";
    }

    @GetMapping("/demo3/{smth}")
    @PreAuthorize("#something == authentication.name")
    public String demo3(@PathVariable("smth") String something){

        var a = SecurityContextHolder.getContext().getAuthentication();
        return "demo3";
    }

}
