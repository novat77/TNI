package com.sincera.intern.security;

import com.sincera.intern.model.User;
import com.sincera.intern.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public class UserDetailsServiceImpl implements UserDetailsService {
	
	@Autowired
	private UserRepository userRepository;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user =userRepository.getUserByUsername(username);
		if(user==null) {
			throw new UsernameNotFoundException("Could not find User: "+username);
		}
		return new MyUserDetails(user);
	}

}
