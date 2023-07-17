package javabasic.collection;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.function.Consumer;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class 링크드리스트삭제 {
	public static void main(String[] args) {
		List<Integer> list1 = IntStream.range(0, 100_000).boxed().collect(Collectors.toCollection(LinkedList::new));
	
		System.out.println("middle = "+ timeGap(list1, list -> middleDelete(list))) ;
		System.out.println("end = "+ timeGap(list1, list -> middleDelete(list))) ;
	}
	
	static long timeGap(List<Integer> list, Consumer<List<Integer>> run) {
		List<Integer> clone = new LinkedList<>(list);
		long start = System.nanoTime();
		run.accept(clone);
		return System.nanoTime() - start;
	}
	
	static void middleDelete(List<Integer> list) {
		for(int i = 0; i < list.size(); i++) {
			if(list.get(i) / 2 == 0) list.remove(i);
		}
	}
	static void endDelete(List<Integer> list) {
		for(int i = list.size() - 1; i <= 0; i--) {
			if(list.get(i) / 2 == 0) list.remove(i);
		}
	}
}
