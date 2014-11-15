package hackbee.controllers;

public class Event {
	int event_id;
	String type_event;
	String event_name;
	String venue;
	String hosted_by;
	public Event(int event_id,String type_event, String event_name, String venue, String hosted_by) {
		super();
		this.event_id = event_id;
		this.event_name = event_name;
		this.venue = venue;
		this.hosted_by = hosted_by;
		this.type_event = type_event;
	}
	public String getType_event() {
		return type_event;
	}
	public void setType_event(String type_event) {
		this.type_event = type_event;
	}
	public int getEvent_id() {
		return event_id;
	}
	public void setEvent_id(int event_id) {
		this.event_id = event_id;
	}
	public String getEvent_name() {
		return event_name;
	}
	public void setEvent_name(String event_name) {
		this.event_name = event_name;
	}
	public String getVenue() {
		return venue;
	}
	public void setVenue(String venue) {
		this.venue = venue;
	}
	public String getHosted_by() {
		return hosted_by;
	}
	public void setHosted_by(String hosted_by) {
		this.hosted_by = hosted_by;
	}
	
	
}
