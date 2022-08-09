package com.example.board.service;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.board.dao.UserDAO;
import com.example.board.dto.UserDTO;

@Service
public class UserServiceImpl implements UserService {

	private static final Logger log = LoggerFactory.getLogger(UserServiceImpl.class);
	
	@Autowired
	private UserDAO userDAO;
	
	public void setUserDAO(UserDAO userDAO) {
		this.userDAO = userDAO;
	}

	@Override
	public void join(UserDTO userDTO) throws Exception {
		log.info("join(UserDTO userDTO)");
		userDAO.join(userDTO);
	}

	@Override
	public int idCheck(String userId) throws Exception {
		log.info("idCheck(String userId) - {}", userId);
		return userDAO.idCheck(userId);
	}

	@Override
	public boolean login(UserDTO userDTO, HttpSession session) throws Exception {
		log.info("login(UserDTO userDTO) - {}", userDTO);
		boolean result = userDAO.login(userDTO);
		log.info("{}", result);
		if(result) {
			UserDTO loginUser = loginUserInfo(userDTO);
			
			session.setAttribute("userId", loginUser.getUserId());
			session.setAttribute("nickName", loginUser.getNickName());
		}
		
		return result;
	}

	@Override
	public UserDTO loginUserInfo(UserDTO userDTO) throws Exception {
		log.info("로그인한 회원 정보 - {}", userDTO);
		return userDAO.loginUserInfo(userDTO);
	}

	@Override
	public void logout(HttpSession session) throws Exception {
		log.info("로그아웃");
		session.invalidate();
	}


}
