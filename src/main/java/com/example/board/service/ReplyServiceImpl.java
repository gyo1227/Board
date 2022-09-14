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
	public int replyCnt(int boardNum) {
		return replyDAO.replyCnt(boardNum);
	}

	@Override
	public int writeReply(ReplyDTO replyDTO) {
		return replyDAO.writeReply(replyDTO);
	}

	@Override
	public int updateReply(ReplyDTO replyDTO) {
		return replyDAO.updateReply(replyDTO);
	}

	@Override
	public int deleteUpdate(int replyNum) {
		return replyDAO.deleteUpdate(replyNum);
	}

	@Override
	public int deleteReply(int replyNum) {
		return replyDAO.deleteReply(replyNum);
	}

}
