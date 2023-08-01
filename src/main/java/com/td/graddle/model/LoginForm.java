package com.td.graddle.model;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class LoginForm {
    private String username;
    private String password;

    public LoginForm() {

    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }
}
