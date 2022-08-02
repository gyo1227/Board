package com.example.board.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.board.dao.BoardDAO;
import com.example.board.dto.BoardDTO;

@Service
public class BoardServiceImpl implements BoardService {

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
		return boardDAO.read(boardNum);
	}

	@Override
	public void update(BoardDTO boardDTO) throws Exception {
		boardDAO.update(boardDTO);
	}

	@Override
	public void delete(int boardNum) throws Exception {
		boardDAO.delete(boardNum);
	}

	@Override
	public List<BoardDTO> listAll() throws Exception {
		return boardDAO.listAll();
	}

	@Override
	public void incViewCnt(int boardNum) throws Exception {
		// TODO Auto-generated method stub

	}

}
