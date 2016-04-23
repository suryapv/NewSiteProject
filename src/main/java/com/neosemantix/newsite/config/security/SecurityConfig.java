// Copyright 2014; All rights reserved with NeoSemantix, Inc.
package com.neosemantix.newsite.config.security;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.servlet.configuration.EnableWebMvcSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * @author umesh
 *
 */
//@EnableWebSecurity
@Configuration
@EnableWebMvcSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private DataSource dataSource;
    
    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth)
	    throws Exception {

//	auth.jdbcAuthentication()
//		.dataSource(dataSource)
//		.usersByUsernameQuery(
//			"select user_name, password, enabled from ns_user where user_name=?")
//		.authoritiesByUsernameQuery(
//			"select user_name, role from ns_user where user_name=?");
	
	
	auth.userDetailsService(userDetailsService).passwordEncoder(passwordencoder());;


    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
	System.out.println("http: " + http);
	http.authorizeRequests().antMatchers("/admin/**")
		.access("hasRole('ADMIN')").antMatchers("/commentator/**")
		.access("hasRole('COMMENTATOR')").and().formLogin();

    }    
    
    @Bean(name="passwordEncoder")
       public PasswordEncoder passwordencoder(){
        return new BCryptPasswordEncoder();
       }

}
