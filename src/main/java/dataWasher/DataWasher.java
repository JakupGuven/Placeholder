package dataWasher;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;

import com.google.api.services.calendar.model.Event;
import com.google.api.client.util.DateTime;
import com.google.api.services.calendar.model.EventDateTime;

import dataFetcher.LessonList;
import dataFetcher.WebScraper;


/**
 * Turns scraped data into a format usable by Google API
 * TODO: break it up into different methods and make it prettier
 * @author jakup
 *
 */
public class DataWasher {
	Calendar calendar = new GregorianCalendar();
	private ArrayList<Event> list;
	private int lastMonth;
	private int currentYear = calendar.get(Calendar.YEAR);

	public String[] formatTime(String time) {
		String[] times = time.split("-");
		StringBuilder cb0, cb1;
		cb0 = new StringBuilder(times[0]);
		cb1 = new StringBuilder(times[1]);
		cb1.deleteCharAt(cb1.length()-1);
//		System.out.println(times[0].length());
//		System.out.println(times[1].length());
		cb0.append(":00+");
		cb1.append(":00+");
		times[0] = cb0.toString();
		times[1] = cb1.toString();
		return times;
	}
	
	public String formatDate(String date) {
		String[] dateArray = date.split("\\s+");
		StringBuilder cb;		
		if (dateArray[0].length() == 1) {
			dateArray[0] = new String("0" + dateArray[0]);
		}

		switch (dateArray[1]) {
		case "Jan":
			dateArray[1] = "01";
			break;
		case "Feb":
			dateArray[1] = "02";
			break;
		case "Mar":
			dateArray[1] = "03";
			break;
		case "Apr":
			dateArray[1] = "04";
			break;
		case "Maj":
			dateArray[1] = "05";
			break;
		case "Jun":
			dateArray[1] = "06";
			break;
		case "Jul":
			dateArray[1] = "07";
			break;
		case "Aug":
			dateArray[1] = "08";
			break;
		case "Sep":
			dateArray[1] = "09";
			break;
		case "Okt":
			dateArray[1] = "10";
			break;
		case "Nov":
			dateArray[1] = "11";
			break;
		case "Dec":
			dateArray[1] = "12";
			break;
		}
		
		if(lastMonth == 12 && Integer.parseInt(dateArray[1]) == 1) {
			currentYear++;
		}
		String year = "" + currentYear;
		
		cb = new StringBuilder(year);
		cb.append("-" + dateArray[1] + "-" + dateArray[0] + "T");
		lastMonth = Integer.parseInt(dateArray[1]);
		return cb.toString();
	}

	public ArrayList<Event> makeEventList(LessonList lessons) {
		list = new ArrayList<Event>();
		for (int i = 0; i < lessons.size(); i++) {
			String[] times = formatTime(lessons.get(i).getTime());
			String startTime = times[0];
			String endTime = times[1];
			String date = formatDate(lessons.get(i).getDate());
			StringBuilder startData = new StringBuilder(date);
			StringBuilder endData = new StringBuilder(date);
			startData.append(startTime + "02:00");
			endData.append(endTime + "02:00");
			DateTime startDate = new DateTime(startData.toString());
			EventDateTime start = new EventDateTime();
			start.setDateTime(startDate);
			DateTime endDate = new DateTime(endData.toString());
			EventDateTime end = new EventDateTime();
			end.setDateTime(endDate);
			Event event = new Event();
			event.setDescription(lessons.get(i).getDescription());
			event.setStart(start);
			event.setEnd(end);
			event.setLocation(lessons.get(i).getLocation());
			event.setSummary(lessons.get(i).getName());
			list.add(event);
		}
		return list;
	}
	
//	public static void main(String[] args) {
//		WebScraper scraper = new WebScraper();
//		DataWasher maker = new DataWasher();
//		ArrayList<Event> list = maker.makeEventList(scraper.getLessons("http://schema.mah.se/setup/jsp/Schema.jsp?startDatum=idag&intervallTyp=m&intervallAntal=6&sprak=SV&sokMedAND=true&forklaringar=true&resurser=p.TGSYA15h"));
//		for(int i = 0; i < list.size(); i++) {
//			try {
//				System.out.println(list.get(i).toPrettyString());
//			} catch (IOException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//		}
//	}

}
