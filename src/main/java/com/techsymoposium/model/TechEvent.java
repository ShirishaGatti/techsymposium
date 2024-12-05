package com.techsymoposium.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity 
@Table(name = "tech_event")
public class TechEvent {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "event_name")
    @NotNull
    private String eventName;

    private String eventDescription;

    private boolean fee;

    private int price;

    private String venue;

    private long coordinatorNo;

    private int password;

    // New field for image (you can store as a byte array or file path)
    @Lob
    @Column(name = "event_image",columnDefinition="BLOB")
    private byte[] eventImage; // Store image as binary data in the database

    // Optionally, if you want to store the file name
   
    // Getters and Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEventName() {
        return eventName;
    }

    public void setEventName(String eventName) {
        this.eventName = eventName;
    }

    public String getEventDescription() {
        return eventDescription;
    }

    public void setEventDescription(String eventDescription) {
        this.eventDescription = eventDescription;
    }

    public boolean isFee() {
        return fee;
    }

    public void setFee(boolean fee) {
        this.fee = fee;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getVenue() {
        return venue;
    }

    public void setVenue(String venue) {
        this.venue = venue;
    }

    public long getCoordinatorNo() {
        return coordinatorNo;
    }

    public void setCoordinatorNo(long coordinatorNo) {
        this.coordinatorNo = coordinatorNo;
    }

    public int getPassword() {
        return password;
    }

    public void setPassword(int password) {
        this.password = password;
    }

    public byte[] getEventImage() {
        return eventImage;
    }

    public void setEventImage(byte[] eventImage) {
        this.eventImage = eventImage;
    }

    

    // Default constructor
    public TechEvent() {}

    // Parameterized constructor
}
