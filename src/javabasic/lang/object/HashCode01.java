package javabasic.lang.object;

public class HashCode01 {
    public static void main(String[] args) {
    	String str1 = "스트링";
    	String str2 = "스트링";
    	String str3 = String.valueOf("스트링");
    	String str4 = new String("스트링");
    	System.out.println(str1.hashCode());
    	System.out.println(str2.hashCode());
    	System.out.println(str3.hashCode());
    	System.out.println(str4.hashCode());
    	System.out.println("Object.hashCode()호출");
    	System.out.println(System.identityHashCode(str1));
    	System.out.println(System.identityHashCode(str2));
    	System.out.println(System.identityHashCode(str3));
    	System.out.println(System.identityHashCode(str4));
    }
}
