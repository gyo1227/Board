package com.example.board.service;

import com.example.board.dto.UserDTO;

public interface UserService {

	// 회원가입
	public void join(UserDTO userDTO) throws Exception;
	
	// 아이디 중복 체크
	public int idCheck(String userId) throws Exception;
	
	// 로그인
	
	
}
