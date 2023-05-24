package designpattern.headfirst.chapter9;

import java.util.*;
import java.util.Map.Entry;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class IteratorTest {
	static Supplier<IntStream> supply(){
		return ()-> new Random().ints(10, 1, 100);
	}
	
	static class ClassArr<E> implements Iterable<E>{
		E[] dataArr;
		
		public ClassArr(E[] dataArr) {
			this.dataArr = (E[]) dataArr;
		}

		@Override
		public Iterator<E> iterator() {
			return new Iterator<E>() {
				int cursor = 0;
				public boolean hasNext() {
					return cursor < dataArr.length;
				}
				public E next() {
					return dataArr[cursor++];
				}
			};
		}
		
	}
	
	static class MapClass implements Iterable<Map.Entry<Object, List<Integer>>>{
		Map<Object, List<Integer>> map = new HashMap<>();

		public MapClass() {
			map= supply().get().boxed().collect(Collectors.groupingBy(n->n));
		}
		
		@Override
		public Iterator<Entry<Object, List<Integer>>> iterator() {
			return map.entrySet().iterator();
		}

	}

	
	static class ArrClass implements Iterable<Integer>{
		int[] intArr = supply().get().toArray();

		@Override
		public Iterator<Integer> iterator() {
			return new Iterator<Integer>() {
				int cursor = 0;
				public Integer next() {
					return intArr[cursor++];
				}
				public boolean hasNext() {
					return cursor<intArr.length;
				}
			};
		}
	}
	static class ListClass implements Iterable<Integer>{
		List<Integer> intList = supply().get().boxed().toList();
		@Override
		public Iterator<Integer> iterator() {
			return intList.iterator();
		}
		
	}
	
	public static void main(String[] args) {
		String[] test = {"배열은","향상된","반복문","사용가능"};
		//배열 향상 포문 가능
		for(String data : test) {
			System.out.print(data+" ");
		}
		System.out.println();
		//어떠한 클래스도 Iterable만 알맞게 구현하면 전부 향상 포문 가능하다.
		for(String data : new ClassArr<>(test)) {
			System.out.print(data+" ");
		}
		System.out.println();
		for(Integer val : new ArrClass()) {
			System.out.print(val+" ");
		}
		System.out.println();
		for(Integer val :new ListClass()) {
			System.out.print(val+" ");
		}
		System.out.println();
		for(Entry en : new MapClass()) {
			System.out.print(en + " ");
		}
	}
}
