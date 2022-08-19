package com.example.board.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.board.dao.ReplyDAO;
import com.example.board.dto.ReplyDTO;

@Service
public class ReplyServiceImpl implements ReplyService {

	@Autowired
	private ReplyDAO replyDAO;
	
	public void setReplyDAO(ReplyDAO replyDAO) {
		this.replyDAO = replyDAO;
	}

	@Override
	public List<ReplyDTO> list(int boardNum) {
		return replyDAO.list(boardNum);
	}

	@Override
	public int create(ReplyDTO replyDTO) {
		return replyDAO.create(replyDTO);
	}

	@Override
	public void update(ReplyDTO replyDTO) {
		replyDAO.update(replyDTO);
	}

	@Override
	public void delete(int replyNum) {
		replyDAO.delete(replyNum);
	}

}
