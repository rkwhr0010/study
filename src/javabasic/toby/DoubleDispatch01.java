package javabasic.toby;

import java.util.Arrays;
import java.util.List;

public class DoubleDispatch01 {
	/*문제해결 시도 2 - 더블 디스패칭 이용 */
	static interface Post{
		void postOn(SNS sns);
	}
	static class Text implements Post{
		public void postOn(SNS sns) {
			/*자기 자신을 넘김
			 * 이 시점에 다이나믹 디스패칭이 일어나, sns에 receiver parameter 보고 어떤 메서드를 실행할지 알게된다. */
			sns.post(this);
		}
	}
	static class Picture implements Post{
		public void postOn(SNS sns) {
			sns.post(this);
		}
	}
	
	static interface SNS{
		/*로직이 여기로 이동*/
		void post(Text post);
		void post(Picture post);
	}
	static class Facebook implements SNS{
		public void post(Text post) {
			System.out.println("Facebook - Text");
		}
		public void post(Picture post) {
			System.out.println("Facebook - Picture");
		}
	}
	static class Twitter implements SNS{
		public void post(Text post) {
			System.out.println("Twitter - Text");
		}
		public void post(Picture post) {
			System.out.println("Twitter - Picture");
		}
	}
	/*새로 추가된 SNS가 별도 다른 코드를 손댈 필요없이 잘 동작하게 된다. OCP 준수*/
	static class GooglePlus implements SNS{
		public void post(Text post) {
			System.out.println("GooglePlus - Text");
		}
		public void post(Picture post) {
			System.out.println("GooglePlus - Picture");
		}
	}
	
	public static void main(String[] args) {
		List<Post> posts = Arrays.asList(new Text(),new Picture());
		List<SNS> sns = Arrays.asList(new Facebook(), new Twitter(), new GooglePlus());
		
		for(Post p : posts) {
			for(SNS s : sns) {
				/* 이전에 안된 이유
				 * 파라미터는 다이나믹 디스패치 대상이 아니다. 그래서 스테틱 디스패칭이 일어나 컴파일 시점에 명확한 정보가 없었다.
				 * */
				p.postOn(s);
			}
		}
	}
}
