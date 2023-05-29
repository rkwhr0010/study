package javabasic.lang.string;

import java.util.StringJoiner;

public class StringJoiner01 {
	public static void main(String[] args) {
		StringJoiner sj= new StringJoiner("</b>\n<b>", "<b>", "</b>");
		sj.add("멍멍이");
		sj.add("냐옹이");
		sj.add("짹짹이");
		System.out.println(sj.toString());
	}
}
