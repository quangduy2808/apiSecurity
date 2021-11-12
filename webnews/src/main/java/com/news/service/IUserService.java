package com.news.service;

import com.news.dto.UserDTO;

public interface IUserService {
	UserDTO register(UserDTO dto) throws Exception;
}
