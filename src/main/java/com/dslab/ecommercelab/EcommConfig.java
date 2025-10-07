package com.dslab.ecommercelab;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;

@Configuration
@EnableWebSecurity
public class EcommConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    EcommUserDetailsService userDetailsService;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .httpBasic()
                .and()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authorizeRequests()
                .antMatchers("/user/register").permitAll()
                .antMatchers("/user/all").hasAuthority("ADMIN")
                .antMatchers(HttpMethod.DELETE, "/user/{id}").hasAuthority("ADMIN")
                .antMatchers(HttpMethod.GET, "/product/*").permitAll()
                .antMatchers(HttpMethod.POST, "/product/add").hasAuthority("ADMIN")
                .antMatchers(HttpMethod.DELETE, "/product/{id}").hasAuthority("ADMIN")
                .antMatchers(HttpMethod.DELETE, "/product/{id}").hasAuthority("ADMIN")
                .antMatchers(HttpMethod.DELETE, "/order/{id}").hasAuthority("ADMIN")
                .antMatchers(HttpMethod.GET, "/order/all").hasAuthority("ADMIN")
                .anyRequest().authenticated()
                .and()
                .csrf().disable();



    }
}
