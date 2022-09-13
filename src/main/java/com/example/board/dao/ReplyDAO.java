package com.example.board.dao;

import java.util.List;

import com.example.board.dto.ReplyDTO;

public interface ReplyDAO {
	
	// 댓글 목록
	public List<ReplyDTO> list(int boardNum);
	
	// 댓글 갯수
	public int replyCnt(int boardNum);
	
	// 댓글 쓰기
	public int writeReply(ReplyDTO replyDTO);
	
	// 댓글 수정
	public void update(ReplyDTO replyDTO);
	
	// 댓글 삭제
	public void delete(int replyNum);
	
}
