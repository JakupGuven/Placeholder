package controller;

import java.io.IOException;

import calendarConnector.CalendarConnector;
import dataFetcher.WebScraper;
import dataWasher.DataWasher;

public class Test {

	public static void main(String[] args) {
		WebScraper scraper = new WebScraper();
		DataWasher washer = new DataWasher();
		try {
			CalendarConnector.insertEvents(washer.makeEventList(scraper.getLessons("http://schema.mah.se/setup/jsp/Schema.jsp?startDatum=idag&intervallTyp=m&intervallAntal=6&sprak=SV&sokMedAND=true&forklaringar=true&resurser=p.TGSYA15h")));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
