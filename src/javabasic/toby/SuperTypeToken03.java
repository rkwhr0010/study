package javabasic.toby;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class SuperTypeToken03 {
	static class TypesafeMap{
		Map<TypeReference<?>, Object> map = new HashMap<>();
		
		<T> void put(TypeReference<T> tr, T v) {
			map.put(tr, v);
		}
		<T> T get(TypeReference<T> tr) {
			//단순 타입이면 문제없지만, 컨테이너 타입이면 캐스팅을 못한다. 
			//<String> ok ,  <List<String>> not ok
			return ((Class<T>)tr.type).cast(map.get(tr));
		}
	}
	
	static class TypeReference<T>{
		//지네릭 정보까지 포함한 정보를 저장한 필드
		Type type;
		public TypeReference() {
			Type stype = this.getClass().getGenericSuperclass();
			//ParameterizedType인 경우는 <T> 에 정보가 들어와있는 것
			if(stype instanceof ParameterizedType) {
				this.type = ((ParameterizedType) stype).getActualTypeArguments()[0];
			} 
			else throw new RuntimeException();
		}
		
		//IDE에서 제공한 자동 생성 코드
		@Override
		public int hashCode() {
			return Objects.hash(type);
		}
		@Override
		public boolean equals(Object obj) {
			if (this == obj) return true;
			//getClass() != obj.getClass() 여기에 걸려 false를 리턴해 현재 동작하지 않는다.
			//우리는 현재 클래스가 아닌 클래스속 Type 이 목적이다.
			if (obj == null || getClass().getSuperclass() != obj.getClass().getSuperclass())
				return false;
			//이 클래스의 익명 클래스를 사용하기 때문에 슈퍼클래스가 익명클래스가 된다.
			TypeReference<?> other = (TypeReference<?>) obj;
			return Objects.equals(type, other.type);
		}
		
	}
	
	public static void main(String[] args) {
		
		TypesafeMap typesafeMap = new TypesafeMap();
		//되는 경우
		TypeReference<String> tr = new TypeReference<String>() {};
		typesafeMap.put(tr, "하나둘");
		System.out.println(typesafeMap.get(tr));
		//안되는 경우
		typesafeMap.put(new TypeReference<Integer>() {}, 123);
		System.out.println(typesafeMap.get(new TypeReference<Integer>() {}));
		//컬렉션에서 요소가 같은 값인지 판단하는 기준은 Object.equals()메소드 결과다.
		//따라서 아래와 같은 경우는 다른 key로 인식하여 값을 가져오지 못한다.
		
		//현재 컨테이너 같은 타입이 들어오면 캐스팅을 못한다.
		typesafeMap.put(new TypeReference<List<String>>() {}, Arrays.asList("하나","둘","셋"));
		typesafeMap.get(new TypeReference<List<String>>() {});
		
		
		
	}
}
