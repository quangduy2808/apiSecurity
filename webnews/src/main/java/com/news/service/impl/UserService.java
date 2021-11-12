package com.news.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.news.converter.UserConverter;
import com.news.dto.UserDTO;
import com.news.entity.RoleEntity;
import com.news.entity.UserEntity;
import com.news.repositoy.RoleRepository;
import com.news.repositoy.UserRepository;
import com.news.service.IUserService;

@Service
public class UserService implements IUserService{

	@Autowired
	private UserRepository userRepository;
	@Autowired
	private UserConverter userConverter;
	@Autowired
	private RoleRepository roleRepository;
	@Override
	@Transactional
	public UserDTO register(UserDTO dto) throws Exception {
		//Let's check if user already registered with us
        if(userRepository.findByUsername(dto.getUserName())!=null){
            throw new Exception("User already exists for this email");
        }
        UserEntity userEntity = userConverter.toEntity(dto);
        RoleEntity roleEntity = roleRepository.findByName("ADMIN");
        userEntity.setRole(roleEntity);
        
        return userConverter.toDTO(userRepository.save(userEntity));
	}
	
}
