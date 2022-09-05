package com.example.board.dao;

import java.util.List;

import javax.inject.Inject;

import org.apache.ibatis.session.RowBounds;
import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.example.board.dto.BoardDTO;
import com.example.board.dto.PageDTO;

@Repository
public class BoardDAOImpl implements BoardDAO{

	private static final Logger log = LoggerFactory.getLogger(BoardDAOImpl.class);
	
	@Inject
	private SqlSession sqlsession;
	
	public void setSqlsession(SqlSession sqlsession) {
		this.sqlsession = sqlsession;
	}
	
	private final String NAMESPACE = "com.example.board.mappers.BoardMapper";

	@Override
	public void create(BoardDTO boardDTO) throws Exception {
		sqlsession.insert(NAMESPACE + ".create", boardDTO);
	}

	@Override
	public BoardDTO read(int boardNum) throws Exception {
		return sqlsession.selectOne(NAMESPACE + ".read", boardNum);
	}

	@Override
	public void update(BoardDTO boardDTO) throws Exception {
		sqlsession.update(NAMESPACE + ".update", boardDTO);
	}

	@Override
	public void delete(int boardNum) throws Exception {
		sqlsession.delete(NAMESPACE + ".delete", boardNum);
	}

	@Override
	public List<BoardDTO> list(PageDTO pageDTO) throws Exception {
		return sqlsession.selectList(NAMESPACE + ".list", pageDTO);
	}
	
	@Override
	public void incViewCnt(int boardNum) throws Exception {
		sqlsession.update(NAMESPACE + ".incViewCnt", boardNum);
	}

	@Override
	public int total(PageDTO pageDTO) throws Exception {
		return sqlsession.selectOne(NAMESPACE + ".total", pageDTO);
	}

}
