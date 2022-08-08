package com.example.board.dao;

import com.example.board.dto.UserDTO;

public interface UserDAO {
	
	// 회원가입
	public void join(UserDTO userDTO) throws Exception;

	// 아이디 중복 체크
	public int idCheck(String userId) throws Exception;
	
}
