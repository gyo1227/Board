package com.example.board.dao;

import java.util.List;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.example.board.dto.BoardDTO;
import com.example.board.dto.PageDTO;
import com.example.board.dto.UserDTO;

@Repository
public class UserDAOImpl implements UserDAO {

//	private static final Logger log = LoggerFactory.getLogger(UserDAOImpl.class);
	
	@Inject
	private SqlSession sqlsession;
	
	public void setSqlsession(SqlSession sqlsession) {
		this.sqlsession = sqlsession;
	}
	
	private final String NAMESPACE = "com.example.board.mappers.UserMapper";
	
	@Override
	public boolean join(UserDTO userDTO) throws Exception {
		if(sqlsession.insert(NAMESPACE + ".join", userDTO) != 0) {
			return true;
		}
		return false;
	}

	@Override
	public int idCheck(String userId) throws Exception {
		return sqlsession.selectOne(NAMESPACE + ".idCheck", userId);
	}

	@Override
	public int nickNameCheck(String nickName) throws Exception {
		return sqlsession.selectOne(NAMESPACE + ".nickNameCheck", nickName);
	}

	@Override
	public boolean login(UserDTO userDTO) throws Exception {
		String userId = sqlsession.selectOne(NAMESPACE + ".login", userDTO);
		return (userId != null) ? true : false;
	}

	@Override
	public UserDTO loginUserInfo(String userId) throws Exception {
		return sqlsession.selectOne(NAMESPACE + ".loginUserInfo", userId);
	}
	
	@Override
	public void changePw(UserDTO userDTO) throws Exception {
		sqlsession.update(NAMESPACE + ".changePw", userDTO);
	}
	
	@Override
	public void changeNickName(UserDTO userDTO)throws Exception {
		sqlsession.update(NAMESPACE + ".changeNickName", userDTO);
	}
	
	@Override
	public void deleteUser(String userId) throws Exception {
		sqlsession.delete(NAMESPACE + ".deleteUser", userId);
	}
	
	@Override
	public int boardTotal(String userId) throws Exception {
		return sqlsession.selectOne(NAMESPACE + ".boardTotal", userId);
	}

	@Override
	public List<BoardDTO> boardList(PageDTO pageDTO) throws Exception {
		return sqlsession.selectList(NAMESPACE + ".boardList", pageDTO);
	}

	@Override
	public int replyTotal(String userId) throws Exception {
		return sqlsession.selectOne(NAMESPACE + ".replyTotal", userId);
	}
	
	@Override
	public List<BoardDTO> replyList(PageDTO pageDTO) throws Exception {
		return sqlsession.selectList(NAMESPACE + ".replyList", pageDTO);
	}
}
