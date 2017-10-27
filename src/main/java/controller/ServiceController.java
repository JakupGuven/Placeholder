package controller;

import java.io.IOException;
import java.util.ArrayList;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.google.api.services.calendar.model.Event;

import calendarConnector.CalendarConnector;
import dataFetcher.LessonList;
import dataFetcher.WebScraper;
import dataWasher.DataWasher;
import database.DatabaseConnector;

//@CrossOrigin(origins = "*", maxAge = 3600)
@EnableAutoConfiguration
@RestController
public class ServiceController {
	WebScraper scraper = new WebScraper();
	DataWasher washer = new DataWasher();
	private ArrayList<String> list = new ArrayList<String>();

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String helloWorld() {
		return "Hello World";
	}

//	@CrossOrigin
//	@RequestMapping(value = "/schedule/{id}", method = RequestMethod.POST)
//	public void putScheduale(@PathVariable("id") int id) {
//		try {
//			String URL = DatabaseConnector.getURL(id);
//			System.out.println("sent to method");
//			ArrayList<Event> eventList = washer.makeEventList(scraper.getLessons(URL));
//			CalendarConnector.insertEvents(eventList);
//			System.out.println("sent to GC");
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//	}
	
	@CrossOrigin
	@RequestMapping(value = "/schedule/{id}", method = RequestMethod.GET)
	public ArrayList<Event> getListAsJSON(@PathVariable("id") int id) {
		ArrayList<Event> eventList = null;
		LessonList lessonList = null;
		String URL = DatabaseConnector.getURL(id);
		lessonList = scraper.getLessons(URL);
		eventList = washer.makeEventList(lessonList);
		System.out.println(lessonList.size() + " scraper");
		System.out.println(eventList.size() + " datawasher");
//		System.out.println(id);
		return washer.makeEventList(scraper.getLessons(URL));

	}
	
	@CrossOrigin
	@RequestMapping(value = "/getAll", method = RequestMethod.GET)
	public String[] getProgramNames() {
		return DatabaseConnector.getAllProgramNames();

	}

	// @RequestMapping(value = "/scheduale", method = RequestMethod.GET)
	// public int ngt (){
	//
	// }
	//
	// @RequestMapping(value ="/scheduale", method = RequestMethod.PUT)
	// public void ngtannat() {
	//
	// }

}
