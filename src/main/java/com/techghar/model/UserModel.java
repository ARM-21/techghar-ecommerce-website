package com.techghar.model;

import java.sql.Date;
import java.time.LocalDateTime;

public class UserModel {
	private String firstName;
	private String lastName;
	private String email;
	private String password;
	private String phone;
	private String address;
    private LocalDateTime createdAt;
	private String username;
	private Date dob;
	private String gender;
	
	public LocalDateTime getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}

	

	public UserModel() {

	}

	public UserModel(String firstName, String lastName, String email, String password, String phone, String address,
			String username, Date dob, String gender) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.password = password;
		this.phone = phone;
		this.address = address;

		this.username = username;
		this.dob = dob;
		this.gender = gender;
	}

	// Getters and Setters
	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public java.sql.Date getDob() {
		return (java.sql.Date) dob;
	}

	public void setDob(Date dob) {
		this.dob = dob;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	@Override
	public String toString() {
		return "UserModel{" + "firstName='" + firstName + '\'' + ", lastName='" + lastName + '\'' + ", email='" + email
				+ '\'' + ", phone='" + phone + '\'' + ", address='" + address + '\'' + ", username='" + username + '\''
				+ ", dob='" + dob + '\'' + ", gender='" + gender + '\'' + '}';
	}
}
