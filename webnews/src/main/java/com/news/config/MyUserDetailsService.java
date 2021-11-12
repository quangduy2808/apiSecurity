package com.news.config;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.news.entity.RoleEntity;
import com.news.entity.UserEntity;
import com.news.repositoy.UserRepository;

@Service
@Transactional
public class MyUserDetailsService implements org.springframework.security.core.userdetails.UserDetailsService{

	@Autowired
	private UserRepository userRepository;
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		UserEntity userEntity =  userRepository.findByUsername(username);
		if (userEntity == null) {
			throw new UsernameNotFoundException("User not found");
		}
		List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
		for (RoleEntity roleEntity : userEntity.getRoles()) {
			authorities.add(new SimpleGrantedAuthority(roleEntity.getCode()));
		}
		return new User(userEntity.getUsername(),userEntity.getPassword(),true,true,true,true,authorities);
	}

}
