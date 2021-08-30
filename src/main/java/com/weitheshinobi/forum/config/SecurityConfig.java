package com.weitheshinobi.forum.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;


@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private static final String ADMIN = "ADMIN";

    private final PasswordEncoder pwEncoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();

    @Value("${admin-username: admin}")
    private String adminUsername;
    @Value("${admin-password: 123456}")
    private String adminPassword;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .mvcMatchers("/actuator/**").hasRole(ADMIN)
                .anyRequest().authenticated()
                .and()
                .formLogin().disable().httpBasic().disable();
    }

    @Bean
    UserDetailsService authentication() {
        UserDetails admin = User.builder()
                .username(adminUsername)
                .password(pwEncoder.encode(adminPassword))
                .roles(ADMIN)
                .build();

        UserDetails test = User.builder()
                .username("test")
                .password(pwEncoder.encode(adminPassword))
                .roles("test")
                .build();

        return new InMemoryUserDetailsManager(admin, test);
    }

}
