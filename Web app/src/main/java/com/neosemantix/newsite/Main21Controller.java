// Copyright 2014; All rights reserved with NeoSemantix, Inc.
package com.neosemantix.newsite;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author umesh
 *
 */
@Controller
public class Main21Controller {

    @RequestMapping(value = { "/", "/welcome**", "/main21**" })
    public String main21(Model model) {
	return "main21";
    }

    @RequestMapping(value = "/admin**")
    public String adminPage(Model model) {
	model.addAttribute("title", "News site admin");
	model.addAttribute("message", "Admin Page for News site.");
	return "hello";
    }

    @RequestMapping(value = "/commentator**")
    public String dbaPage(Model model) {
	model.addAttribute("title", "News site DBA");
	model.addAttribute("message", "DBA Page for News site");
	return "hello";
    }    
}
