package javabasic.timedatecalendar;

import java.util.*;
import java.text.*;

class ChoiceFormatEx1 {
	public static void main(String[] args) {
		double[] limits = {60, 70, 80, 90};	// ���� ������ ū ���� ������ ������Ѵ�.
		// limits, grades���� ������ ������ ���߾�� �Ѵ�. 
		String[] grades = {"D", "C", "B", "A"};	
		ChoiceFormat form = new ChoiceFormat(limits, grades);
		
		int[] scores = { 100, 95, 88, 70, 52, 60, 70};

		for(int i=0;i<scores.length;i++) {
			System.out.println(scores[i]+":"+form.format(scores[i]));		
		}
	} // main
}
