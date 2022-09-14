package com.example.board.dao;

import java.util.List;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.example.board.dto.ReplyDTO;

@Repository
public class ReplyDAOImpl implements ReplyDAO {

	private static final Logger log = LoggerFactory.getLogger(ReplyDAOImpl.class);
	
	@Inject
	private SqlSession sqlsession;
	
	public void setSqlsession(SqlSession sqlsession) {
		this.sqlsession = sqlsession;
	}
	
	private final String NAMESPACE = "com.example.board.mappers.ReplyMapper";
	
	@Override
	public List<ReplyDTO> list(int boardNum) {
		return sqlsession.selectList(NAMESPACE + ".read", boardNum);
	}
	
	@Override
	public int replyCnt(int boardNum) {
		return sqlsession.selectOne(NAMESPACE + ".count", boardNum);
	}

	@Override
	public int writeReply(ReplyDTO replyDTO) {
		return sqlsession.insert(NAMESPACE + ".writeReply", replyDTO);
	}

	@Override
	public int updateReply(ReplyDTO replyDTO) {
		return sqlsession.update(NAMESPACE + ".updateReply", replyDTO);
		
	}

	@Override
	public int deleteUpdate(int replyNum) {
		return sqlsession.update(NAMESPACE + ".deleteUpdate", replyNum);
		
	}

	@Override
	public int deleteReply(int replyNum) {
		return sqlsession.delete(NAMESPACE + ".deleteReply", replyNum);
		
	}

}
