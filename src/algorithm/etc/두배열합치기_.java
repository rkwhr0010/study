package algorithm.etc;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;
import java.util.stream.IntStream;

public class 두배열합치기_ {
	public static void main(String[] args) {
		
		int[] is1 = new Random().ints((int)(Math.random()*5000), Integer.MIN_VALUE, Integer.MAX_VALUE)
					.toArray();
		int[] is2 = new Random().ints((int)(Math.random()*5000), Integer.MIN_VALUE, Integer.MAX_VALUE)
					.toArray();
		
		int[] copyOf1 = Arrays.copyOf(is1, is1.length);
		int[] copyOf2 = Arrays.copyOf(is2, is2.length);
		
		
		StopWatch sw = new StopWatch();
		
		ArrayList<Integer> list1 = new ArrayList<Integer>();
		int p1=0,p2=0;
		
		sw.start();
		Arrays.sort(is1);
		Arrays.sort(is2);
		
		while(p1<is1.length && p2<is2.length) {
			if(is1[p1]<is2[p2] ) {
				list1.add(is1[p1++]);
			}else {
				list1.add(is2[p2++]);
			}
		}
		while(p1<is1.length) {
			list1.add(is1[p1++]);
		}
		while(p2<is2.length) {
			list1.add(is2[p2++]);
		}
		sw.stop();
		System.out.println("소요시간 : " + sw.time());
		
		sw.start();
		
		IntStream.concat(IntStream.of(copyOf1), IntStream.of(copyOf2))
		         .sorted()
		         .toArray();
		sw.stop();
		
		System.out.println("소요시간 : " + sw.time());
		
	}
}

class StopWatch{
	long startTime;
	long endTime;
	
	
	public long start() {
		return this.startTime = System.nanoTime();
	}
	
	public long stop() {
		return this.endTime = System.nanoTime();
	}
	
	public long time() {
		if(startTime == 0 && endTime == 0) {
			return -1;
		}
		return endTime - startTime;
	}
	
}


