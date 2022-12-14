package com.example.board.dto;

/*@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString*/
public class UserDTO {
	
	private String userId;
	private String userPw;
	private String nickName;
	
	public UserDTO() {
		super();
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getUserPw() {
		return userPw;
	}

	public void setUserPw(String userPw) {
		this.userPw = userPw;
	}

	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	@Override
	public String toString() {
		return "UserDTO [userId=" + userId + ", userPw=" + userPw + ", nickName=" + nickName + "]";
	}
	
	
	
	
	
	
}
