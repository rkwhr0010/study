package javabasic.toby;

import java.util.Arrays;
import java.util.List;

public class DoubleDispatch01 {
	static interface Post{
		void postOn(SNS sns);
	}
	static class Text implements Post{
		public void postOn(SNS sns) {
			if(sns instanceof Facebook) {
				System.out.println("Text - >  Facebook");
			}
			if(sns instanceof Twitter) {
				System.out.println("Text - >  Twitter");
			}
			/* if 문으로 타입체크 후 로직을 결정하면 생기는 단점
			 * 새로운 SNS 추가 시 놓치기가 쉽다. 당장 이 예제에서도 
			 * 동작은 하는데 if 문이 걸리는게 없어 예외도 발생하지 않는다.
			 * 이게 더 큰 문제다. 놓치고 지나치면 언제 알아차릴지도 미지수
			 * 놓치지 않기 위해 예외를 던지도록 설정을 한다면, 어찌됐건 런타임 시점에 문제가 된다.
			 */
			if(sns instanceof GooglePlus) {
				System.out.println("Text - >  Twitter");
			}
		}
	}
	static class Picture implements Post{
		public void postOn(SNS sns) {
			if(sns instanceof Facebook) {
				System.out.println("Picture - >  Facebook");
			}
			if(sns instanceof Twitter) {
				System.out.println("Picture - >  Twitter");
			}
			/* 다른 곳에 GooglePlus를 처리했다고 생각했는데 빼먹을 수도 있다.
			 * 이것도 심각한 로직 에러로 발견도 힘들다.
			 */
		}
	}
	
	static interface SNS{	}
	static class Facebook implements SNS{
	}
	static class Twitter implements SNS{
	}
	static class GooglePlus implements SNS{
	}
	
	public static void main(String[] args) {
		List<Post> posts = Arrays.asList(new Text(),new Picture());
		List<SNS> sns = Arrays.asList(new Facebook(), new Twitter());
		
		for(Post p : posts) {
			for(SNS s : sns) {
				p.postOn(s);
			}
		}
	}
}
