package algorithm.sortsearching;

import java.util.Random;
import java.util.stream.IntStream;

public class RandomUtils {
	public static int[] 이분검색() {
		return new Random().ints(1, 30).distinct().limit(20).toArray();
	}
	public static int[] 뮤직비디오() {
		return IntStream.rangeClosed(1, 10).toArray();
	}
}
