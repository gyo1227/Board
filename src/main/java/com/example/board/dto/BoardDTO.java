package com.example.board.dto;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class BoardDTO {

	private int boardNum;				// 게시글 번호
	private String title;				// 게시글 제목
	private String content;				// 게시글 내용
	private String nickName;			// 게시글 작성자
	private LocalDateTime regDate;		// 게시글 작성일
	private int viewCnt;				// 게시글 조회수
	private int replyCnt;				// 게시글 댓글수
	
	public BoardDTO() {
		super();
	}
	
	public BoardDTO(int boardNum, String title, String content, String nickName, LocalDateTime regDate, int viewCnt,
			int replyCnt) {
		super();
		this.boardNum = boardNum;
		this.title = title;
		this.content = content;
		this.nickName = nickName;
		this.regDate = regDate;
		this.viewCnt = viewCnt;
		this.replyCnt = replyCnt;
	}

	public int getBoardNum() {
		return boardNum;
	}
	public void setBoardNum(int boardNum) {
		this.boardNum = boardNum;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getNickName() {
		return nickName;
	}
	public void setNickName(String nickName) {
		this.nickName = nickName;
	}
	public LocalDateTime getRegDate() {
		return regDate;
	}
	public void setRegDate(LocalDateTime regDate) {
		this.regDate = regDate;
	}
	public int getViewCnt() {
		return viewCnt;
	}
	public void setViewCnt(int viewCnt) {
		this.viewCnt = viewCnt;
	}
	public int getReplyCnt() {
		return replyCnt;
	}
	public void setReplyCnt(int replyCnt) {
		this.replyCnt = replyCnt;
	}
	@Override
	public String toString() {
		return "BoardDTO [boardNum=" + boardNum + ", title=" + title + ", content=" + content + ", nickName=" + nickName
				+ ", regDate=" + regDate + ", viewCnt=" + viewCnt + ", replyCnt=" + replyCnt + "]";
	}
	
	
	
	
}
