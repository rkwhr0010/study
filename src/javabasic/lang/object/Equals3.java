package javabasic.lang.object;

import java.util.Objects;

public class Equals3 {
    public static void main(String[] args) {
    	String str1 = "스트링";
    	String str2 = "스트링";
    	
    	String str3 = String.valueOf("스트링");
    	String str4 = new String("스트링");
    	
        System.out.println(str1 == str2 ? "같아요" : "달라요");
        System.out.println(str1 == str3 ? "같아요" : "달라요");
        System.out.println(Objects.equals(str1, str2) ? "같아요" : "달라요");
        //특이 케이스
        System.out.println(str1 == str4 ? "같아요" : "달라요");
        System.out.println(Objects.equals(str1, str4) ? "같아요" : "달라요");
    }
}
