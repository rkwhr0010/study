package javabasic.lang;

import java.util.Iterator;
import java.util.StringTokenizer;

public class StringTokenier01 {
	public static void main(String[] args) {
		StringTokenizer tokenizer = new StringTokenizer("123 123 123"," ");
		
		while (tokenizer.hasMoreElements()) {
			Object object = (Object) tokenizer.nextElement();
			System.out.println(object);
		}
	}
}
