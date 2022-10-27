package com.nnk.springboot.Utils;

import com.nnk.springboot.services.MyUserLoginInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.core.env.StandardEnvironment;
import org.springframework.core.env.SystemEnvironmentPropertySource;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import java.util.Properties;

@EnableWebSecurity
@Configuration
public class ApplicationSecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Autowired
    private Environment env;


    @Autowired
    public MyUserLoginInfoService myUserLoginInfoService;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(12);
    }

    @Override
    protected void configure(final AuthenticationManagerBuilder auth) throws Exception {

        BCryptPasswordEncoder encode = new BCryptPasswordEncoder();
        encode.encode("test");
        auth.authenticationProvider(provider());
    }

    @Bean
    public DaoAuthenticationProvider provider() {
        DaoAuthenticationProvider prov = new DaoAuthenticationProvider();
        prov.setUserDetailsService(myUserLoginInfoService);
        prov.setPasswordEncoder(passwordEncoder());
        return prov;
    }


    @Override
    protected void configure(final HttpSecurity http) throws Exception {
        String envType = (String) env.getProperty("environnement");
        if (envType.equals("dev")) {
            http.requiresChannel().anyRequest().requiresSecure();
        } else {
            http.authorizeRequests()
                    .antMatchers("/user/**").hasRole("ADMIN")
                    .antMatchers("/**").permitAll().anyRequest().authenticated().and()
                    .csrf().disable()
                    .formLogin()  // #8
                    //.loginPage("/login") //
                    .defaultSuccessUrl("/trade/list")
                    .permitAll()
                    .and()
                    .logout()
                    .permitAll(); // #5
        }
    }

    // TODO : auth google

    }


