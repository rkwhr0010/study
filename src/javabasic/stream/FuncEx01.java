package javabasic.stream;

import static java.util.stream.Collectors.counting;
import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.summarizingLong;
import static java.util.stream.Collectors.toList;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.LongSummaryStatistics;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Objects;
import java.util.Optional;
import java.util.TreeMap;
import java.util.concurrent.ThreadLocalRandom;
import java.util.function.BiFunction;
import java.util.function.BinaryOperator;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.IntStream;


public class FuncEx01 {
	private static final int USER_SIZE = 100;
	
	static <T> void println(T data) {
		System.out.println(data);
	}
	
	
	public static void main(String[] args) {
		List<User> users = DB.users();
		
		
		example(users);
	}


	private static void example(List<User> users) {
		/*
		 * 실습, 만든 Stream과 실제 java Stream 비교
		 * 단, 자바 Stream.toList()의 경우 JDK 16부터 추가된 것이라 collect()메서드를 사용했다.
		 */
		//나이가 30대, 남자, 돈이 250_000원 이상 있는 사람
		List<User> list = Stream.stream(users)
			.filter(u->u.age-u.age%10 == 30)
			.filter(u->"남자".equals(u.gender))
			.filter(u->u.money>=250_000)
			.toList();
		List<User> list2 = users.stream()
			.filter(u->u.age-u.age%10 == 30)
			.filter(u->"남자".equals(u.gender))
			.filter(u->u.money>=250_000)
			.collect(toList());  
		println("비교 결과 = " + list.containsAll(list2)+"\n"+list);
		println("\n");
		
		
		//나이가 30대, 남자, 돈이 250_000원 이상 있는 사람의 돈의 합
		Optional<Integer> reduce = Stream.stream(users)
			.filter(u->u.age-u.age%10 == 30)
			.filter(u->"남자".equals(u.gender))
			.map(u->u.money)
			.filter(m->m>=250_000)
			.reduce(Integer::sum);
		Optional<Integer> reduce2 = users.stream()
			.filter(u->u.age-u.age%10 == 30)
			.filter(u->"남자".equals(u.gender))
			.map(u->u.money)
			.filter(m->m>=250_000)
			.reduce(Integer::sum);
		println(reduce+"  "+reduce2);
		println("비교 결과 = " + reduce.equals(reduce2));
		println("\n");
		
		
		//20대 여자 중에 kakao 이메일을 쓰는 사람
		int[] cnt1 = new int[] {0};
		
		List<User> list3 = Stream.stream(users)
			.filter(u-> {cnt1[0]++; return u.email.contains("@kakao");} )//분포도가 좋다.
			.filter(u-> {cnt1[0]++; return u.age/10 == 2;})
			.filter(u-> {cnt1[0]++; return Objects.equals("여자", u.gender);})
			.toList();
		//스트림 순서도 중요하다. 분포도를 따졌을 때 첫번째 필터 조건에서 최대한 걸러내야 
		//다음 로직에 넘겨줄 요소가 적기 때문이다.
		
		int[] cnt2 = new int[] {0};
		List<User> collect = users.stream()
			.filter(u-> {cnt2[0]++; return Objects.equals("여자", u.gender);})//분포도가 안좋다.
			.filter(u-> {cnt2[0]++; return u.email.contains("@kakao");} )
			.filter(u-> {cnt2[0]++; return u.age/10 == 2;})
			.collect(toList());
		println("비교 결과 = " + list3.containsAll(collect)+"\n"+list3);
		println("cnt 비교 결과 = cnt1("+cnt1[0]+") cnt2("+cnt2[0]+")");
		println("\n");
		//저 요인 만이 전부는 아니다. 자바 Stream은 지연로딩 기법으로 최종연산 전까진 로직을 수행하지 않는다.
		//최종 연산이 호출된 순간 리스트 속 요소 하나하나가 스트림 중간연산을 통과한다.
		//모든 요소가 중간연산을 통과하고 그 결과를 다음 중간연산으로 보내는 방식이 아니다.
		cnt1[0] = 0;
		cnt2[0] = 0;
		
		long count1 = Stream.stream(users)
				.map(u->{cnt1[0]++; return u.age;})
				.filter(age->{cnt1[0]++; return age>40;})
				.count();
		long count2 = list.stream()
			.map(u->{cnt2[0]++; return u.age;})
			.filter(age->{cnt2[0]++; return age>40;})
			.count();
		println("비교 결과 = " + (count1==count2));
		println("내가 만든 stream은 최적화가 전혀 없기 때문에 users 크기 * 2 (map,filter) 만큼 반복될 것이다.");
		println(cnt1[0]+"   "+cnt2[0]);
		println("\n");
		
		
		//남자, 여자 수
		Map<String, Long> countBy = Stream.stream(users)
			.countBy(u->u.gender);
		
		Map<String, Long> collect2 = users.stream()
			.collect(groupingBy(u->u.gender, counting()));
		println("비교 결과 = " + countBy.entrySet().equals(collect2.entrySet()) +"\n"+collect2);
		println("\n");
		
		
		//40대 남자, 30대 여자 유저
		Map<String, List<User>> groupBy = Stream.stream(users)
			.filter(u-> (u.age/10==3 && Objects.equals("여자", u.gender))
					|| u.age/10==4 && Objects.equals("남자", u.gender))
			.groupBy(u-> Objects.equals("남자", u.gender) 
					? "40대 남자" :"30대 여자");
		Map<String, List<User>> collect3 = users.stream()
			.filter(u-> (u.age/10==3 && Objects.equals("여자", u.gender))
					|| u.age/10==4 && Objects.equals("남자", u.gender))
			.collect(groupingBy(u-> Objects.equals("남자", u.gender) 
					? "40대 남자" :"30대 여자"));
		println("비교 결과 = " + groupBy.entrySet().equals(collect3.entrySet()) +"\n"+collect3);
		println("\n");
		
		
		//새대별 남,여 자산 정보
		//이 정도로 그룹화하는 정보는 현재 구현한 스트림으로 불가
		Map<String, Map<Integer, LongSummaryStatistics>> collect4 = users.stream()
			.collect((groupingBy(u->u.gender
					,groupingBy(u->u.age/10*10, TreeMap::new ,summarizingLong(u->u.money)))));
		collect4.forEach((gender,data)->{
			println(gender);
			data.forEach((age, data2)-> println(age+" "+data2));
		});
		
		
		//메일별, 세대 , 남녀 사용자
		TreeMap<String, TreeMap<Integer, Map<String, List<User>>>> collect5 = users.stream()
			.collect(groupingBy(u->u.email.substring(u.email.indexOf("@")+1, u.email.lastIndexOf(".")), TreeMap::new ,
						groupingBy(u->u.age/10*10,TreeMap::new,
							groupingBy(u->u.gender))));
		collect5.forEach( (email, data1)->{
			println(email);
			data1.forEach((age,data2)->{
				println("  "+age);
				data2.forEach((gender,user)->{
					println("    "+gender);
					user.forEach(u->println("      "+u));
				});
			});
		});
	}
	
