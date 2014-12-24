package com.geomarket.entity;

public class User {
	private String email, pwd, fname, lName, nric, contact, address, role;
	
	public User(){
		
	}
	
	public User(String email, String pwd, String fname, String lName,
			String nric, String contact, String address) {
		this.email = email;
		this.pwd = pwd;
		this.fname = fname;
		this.lName = lName;
		this.nric = nric;
		this.contact = contact;
		this.address = address;
		this.role = role;
	}
	
	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPwd() {
		return pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}

	public String getFname() {
		return fname;
	}

	public void setFname(String fname) {
		this.fname = fname;
	}

	public String getlName() {
		return lName;
	}

	public void setlName(String lName) {
		this.lName = lName;
	}

	public String getNric() {
		return nric;
	}

	public void setNric(String nric) {
		this.nric = nric;
	}

	public String getContact() {
		return contact;
	}

	public void setContact(String contact) {
		this.contact = contact;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}
	
	
}
