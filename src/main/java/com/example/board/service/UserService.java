package com.example.board.service;

import javax.servlet.http.HttpSession;

import com.example.board.dto.UserDTO;

public interface UserService {

	// 회원가입
	public void join(UserDTO userDTO) throws Exception;
	
	// 아이디 중복 체크
	public int idCheck(String userId) throws Exception;
	
	// 로그인
	public boolean login(UserDTO userDTO, HttpSession session) throws Exception;
	
	// 로그인한 회원 정보
	public UserDTO loginUserInfo(UserDTO userDTO) throws Exception;
	
	// 로그아웃
	public void logout(HttpSession session) throws Exception;
	
}
