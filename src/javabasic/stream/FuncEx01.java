package javabasic.stream;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;
import java.util.function.BinaryOperator;
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
		/*
		System.out.println("filter = "+
				filter(users,user->user.age>30)+"\n"
				+users);
		
		System.out.println("map = "+
				map(users, user->user.id));
		each(Arrays.asList(1,2,3,4,5),System.out::print);
		System.out.println();
		
		System.out.println("reduce = "+
				reduce(Arrays.asList(1,2,3,4,5), (a,b)->a+b, Integer.valueOf(0)));
		System.out.println("reduce = "+
				reduce(Arrays.asList(1,2,3,4,5), (a,b)->a+b));
		System.out.println("reduce = "+
				reduce(Arrays.asList(1), (a,b)->a+b, Integer.valueOf(0)));
		System.out.println("reduce = "+
				reduce(Arrays.asList(1), (a,b)->a+b));
		
		List<String> result = Stream.stream(users)
			.filter(user->user.age > 30)
			.map(user->user.name)
			.toList();
		System.out.println(result);
		//메서드 체이닝 방식과 차이 비교
		System.out.println("default = "+
					reduce(
							map(
								filter(users,user->user.age>30) 
								, user->user.age)
							, (a,b)->a+b));
		
		//기존은 안쪽에서부터 밖으로 나온다. 중첩구조가 심해질 수록 가독성이 안좋아진다.
		//스트림을 통한 메서드 체이닝 방식은 순차적으로 적용된다.
		
		System.out.println("stream = " +
				Stream.stream(users)
					.filter(user->user.age > 30)
					.map(user->user.age)
					.reduce(Integer::sum)
//					.reduce((a,b)->a+b)
				);
		*/
		//find, findIndex 실습
		System.out.println(find(users, user->user.age<32));
		System.out.println(find(users, user->user.age>55));
		System.out.println(findIndex(users, user->user.age<32));
		System.out.println(findIndex(users, user->user.age>55));
		System.out.println(Stream.stream(users).find(user->user.age<32));
		System.out.println(Stream.stream(users).find(user->user.age>55));
		System.out.println(Stream.stream(users).findIndex( user->user.age<32));
		System.out.println(Stream.stream(users).findIndex( user->user.age>55));
		
		System.out.println("every , find 테스트");
		System.out.println(some(users, u->u.age>30));
		System.out.println(some(users, u->u.age>50));
		System.out.println(every(users, u->u.age>30));
		System.out.println(every(users, u->u.age>10));
		System.out.println(Stream.stream(users).some(u->u.age>30));
		System.out.println(Stream.stream(users).some(u->u.age>50));
		System.out.println(Stream.stream(users).every(u->u.age>30));
		System.out.println(Stream.stream(users).every(u->u.age>10));
		
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
		each(list, data->newList.add(mapper.apply(data)));
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
	
	/*
	 * 자바는 스트림 안에서 외부 변수를 참조하면 그 변수는 final 속성이 된다. 
	 * 따라서 갱신하면서 누산을 하지 못해, 간접적으로 값을 수정해야 한다.
	 */
	static <T> T reduce(List<T> list, BinaryOperator<T> reducer ,T memo) {
		//간소화된 유효성 검사, 본질을 흐리지 않는 선에서 간략화
		if(memo == null ) return reduce(list.subList(1, list.size()), reducer, list.get(0));
		if(list.size() < 2) return list.get(0);
		HashMap<Class<?>, T> map = new HashMap<>();
		map.put(memo.getClass(), memo);
		
		each(list, data->map.compute(memo.getClass(), (k,v)->reducer.apply(v, data)));
		return map.get(memo.getClass());
	}
	/*
	 * 자바는 람다에서 사용하는 변수는 final 속성을 띄기 때문에 memo 값 처리가 애매해진다.
	 * 삼항연산자 등 별도 처리하기보다 오버로딩으로 처리했다.
	 */
	static <T> T reduce(List<T> list, BinaryOperator<T> reducer) {
		if(list.size() < 2) return list.get(0);
		return reduce(list.subList(1, list.size()), reducer, list.get(0));
	}
	/*
	 * null 값을 간접적으로 다루기 위한 래퍼클래스 사용
	 */
	static <T> Optional<T> find(List<T> list, Predicate<T> predi) {
		for(int i=0;i<list.size();i++) {
			T value = list.get(i);
			if(predi.test(value)) return Optional.of(value);
		}
		return Optional.empty();//편의상 null리턴
	}
	static <T> Integer findIndex(List<T> list, Predicate<T> predi) {
		for(int i=0;i<list.size();i++) {
			if(predi.test(list.get(i))) return i;
		}
		return -1;
	}
	
	static <T> Boolean some(List<T> list, Predicate<T> predi) {
		return findIndex(list, predi) != -1;
	}
	static <T> Boolean every(List<T> list, Predicate<T> predi) {
		return findIndex(list, predi.negate()) == -1;
	}
	
	
	/*
	 * pipe, go 구현을 시도하려 했지만, 근본적으로 자바는 함수가 개념이 없어
	 * 호출부를 단일로 추상화할 수 없다. apply, test ... 
	 * 따라서 하나의 클래스로 묶었다.
	 */
	static class Stream<T> {
		List<T> list;
		
		static <T> Stream<T> stream(List<T> list){
			Stream<T> stream = new Stream<>();
			stream.list = list;
			return stream;
		}
		Stream<T> filter(Predicate<T> predi) {
			//함수형 프로그래밍은 원본 데이터를 수정하지 않는다. 새로운 데이터를 리턴하여
			//부수효과를 극단적으로 배제한다.
			ArrayList<T> newList = new ArrayList<>();
			forEach(data->{
				if(predi.test(data))
					newList.add(data);
			});
			return stream(newList);
		}
		<R> Stream<R> map(Function<T, R> mapper){
			ArrayList<R> newList = new ArrayList<>();
			forEach(data->newList.add(mapper.apply(data)));
			return stream(newList);
		}
		
		void forEach(Consumer<T> iter){
			for(T data : list) iter.accept(data);
		}
		
		T reduce(BinaryOperator<T> reducer ,T memo) {
			//간소화된 유효성 검사, 본질을 흐리지 않는 선에서 간략화
			if(memo == null) return reduce(reducer);
			if(this.list.size() < 2) return this.list.get(0);
			HashMap<Class<?>, T> map = new HashMap<>();
			map.put(memo.getClass(), memo);
			each(this.list, data->map.compute(memo.getClass(), (k,v)->{
				T result = reducer.apply(v, data);
				return result;
			}));
			return map.get(memo.getClass());
		}
		T reduce(BinaryOperator<T> reducer) {
			T tmpValue = this.list.get(0);
			if(this.list.size() < 2) return tmpValue;
			this.list = this.list.subList(1, list.size());
			return reduce(reducer, tmpValue);
		}
		
		Optional<T> find(Predicate<T> predi) {
			for(int i=0;i<this.list.size();i++) {
				T value = this.list.get(i);
				if(predi.test(value)) return Optional.of(value);
			}
			return Optional.empty();//편의상 null리턴
		}
		Integer findIndex(Predicate<T> predi) {
			for(int i=0;i<this.list.size();i++) {
				if(predi.test(this.list.get(i))) return i;
			}
			return -1;//편의상 null리턴
		}
		
		Boolean some(Predicate<T> predi) {
			return findIndex(predi) != -1;
		}
		Boolean every(Predicate<T> predi) {
			return findIndex(predi.negate()) == -1;
		}
		
		List<T> toList(){
			return this.list;
		}
		
	}
	
	
}
