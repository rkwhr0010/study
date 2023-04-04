package designpattern.headfirst.chapter9;

import java.util.Iterator;
import java.util.List;
import java.util.Random;

public class IteratorTest {
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
		
	}
}
