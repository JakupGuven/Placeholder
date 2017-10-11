package dataFetcher;

public class Lesson {
private String date, time, name, location, description;
	
	public Lesson(String inDate, String inTime, String inName, String inLocation, String inDescription) {
		date = inDate.trim();
		time = inTime.trim(); 
		name = inName;
		location = inLocation;
		description = inDescription;
	}
	
	public void setDate(String inDate) {
		date = inDate;
	}

	public String getDate() {
		return date;
	}

	public String getTime() {
		return time;
	}

	public String getName() {
		return name;
	}
	
	public String getLocation() {
		return location;
	}

	public String getDescription() {
		return description;
	}
	
	public String toString() {
		return date + " " + time + " " + name + " " + location + " " + description;
	}
}
