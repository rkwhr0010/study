package javabasic.lang.regex;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Regex01 {
	public static void main(String[] args) {
		String ph = "HP:011-1111-1111, HOME:02-999-9999";
		String p = "(0\\d{1,2})-(\\d{3,4})-(\\d{4})";
		
		Pattern pn = Pattern.compile(p);
		Matcher m = pn.matcher(ph);
		
		int i =0;
		while(m.find()) {
			System.out.println(++i+":"+m.group(/*0*/)
					+" -> "+m.group(1)+" "+m.group(2)+" "+m.group(3));
		}
		ph = "HP:01111111111, HOME:02999999999"
				+ "0191231234";
		p = "(0\\d{1,2})(\\d{3,4})(\\d{4})";
		
		pn = Pattern.compile(p);
		m = pn.matcher(ph);
		
		i =0;
		while(m.find()) {
			System.out.println(++i+":"+m.group(/*0*/)
			+" -> "+m.group(1)+" "+m.group(2)+" "+m.group(3));
		}
	}
}
