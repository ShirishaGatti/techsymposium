package com.techsymoposium.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

@Entity
public class Student {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	

	@Column(name ="student_name")
	//@Index(name="IndexStudentName", columnList = "")
	@NotNull
	private String studentName;
	
	@Column(name = "student_college")
	//@Index(name="IndexStudentCollege")
@NotNull
	private String  studentCollege;

	@Email
	@Column(name = "student_email")
	@Email
	private String  studentEmail;
	
	@Column(name = "student_Mno")
	//@NotNull
	private String  studentMno;	

	@Column(name = "student_Year")
	@NotNull
	private String  studentYear;
	
	@Column(name ="event_name")
	@NotNull
	//@Index(name="IndexEventName")
	public String eventName;
	
	
	@Column(unique = true, nullable = false)
	private String transactionID;
	
	@Column(name = "password")
	private String password;

	 @Lob
	 @Column(name = "payment_image",columnDefinition="BLOB")
	 private byte[] paymentImage;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getStudentName() {
		return studentName;
	}

	public void setStudentName(String studentName) {
		this.studentName = studentName;
	}

	public String getStudentCollege() {
		return studentCollege;
	}

	public void setStudentCollege(String studentCollege) {
		this.studentCollege = studentCollege;
	}

	public String getStudentEmail() {
		return studentEmail;
	}

	public void setStudentEmail(String studentEmail) {
		this.studentEmail = studentEmail;
	}

	public String getStudentMno() {
		return studentMno;
	}

	public void setStudentMno(String studentMno) {
		this.studentMno = studentMno;
	}

	public String getStudentYear() {
		return studentYear;
	}

	public void setStudentYear(String studentYear) {
		this.studentYear = studentYear;
	}

	public String getEventName() {
		return eventName;
	}

	public void setEventName(String eventName) {
		this.eventName = eventName;
	}

	public String getTransactionID() {
		return transactionID;
	}

	public void setTransactionID(String transactionID) {
		this.transactionID = transactionID;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public byte[] getPaymentImage() {
		return paymentImage;
	}

	public void setPaymentImage(byte[] paymentImage) {
		this.paymentImage = paymentImage;
	}

	public Student() {
		//super();
		// TODO Auto-generated constructor stub
	} 
	
}
