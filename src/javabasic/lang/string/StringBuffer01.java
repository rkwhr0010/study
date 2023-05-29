package javabasic.lang.string;

import java.util.Arrays;
import java.util.stream.Collectors;

public class StringBuffer01 {
    public static void main(String[] args) throws Exception {
    	StringBuffer sb1 = new StringBuffer();
    	StringBuffer sb2 = new StringBuffer();
    	sb1.append("abc");
    	sb2.append("abc");
    	
    	System.out.println(sb1.equals(sb2));
    	System.out.println(sb1 == sb2);
    	System.out.println(id(sb1.toString(),sb2.toString()));
    	System.out.println(sb1.toString() == sb2.toString());
    	System.out.println(id(sb1.toString().intern(),sb2.toString().intern()));
    	System.out.println(sb1.toString().intern() == sb2.toString().intern());
    	System.out.println(sb1.toString().equals(sb2.toString()));
    }
    static Object id(Object... objs) {
		return Arrays.stream(objs)
        		.mapToInt(System::identityHashCode)
				.boxed()
        		.collect(Collectors.toList());
    }
}