package javabasic.collection;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class ArrayTest {
	public static void main(String[] args) {
		List<String> list = new ArrayList<>();
		list.add("<div>");
		list.add("으핳핳핳");
		list.add("</div>");
		String[] strings = list.toArray(String[]::new);
		String string = Arrays.stream(strings)
			.collect(Collectors.joining("\n"));
		System.out.println(string);
		Optional<String> reduce = list.stream()
			.reduce((a,b) -> a+"\n"+b );
		System.out.println(reduce.get());
		
		StringBuilder sb =new StringBuilder();
		sb.append("<div>").append("\n")
			.append("으핳핳핳").append("\n")
			.append("</div>");
		System.out.println(sb.toString());
		
	}
	
}
