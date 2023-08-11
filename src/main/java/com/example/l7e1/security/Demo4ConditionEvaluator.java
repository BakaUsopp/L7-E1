package com.example.l7e1.security;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component
public class Demo4ConditionEvaluator {
    public boolean condition(){
        var a = SecurityContextHolder.getContext().getAuthentication();

        return true;
    }
}
