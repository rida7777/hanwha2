package com.hanwha.myapp;

public class UserDTO {
	Integer userid;
	String username;
	
	public Integer getUserid() {
		return userid;
	}
	public void setUserid(Integer userid) {
		this.userid = userid;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	@Override
	public String toString() {
		return "UserDTO [userid=" + userid + ", username=" + username + "]";
	}
	
	
}
