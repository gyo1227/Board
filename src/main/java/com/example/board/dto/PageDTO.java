package com.example.board.dto;

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
public class PageDTO {
	
	private String userId;
	private int total;			// 게시글 전체 갯수
	private int curPage;		// 현재 페이지
	private int startPage;		// 시작 페이지
	private int endPage;		// 마지막 페이지
	private int firstBoardNum;	// 페이지의 첫번째 게시글 번호
	private boolean prev;		// 이전
	private boolean next;		// 다음
	private int amount;			// 한 페이지당 보여줄 게시글 수
	private String option;		// 검색 옵션
	private String query;		// 검색어
	
	
	public PageDTO() {
		super();
	}

	public PageDTO(String option, String query) {
		this.option = option;
		this.query = query;
	}
	
	public PageDTO(String userId, int total, int curPage) {
		super();
		this.userId = userId;
		this.amount = 10;
		this.curPage = curPage;
		this.total = total;
		
		this.endPage = (int)(Math.ceil(curPage / 10.0)) * 10;
		this.startPage = (int)(Math.ceil(endPage / 10.0) * 10) - 9;

		this.firstBoardNum = (curPage - 1) * amount;
		
		int realEnd = (int)(Math.ceil(total * 1.0 / amount));
		
		if(realEnd < this.endPage) {
			this.endPage = realEnd;
		}
		
		this.prev = this.startPage > 1;
		this.next = this.endPage < realEnd;
	}

	public PageDTO(int total, int curPage, String option, String query) {
		super();
		this.amount = 10;
		this.curPage = curPage;
		this.total = total;
		
		this.endPage = (int)(Math.ceil(curPage / 10.0)) * 10;
		this.startPage = (int)(Math.ceil(endPage / 10.0) * 10) - 9;

		this.firstBoardNum = (curPage - 1) * amount;
		
		int realEnd = (int)(Math.ceil(total * 1.0 / amount));
		
		if(realEnd < this.endPage) {
			this.endPage = realEnd;
		}
		
		this.prev = this.startPage > 1;
		this.next = this.endPage < realEnd;
		this.option = option;
		this.query = query;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}

	public int getCurPage() {
		return curPage;
	}

	public void setCurPage(int curPage) {
		this.curPage = curPage;
	}

	public int getStartPage() {
		return startPage;
	}

	public void setStartPage(int startPage) {
		this.startPage = startPage;
	}

	public int getEndPage() {
		return endPage;
	}

	public void setEndPage(int endPage) {
		this.endPage = endPage;
	}

	public int getFirstBoardNum() {
		return firstBoardNum;
	}

	public void setFirstBoardNum(int firstBoardNum) {
		this.firstBoardNum = firstBoardNum;
	}

	public boolean isPrev() {
		return prev;
	}

	public void setPrev(boolean prev) {
		this.prev = prev;
	}

	public boolean isNext() {
		return next;
	}

	public void setNext(boolean next) {
		this.next = next;
	}

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

	public String getOption() {
		return option;
	}

	public void setOption(String option) {
		this.option = option;
	}

	public String getQuery() {
		return query;
	}

	public void setQuery(String query) {
		this.query = query;
	}

	@Override
	public String toString() {
		return "PageDTO [userId=" + userId + ", total=" + total + ", curPage=" + curPage + ", startPage=" + startPage
				+ ", endPage=" + endPage + ", firstBoardNum=" + firstBoardNum + ", prev=" + prev + ", next=" + next
				+ ", amount=" + amount + ", option=" + option + ", query=" + query + "]";
	}

}
