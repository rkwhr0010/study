package javabasic;

import java.util.EnumMap;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.Map;

import javabasic.EnumMapTest01.Type;

public class EnumMapTest01 {
	enum Type{
		NAME, AGE
	}
	
	public static void main(String[] args) {
		Map<String,String> hashMap = new HashMap<>();
		hashMap.put("NAME", "테스트");
		hashMap.put("AGE", "테스트");
		hashMap.put("OTHER", "테스트");
		
		EnumMap<Type, Object> test = new EnumMap<>(Type.class);
		System.out.println(hashMap.get(Type.AGE.toString()));
		
		EnumSet<Type> allOf = EnumSet.of(Type.NAME);
		System.err.println(allOf);
	}
	
	
}
