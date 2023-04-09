package designpattern.headfirst.chapter11;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.HashMap;

public class ProtectProxyTest {
	static interface Person{
		String getName();
		String getGender();
		String getInterests();
		int getGeekRating();
		
		void setName(String name);
		void setGender(String gender);
		void setInterests(String interests);
		void setGeekRating(int rating);
	}
	
	static class PersonImpl implements Person{
		String name;
		String gender;
		String interests;
		int rating;
		int ratingCount = 0;
		
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
		public String getGender() {
			return gender;
		}
		@Override
		public int getGeekRating() {
			if(ratingCount == 0 ) return 0;
			return (rating/ratingCount);
		}
		public void setGender(String gender) {
			this.gender = gender;
		}
		public String getInterests() {
			return interests;
		}
		public void setInterests(String interests) {
			this.interests = interests;
		}
		
		@Override
		public void setGeekRating(int rating) {
			this.rating = rating;
			ratingCount++;
		}
		
	}
	static class OwnerInvocationHandler implements InvocationHandler{
		Person person;
		
		public OwnerInvocationHandler(Person person) {
			this.person = person;
		}
		@Override
		public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
			try {
				if(method.getName().startsWith("get")) {
					return method.invoke(person, args);
				}else if(method.getName().startsWith("setGeekRating")) {
					//나한테 평가는 불가
					throw new IllegalAccessException();
				}else if(method.getName().startsWith("set")) {
					return method.invoke(person, args);
				}
			} catch (InvocationTargetException e) {
				e.printStackTrace();
			}
			return null;
		}
	}
	static class NonOwnerInvocationHandler implements InvocationHandler{
		Person person;
		
		public NonOwnerInvocationHandler(Person person) {
			this.person = person;
		}
		@Override
		public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
			try {
				if(method.getName().startsWith("get")) {
					return method.invoke(person, args);
				}else if(method.getName().startsWith("setGeekRating")) {
					return method.invoke(person, args);
				}else if(method.getName().startsWith("set")) {
					//내것 아니니까 수정 불가
					throw new IllegalAccessException();
				}
			} catch (InvocationTargetException e) {
				e.printStackTrace();
			}
			return null;
		}
	}
	
	static Person getOwnerProxy(Person person) {
		return (Person)Proxy.newProxyInstance(
				person.getClass().getClassLoader(),
				person.getClass().getInterfaces(),
				new OwnerInvocationHandler(person));
	}
	static Person getNonOwnerProxy(Person person) {
		return (Person)Proxy.newProxyInstance(
				person.getClass().getClassLoader(),
				person.getClass().getInterfaces(),
				new NonOwnerInvocationHandler(person));
	}
	
	
	static Person getPersonFromDatabase(String name) {
		return (Person)datingDB.get(name);
	}
	
	static void initializeDatabase() {
		Person joe = new PersonImpl();
		joe.setName("김자바");
		joe.setInterests("자동차, 컴퓨터, 음악");
		joe.setGeekRating(7);
		datingDB.put(joe.getName(), joe);

		Person kelly = new PersonImpl();
		kelly.setName("박자바");
		kelly.setInterests("웹쇼핑, 영화, 음악");
		kelly.setGeekRating(6);
		datingDB.put(kelly.getName(), kelly);
	}
	
	static HashMap<String, Person> datingDB = new HashMap<String, Person>();
	
	public static void main(String[] args) {
		initializeDatabase();
		drive();
	}

	static void drive() {
		Person joe = getPersonFromDatabase("김자바");
		
		Person ownerProxy = getOwnerProxy(joe);//프록시 생성
		
		System.out.println("이름은 " + ownerProxy.getName());
		ownerProxy.setInterests("볼링, 바둑");
		System.out.println("본인 프록시에 관심 사항을 등록합니다.");
		try {
			ownerProxy.setGeekRating(10);
		} catch (Exception e) {
			System.out.println("본인 프록시에 괴짜 지수를 매길 수 없습니다.");
		}
		System.out.println("괴짜 지수 " + ownerProxy.getGeekRating());

		Person nonOwnerProxy = getNonOwnerProxy(joe);// 프록시 생성
		
		System.out.println("이름은 " + nonOwnerProxy.getName());
		try {
			nonOwnerProxy.setInterests("볼링, 바둑");
		} catch (Exception e) {
			System.out.println("타인 프록시에는 관심 사항을 등록할 수 없습니다.");
		}
		nonOwnerProxy.setGeekRating(3);
		System.out.println("타인 프록시에 괴짜 지수를 매깁니다.");
		System.out.println("괴짜 지수 " + nonOwnerProxy.getGeekRating());

	}

}
