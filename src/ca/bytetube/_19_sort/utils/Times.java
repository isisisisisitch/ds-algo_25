package ca.bytetube._19_sort.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Times {
	private static final SimpleDateFormat fmt = new SimpleDateFormat("HH:mm:ss.SSS");
	
	public interface Task {
		void execute();
	}
	
	public static void test(String title, Task task) {
		if (task == null) return;
		title = (title == null) ? "" : ("【" + title + "】");
		System.out.println(title);
		System.out.println("start：" + fmt.format(new Date()));
		long begin = System.currentTimeMillis();
		task.execute();
		long end = System.currentTimeMillis();
		System.out.println("end：" + fmt.format(new Date()));
		double delta = (end - begin) / 1000.0;
		System.out.println("takes：" + delta + "seconds");
		System.out.println("-------------------------------------");
	}
}
