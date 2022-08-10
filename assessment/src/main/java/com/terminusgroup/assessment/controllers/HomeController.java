package com.terminusgroup.assessment.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {
	/**
	 * Loads the index page
	 */
	@RequestMapping(value = "/")
	public String homePage() {
		return "index";
	}
}
