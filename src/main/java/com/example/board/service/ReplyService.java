package com.example.board.service;

import java.util.List;

import com.example.board.dto.ReplyDTO;

public interface ReplyService {
	
	// 댓글 목록
	public List<ReplyDTO> list(int boardNum);
	
	// 댓글 쓰기
	public int create(ReplyDTO replyDTO);

	// 댓글 수정
	public void update(ReplyDTO replyDTO);
	
	// 댓글 삭제
	public void delete(int replyNum);
}
