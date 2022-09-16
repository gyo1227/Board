package com.example.board.dto;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;

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
public class ReplyDTO {
	
	private int replyNum;				// 댓글 번호
	private String userId;				// 댓글 작성자 아이디
	private String content;				// 댓글 내용
	private String nickName;			// 댓글 작성자 닉네임
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Seoul")
	private LocalDateTime regDate;		// 댓글 작성일
	private int depth;					// 댓글, 대댓글 구분
	private int replyGroup;				// 부모 댓글 번호
	private int deleteChk;				// 논리 삭제 여부
	private int boardNum;				// 작성된 댓글의 게시글 번호
	private String title;				// 작성된 댓글의 게시글 제목
	
	public ReplyDTO() {
		super();
	}

	public ReplyDTO(int replyNum, String userId, String content, String nickName, LocalDateTime regDate, int depth,
			int replyGroup, int deleteChk, int boardNum, String title) {
		super();
		this.replyNum = replyNum;
		this.userId = userId;
		this.content = content;
		this.nickName = nickName;
		this.regDate = regDate;
		this.depth = depth;
		this.replyGroup = replyGroup;
		this.deleteChk = deleteChk;
		this.boardNum = boardNum;
		this.title = title;
	}

	public int getReplyNum() {
		return replyNum;
	}



	public void setReplyNum(int replyNum) {
		this.replyNum = replyNum;
	}



	public String getUserId() {
		return userId;
	}



	public void setUserId(String userId) {
		this.userId = userId;
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



	public int getDepth() {
		return depth;
	}



	public void setDepth(int depth) {
		this.depth = depth;
	}



	public int getReplyGroup() {
		return replyGroup;
	}



	public void setReplyGroup(int replyGroup) {
		this.replyGroup = replyGroup;
	}



	public int getDeleteChk() {
		return deleteChk;
	}



	public void setDeleteChk(int deleteChk) {
		this.deleteChk = deleteChk;
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

	@Override
	public String toString() {
		return "ReplyDTO [replyNum=" + replyNum + ", userId=" + userId + ", content=" + content + ", nickName="
				+ nickName + ", regDate=" + regDate + ", depth=" + depth + ", replyGroup=" + replyGroup + ", deleteChk="
				+ deleteChk + ", boardNum=" + boardNum + ", title=" + title + "]";
	}
}
