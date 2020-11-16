package com.project.center.user;

public class User {
	
	String code;
	String id;
	String pw;
	String birth;
	String gender;
	String tel;
	String address;
	String group;
	

	
	public User(String code, String id, String pw, String birth, String gender, String tel, String address, String group) {
		this.code = code;
		this.id = id;
		this.pw = pw;
		this.birth = birth;
		this.gender = gender;
		this.tel = tel;
		this.address = address;
		this.group = group;
	}
	
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getPw() {
		return pw;
	}
	public void setPw(String pw) {
		this.pw = pw;
	}
	public String getBirth() {
		return birth;
	}
	public void setBirth(String birth) {
		this.birth = birth;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getTel() {
		return tel;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getGroup() {
		return group;
	}
	public void setGroup(String group) {
		this.group = group;
	}
}
