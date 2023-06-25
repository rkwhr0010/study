package javabasic.toby;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class SuperTypeToken02 {
	
	static class Sup<T>{
		T value;
	}
	//super 타입의 지네릭 정보는 런타임 시 지워지지 않고, 바이트 코드에 남아있다.
	static class Sub extends Sup<String>{	}
	
	public static void main(String[] args) throws Exception {
		Sup<String> sup = new Sup<>();
		sup.value = "asd";
		
		//java.lang.Object
		System.out.println(sup.getClass().getDeclaredField("value").getType());
		
		//지네릭 정보를 런타임에 읽을 수 있는 상황예시
		Sub sub = new Sub();
		Type type = sub.getClass().getGenericSuperclass();
		ParameterizedType pType = (ParameterizedType) type;
		//타입이 런타임에 지워지지 않는다.
		//java.lang.String
		System.out.println(pType.getActualTypeArguments()[0]);
		
		Sup<String> sup2 = new Sup<String>() {};
		Type type2 = sup2.getClass().getGenericSuperclass();
		ParameterizedType pType2 = (ParameterizedType) type2;
		//java.lang.String
		//바이트 코드에 정보가 남아 있기 때문에 아무리 중첩되도 정보를 다 가져올 수 있다.
		System.out.println(pType2.getActualTypeArguments()[0]);
		
		Sup sup3 = new Sup<List<Map<Class<String>,Number>>>() {};
		Type type3 = sup3.getClass().getGenericSuperclass();
		ParameterizedType pType3 = (ParameterizedType) type3;
		//java.lang.String
		System.out.println(pType3.getActualTypeArguments()[0]);
		
	}
}
