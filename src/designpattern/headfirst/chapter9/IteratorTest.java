package designpattern.headfirst.chapter9;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Random;

public class IteratorTest {
	static class Test<E> implements Iterable<E>{
		E[] dataArr;
		
		public Test(E[] dataArr) {
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
	
	static class MapClass implements Iterable<Map.Entry<String, String>>{
		Map<String,String> map = new HashMap<>();

		@Override
		public Iterator<Map.Entry<String, String>> iterator() {
			return map.entrySet().iterator();
		}

	}

	
	static class ArrClass implements Iterable<Integer>{
		int[] intArr = new Random().ints(100, 1, 500).toArray();

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
		List<Integer> intList = new Random().ints(100, 1, 500).boxed().toList();
		@Override
		public Iterator<Integer> iterator() {
			return intList.iterator();
		}
		
	}
	
	public static void main(String[] args) {
		ArrClass arrClass = new ArrClass();
		for(Integer val : arrClass) {
			System.out.print(val+" ");
		}
		
		System.out.println();
		for(Integer val :new ListClass()) {
			System.out.print(val+" ");
		}
		System.out.println();
		String[] test = {"gg","aaa","vbb","asd"};
		Test<String> test2 = new Test<>(test);
		
		for(String data : test2) {
			System.out.println(data);
		}
		for(String data : test) {
			System.out.println(data);
		}
		ArrayList<Object> list = new ArrayList<>();
		test2.forEach(data-> {
			if(data.equals("gg")) list.add(data);
		});
		
		for(Entry en : new MapClass()) {
		}
		
		
	}
}
