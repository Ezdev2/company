package com.td.graddle.config;

import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;

public interface SecurityConfig1 {
    void configure(AuthenticationManagerBuilder auth) throws Exception;
}
