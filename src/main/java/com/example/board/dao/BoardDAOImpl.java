package com.example.board.dao;

import java.util.List;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.example.board.controller.BoardController;
import com.example.board.dto.BoardDTO;

@Repository
public class BoardDAOImpl implements BoardDAO{

	private static final Logger log = LoggerFactory.getLogger(BoardController.class);
	
	@Inject
	private SqlSession sqlsession;
	
	public void setSqlsession(SqlSession sqlsession) {
		this.sqlsession = sqlsession;
	}

	// 게시글 작성
	@Override
	public void create(BoardDTO boardDTO) throws Exception {
		log.info("{}",boardDTO);
		sqlsession.insert("BoardMapper.create", boardDTO);
	}

	// 게시글 상세보기
	@Override
	public void read(int boardNum) throws Exception {
		// TODO Auto-generated method stub
		
	}

	// 게시글 수정
	@Override
	public void update(BoardDTO boardDTO) throws Exception {
		// TODO Auto-generated method stub
		
	}

	// 게시글 삭제
	@Override
	public void delete(int boardNum) throws Exception {
		// TODO Auto-generated method stub
		
	}

	// 게시글 전체 조회
	@Override
	public List<BoardDTO> listAll() throws Exception {
		return sqlsession.selectList("BoardMapper.listAll");
	}
	
	// 게시글 조회수 증가
	@Override
	public void incViewCnt(int boardNum) throws Exception {
		// TODO Auto-generated method stub
		
	}

}
