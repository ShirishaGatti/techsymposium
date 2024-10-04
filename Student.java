package com.techsymoposium.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Student {
	@Id
  //  @GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	
	//@Column(name ="student_id")
	//private int studentID;
	
	@Column(name ="student_name")
	private String studentName;
	
	@Column(name = " student_college")
	private String  studentCollege;

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

	public Student() {
		
	}
	
}
