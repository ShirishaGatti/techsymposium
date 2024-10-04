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

import com.techsymoposium.model.Event;
import com.techsymoposium.repository.EventRepository;

@RestController
@RequestMapping("/api")
public class EventController {
	
	@Autowired
	EventRepository eventRepository;
	
	@PostMapping("/event")
	public String createNewEvent(@RequestBody Event event) {
		try {
	        eventRepository.save(event);
	        return "successful";
	    } catch (Exception e) {
	        return "Error creating event: " + e.getMessage();
	    }
	}
	@GetMapping("/event")
	public ResponseEntity<List<Event>> getAllEvents() {
		try {
	        List<Event>eventList=new ArrayList<Event>();
	        eventRepository.findAll().forEach(eventList::add);
	        return new ResponseEntity<List<Event>>(eventList,HttpStatus.OK);
	    } catch (Exception e) {
	       // return "Error creating event: " + e.getMessage();
return null ;
	    }
	}
}