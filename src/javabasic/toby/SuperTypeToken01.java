package javabasic.toby;

public class SuperTypeToken01 {
	public static void main(String[] args) throws Exception {
		System.out.println(TypeToken01.create().getClass());
		System.out.println(TypeToken01.create(String.class).getClass());
		System.out.println(TypeToken01.create(Integer.class).getClass());//예외발생, 기본 생성자가 없음
//		new Integer()
		
	}
	
	static class TypeToken01{
		//변경없이 한 종류가 아닌 다양한 클래스를 만들고 싶다.
		static Object create() {
			return new Object();
		}
		//지네릭 타입 토큰
		static <T> T create(Class<T> clazz) throws Exception{
			return clazz.newInstance();
		}
		
	}
}
