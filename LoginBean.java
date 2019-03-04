package com.mvc.bean;

public class LoginBean {
	private String userName;
	private String password;
	private String Sataus;
	private int count;
	public String getSataus() {
		return Sataus;
	}

	public void setSataus(String sataus) {
		Sataus = sataus;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
}