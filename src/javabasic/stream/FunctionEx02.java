package javabasic.stream;

import java.util.concurrent.ThreadLocalRandom;
import java.util.function.Function;
import java.util.function.Predicate;

public class FunctionEx02 {
	public static void main(String[] args) {
		
		Predicate<Integer> predi = n -> n%2 == 0;
		Predicate<Integer> and = predi.and(n->n%3==0);
		
		Function<Integer, Integer> fn = n -> n*10;
		Function<Integer, Integer> andThen = fn.andThen(n->n/100);
		
		ThreadLocalRandom.current().ints(100, 1, 200)
			.boxed()
			.filter(and)
			.map(andThen)
			.forEach(System.out::println);
//			.filter(n-> Function)
	}
}
