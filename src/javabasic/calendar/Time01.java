package javabasic.calendar;

import java.time.*;

public class Time01 {
	public static void main(String[] args) {
		System.out.println(ZonedDateTime.now());
		System.out.println(LocalDateTime.now());
		System.out.println(LocalDate.now());
		System.out.println(LocalTime.now());
	}
}
