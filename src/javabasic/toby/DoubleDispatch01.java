package javabasic.toby;

import java.util.Arrays;
import java.util.List;

public class DoubleDispatch01 {
	
	static interface Post{
		/*문제해결 시도 1 메서드 오버로딩 이용하기 */
		void postOn(Facebook sns);
		void postOn(Twitter sns);
	}
	static class Text implements Post{
		public void postOn(Facebook sns) {
			System.out.println("Text - >  Facebook");
		}
		public void postOn(Twitter sns) {
			System.out.println("Text - >  Twitter");
		}
	}
	static class Picture implements Post{
		public void postOn(Facebook sns) {
			System.out.println("Picture - >  Facebook");
		}
		public void postOn(Twitter sns) {
			System.out.println("Picture - >  Twitter");
		}
	}
	
	static interface SNS{	}
	static class Facebook implements SNS{
	}
	static class Twitter implements SNS{
	}
	
	public static void main(String[] args) {
		List<Post> posts = Arrays.asList(new Text(),new Picture());
		List<SNS> sns = Arrays.asList(new Facebook(), new Twitter());
		
		for(Post p : posts) {
			for(SNS s : sns) {
				//오버로딩된 메서드 선택은 스테틱 디스패칭(컴파일 시점)을 통해서 이워진다.
				//따라서 어떤 오버로딩된 메서드를 실행할지 몰라서 컴파일 시점에 에러가 난다.
				p.postOn(s);
//				p.postOn((Facebook) s); 아래 둘 중 뭐가 해당되는지 알수 없다.
//				p.postOn((Twitter) s);
			}
		}
	}
}
