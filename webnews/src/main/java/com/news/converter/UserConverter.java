package com.news.converter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.news.dto.UserDTO;
import com.news.entity.UserEntity;

@Component
public class UserConverter {
	
	@Autowired
	private PasswordEncoder encoder;
	public UserEntity toEntity(UserDTO dto) {
		UserEntity entity = new UserEntity();
		entity.setUsername(dto.getUserName());
		entity.setPassword(encoder.encode(dto.getPassword()));
		entity.setStatus(1);
		return entity;
	}
	public UserDTO toDTO(UserEntity entity) {
		UserDTO dto = new UserDTO();
		dto.setUserName(entity.getUsername());
		return dto;
	}
}
