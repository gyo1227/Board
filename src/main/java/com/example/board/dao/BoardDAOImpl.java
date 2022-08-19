package com.example.board.dao;

import java.util.List;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.example.board.dto.BoardDTO;

@Repository
public class BoardDAOImpl implements BoardDAO{

	private static final Logger log = LoggerFactory.getLogger(BoardDAOImpl.class);
	
	@Inject
	private SqlSession sqlsession;
	
	public void setSqlsession(SqlSession sqlsession) {
		this.sqlsession = sqlsession;
	}
	
	private final String NAMESPACE = "com.example.board.mappers.BoardMapper";

	// 게시글 작성
	@Override
	public void create(BoardDTO boardDTO) throws Exception {
		sqlsession.insert(NAMESPACE + ".create", boardDTO);
	}

	// 게시글 상세보기
	@Override
	public BoardDTO read(int boardNum) throws Exception {
		return sqlsession.selectOne(NAMESPACE + ".read", boardNum);
	}

	// 게시글 수정
	@Override
	public void update(BoardDTO boardDTO) throws Exception {
		sqlsession.update(NAMESPACE + ".update", boardDTO);
	}

	// 게시글 삭제
	@Override
	public void delete(int boardNum) throws Exception {
		sqlsession.delete(NAMESPACE + ".delete", boardNum);
	}

	// 게시글 전체 조회
	@Override
	public List<BoardDTO> listAll() throws Exception {
		return sqlsession.selectList(NAMESPACE + ".listAll");
	}
	
	// 게시글 조회수 증가
	@Override
	public void incViewCnt(int boardNum) throws Exception {
		sqlsession.update(NAMESPACE + ".incViewCnt", boardNum);
	}

}
