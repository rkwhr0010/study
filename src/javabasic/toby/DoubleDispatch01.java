package javabasic.toby;

import java.util.Arrays;
import java.util.List;

public class DoubleDispatch01 {
	static interface Post{
		void postOn(SNS sns);
	}
	static class Text implements Post{
		public void postOn(SNS sns) {
			System.out.println("Text - >  "+sns.getClass().getSimpleName());
		}
	}
	static class Picture implements Post{
		public void postOn(SNS sns) {
			System.out.println("Picture - >  "+sns.getClass().getSimpleName());
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
				//이 p는 다이나믹 디스패치가 이루어진다. 컴파일 시점에 알 수 없고, 런타임 시점에 receiver parameter의 타입을 보고 알 수 있다.
				p.postOn(s);
			}
		}
		
		
	}
}
