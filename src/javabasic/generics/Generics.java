package javabasic.generics;

import java.util.Arrays;
import java.util.List;

public class Generics {
	public static void main(String[] args) {
		List<Integer> list = Arrays.asList(1,2,3,4);
//		method1(list);List<Object>불가
		method2_1(list);// List<?> == <? extends Object>  가능
		
		
	}
	
	//? 와일드카드는 ? extends Object와 같다고 헀는데 Object를 안쓰는 이유는
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
	
	
}


