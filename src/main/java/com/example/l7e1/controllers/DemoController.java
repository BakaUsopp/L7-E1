package com.example.l7e1.controllers;



import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.access.prepost.PreFilter;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class DemoController {


    @GetMapping("/demo")
    @PreAuthorize("hasAuthority('read')")
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

    @GetMapping("/demo4/{smth}")
    @PreAuthorize("@demo4ConditionEvaluator.condition()")
    public String demo4(){
        return "demo4";
    }

    @GetMapping("/demo5")
    @PostAuthorize("returnObject !='demo5'")
    public String demo5(){
        System.out.println(":)"); // never use @PostAuthorize with methods that change data
        return "demo5";
    }

//    @PreFilter => collection and array
    @GetMapping("/demo6")
    @PreFilter("filterObject.contains('a')")
    public String demo6(@RequestBody List<String> values){
        System.out.println("value: " + values);
        return "demo6";
    }



}
