package dataFetcher;

import java.io.IOException;
import java.util.ArrayList;
import org.jsoup.select.*;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

/**
 * Scans schema.mah.se site for lessons and turn them into an ArrayList of
 * Lesson objects.
 * 
 * @author jakup
 */

public class WebScraper {
	public static final String USER_AGENT = "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/45.0.2454.101 Safari/537.36";
	private Elements whiteList, greyList, firstList, secondList;
	private LessonList lessonList;
	private int largestSize;

	private void setLargestSize() {
		if (firstList.size() > secondList.size()) {
			largestSize = firstList.size();
		} else {
			largestSize = secondList.size();
		}
	}

	private void setListOrder() {
		if (whiteList.get(0).child(2).text().length() == 1 || greyList.get(0).child(2).text().length() == 1) {
			if (whiteList.get(0).child(2).text().length() == 1) {
				firstList = greyList;
				secondList = whiteList;
			} else {
				firstList = whiteList;
				secondList = greyList;
			}
		} else if (whiteList.get(0).child(2).text().length() != greyList.get(0).child(2).text().length()) {
			if (whiteList.get(0).child(2).text().length() < greyList.get(0).child(2).text().length()) {
				firstList = whiteList;
				secondList = greyList;
			} else {
				firstList = greyList;
				secondList = whiteList;
			}
		} else if (whiteList.get(0).child(2).text().length() == 6) {
			int white = Integer.parseInt(whiteList.get(0).child(2).text().substring(0, 2));
			int grey = Integer.parseInt(greyList.get(0).child(2).text().substring(0, 2));
			if (white < grey) {
				firstList = whiteList;
				secondList = greyList;
			} else {
				firstList = greyList;
				secondList = whiteList;
			}
		} else {
			int white = Integer.parseInt(whiteList.get(0).child(2).text().substring(0, 1));
			int grey = Integer.parseInt(greyList.get(0).child(2).text().substring(0, 1));
			if (white < grey) {
				firstList = whiteList;
				secondList = greyList;
			} else {
				firstList = greyList;
				secondList = whiteList;
			}
		}

	}

	public LessonList getLessons(String inUrl) {
		try {
			lessonList = new LessonList();
			final Document HTML_DOCUMENT = Jsoup.connect(inUrl).userAgent(USER_AGENT).get();
			Elements list = HTML_DOCUMENT.select(".schemaTabell");
			whiteList = list.select(".data-white");
			greyList = list.select(".data-grey");
			setListOrder();
			setLargestSize();
			for (int i = 0; i < largestSize; i++) {
				if (firstList.size() != i) {
					String date0 = firstList.get(i).child(2).text();
					String time0 = firstList.get(i).child(3).text().trim();
					String name0 = firstList.get(i).child(4).text();
					String location0 = firstList.get(i).child(6).text();
					String description0 = firstList.get(i).child(8).text();
					lessonList.add(new Lesson(date0, time0, name0, location0, description0));
				}
				if (secondList.size() != i) {
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
		return lessonList;

	}

}
