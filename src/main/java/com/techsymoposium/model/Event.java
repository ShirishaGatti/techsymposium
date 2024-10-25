package com.techsymoposium.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Lob;
@Entity(name ="event")
public class Event {
	 @Id
	    //@GeneratedValue(strategy = GenerationType.IDENTITY)
	    private int id;

	   // @NotNull
	    @Column(name = "event_name")
	    private String eventName;

	   // @NotNull
	    @Column(name = "event_description")
	    private String eventDescription;

	   // @NotNull
	    @Column(name = "event_venue")
	    private String eventVenue;

	   // @NotNull
	    @Column(name = "pass")
	    private String pass;

	    @Lob
	    @Column(name = "event_image")
	    private String eventImage;

	   // @NotNull
	    @Column(name = "coordinator_mno")
	    private int coordinatorMno;

	    @Column(name = "event_price")
	    private int price;

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

		public String getEventVenue() {
			return eventVenue;
		}

		public void setEventVenue(String eventVenue) {
			this.eventVenue = eventVenue;
		}

		public String getPass() {
			return pass;
		}

		public void setPass(String pass) {
			this.pass = pass;
		}

		public String getEventImage() {
			return eventImage;
		}

		public void setEventImage(String eventImage) {
			this.eventImage = eventImage;
		}

		public int getCoordinatorMno() {
			return coordinatorMno;
		}

		public void setCoordinatorMno(int coordinatorMno) {
			this.coordinatorMno = coordinatorMno;
		}

		public int getPrice() {
			return price;
		}

		public void setPrice(int price) {
			this.price = price;
		}

		public Event() {
			super();
			// TODO Auto-generated constructor stub
		}


	



	
}
