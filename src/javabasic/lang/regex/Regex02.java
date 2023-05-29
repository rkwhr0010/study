package javabasic.lang.regex;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Regex02 {
	public static void main(String[] args) {
		String ph = "1231231234";
		String p = "(\\d{2,3})(\\d{3,4})(\\d{4})";
		
		Pattern pn = Pattern.compile(p);
		Matcher m = pn.matcher(ph);
		
		int i =0;
		while(m.find()) {
			System.out.println(++i+":"+m.group(/*0*/)
					+" -> "+m.group(1)+" "+m.group(2)+" "+m.group(3));
		}
	}
}
