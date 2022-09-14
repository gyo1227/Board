package com.example.board.service;

import java.util.List;

import com.example.board.dto.ReplyDTO;

public interface ReplyService {
	
	// 댓글 목록
	public List<ReplyDTO> list(int boardNum);
	
	// 댓글 갯수
	public int replyCnt(int boardNum);
	
	// 댓글 쓰기
	public int writeReply(ReplyDTO replyDTO);
	
	// 댓글 수정
	public int updateReply(ReplyDTO replyDTO);
	
	// 댓글 삭제(논리 삭제)
	public int deleteUpdate(int replyNum);

	// 댓글 삭제
	public int deleteReply(int replyNum);
}
