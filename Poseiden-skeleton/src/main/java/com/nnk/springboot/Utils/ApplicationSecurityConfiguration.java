package com.nnk.springboot.Utils;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@EnableWebSecurity
@Configuration
public class ApplicationSecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable().authorizeRequests()
                .antMatchers("/").permitAll()
                .antMatchers("/user/add").permitAll()
                .antMatchers(HttpMethod.GET,"/user/list").permitAll()
                .antMatchers(HttpMethod.POST,"/user/add").permitAll()
                .antMatchers(HttpMethod.POST,"/user/validate").permitAll()
                .antMatchers(HttpMethod.POST,"/user/update").permitAll()
                .antMatchers(HttpMethod.GET,"/user/delete/*").permitAll()

                .antMatchers(HttpMethod.GET,"/rulename/list").permitAll()
                .anyRequest().authenticated();


    }
}
