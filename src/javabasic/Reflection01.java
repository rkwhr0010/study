package javabasic;

import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.util.HashMap;

public class Reflection01 {
	public static void main(String[] args) {
		Field[] fields = Data.class.getDeclaredFields();
		for(Field f : fields) {
			System.out.println(f.getType().getSimpleName()+" "+f.getName());
		}
		
		HashMap<String,Integer> map = new HashMap<>();
		map.put("add", 1000000);
		
		map.computeIfPresent("add", (k,v)->{System.out.println(k+"  "+v); return v;});
		System.out.println(map);
		
//		System.out.println(("123"+" "+"456").substring(0, 200));
		BigDecimal a = null;
		System.out.println(a);
		
	}
	
	
	
	static class Data{
		String name;
		Integer age;
		
		
	}
	
}
