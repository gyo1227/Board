package com.example.board.dao;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.example.board.dto.UserDTO;

@Repository
public class UserDAOImpl implements UserDAO {

	private static final Logger log = LoggerFactory.getLogger(UserDAOImpl.class);
	
	@Inject
	private SqlSession sqlsession;
	
	public void setSqlsession(SqlSession sqlsession) {
		this.sqlsession = sqlsession;
	}
	
	private final String NAMESPACE = "com.example.board.mappers.UserMapper";
	
	// 회원가입
	@Override
	public void join(UserDTO userDTO) throws Exception {
		log.info("회원가입 처리 - {}", userDTO);
		sqlsession.insert(NAMESPACE + ".join", userDTO);
	}

	// 아이디 중복 체크
	@Override
	public int idCheck(String userId) throws Exception {
		log.info("아이디 중복 체크 - {}", userId);
		return sqlsession.selectOne(NAMESPACE + ".idCheck", userId);
	}

	// 로그인
	@Override
	public boolean login(UserDTO userDTO) throws Exception {
		log.info("로그인 - {}", userDTO);
		String userId = sqlsession.selectOne(NAMESPACE + ".login", userDTO);
		return (userId != null) ? true : false;
	}

	@Override
	public UserDTO loginUserInfo(UserDTO userDTO) throws Exception {
		log.info("로그인한 회원 정보 - {}", userDTO);
		return sqlsession.selectOne(NAMESPACE + ".loginUserInfo", userDTO);
	}

}
