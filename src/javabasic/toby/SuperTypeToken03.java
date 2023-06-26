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
		Map<Type, Object> map = new HashMap<>();
		
		<T> void put(TypeReference<T> tr, T v) {
			map.put(tr.type, v);
		}
		<T> T get(TypeReference<T> tr) {
			//단순 타입이면 문제없지만, 컨테이너 타입이면 캐스팅을 못한다. 
			//<String> ok ,  <List<String>> not ok
			
			if(tr.type instanceof Class<?>)
				return ((Class<T>)tr.type).cast(map.get(tr.type));
			else 
				return ((Class<T>)((ParameterizedType)tr.type).getRawType()).cast(map.get(tr.type));
				
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
	}
	
	public static void main(String[] args) {
		TypesafeMap typesafeMap = new TypesafeMap();
		
		typesafeMap.put(new TypeReference<String>() {}, "하나둘");
		System.out.println(typesafeMap.get(new TypeReference<String>() {}));
		typesafeMap.put(new TypeReference<Integer>() {}, 123);
		System.out.println(typesafeMap.get(new TypeReference<Integer>() {}));
		typesafeMap.put(new TypeReference<List<String>>() {}, Arrays.asList("하나","둘","셋"));
		System.out.println(typesafeMap.get(new TypeReference<List<String>>() {}));
		typesafeMap.put(new TypeReference<List<List<String>>>() {}, Arrays.asList(Arrays.asList("하나"),Arrays.asList("둘"),Arrays.asList("셋")));
		System.out.println(typesafeMap.get(new TypeReference<List<List<String>>>() {}));
		
	}
}
