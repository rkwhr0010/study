package designpattern.behavioral.chainofresponsibility;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

public class ChainOfResponsibilityEx {
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
	
	
	static abstract class Filter<T>{
		private Filter<T> next;
		
		@SafeVarargs
		public static <T> Filter<T> createFilterChain(Filter<T> first, Filter<T> ...filters) {
			Filter<T> tmp = first;
			
			for(Filter<T> nextFilter : filters) {
				tmp.next = nextFilter;
				tmp = nextFilter;
			}
			return first; 
		}
		public abstract boolean check(T data);
		
		public boolean nextFilter(T data) {
			if(next == null) {
				return true;
			}
			return next.check(data);
		}
	}
	
	static class PrediFilter<T> extends Filter<T>{
		Predicate<T> predi;
		String msg;
		public PrediFilter(Predicate<T> predi,String msg) {
			this.predi = predi;
			this.msg = msg;
		}
		public boolean check(T data) {
			if(predi.test(data)) {
				return nextFilter(data);
			}
			System.out.println(msg);
			return false;
		}
	}
	
	
	public static void main(String[] args) {
		Filter<Person> filterChain = Filter.createFilterChain(
				new PrediFilter<>(per -> per.age>=20, "20세 이상만")
				,new PrediFilter<>(per -> per.sex.startsWith("남"), "남자만")
				,new PrediFilter<>(per -> per.name.startsWith("김"), "김씨만")
				);
		for(Person person : persons) {
			if(filterChain.check(person)) {
				System.out.println(person);
			}
		}
	}
	
}
