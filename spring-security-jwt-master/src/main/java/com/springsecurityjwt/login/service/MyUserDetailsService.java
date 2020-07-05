package com.springsecurityjwt.login.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
/*import org.springframework.security.core.userdetails.User;
*/import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.springsecurityjwt.loginTo.MyUserDetails;
import com.springsecurityjwt.models.User;
import com.springsecurityjwt.repository.UserRepository;

@Service
public class MyUserDetailsService implements UserDetailsService {

	@Autowired
	UserRepository userRepository;
	
    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
		
		  User user = userRepository.findByUserName(userName);
		  
		 if(user!=null) {
			 return new MyUserDetails(user);
		 }
		 else {
			 throw new UsernameNotFoundException("Not found: " +
					  userName);
		 }
		 
    	//return new User("foo","foo",new ArrayList<>());
    }
}