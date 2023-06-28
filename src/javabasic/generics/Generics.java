package javabasic.generics;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Generics {

	
	//? 와일드카드는 <? extends Object>와 같다고 헀는데 Object를 안쓰는 이유는
	//호출하는 쪽에서 List<Object> 타입만 넣을 수 있기 때문이다. 
	static void method1(List<Object> list) {}
	//아래 두 개 차이는?? 
	static void method2_1(List<?> list) {
		//<? extends Object> 와 같다 따라서 Object 메서드만 사용 가능
		//다른 의미로는 나는 내부 객체를 다루지 않고 List만 사용하겠다.
		for(Object obj : list) {
		}
	}
	// 타입파라미터는 컴파일 시 제거되어 런타임에 존재하지 않는다. 단, 제한된 파라미터 정보는 런타임에도 남는다.
	//따라서 Comparable 메서드를 사용할 수 있다.
	static void method2_2(List<? extends Comparable<?>> list) {
		for(Comparable<?> obj : list) {
//			obj.compareTo();
		}
	}
	public static void main(String[] args) {
		List<Integer> asList = Arrays.asList(1,2,3,4,5,6);
		reverse(asList);
		System.out.println(asList);
	}
	
	static void reverse(List<?> list) {
		List l = list;
		int low = 0;
		int high = list.size()-1;
		
		for(;low<high;low++,high--) {
			final Object tmp = l.get(low);
			l.set(low, l.get(high));
			l.set(high, tmp);
		}
//		extracted(list);
	}
	private static <T> void extracted(List<T> list) {
		int low = 0;
		int high = list.size()-1;
		
		for(;low<high;low++,high--) {
			final T tmp = list.get(low);
			list.set(low, list.get(high));
			list.set(high, tmp);
		}
	}
	
	
	static <T extends Comparable<? super T>> boolean granterThan(List<T> list, T o) {
		return list.stream().filter(a->a.compareTo(o)>0).count()>0;
	}
	
	static long frequency(Collection<?> list , Object data) {
		return list.stream().filter(arg->arg.equals(data)).count();
	}
	
	static <T extends Comparable<? super T>> T max(List<? extends T> list) {
		return list.stream().reduce((a,b)-> a.compareTo(b) > 0 ? a : b).get();
	}
	
	
	
	
	//API 설계에서 T타입의 노출은 내부에서 T타입을 사용한다는 것을 의미한다.
	//내부에서 T 타입을 사용안한다면, 숨기는 것이 권장된다
	static <T> void reverse1(List<T> list) {
		List<T> temp = new ArrayList<>(list);
		for (int i = 0, len = list.size(); i < len; i++) {
			temp.set(i, list.get(len-1-i));
		}
	}
	//The method set(int, capture#5-of ?) in the type List<capture#5-of ?> is not applicable for the arguments (int, capture#6-of ?)
	static void reverse2(List<?> list) {
		List<?> temp = new ArrayList<>(list);
		for (int i = 0, len = list.size(); i < len; i++) {
//			temp.set(i, list.get(len-1-i));
		}
	}
	//숨길 시 와일드 카드를 사용하는데 캡쳐문제가 발생한다.
	//이럴때 헬퍼 메서드를 사용하라고 권장한다. 어찌되었건, T가 노출되지 않는다. private
	static void reverse3(List<?> list) {
		reverseHelper(list);
	}
	private static <T> void reverseHelper(List<T> list) {
		List<T> temp = new ArrayList<>(list);
		for (int i = 0, len = temp.size(); i < len; i++) {
			list.set(i, temp.get(len-1-i));
		}
	}
	//다른 방법 raw 타입을 쓴다. 어차피 T 타입을 이용하지 않을 것이므로 논리적으로 문제는 없다.
	static void reverse4(List<?> list) {
		List temp = new ArrayList<>(list);
		for (int i = 0, len = list.size(); i < len; i++) {
			temp.set(i, list.get(len-1-i));
		}
	}
	
	
	
	
}


