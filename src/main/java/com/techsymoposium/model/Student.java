package com.techsymoposium.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Email;

@Entity
public class Student {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	

	@Column(name ="student_name")
//	@Index(name="IndexStudentName")
	//@NotNull
	private String studentName;
	
	@Column(name = " student_college")
	//@Index(name="IndexStudentCollege")
	//@NotNull
	private String  studentCollege;

	@Column(name = " student_email")
	//@NotNull
	@Email
	private String  studentEmail;
	
	@Column(name = " student_Mno")
	//@NotNull
	private int  studentMno;
	
	@Override
	public String toString() {
		return "Student [id=" + id + ", studentName=" + studentName + ", studentCollege=" + studentCollege
				+ ", studentEmail=" + studentEmail + ", studentMno=" + studentMno + ", studentYear=" + studentYear
				+ ", eventName=" + eventName + "]";
	}

	@Column(name = " student_Year")
	private String  studentYear;
	
	@Column(name ="event_name")
//	@NotNull
	//@Index(name="IndexEventName")
	public String eventName;
}
