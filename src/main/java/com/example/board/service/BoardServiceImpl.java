package com.example.board.service;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.board.controller.BoardController;
import com.example.board.dao.BoardDAO;
import com.example.board.dto.BoardDTO;
import com.example.board.dto.PageDTO;

@Service
public class BoardServiceImpl implements BoardService {

	private static final Logger log = LoggerFactory.getLogger(BoardServiceImpl.class);
	
	@Autowired
	private BoardDAO boardDAO;

	public void setBoardDAO(BoardDAO boardDAO) {
		this.boardDAO = boardDAO;
	}

	@Override
	public void create(BoardDTO boardDTO) throws Exception {
		boardDAO.create(boardDTO);
	}

	@Override
	public BoardDTO read(int boardNum) throws Exception {
		boardDAO.incViewCnt(boardNum);
		return boardDAO.read(boardNum);
	}

	@Override
	public void update(HttpSession session, BoardDTO boardDTO) throws Exception {
		boardDAO.update(boardDTO);
	}

	@Override
	public void delete(HttpSession session, int boardNum) throws Exception {
		boardDAO.delete(boardNum);
	}

	@Override
	public List<BoardDTO> list(PageDTO pageDTO) throws Exception {
		return boardDAO.list(pageDTO);
	}
	
	@Override
	public int total(PageDTO pageDTO) throws Exception {
		return boardDAO.total(pageDTO);
	}

}