	static class StreamUtils{
		private StreamUtils() {}
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
		
	}
	
	
	
	/*
	 * pipe, go 구현을 시도하려 했지만, 근본적으로 자바는 함수가 개념이 없어
	 * 호출부를 단일로 추상화할 수 없다. apply, test ... 
	 * 따라서 하나의 클래스로 묶었다.
	 */
	static class Stream<T> {
		final List<T> list;
		
		private Stream(List<T> list) {
			this.list = list;
		}
		
		static <T> Stream<T> stream(List<T> list){
			return new Stream<>(list);
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
			if(memo == null) return reduce(reducer);
			if(this.list.size() < 2) return Optional.of(this.list.get(0));
			//람다에서 참조하는 변수는 final 특성을 부여받는다. 따라서 간접적으로 값을 갱신하기 위해 Map을 활용
			HashMap<String, T> reduceStore = new HashMap<>();
			String accumulation = "누산용임시키";
			reduceStore.put(accumulation, memo);
			forEach(data->reduceStore.compute(accumulation, (k,v)-> reducer.apply(v, data)));
			return Optional.of(reduceStore.get(accumulation));
		}
		Optional<T> reduce(BinaryOperator<T> reducer) {
			T tmpValue = this.list.get(0);
			if(this.list.size() < 2) return Optional.of(tmpValue);
			List<T> subList = this.list.subList(1, list.size());
			return Stream.stream(subList).reduce(reducer, tmpValue);
		}
		//groupBy,countBy 용 reduce 오버로딩
		<R> R reduce(BiFunction<R ,T, R> reducer, R memo) {
			forEach(val-> reducer.apply(memo, val));
			return memo;
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
		 * 주어진 조건에 따라 그룹화
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
		Optional<T> find(Predicate<T> predi) {
			for(int i=0;i<this.list.size();i++) {
				T value = this.list.get(i);
				if(predi.test(value)) return Optional.of(value);
			}
			return Optional.empty();//편의상 null리턴
		}
		Integer findIndex(Predicate<T> predi) {
			for(int i=0;i<this.list.size();i++) 
				if(predi.test(this.list.get(i))) return i;
			return -1;
		}
		Boolean some(Predicate<T> predi) {
			return findIndex(predi) != -1;
		}
		Boolean every(Predicate<T> predi) {
			return findIndex(predi.negate()) == -1;
		}
		<R> List<R> pluck(String key,Class<R> typeToken){
			Stream<R> result = map(val-> pluckHelper(val, key, typeToken));
			return result.some(Objects::isNull).booleanValue() ? 
					Collections.emptyList() :result.toList();
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
		List<T> toList(){
			//방어적 복사
			return new ArrayList<>(this.list);
		}
		long count() {
			return this.list.size();
		}
		
	}
	
	static class User{
		private static long serial;
		final long id;
		final String name;
		final int age;
		final String gender;
		final int money;
		final String email; 
		public User(String name, int age, String gender, int money, String email) {
			id = ++serial;
			this.name = name;
			this.age = age;
			this.gender = gender;
			this.money = money;
			this.email = email;
		}
		public String toString() {
			return "[id=" + id + ", name=" + name + ", age=" + age + ", gender=" + gender + ", money=" + money
					+ ", email=" + email + "]\n";
		}
		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + (int) (id ^ (id >>> 32));
			result = prime * result + ((name == null) ? 0 : name.hashCode());
			result = prime * result + age;
			result = prime * result + ((gender == null) ? 0 : gender.hashCode());
			result = prime * result + money;
			result = prime * result + ((email == null) ? 0 : email.hashCode());
			return result;
		}
		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			User other = (User) obj;
			if (id != other.id)
				return false;
			if (name == null) {
				if (other.name != null)
					return false;
			} else if (!name.equals(other.name))
				return false;
			if (age != other.age)
				return false;
			if (gender == null) {
				if (other.gender != null)
					return false;
			} else if (!gender.equals(other.gender))
				return false;
			if (money != other.money)
				return false;
			if (email == null) {
				if (other.email != null)
					return false;
			} else if (!email.equals(other.email))
				return false;
			return true;
		}
		
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
		static String[] mailDB = {
				"@naver.com", "@nate.com" , "@daum.net" , "@kakao.com" , "@gmail.com" , "@outlook.com",
				"@hotmail.com" , "@yahoo.com"
		};
		static String[] gender = {
			"남자", "여자"	
		};
		
		static ThreadLocalRandom ran = ThreadLocalRandom.current();
		static List<User> users() {
			return IntStream.range(0, USER_SIZE)
					.mapToObj(n-> getUser())
					.collect(Collectors.toList());
		}
		static User getUser() {
			return new User(getUsername(), getAge(), getGender(), getMoney(), getEmail());
		}
		
		static String getUsername() {
			return nameDB[ran.nextInt(nameDB.length)];
		}
		
		static String getEmail() {
			StringBuilder sb = new StringBuilder();
			for(int i=0,len=ran.nextInt(5, 15);i<len;i++) 
				sb.append(getWord());
			sb.append(mailDB[ran.nextInt(mailDB.length)]);
			return sb.toString();
		}
		
		static char getWord() {
			return (char)ran.nextInt(97, 122+1);
		}
		static String getGender() {
			return gender[ran.nextInt(gender.length)];
		}
		static int getAge() {
			return ran.nextInt(10, 80);
		}
		static int getMoney() {
			return ran.nextInt(10_000, 1_000_000);
		}
	}
}
