package javabasic.io;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class StreamTest {
	public static void main(String[] args)  {
		List<User> users = new ArrayList<>();
		users.add(new User("홍길동", 10));
		users.add(new User("홍길동", 11));
		users.add(new User("홍길동", 12));
		users.add(new User("홍길동", 10));
		users.add(new User("홍길동", 15));
		users.add(new User("홍길동", 10));
		users.add(new User("홍길동", 10));
		users.add(new User("홍길동", 10));
		users.add(new User("홍길동", 10));
		users.add(new User("홍길동", 10));

		System.out.println(
			users.stream()
				.reduce(new HashMap<>(), (map, user) -> {
					map.put(Objects.hash(
						user.age ,
						user.name
						), user);
					return map;
				}, (map1, map2) -> {
					map1.putAll(map2);
					return map1;
				})
		);
	}



}

class User {
	String name ;
	int age ;
	public User(String name, int age) {
		this.name = name;
		this.age = age;
	}
	@Override
	public String toString() {
		return "User [name=" + name + ", age=" + age + "]";
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + age;
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		User other = (User) obj;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (age != other.age)
			return false;
		return true;
	}
	
}