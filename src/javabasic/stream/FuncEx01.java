package javabasic.stream;

import java.lang.reflect.Field;
import java.util.*;
import java.util.Map.Entry;
import java.util.concurrent.ThreadLocalRandom;
import java.util.function.*;


public class FuncEx01 {
	public static void main(String[] args) {
		List<User> users = getUsers();
		
		
		System.out.println("filter = "+
				StreamUtils.filter(users,user->user.age>30)+"\n"
				+users);
		
		System.out.println("map = "+
				StreamUtils.map(users, user->user.id));
		StreamUtils.each(Arrays.asList(1,2,3,4,5),System.out::print);
		System.out.println();
		
		System.out.println("reduce = "+
				StreamUtils.reduce(Arrays.asList(1,2,3,4,5), (a,b)->a+b, Integer.valueOf(0)));
		System.out.println("reduce = "+
				StreamUtils.reduce(Arrays.asList(1,2,3,4,5), (a,b)->a+b));
		System.out.println("reduce = "+
				StreamUtils.reduce(Arrays.asList(1), (a,b)->a+b, Integer.valueOf(0)));
		System.out.println("reduce = "+
				StreamUtils.reduce(Arrays.asList(1), (a,b)->a+b));
		
		List<String> result = Stream.stream(users)
			.filter(user->user.age > 30)
			.map(user->user.name)
			.toList();
		System.out.println(result);
		//메서드 체이닝 방식과 차이 비교
		System.out.println("default = "+
				StreamUtils.reduce(
							StreamUtils.map(
									StreamUtils.filter(users,user->user.age>30) 
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
				
		//find, findIndex 실습
		System.out.println(StreamUtils.find(users, user->user.age<32));
		System.out.println(StreamUtils.find(users, user->user.age>55));
		System.out.println(StreamUtils.findIndex(users, user->user.age<32));
		System.out.println(StreamUtils.findIndex(users, user->user.age>55));
		System.out.println(Stream.stream(users).find(user->user.age<32));
		System.out.println(Stream.stream(users).find(user->user.age>55));
		System.out.println(Stream.stream(users).findIndex( user->user.age<32));
		System.out.println(Stream.stream(users).findIndex( user->user.age>55));
		
		System.out.println("every , find 테스트");
		System.out.println(StreamUtils.some(users, u->u.age>30));
		System.out.println(StreamUtils.some(users, u->u.age>50));
		System.out.println(StreamUtils.every(users, u->u.age>30));
		System.out.println(StreamUtils.every(users, u->u.age>10));
		System.out.println(Stream.stream(users).some(u->u.age>30));
		System.out.println(Stream.stream(users).some(u->u.age>50));
		System.out.println(Stream.stream(users).every(u->u.age>30));
		System.out.println(Stream.stream(users).every(u->u.age>10));
		
		System.out.println("pluck 테스트");
		System.out.println(StreamUtils.pluck(users, "age",Integer.class));
		//타입불일치
		System.out.println(StreamUtils.pluck(users, "age",String.class));
		System.out.println(Stream.stream(users).pluck("age",Integer.class));
		//타입불일치
		System.out.println(Stream.stream(users).pluck("age",String.class));
		
		System.out.println("reject 테스트");
		System.out.println(StreamUtils.reject(users,u->u.age>30));
		System.out.println(Stream.stream(users).reject(u->u.age>30).toList());
		
		System.out.println("min, max, minBy, maxBy 테스트");
		System.out.println("나이 "+StreamUtils.min(users, (u1,u2)->Integer.compare(u1.age,u2.age)));
		System.out.println("나이 "+StreamUtils.max(users, (u1,u2)->Integer.compare(u1.age,u2.age)));
		System.out.println("이름 "+StreamUtils.min(users, (u1,u2)-> u1.name.compareTo(u2.name) ));
		System.out.println("이름 "+StreamUtils.max(users, (u1,u2)-> u1.name.compareTo(u2.name) ));
		System.out.println("나이 "+StreamUtils.minBy(users, Integer::compare, u->u.age));
		System.out.println("나이 "+StreamUtils.maxBy(users, Integer::compare, u->u.age));
		System.out.println(
				Stream.stream(users)
					.map(u->u.age)
					.max(Integer::compare));
		System.out.println(
				Stream.stream(users)
				.minBy(Long::compare,u->u.id));
		
		System.out.println(StreamUtils.groupBy(users, u->u.age-u.age%10));
		System.out.println(StreamUtils.countBy(users, u->u.age-u.age%10));
		System.out.println(Stream.stream(users).groupBy(u->u.age-u.age%10));
		System.out.println(Stream.stream(users).countBy(u->u.age-u.age%10));
		
		
		
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
	
	
	static class StreamUtils{
		/*
		 * filter는 처리 결과가 입력 결과와 타입은 같다.
		 * 길이는 같거나, 작을 수 밖에 없다.
		 */
		private StreamUtils() {}
		
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
		 * filter 정반대, 주어진 조건에 해당하지 않은 값만 걸러낸다.
		 */
		static <T> List<T> reject(List<T> list, Predicate<T> predi){
			return filter(list, predi.negate());
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
		static <T> Boolean contains(List<T> list, T data){
			return findIndex(list, val-> Objects.equals(val, data) ) != -1 ;
		}
		static <T,R> List<R> pluck(List<T> list, String key,Class<R> typeToken){
			List<R> result = map(list, val-> pluckHelper(val, key, typeToken));
			return some(result, Objects::isNull).booleanValue() ? Collections.emptyList() :result;
		}
		//런타임 시 타입이 확정되는 경우 어쩔 수 없이 Object를 리턴해야 한다.
		static <T,R> R pluckHelper(T val,String key,Class<R> typeToken) {
			try {
				Field field = val.getClass().getDeclaredField(key);
				return typeToken.cast(field.get(val));
			} catch (Exception e) {
				return null;
			}
		}
		/*
		 * reduce 특화 함수
		 */
		static <T> T min(List<T> list, Comparator<T> comparator) {
			return reduce(list, BinaryOperator.minBy(comparator));
		}
		static <T> T max(List<T> list, Comparator<T> comparator) {
			return reduce(list, BinaryOperator.maxBy(comparator));
		}
		//주어진 조건으로 검사한 결과로 최대 최소 값을 판단한다.
		static <T,R> T minBy(List<T> list,Comparator<R> comparator ,Function<T, R> mapper) {
			return reduce(list, (a,b)-> comparator.compare(mapper.apply(a), mapper.apply(b)) > 0 ? b:a);
		}
		static <T,R> T maxBy(List<T> list,Comparator<R> comparator ,Function<T, R> mapper) {
			return minBy(list,comparator.reversed(),mapper);
		}
		

		/*
		 * 그룹 방법 2, 리스트의 요소를 하나로 축약한 다는 점에서 groupBy는 reduce의 특화 메서드여야 한다.
		 * 작성은 까다롭고 가시성이 안좋지만, 어차피 사용자입장에선 방법1과 똑같은 방식으로 호출한다.
		 */
		static <T,R> Map<R,List<T>> groupBy(List<T> list, Function<T,R> mapper){
			BiFunction<Map<R,List<T>> , T, Map<R,List<T>> > bi = (group, val) -> {
				group.compute(mapper.apply(val), (k,v)->{
					if(v == null) v = new ArrayList<>();
					v.add(val);
					return v;
				});
				return group;
			};
			return reduce(list, bi, new HashMap<R, List<T>>());
		}
		//groupBy,countBy 용 reduce 오버로딩
		static <T,R> R reduce(List<T> list, BiFunction<R ,T, R> reducer, R memo) {
			each(list, val-> reducer.apply(memo, val));
			return memo;
		}
		/*
		 * 기본 groupBy로직을 재사용했다. 
		 * 만약 groupBy로직이 변경되면 똑같이 적용받는다.
		 */
		static <T,R> Map<R,Long> countBy(List<T> list, Function<T,R> mapper){
			Map<R, Long> countBy = new HashMap<>();
			for(Entry<R, List<T>> entry:groupBy(list,mapper).entrySet()) 
				countBy.put(entry.getKey(), Long.valueOf(entry.getValue().size()));
			return countBy;
		}
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
		Stream<T> reject(Predicate<T> predi){
			return filter(predi.negate());
		}
		<R> Stream<R> map(Function<T, R> mapper){
			ArrayList<R> newList = new ArrayList<>();
			forEach(data->newList.add(mapper.apply(data)));
			return stream(newList);
		}
		
		void forEach(Consumer<T> iter){
			for(T data : list) iter.accept(data);
		}
		
		Optional<T> reduce(BinaryOperator<T> reducer ,T memo) {
			//간소화된 유효성 검사, 본질을 흐리지 않는 선에서 간략화
			if(memo == null) return reduce(reducer);
			if(this.list.size() < 2) return Optional.of(this.list.get(0));
			HashMap<Class<?>, T> map = new HashMap<>();
			map.put(memo.getClass(), memo);
			forEach(data->map.compute(memo.getClass(), (k,v)-> reducer.apply(v, data)));
			return Optional.of(map.get(memo.getClass()));
		}
		Optional<T> reduce(BinaryOperator<T> reducer) {
			T tmpValue = this.list.get(0);
			if(this.list.size() < 2) return Optional.of(tmpValue);
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
		<R> List<R> pluck(String key,Class<R> typeToken){
			Stream<R> result = map(val-> pluckHelper(val, key, typeToken));
			return result.some(Objects::isNull).booleanValue() ? Collections.emptyList() :result.toList();
		}
		//런타임 시 타입이 확정되는 경우 어쩔 수 없이 Object를 리턴해야 한다.
		<R> R pluckHelper(T val,String key,Class<R> typeToken) {
			try {
				Field field = val.getClass().getDeclaredField(key);
				return typeToken.cast(field.get(val));
			} catch (Exception e) {
				return null;
			}
		}
		
		/*
		 * reduce 특화 함수
		 */
		Optional<T> min(Comparator<? super T> comparator) {
			return reduce(BinaryOperator.<T>minBy(comparator));
		}
		Optional<T> max(Comparator<? super T> comparator) {
			return reduce(BinaryOperator.<T>maxBy(comparator));
		}
		//주어진 조건으로 검사한 결과로 최대 최소 값을 판단한다.
		<R> Optional<T> minBy(Comparator<? super R> comparator ,Function<T, R> mapper) {
			return reduce( (a,b)-> comparator.compare(mapper.apply(a), mapper.apply(b)) > 0 ? b:a);
		}
		<R> Optional<T> maxBy(Comparator<? super R> comparator ,Function<T, R> mapper) {
			return minBy(comparator.reversed(),mapper);
		}
		
		/*
		 * 그룹 방법 2, 리스트의 요소를 하나로 축약한 다는 점에서 groupBy는 reduce의 특화 메서드여야 한다.
		 * 작성은 까다롭고 가시성이 안좋지만, 어차피 사용자입장에선 방법1과 똑같은 방식으로 호출한다.
		 */
		<R> Map<R,List<T>> groupBy(Function<T,R> mapper){
			BiFunction<Map<R,List<T>> , T, Map<R,List<T>> > bi = (group, val) -> {
				group.compute(mapper.apply(val), (k,v)->{
					if(v == null) v = new ArrayList<>();
					v.add(val);
					return v;
				});
				return group;
			};
			return reduce(bi, new HashMap<R, List<T>>());
		}
		//groupBy,countBy 용 reduce 오버로딩
		<R> R reduce(BiFunction<R ,T, R> reducer, R memo) {
			forEach(val-> reducer.apply(memo, val));
			return memo;
		}
		/*
		 * 기본 groupBy로직을 재사용했다. 
		 * 만약 groupBy로직이 변경되면 똑같이 적용받는다.
		 */
		<R> Map<R,Long> countBy(Function<T,R> mapper){
			Map<R, Long> countBy = new HashMap<>();
			for(Entry<R, List<T>> entry:groupBy(mapper).entrySet()) 
				countBy.put(entry.getKey(), Long.valueOf(entry.getValue().size()));
			return countBy;
		}
		
		List<T> toList(){
			return this.list;
		}
		
	}
	
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
	static class User2{
//		static final Long id;
//		final String name;
//		final Integer age;
//		final Boolean gender;
//		final Integer money;
		
	}
	
	static class DB{
		static String[] nameDB = {
				"남재인", "한재인", "신이경", "오유리", "장경완", "봉숙자", "황여진", "심재인", "복미래", "신여진", 
				"배미란", "배영신", "이애정", "송여진", "남영애", "안미란", "문재규", "홍숙자", "장경님", "양경님", 
				"양장미", "추자경", "백장미", "권민서", "전재규", "윤재규", "전지해", "설재신", "배경완", "황보혜린",
				 "정무열", "조경구", "남성한", "조경모", "남치원", "유병헌", "최성한", "윤남규", "문성한", "강승헌", 
				 "백성한", "표경구", "조치원", "오재섭", "하경구", "정요한", "송광조", "백재범", "안남규", "배덕수", 
				 "노광조", "복일성", "안재범", "임경구", "유무영", "남궁덕수", "하치원", "하동건", "유무영", "유무영",
				 "성란", "임재", "심진", "설린", "강현", "전진", "이성", "사공리", "탁재", "복상", "하진", "이상", 
				 "심리", "송설", "조진", "문성", "문지", "임린", "예현", "손진", "유리", "전현", "유재", "배은", 
				 "예상", "황성", "임진", "심재", "백현", "한리", "남훈", "심광", "예철", "정건", "하웅", "권훈", 
				 "성훈", "한훈", "하훈", "심광", "성호", "봉건", "류혁", "노훈", "서훈", "권훈", "윤호", "송건", 
				 "윤광", "하웅", "노호", "이철", "문혁", "추철", "정광", "한호", "예철", "오훈", "사공웅", "고훈",
				 "제갈다솜", "하햇살", "양보다", "정자람", "전우리", "노보람", "최한울", "봉빛나", "장비", "전누리", 
				 "전두리", "전마음", "예한별", "김은별", "김민들레", "홍자람", "안꽃", "전나빛", "안아름", "고보름", 
				 "백나라", "전아람", "설빛나", "강나비", "문샛별", "유새벽", "성여름", "남다솜", "양하다", "권하루",
				 "손우람", "허버들", "봉미르", "남궁나라우람", "노힘찬", "황보달", "류빛가람", "윤나라우람", "유미르", 
				 "황보힘찬", "이믿음", "남궁샘", "남한길", "황보나길", "한한길", "전나라우람", "최한길", "권한길", "임믿음", 
				 "고한결", "설믿음", "황샘", "표나길", "안달", "양샘", "임달", "황빛가람", "홍한길", "제갈한결", "배버들",
				 "강광일", "송세은", "문준웅", "백은선", "설서우", "강경희", "권윤미", "봉재경", "표수민", "조동근", 
				 "추진석", "황민숙", "남원석", "심시준", "이선우", "조정우", "유태일", "추경윤", "권규환", "임은주", 
				 "표연웅", "류창기", "풍병수", "서인숙", "남궁명욱", "박시현", "전창현", "남궁주원", "이우태", "사공혜윤"
		};
		static void users() {
			ThreadLocalRandom current = ThreadLocalRandom.current();
			current.nextInt(97, 122+1);
			
		}
		
//		static getWord() {
//			return current.nextInt(97, 122+1)
//		}
		
		
	}
	
	
}
