package dataFetcher;

import java.io.IOException;
import java.util.ArrayList;
import org.jsoup.select.*;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

/**
 * Scans schema.mah.se site for lessons and turn them into an ArrayList of Lesson objects.
 * @author jakup
 *TODO: Make possible to input different schema sites 
 *Make elegant solution for when whiteList and greyList length differ
 */

public class WebScraper {
	public static final String USER_AGENT = "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/45.0.2454.101 Safari/537.36";
	private Elements whiteList, greyList, firstList, secondList;
	private LessonList lessonList = new LessonList();
	private int largestSize;
	
	private void setLargestSize() {
		if (firstList.size() > secondList.size()) {
			largestSize = firstList.size();
		} else {
			largestSize = secondList.size();
		}
	}

	private void setListOrder() {
		if(whiteList.get(0).child(2) == null ) {
			firstList = greyList;
			secondList = whiteList;
		}else {
			firstList = whiteList;
			secondList = greyList;
		}
	}

	public LessonList getLessons(String inUrl) {
		try {
			final Document HTML_DOCUMENT = Jsoup.connect(inUrl).userAgent(USER_AGENT).get();
			Elements list = HTML_DOCUMENT.select(".schemaTabell");
			whiteList = list.select(".data-white");
			greyList = list.select(".data-grey");
//			for(int i = 0; i < whiteList.size(); i++) {
//				System.out.println(whiteList.get(i).child(4).text());
//				System.out.print(whiteList.get(i).child(2).text());
//				System.out.print(whiteList.get(i).child(3).text());
//				System.out.print(whiteList.get(i).child(4).text());
//				System.out.println();
//			}
			setListOrder();
			setLargestSize();
			for(int i = 0; i < largestSize; i++) {
				if(firstList.size() != i) {
					String date0 = firstList.get(i).child(2).text();
					String time0 = firstList.get(i).child(3).text().trim();
					String name0 = firstList.get(i).child(4).text();
					String location0 = firstList.get(i).child(6).text();
					String description0 = firstList.get(i).child(8).text();
					lessonList.add(new Lesson(date0, time0, name0, location0, description0));
				}
				if(secondList.size() != i) {
					String date1 = secondList.get(i).child(2).text();
					String time1 = secondList.get(i).child(3).text().trim();
					String name1 = secondList.get(i).child(4).text();
					String location1 = secondList.get(i).child(6).text();
					String description1 = secondList.get(i).child(8).text();
					lessonList.add(new Lesson(date1, time1, name1, location1, description1));
				}
			}
			
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
//		for(int i = 0; i < firstList.size(); i++) {
//			System.out.println(firstList.get(i).toString());
//		}
		return lessonList;

	}


	public static void main(String[] args) {
		WebScraper web = new WebScraper();
		LessonList list = web.getLessons("http://schema.mah.se/setup/jsp/Schema.jsp?startDatum=idag&intervallTyp=m&intervallAntal=6&sprak=SV&sokMedAND=true&forklaringar=true&resurser=p.TGSYA15h");
		for( int i = 0; i < list.size(); i++) {
			System.out.println(list.get(i).toString());
		}
		
	
	}

}
