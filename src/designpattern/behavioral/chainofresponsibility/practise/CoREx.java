package designpattern.behavioral.chainofresponsibility.practise;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

public class CoREx {
	//커맨드 객체
	static class Person {
		String name;
		String sex;
		int age;
		public Person(String name, String sex, int age) {
			this.name = name;
			this.sex = sex;
			this.age = age;
		}
		@Override
		public String toString() {
			return "Person [name=" + name + ", sex=" + sex + ", age=" + age + "]";
		}
	}
	//각 요소를 요청이라고 가정한다.
	static List<Person> persons = new ArrayList<>();
	static {
		persons.add(new Person("김필터", "남", 20));
		persons.add(new Person("임자바", "남", 33));
		persons.add(new Person("이자바", "여", 22));
		persons.add(new Person("김자바", "여", 18));
		persons.add(new Person("이육사", "남", 27));
		persons.add(new Person("김육사", "여", 15));
		persons.add(new Person("박세종", "남", 30));
		persons.add(new Person("김세종", "남", 30));
	}
	//핸들러 
	static class Filter<T>{
		//나의 다음 체인 참조변수
		private Filter<T> nextFilter;
		//요구 사항
		private final Predicate<T> predi;
		
		protected Filter(Predicate<T> predi) {
			this.predi = predi;
		}
		@SafeVarargs //필터 체인 형성
		public static <T> Filter<T> createFilterChain(Filter<T> first, Filter<T>...filters){
			Filter<T> nowFilter = first;
			for(Filter<T> filter : filters) {
				nowFilter.nextFilter = filter;
				nowFilter = filter;
			}
			return first;
		}
		public boolean check(T data) {
			return predi.test(data) ? nextCheck(data) : false;
		}
		public boolean nextCheck(T data) {
			return nextFilter == null ? true : nextFilter.check(data);
		}
	}
	
	public static void main(String[] args) {
		//필터체인
		Filter<Person> filterChain = Filter.createFilterChain(
				new Filter<>(per -> per.age>=20)
				,new Filter<>(per -> per.sex.startsWith("남"))
				,new Filter<>(per -> per.name.startsWith("김"))
				);
		for(Person person : persons) {
			if(filterChain.check(person)) {
				System.out.println(person);
			}
		}
	}
}
