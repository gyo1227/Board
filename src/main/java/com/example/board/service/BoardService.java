package com.example.board.service;

import java.util.List;

import javax.servlet.http.HttpSession;

import com.example.board.dto.BoardDTO;

public interface BoardService {

	// 게시글 작성
	public void create(HttpSession session, BoardDTO boardDTO) throws Exception;

	// 게시글 상세보기
	public BoardDTO read(int boardNum) throws Exception;
	
	// 게시글 수정
	public void update(BoardDTO boardDTO) throws Exception;
	
	// 게시글 삭제
	public void delete(int boardNum) throws Exception;
	
	// 게시글 전체 조회
	public List<BoardDTO> listAll() throws Exception;
	
	// 게시글 조회수 증가
	public void incViewCnt(int boardNum) throws Exception;
}
