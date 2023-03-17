package algorithm.sortsearching;

import java.util.Random;

public class RandomUtils {
	public static int[] 이분검색() {
		return new Random().ints(1, 30).distinct().limit(20).toArray();
	}
}
