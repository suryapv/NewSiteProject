// Copyright 2014; All rights reserved with NeoSemantix, Inc.
package com.neosemantix.newsite;

import java.io.IOException;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.ImportResource;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import com.neosemantix.newsite.config.security.SecurityConfig;

/**
 * @author umesh
 *
 */
// @SpringBootApplication
@Configuration
@EnableAutoConfiguration
@EnableWebMvc
@ComponentScan
@ImportResource("classpath:spring.xml")
@Import({ SecurityConfig.class })
public class Main {

    public static void main(String[] args) {
	try {
	    Registry reg = Registry.getInstance();
	    SpringApplication.run(Main.class, args);
	} catch (IOException e) {
	    e.printStackTrace();
	    System.exit(0);
	}
    }
}
