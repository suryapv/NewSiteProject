// Copyright 2014; All rights reserved with NeoSemantix, Inc.
package com.neosemantix.newsite.config.security;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.jdbc.JdbcDaoImpl;

/**
 * @author upatil
 *
 */
@Configuration
public class AuthenticationProviderConfig {
    
    @Autowired
    private DataSource dataSource;
    
    @Bean(name="userDetailsService")
    public UserDetailsService userDetailsService(){
     JdbcDaoImpl jdbcImpl = new JdbcDaoImpl();
     jdbcImpl.setDataSource(dataSource);
     jdbcImpl.setUsersByUsernameQuery("select user_name, password, enabled from ns_user where user_name=?");
     jdbcImpl.setAuthoritiesByUsernameQuery("select user_name, role from ns_user where user_name=?");         
     return jdbcImpl;
    }

}
