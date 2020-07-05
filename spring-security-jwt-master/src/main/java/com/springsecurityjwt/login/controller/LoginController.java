package com.springsecurityjwt.login.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.springsecurityjwt.login.jwtAuthenticatoin.JwtUtil;
import com.springsecurityjwt.login.service.MyUserDetailsService;
import com.springsecurityjwt.loginTo.AuthenticationRequest;
import com.springsecurityjwt.loginTo.AuthenticationResponse;
import com.springsecurityjwt.models.User;
import com.springsecurityjwt.repository.UserRepository;


@RestController
public class LoginController {


		@Autowired
		private AuthenticationManager authenticationManager;

		
		  @Autowired private BCryptPasswordEncoder passwordEncoder;
		 
		@Autowired
		private JwtUtil jwtTokenUtil;

		@Autowired
		private UserRepository repo;

		@Autowired
		private MyUserDetailsService userDetailsService;

		@RequestMapping("/hello")
		public String firstPage() {
			return "Hello World";
		}

		@PostMapping("/register")
		public String register(@RequestBody User u) {

			
			  String encodedPassword = passwordEncoder.encode(u.getPassword());
			  u.setPassword(encodedPassword);
			 
			repo.save(u);
			System.out.println(u.getId() + "," + u.getUserName() + "," + u.getPassword());

			return "registered";

		}

		@RequestMapping(value = "/authenticate", method = RequestMethod.POST)
		public ResponseEntity<?> createAuthenticationToken(@RequestBody AuthenticationRequest authenticationRequest)
				throws Exception {

			try {
				authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
						authenticationRequest.getUsername(), authenticationRequest.getPassword()));
			} catch (BadCredentialsException e) {
				throw new Exception("Incorrect username or password", e);
			}

			final UserDetails userDetails = userDetailsService.loadUserByUsername(authenticationRequest.getUsername());

			final String jwt = jwtTokenUtil.generateToken(userDetails);
			User user = repo.findByUserName(authenticationRequest.getUsername());
			return ResponseEntity.ok(new AuthenticationResponse(jwt,user.getId()));
		}

	}


