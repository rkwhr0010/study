package designpattern.headfirst.chapter8;

import java.util.Arrays;

public class ArraysSortTest {
	static class Dummy {}
	
	public static void main(String[] args) {
		Dummy[] dumArr = {new Dummy(), new Dummy()};
		Arrays.sort(dumArr);
	}
}
