package com.fututre.tugas.config;

import com.fututre.tugas.service.MongoUserDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.access.AccessDeniedHandler;

public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    MongoUserDetailService userDetailService;

    @Autowired
    private AccessDeniedHandler accessDeniedHandler;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/").permitAll()
                .antMatchers("/admin/**").hasRole("admin")
                .antMatchers("/user/**").hasRole("manager")
                .anyRequest().authenticated()
                .antMatchers("/resources/**").permitAll().anyRequest().permitAll()
                .and()
                .formLogin()
                .loginPage("/login")
//                .failureUrl("/login.html?error=true")
                .permitAll()
                .and()
                .logout()
                .and()
                .exceptionHandling().accessDeniedHandler(accessDeniedHandler);
        http.httpBasic();
        http.csrf().disable();
    }
    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Autowired
    public void init(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailService).passwordEncoder(passwordEncoder()).and()
                .inMemoryAuthentication()
                .withUser("habib").password("habib123").roles("admin").and()
                .withUser("robin").password("robin456").roles("manager");
    }
}
