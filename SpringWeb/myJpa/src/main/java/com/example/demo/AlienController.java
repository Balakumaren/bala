package com.example.demo;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.example.demo.dao.AlienRepo;
import com.example.demo.model.Alien;

@RestController
public class AlienController {
	@Autowired
	AlienRepo repo;
	@RequestMapping("/")
	public String home() {
		return "home.jsp";
	}
	
	@RequestMapping("addAlien")
	public String add(Alien a) {
		repo.save(a);
		return "home.jsp";
	}
	
	@RequestMapping("fetchAlien")
	public ModelAndView fetch(@RequestParam int id ) {
		ModelAndView mvc = new ModelAndView();
		Alien alien=repo.findById(id).orElse(new Alien());
		mvc.setViewName("result.jsp");
		alien.toString();
		mvc.addObject(alien);
		System.out.println(repo.findByTech("react"));
		System.out.println(repo.findByIdGreaterThan(2));
		System.out.println(repo.findByTechSorted("react"));
		return mvc;
	}
	
	@GetMapping("/aliens")
	public List<Alien> getAliens(){
		return repo.findAll();
	}
	
	@GetMapping("/alien/{id}")
	public Optional<Alien> getAlien(@PathVariable("id") int id){
		return repo.findById(id);
	}
	
	@PostMapping("/alien")
	public Alien addalien(@RequestBody Alien a){
		repo.save(a);
		return a;
	}
}
