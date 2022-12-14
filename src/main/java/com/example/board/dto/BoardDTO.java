package com.example.board.dto;

import java.time.LocalDateTime;

/*@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString*/
public class BoardDTO {

	private int boardNum;				// 게시글 번호
	private String title;				// 게시글 제목
	private String content;				// 게시글 내용
	private LocalDateTime regDate;		// 게시글 작성일
	private int viewCnt;				// 게시글 조회수
	private int replyCnt;				// 게시글 댓글수
	private int deleteChk;				// 게시글 삭제 여부
	private String userId;				// 게시글 작성자 아이디
	private String nickName;			// 게시글 작성자 닉네임
	
	public BoardDTO() {
		super();
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

	public int getDeleteChk() {
		return deleteChk;
	}

	public void setDeleteChk(int deleteChk) {
		this.deleteChk = deleteChk;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	@Override
	public String toString() {
		return "BoardDTO [boardNum=" + boardNum + ", title=" + title + ", content=" + content + ", regDate=" + regDate
				+ ", viewCnt=" + viewCnt + ", replyCnt=" + replyCnt + ", deleteChk=" + deleteChk + ", userId=" + userId
				+ ", nickName=" + nickName + "]";
	}

	
	
	
}
