package com.techsymoposium.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.techsymoposium.model.Student;
import com.techsymoposium.model.StudentLogin;
import com.techsymoposium.repository.StudentRepository;

@RestController
@RequestMapping("/api")
public class StudentController {
	
	@Autowired
	StudentRepository studentRepository;
	
	@PostMapping("/s")
	public ResponseEntity<String> createNewStudent(
	        @RequestParam("studentName") String studentName,
	        @RequestParam("studentCollege") String studentCollege,
	        @RequestParam("studentEmail") String studentEmail,
	        @RequestParam("studentMno") String studentMno,
	        @RequestParam("studentYear") String studentYear,
	        @RequestParam("eventName") String eventName,
	        @RequestParam("transactionID") String transactionID,
	        @RequestParam("password") String password,
	        @RequestParam("paymentImage") MultipartFile paymentImage) {
	    try {
	        Student student = new Student();
	        student.setStudentName(studentName);
	        student.setStudentCollege(studentCollege);
	        student.setStudentEmail(studentEmail);
	        student.setStudentMno(studentMno);
	        student.setStudentYear(studentYear);
	        student.setEventName(eventName);
	        student.setTransactionID(transactionID);
	        student.setPassword(password);
	        
	        if (paymentImage != null && !paymentImage.isEmpty()) {
	            student.setPaymentImage(paymentImage.getBytes());
	        }
	        
	        studentRepository.save(student);
	        return ResponseEntity.status(HttpStatus.CREATED).body("Student created successfully");
	    } catch (Exception e) {
	        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error: " + e.getMessage());
	    }
	}
//
//	@GetMapping("/s/{id}/paymentImage")
//	public ResponseEntity<byte[]> getPaymentImage(@PathVariable("id") int id) {
//	    Optional<Student> studentOptional = studentRepository.findById(id);
//	    if (studentOptional.isPresent() && studentOptional.get().getPaymentImage() != null) {
//	        byte[] image = studentOptional.get().getPaymentImage();
//	        
//	        HttpHeaders headers = new HttpHeaders();
//	        headers.setContentType(MediaType.IMAGE_JPEG); // or IMAGE_PNG based on your image type
//	        headers.setContentLength(image.length);
//	        
//	        return new ResponseEntity<>(image, headers, HttpStatus.OK);
//	    } else {
//	        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//	    }
//	}


	
	@GetMapping("/s")
	public ResponseEntity<List<Student>> getAllStudents() {
	    try {
	        List<Student> studentList = new ArrayList<>();
	        studentRepository.findAll().forEach(student -> {
	            // Avoid sending the image data in the response
	            student.setPaymentImage(null);
	            studentList.add(student);
	        });
	        return new ResponseEntity<>(studentList, HttpStatus.OK);
	    } catch (Exception e) {
	        return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
	    }
	}


	
	 @PostMapping("/login")
	    public ResponseEntity<String> login(@RequestBody StudentLogin loginDetails) {
	        // Check if the student exists in the database with the provided id and password
	        Student student = studentRepository.findByIdAndPassword(loginDetails.getId(), loginDetails.getPassword());

	        if (student != null) {
	            // Login successful
	            return new ResponseEntity<>("Login successful! Welcome " + student.getStudentName(), HttpStatus.OK);
	        } else {
	            // Invalid credentials
	            return new ResponseEntity<>("Invalid login credentials!", HttpStatus.UNAUTHORIZED);
	        }
	    }
	 @DeleteMapping("/s/{id}")
	 public ResponseEntity<String> deleteStudent(@PathVariable("id") int id) {
	     try {
	         Optional<Student> studentOptional = studentRepository.findById(id);
	         
	         if (studentOptional.isPresent()) {
	             studentRepository.deleteById(id);  // Delete the student by ID
	             return ResponseEntity.status(HttpStatus.NO_CONTENT).body("Student deleted successfully");
	         } else {
	             return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Student not found");
	         }
	     } catch (Exception e) {
	         return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error: " + e.getMessage());
	     }
	 }

	 @PutMapping("/s/{id}")
	 public ResponseEntity<String> updateStudent(
	         @PathVariable("id") int id,
	         @RequestParam("studentName") String studentName,
	         @RequestParam("studentCollege") String studentCollege,
	         @RequestParam("studentEmail") String studentEmail,
	         @RequestParam("studentMno") String studentMno,
	         @RequestParam("studentYear") String studentYear,
	         @RequestParam("eventName") String eventName,
	         @RequestParam("transactionID") String transactionID,
	         @RequestParam("password") String password,
	         @RequestParam(value = "paymentImage", required = false) MultipartFile paymentImage) {
	     
	     try {
	         Optional<Student> studentOptional = studentRepository.findById(id);
	         
	         if (studentOptional.isPresent()) {
	             Student student = studentOptional.get();
	             student.setStudentName(studentName);
	             student.setStudentCollege(studentCollege);
	             student.setStudentEmail(studentEmail);
	             student.setStudentMno(studentMno);
	             student.setStudentYear(studentYear);
	             student.setEventName(eventName);
	             student.setTransactionID(transactionID);
	             student.setPassword(password);
	             
	             // Update the paymentImage if a new image is provided
	             if (paymentImage != null && !paymentImage.isEmpty()) {
	                 student.setPaymentImage(paymentImage.getBytes());
	             }
	             
	             studentRepository.save(student);
	             return ResponseEntity.status(HttpStatus.OK).body("Student updated successfully");
	         } else {
	             return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Student not found");
	         }
	     } catch (Exception e) {
	         return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error: " + e.getMessage());
	     }
	 }

}