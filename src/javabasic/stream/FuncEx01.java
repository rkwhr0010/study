package javabasic.stream;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;
import java.util.function.BiFunction;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;

public class FuncEx01 {
	static class User{
		final Long id;
		final String name;
		final Integer age;
		
		public User(Long id, String name, Integer age) {
			this.id = id;
			this.name = name;
			this.age = age;
		}

		@Override
		public String toString() {
			return "[id=" + id + ", name=" + name + ", age=" + age + "]\n";
		}
		
	}
	
	static List<User> getUsers(){
		return Arrays.asList( 
				new User(10L, "ID", 36),
				new User(20L, "BJ", 32),
				new User(30L, "JM", 32),
				new User(40L, "PJ", 27),
				new User(50L, "HA", 25),
				new User(60L, "JE", 26),
				new User(70L, "JI", 31),
				new User(80L, "MP", 23)
				);
	}
	
	
	public static void main(String[] args) {
		List<User> users = getUsers();
		
		System.out.println("filter = "+
				filter(users,user->user.age>30)+"\n"
				+users
				);
		
		System.out.println("map = "+
				map(users, user->user.id)
				);
		
		each(Arrays.asList(1,2,3,4,5),System.out::print);
		System.out.println();
		System.out.println("reduce = "+
				reduce(Arrays.asList(1,2,3,4,5), (a,b)->a+b, Integer.valueOf(0))
				);
		
		
	}
	/*
	 * filter는 처리 결과가 입력 결과와 타입은 같다.
	 * 길이는 같거나, 작을 수 밖에 없다.
	 */
	static <T> List<T> filter(List<T> list, Predicate<T> predi) {
		//함수형 프로그래밍은 원본 데이터를 수정하지 않는다. 새로운 데이터를 리턴하여
		//부수효과를 극단적으로 배제한다.
		ArrayList<T> newList = new ArrayList<>();
		each(list,data->{
			if(predi.test(data))
				newList.add(data);
		});
		
		return newList;
	}
	/*
	 * map은 처리 결과가 입력 결과와 타입은 같거나 다르다.
	 * 길이는 항상 같다.
	 */
	static <T,R> List<R> map(List<T> list, Function<T, R> mapper){
		ArrayList<R> newList = new ArrayList<>();
		each(list, data->{
			newList.add(mapper.apply(data));
		});
		return newList;
	}
	/*
	 * 반복문을 중복을 대체할 함수
	 */
	static <T> List<T> each(List<T> list, Consumer<T> iter){
		for(T data : list) 
			iter.accept(data);
		return list;
	}
	
	@SuppressWarnings("unchecked")
	/*
	 * 자바는 스트림 안에서 외부 변수를 참조하면 그 변수는 final 속성이 된다. 
	 * 따라서 갱신하면서 누산을 하지 못해, 간접적으로 값을 수정해야 한다.
	 */
	static <T> T reduce(List<T> list,BiFunction<T, T, T> reducer ,T memo) {
		//방법1
		T[] result = (T[])Array.newInstance(memo.getClass(), 1);
		result[0] = memo;
		//방법2
		HashMap<Class<T>, T> map = new HashMap<>();
		Class<T> typeToken = (Class<T>) memo.getClass();
		map.put(typeToken, memo);
		
		each(list, data->{
			result[0] = reducer.apply(result[0], data);
			map.compute(typeToken, (k,v)->reducer.apply(v, data));
		});
//		return result[0];
		return map.get(typeToken);
	}
	
}
