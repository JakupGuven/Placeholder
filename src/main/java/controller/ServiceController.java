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


import dataFetcher.LessonList;
import dataFetcher.WebScraper;
import dataWasher.DataWasher;
import dataWasher.Event;
import database.DatabaseConnector;

@EnableAutoConfiguration
@RestController
public class ServiceController {
	WebScraper scraper = new WebScraper();
	DataWasher washer = new DataWasher();
	
	
	@CrossOrigin
	@RequestMapping(value = "/data/{id}", method = RequestMethod.GET)
	public ArrayList<String> getSchedule(@PathVariable("id") int id){
		String URL = DatabaseConnector.getURL(id);
		return washer.makeEventList(scraper.getLessons(URL));
	}
	
	@CrossOrigin
	@RequestMapping(value = "/schedule/{id}", method = RequestMethod.GET)
	public ArrayList<String> getScheduleForWeb(@PathVariable("id") int id) {
		String URL = DatabaseConnector.getURL(id);
		return washer.makeEventListForWeb(scraper.getLessons(URL));

	}
	
	@CrossOrigin
	@RequestMapping(value = "/data/getAll", method = RequestMethod.GET)
	public ArrayList<String> getScheduleList(){
		return DatabaseConnector.getProgramList();
	}
	
	@CrossOrigin
	@RequestMapping(value = "/getAll", method = RequestMethod.GET)
	public String[] getProgramNames() {
		return DatabaseConnector.getAllProgramNames();

	}


}
