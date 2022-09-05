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
	public boolean join(UserDTO userDTO) throws Exception {
		return userDAO.join(userDTO);
	}

	@Override
	public int idCheck(String userId) throws Exception {
		return userDAO.idCheck(userId);
	}

	@Override
	public int nickNameCheck(String nickName) throws Exception {
		return userDAO.nickNameCheck(nickName);
	}

	@Override
	public boolean login(UserDTO userDTO) throws Exception {
		return userDAO.login(userDTO);
	}

	@Override
	public UserDTO loginUserInfo(String userId) throws Exception {
		return userDAO.loginUserInfo(userId);
	}

	@Override
	public void logout(HttpSession session) throws Exception {
		session.invalidate();
	}
	
	@Override
	public void changePw(UserDTO userDTO) throws Exception {
		userDAO.changePw(userDTO);
	}
	
	@Override
	public void changeNickName(UserDTO userDTO) throws Exception {
		userDAO.changeNickName(userDTO);
	}
	
	@Override
	public void deleteUser(String userId) throws Exception {
		userDAO.deleteUser(userId);
	}

}
