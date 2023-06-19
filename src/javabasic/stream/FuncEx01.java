package javabasic.stream;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;

import javabasic.stream.FuncEx01.User;

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

		@Override
		public String toString() {
			return "[id=" + id + ", name=" + name + ", age=" + age + "]\n";
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
		List<User> users = getUsers();
		
		System.out.println(
				filter(users,user->user.age>30)+"\n"
				+users
				);
		
		
	}
	
	static <T> List<T> filter(List<T> list, Predicate<T> predi) {
		//함수형 프로그래밍은 원본 데이터를 수정하지 않는다. 새로운 데이터를 리턴하여
		//부수효과를 극단적으로 배제한다.
		ArrayList<T> newList = new ArrayList<>();
		for(T data : list) {
			//자바는 근본적으로 함수단독으로 존재할 수 없다.
			//따라서 추상 메서드가 단 하나인 인터페이스를 사용해 메서드 형식으로 호출할 수 밖에 없다.
			if(predi.test(data))
				newList.add(data);
		}
		return newList;
	}
}
