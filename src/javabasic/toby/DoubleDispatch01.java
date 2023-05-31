package javabasic.toby;

import java.util.Arrays;
import java.util.List;

public class DoubleDispatch01 {
	static interface Post{
		void postOn(SNS sns);
	}
	//POST에서 SNS 별로 다른 로직이 필요하다!
	static class Text implements Post{
		public void postOn(SNS sns) {
			//instanceof 로 체크헀다.
			if(sns instanceof Facebook) {
				System.out.println("Text - >  Facebook");
			}
			if(sns instanceof Twitter) {
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
				p.postOn(s);
			}
		}
	}
}
