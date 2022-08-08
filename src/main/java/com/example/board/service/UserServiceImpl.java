package com.example.board.service;

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

//	@Override
//	public void create(BoardDTO boardDTO) throws Exception {
//		boardDAO.create(boardDTO);
//	}



}
