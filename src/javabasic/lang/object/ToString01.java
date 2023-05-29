package javabasic.lang.object;

import java.util.Date;

public class ToString01 {
    public static void main(String[] args) {
    	Date date = new Date();
    	System.out.println(date); //toString()을 호출한 결과
    	System.out.println(date.toString());
    }
}
