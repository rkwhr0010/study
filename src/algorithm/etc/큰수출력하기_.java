package algorithm.etc;
import java.util.Arrays;
import java.util.Random;

// 저장소를 두고 나보다 큰 값이 존재할 때 마다 갱신해준다.
public class 큰수출력하기_ {
	static int count1 = 0;
	public static void main(String[] args) {
		int count2 = 0;
		//사용한 스트림은 닫힌다.
		
		int[] array = new Random().ints(100000000, 1, 500000000).toArray();
		
		long start = System.nanoTime();
		int maxInt = Arrays.stream(array).parallel()
				           .reduce(Integer.MIN_VALUE, (a, b) -> {
				     	      	if(a<b) {
				     	      		System.out.println(a+"   "+b);
				     	      		큰수출력하기_.count1++;
				     	      		return b;
				     	      	}else {
				     	      		return a;
				     	      	}
				           	});
		long time1 = System.nanoTime()-start;
		System.out.println("스트림 소요시간 : " + time1);
		System.out.println("=====================");
		
		start = System.nanoTime();
 		int tmp = Integer.MIN_VALUE;
		for(int i=0;i<array.length;i++) {
			if(tmp<array[i]) {
				System.out.println(tmp+"  "+array[i]);
				count2++;
				tmp=array[i];
			}
		}
		long time2 = System.nanoTime()-start;
		System.out.println("반복문 소요시간 : " + time2);
		System.out.println("=====================");
		System.out.println("큰 수1 : "+ maxInt);
		System.out.println("큰 수2 : "+ tmp);
		System.out.println("몇 번 바뀜1? : " + 큰수출력하기_.count1);
		System.out.println("몇 번 바뀜2? : " + count2);
		
		System.out.println(time1<time2?"스트림 승":"반복문 승");
		
	}
}
