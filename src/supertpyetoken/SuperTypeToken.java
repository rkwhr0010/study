package supertpyetoken;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SuperTypeToken {
	static class TypeSafeMap{
		Map<Type, Object> map = new HashMap<>();
		<T> void put(TypeReference<T> tr, T value) {
			map.put(tr.type, value);
		}
		@SuppressWarnings("unchecked")
		<T> T get(TypeReference<T> tr) {
			return ((Class<T>)((ParameterizedType)tr.type).getRawType()).cast(map.get(tr.type));
		}
	}
	static class TypeReference<T>{
		Type type;
		public TypeReference() {
			Type superType = getClass().getGenericSuperclass();
			if( superType instanceof ParameterizedType) {
				this.type = ((ParameterizedType)superType).getActualTypeArguments()[0];
			}
			else new RuntimeException("슈퍼 타입 코튼만 사용 가능합니다.");
		}
	}
	public static void main(String[] args) {
		TypeReference<List<Map<List<String>, Map<String, String>>>> st1 = 
				new TypeReference<List<Map<List<String>,Map<String,String>>>>();
		System.out.println(st1.type);
		TypeReference<List<Map<List<String>, Map<String, String>>>> st2 = 
				new TypeReference<List<Map<List<String>,Map<String,String>>>>(){};
		System.out.println(st2.type);
		List<Map<List<String>,Map<String,String>>> list = new ArrayList<>();
		Map<List<String>,Map<String,String>> map = new HashMap<>();
		Map<String,String> map2 = new HashMap<>();
		map2.put("지네릭은","어려워");
		map.put(new ArrayList<>(),map2);
		list.add(map);
		
		TypeSafeMap TSmap = new TypeSafeMap();
		TSmap.put(st2, list); //List<Map<List<String>,Map<String,String>>> 만 저장됨
//		TSmap.put(st2, new ArrayList<String>());  컴파일에러
		
		System.out.println(TSmap.get(st2));
	}
}
