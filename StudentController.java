package com.techsymoposium.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import com.techsymoposium.model.Student;
import com.techsymoposium.repository.StudentRepository;
import com.techsymoposium.repository.StudentRepository;

@RestController
@RequestMapping("/api")
public class StudentController {
	
	@Autowired
	StudentRepository studentRepository;
	
	@PostMapping("/student")
	public String createNewStudent(@RequestBody  Student student) {
		try {
			studentRepository.save(student);
	        return "successful";
	    } catch (Exception e) {
	        return "Error creating student: " + e.getMessage();
	    }
	}
	@GetMapping("/student")
	public ResponseEntity<List<Student>> getAllStudents() {
		try {
	        List<Student>studentList=new ArrayList<Student>();
	        studentRepository.findAll().forEach(studentList::add);
	        return new ResponseEntity<List<Student>>(studentList,HttpStatus.OK);
	    } catch (Exception e) {
	       // return "Error creating event: " + e.getMessage();
return null ;
	    }
	}
}