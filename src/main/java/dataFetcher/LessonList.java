package dataFetcher;
import java.util.ArrayList;

public class LessonList {
	private ArrayList<Lesson> list = new ArrayList<Lesson>();
	private String lastDate;

	public void add(Lesson inLesson) {
		if (inLesson.getDate().length() < 2) {
			inLesson.setDate(lastDate);
		} else {
			lastDate = inLesson.getDate();
		}
		list.add(inLesson);
	}
	
	public Lesson get(int index) {
		return list.get(index);
	}
	
	public int size() {
		return list.size();
	}

}
