package com.project.center.user;

public class User {
	
	private String code;
	private String name;
	private String birth;
	private String id;
	private String pw;
	private String gender;
	private String tel;
	private String group;
	private String address;
	private int type;


	public User(String code, String name, String birth, String id, String pw, String gender, String tel, String group,
			String address) {
		this.code = code;
		this.name = name;
		this.birth = birth;
		this.id = id;
		this.pw = pw;
		this.gender = gender;
		this.tel = tel;
		this.group = group;
		this.address = address;
	}
	
	public User(String id, String pw) {
		this.id = id;
		this.pw = pw;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getBirth() {
		return birth;
	}

	public void setBirth(String birth) {
		this.birth = birth;
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

	public String getGroup() {
		return group;
	}

	public void setGroup(String group) {
		this.group = group;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}
	
	
	
}
