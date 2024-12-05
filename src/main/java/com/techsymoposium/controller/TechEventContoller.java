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
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.techsymoposium.model.TechEvent;
import com.techsymoposium.repository.TechEventRepository;

@RestController
@RequestMapping("/api")
public class TechEventContoller {

    @Autowired
    TechEventRepository techEventRepository;
    
    // Create new event with image upload
    @PostMapping("/event")
    public ResponseEntity<String> createNewEvent(
        @RequestParam("eventName") String eventName,
        @RequestParam("eventDescription") String eventDescription,
        @RequestParam("fee") boolean fee,
        @RequestParam("price") int price,
        @RequestParam("venue") String venue,
        @RequestParam("coordinatorNo") long coordinatorNo,
        @RequestParam("password") int password,
        @RequestParam("eventImage") MultipartFile eventImage) {

        try {
            // Convert the image file to byte array
            byte[] imageBytes = eventImage.getBytes();

            // Create TechEvent instance
            TechEvent techEvent = new TechEvent();
            techEvent.setEventName(eventName);
            techEvent.setEventDescription(eventDescription);
            techEvent.setFee(fee);
            techEvent.setPrice(price);
            techEvent.setVenue(venue);
            techEvent.setCoordinatorNo(coordinatorNo);
            techEvent.setPassword(password);
            techEvent.setEventImage(imageBytes);

            // Save the event to the repository
            techEventRepository.save(techEvent);

            return new ResponseEntity<>("Event created successfully", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Error creating event: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

//    @GetMapping("/event/{id}/image")
//    public ResponseEntity<byte[]> getEventImage(@PathVariable("id") int id) {
//        Optional<TechEvent> techEvent = techEventRepository.findById(id);
//        
//        if (techEvent.isPresent() && techEvent.get().getEventImage() != null) {
//            byte[] image = techEvent.get().getEventImage();
//            
//            HttpHeaders headers = new HttpHeaders();
//            headers.setContentType(MediaType.IMAGE_JPEG); // assuming the image is JPEG, change it accordingly if different type
//            headers.setContentLength(image.length);
//            
//            return new ResponseEntity<>(image, headers, HttpStatus.OK);
//        } else {
//            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//        }
//    }
    // Get all events
    @GetMapping("/event")
    public ResponseEntity<List<TechEvent>> getAllEvents() {
        try {
            List<TechEvent> techEventList = new ArrayList<>();
            techEventRepository.findAll().forEach(techEventList::add);
            return new ResponseEntity<>(techEventList, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    // Delete an event by id
    @DeleteMapping("/event/{id}")
    public ResponseEntity<String> deleteEvent(@PathVariable("id") int id) {
        try {
            Optional<TechEvent> techEvent = techEventRepository.findById(id);
            if (techEvent.isPresent()) {
                techEventRepository.deleteById(id);
                return new ResponseEntity<>("Event deleted successfully", HttpStatus.OK);
            } else {
                return new ResponseEntity<>("Event not found", HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            return new ResponseEntity<>("Error deleting event: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Update an existing event with image
    @PutMapping("/event/{id}")
    public ResponseEntity<String> updateEvent(@PathVariable("id") int id, 
                                              @RequestParam("image") MultipartFile file, 
                                              @Valid @RequestBody TechEvent updatedTechEvent) {
        try {
            Optional<TechEvent> techEventOptional = techEventRepository.findById(id);
            if (techEventOptional.isPresent()) {
                TechEvent existingTechEvent = techEventOptional.get();
                // Update event details
                existingTechEvent.setEventName(updatedTechEvent.getEventName());
                existingTechEvent.setEventDescription(updatedTechEvent.getEventDescription());

                // Update image if provided
                if (!file.isEmpty()) {
                    byte[] eventImage = file.getBytes();
                    String imageName = file.getOriginalFilename();
                    existingTechEvent.setEventImage(eventImage); // Set new image
        //            existingTechEvent.setImageName(imageName); // Set new image name
                }

                techEventRepository.save(existingTechEvent);
                return new ResponseEntity<>("Event updated successfully", HttpStatus.OK);
            } else {
                return new ResponseEntity<>("Event not found", HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            return new ResponseEntity<>("Error updating event: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
