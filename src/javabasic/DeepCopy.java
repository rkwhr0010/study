package javabasic;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Objects;
import java.util.Set;
import java.util.function.Function;
public class DeepCopy {
	static Object[] types = {Integer.class};
	
    public static Object deepCopy(Object obj) throws InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException {
        if (obj == null) {
            return null;
        }
        
        if(obj instanceof Number ||obj instanceof String || obj instanceof Character) {
        	return obj;
        }
        if(obj instanceof Map) {
        	Map<Object,Object> map = new HashMap<>();
        	Set<?> entrySet = ((Map<?,?>)obj).entrySet();
        	for(Object entry : entrySet ) {
        		map.put(((Entry<?, ?>)entry).getKey(), deepCopy(((Entry<?, ?>)entry).getValue()));
        	}
        	return map;
        }
        if(obj instanceof List) {
        	List<Object> list = new ArrayList<>();
        	for(Object val :  ((List) obj)) {
        		list.add(deepCopy(val));
        	}
        	return list;
        }
        

        Object copy = obj.getClass().getDeclaredConstructor().newInstance();
        for (Field field : obj.getClass().getDeclaredFields()) {
            field.setAccessible(true);
            try {
                Object value = field.get(obj);
                field.set(copy, deepCopy(value));
            } catch (IllegalAccessException | InstantiationException e) {
                e.printStackTrace();
            }
        }
        return copy;
    }
    public static void main(String[] args) throws InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException {
		Object copy = deepCopy(new Data());
		System.out.println(copy);
		
	}
    static class Data {
    	static int a = 0;
    	int serial = ++a;
    	Map<String, Data> map = new HashMap<>();
    	List<Data> list = new ArrayList<>();  
    	{
    		init();
    	}
    	
    	void init() {
    		if(a>10) return;
    		map.put("맵1", new Data());
    		map.put("맵2", new Data());
    		map.put("맵3", new Data());
    		
    		list.add(new Data());
    		list.add(new Data());
    		list.add(new Data());
    	}

		@Override
		public String toString() {
			return "Data [serial=" + serial + ", map=" + map + ", list=" + list + "]\n";
		}

    }
    static void call(Function<String,String> fn) {
    	if(Objects.isNull(fn)) throw new IllegalArgumentException();
    }
}