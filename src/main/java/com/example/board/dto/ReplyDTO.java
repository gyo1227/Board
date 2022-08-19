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
	private String replyContent;		// 댓글 내용
	private String replyUserId;			// 댓글 작성자 아이디
	private String replyNickName;		// 댓글 작성자 닉네임
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Seoul")
	private LocalDateTime replyRegDate;	// 댓글 작성일
	private int boardNum;				// 작성된 댓글의 게시글 번호
	
	public ReplyDTO() {
		super();
	}

	public ReplyDTO(int replyNum, String replyContent, String replyUserId, String replyNickName,
			LocalDateTime replyRegDate, int boardNum) {
		super();
		this.replyNum = replyNum;
		this.replyContent = replyContent;
		this.replyUserId = replyUserId;
		this.replyNickName = replyNickName;
		this.replyRegDate = replyRegDate;
		this.boardNum = boardNum;
	}

	public int getReplyNum() {
		return replyNum;
	}

	public void setReplyNum(int replyNum) {
		this.replyNum = replyNum;
	}

	public String getReplyContent() {
		return replyContent;
	}

	public void setReplyContent(String replyContent) {
		this.replyContent = replyContent;
	}

	public String getReplyUserId() {
		return replyUserId;
	}

	public void setReplyUserId(String replyUserId) {
		this.replyUserId = replyUserId;
	}

	public String getReplyNickName() {
		return replyNickName;
	}

	public void setReplyNickName(String replyNickName) {
		this.replyNickName = replyNickName;
	}

	public LocalDateTime getReplyRegDate() {
		return replyRegDate;
	}

	public void setReplyRegDate(LocalDateTime replyRegDate) {
		this.replyRegDate = replyRegDate;
	}

	public int getBoardNum() {
		return boardNum;
	}

	public void setBoardNum(int boardNum) {
		this.boardNum = boardNum;
	}

	@Override
	public String toString() {
		return "ReplyDTO [replyNum=" + replyNum + ", replyContent=" + replyContent + ", replyUserId=" + replyUserId
				+ ", replyNickName=" + replyNickName + ", replyRegDate=" + replyRegDate + ", boardNum=" + boardNum
				+ "]";
	}

}
