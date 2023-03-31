package designpattern.headfirst.chapter4;

import java.util.ArrayList;
import java.util.List;

public class Test {
	
	public static void main(String[] args) {
//		추상클래스 클래스1 = new 구상클래스();
//		추상클래스 클래스2 = new 추상클래스();
		
		추상클래스 클래스;
		int type = 1;//런타임에 값을 받아 처리함
		switch (type) {
		case 1:
			클래스 = new 구상클래스();
			break;
		case 2:
			클래스 = new 구상클래스2();
			break;
		case 3:
			클래스 = new 구상클래스3();
			break;
		}
	}
	
	static abstract class 추상클래스{}
	static class 구상클래스 extends 추상클래스{}
	static class 구상클래스2 extends 추상클래스{}
	static class 구상클래스3 extends 추상클래스{}
	
}
