package dataWasher;

import java.io.Serializable;

public class Event implements Serializable {
	/**
		 * 
		 */
	private static final long serialVersionUID = 1L;
	/**
		 * 
		 */
	private String date, startTime, endTime, name, location, description;

	public Event() {
	}

	public void setDate(String inDate) {
		date = inDate;
	}

	public void setStartTime(String inStartTime) {
		startTime = inStartTime;
	}

	public void setEndTime(String inEndTime) {
		endTime = inEndTime;
	}

	public void setName(String inName) {
		name = inName;
	}

	public void setLocation(String inLocation) {
		location = inLocation;
	}

	public void setDescription(String inDescription) {
		description = inDescription;
	}

	public String toString() {
		return date + " " + startTime + " " + endTime + " " + name + " " + location + " " + description;
	}
}