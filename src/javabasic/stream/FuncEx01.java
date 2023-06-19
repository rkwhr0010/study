package javabasic.stream;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;

public class FuncEx01 {
	static class User{
		final Long id;
		final String name;
		final Integer age;
		
		public User(Long id, String name, Integer age) {
			this.id = id;
			this.name = name;
			this.age = age;
		}
	}
	
	static List<User> getUsers(){
		return Arrays.asList( 
				new User(10L, "ID", 36),
				new User(20L, "BJ", 32),
				new User(30L, "JM", 32),
				new User(40L, "PJ", 27),
				new User(50L, "HA", 25),
				new User(60L, "JE", 26),
				new User(70L, "JI", 31),
				new User(80L, "MP", 23)
				);
	}
	
	
	public static void main(String[] args) {
		
		
		
	}
	

}
