package com.example.springsecurityjpa.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class HomeController {

	@GetMapping("/")
	public ModelAndView home() {
		ModelAndView mv = new ModelAndView("index");
		return mv;
	}
	
	@GetMapping("/user")
	public ModelAndView user() {
		ModelAndView mv = new ModelAndView("index");
		return mv;
	}
	
	@GetMapping("/admin")
	public ModelAndView admin() {
		ModelAndView mv = new ModelAndView("index");
		return mv;
	}
}
