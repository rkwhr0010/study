package javabasic.timedatecalendar;

import java.time.*;
import java.util.Date;

public class Time02 {
	public static void main(String[] args) {
		Period between = Period.between(LocalDate.of(2010, 1, 1), 
				ZonedDateTime.now().toLocalDate());
		System.out.println(between);
		System.out.println(Instant.from(Instant.now()));
		System.out.println(new Date().toInstant());
	}
}
