package ca.bytetube._19_sort.utils;

public class Asserts {
	public static void test(boolean value) {
		try {
			if (!value) throw new Exception("Failed the test");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
