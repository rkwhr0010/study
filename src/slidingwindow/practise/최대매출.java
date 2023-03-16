package slidingwindow.practise;

import java.util.Arrays;
import java.util.stream.IntStream;

public class 최대매출 {
	public static void main(String[] args) {
		int[] moneyArr = IntStream.generate(() -> ((int) (Math.random() * 20) + 10)).limit(10).toArray();
		int period = 3;

		System.out.println(Arrays.toString(moneyArr));

		int maxMoney = 0;

		for (int i = 0; i < period ; i++)
			maxMoney += moneyArr[i];
		
		System.out.println(maxMoney);
		
		for (int i = period, tmpMoney = maxMoney; i < moneyArr.length; i++) 
			maxMoney = Integer.max(maxMoney, tmpMoney - moneyArr[i - period] + moneyArr[i]);
		

		System.out.println(maxMoney);

	}
}
