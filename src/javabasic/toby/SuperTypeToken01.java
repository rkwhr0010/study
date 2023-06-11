package javabasic.toby;

public class SuperTypeToken01 {
	public static void main(String[] args) throws Exception {
//		System.out.println(TypeToken01.create().getClass());
//		System.out.println(TypeToken01.create(String.class).getClass());
//		System.out.println(TypeToken01.create(Integer.class).getClass());//예외발생, 기본 생성자가 없음
//		new Integer()
		
		//지네릭은 컴파일러에게 정보를 주는 것
		//type erasure에 의해 런타임 시 제거됨(형변환으로 치환됨)
		Generic<String> g1 = new Generic<>();
		//따라서 이렇게 IDE 코드 어시스트를 받기 수월
		g1.value = "Hello World";
		Generic<Integer> g2 = new Generic<>();
		//컴파일 시점에 이미 타입이 명확하기에 같은 다양한 값 활용 가능
		g2.value = Integer.MAX_VALUE;
		
		
		//용어 설명 Generic<String> == > <"String"> 타입변수 or 타입파라미터
		// "Generic<String>" 이 자체(지네릭이 적용된 클래스)는 파라미터화된 타입(매개변수화된 타입) 
	}
	
	static class TypeToken01{
		//변경없이 한 종류가 아닌 다양한 클래스를 만들고 싶다.
		static Object create() {
			return new Object();
		}
		//지네릭 타입 토큰
		static <T> T create(Class<T> clazz) throws Exception{
			//지네릭 타입은 컴파일 시점에 타입이 결정되어 있기 때문에 타입 안정성이 높다
			return clazz.newInstance(); //그 증거로 개발자가 코드로 형변환 할 필요없다.(type erasure) _ (T)clazz.newInstance()
		}
		static void examNonTypeSafe() {
			Object o = "123";
			o = (Integer)o;  // 컴파일러가 잡아내지 못한다.
			//컴파일러가 잡지 못한다. == 일단 구동된다. 
			//이후 이 메서드가 호출되는 순간 시스템에 문제가 된다.
			//즉, 언제 밟을지도 모르는 지뢰가 코드에 스며들게 된다.
			//결과적으로 개발자가 강제로 형변환 코드를 넣는 것은 매우 위험하고, 타입 안정성이 떨어지는 코드이다.
		}
		
	}
	//지네릭은 코드 구조는 유사한데 타입만 다른 경우 사용
	static class Generic<T>{
		T value;
		public T getValue() {	return value;	}
		public void setValue(T value) {	this.value = value;	}
	}
}