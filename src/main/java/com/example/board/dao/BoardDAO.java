package com.example.board.dao;

import java.util.List;

import com.example.board.dto.BoardDTO;
import com.example.board.dto.PageDTO;

public interface BoardDAO {
	
	// 게시글 작성
	public void create(BoardDTO boardDTO) throws Exception;

	// 게시글 상세보기
	public BoardDTO read(int boardNum) throws Exception;
	
	// 게시글 수정
	public void update(BoardDTO boardDTO) throws Exception;
	
	// 게시글 삭제
	public void delete(int boardNum) throws Exception;
	
	// 게시글 전체 조회
	public List<BoardDTO> list(PageDTO pageDTO) throws Exception;
	
	// 게시글 조회수 증가
	public void incViewCnt(int boardNum) throws Exception;
	
	// 게시글 전체 갯수
	public int total(PageDTO pageDTO) throws Exception;
}
