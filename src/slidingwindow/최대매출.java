package slidingwindow;

import java.util.Arrays;
import java.util.stream.IntStream;

public class 최대매출 {
	public static void main(String[] args) {
		int[] moneyArr = IntStream.generate(()->((int)(Math.random()*10)+10))
			.limit(10)
			.toArray();
		
		int period = 3;
		
		System.out.println(Arrays.toString(moneyArr));

		int max = 0;
		
		for (int i = 0; i < period; i++) max+=moneyArr[i];
		
		for (int i = period,tmp=max ; i < moneyArr.length; i++) {
			tmp = tmp-moneyArr[i-period]+moneyArr[i];
			max = Integer.max(max, tmp);
		}
		System.out.println(max);
		
	}
}
