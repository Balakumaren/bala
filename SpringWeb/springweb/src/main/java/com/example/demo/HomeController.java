package com.example.demo;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class HomeController {
	
	@RequestMapping("home")
	public ModelAndView home(Alien alien) {
		ModelAndView mvc = new ModelAndView();
		mvc.addObject(alien);
		mvc.setViewName("home");
		return mvc;
	}
}
