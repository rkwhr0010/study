package algorithm.sortsearching;

import java.util.Arrays;
import java.util.IntSummaryStatistics;
import java.util.Scanner;

/*
답을 정하고 이 답이 유효한지 확인해 가면서 더 좋은 답을 찾아가는 방식

파라매트릭 서치는 조합 최적화를 위한 알고리즘의 설계 및 분석에서 
결정 알고리즘을 최적화 알고리즘으로 변환하기 위해 Nimrod Megiddo가 발명한 기술입니다. 
계산 기하학에서 최적화 문제를 해결하는 데 자주 사용됩니다.

파라매트릭 서치는 매개변수 값을 조정하여 최적화 문제를 해결합니다. 
매개변수 값을 조정하는 방법은 다음과 같습니다.

1. 매개변수 값의 범위를 정의합니다.
2. 매개변수 값을 범위 내에서 순차적으로 탐색합니다.
3. 매개변수 값을 탐색하면서 최적의 값을 찾습니다. (핵심)

파라매트릭 서치는 결정 알고리즘을 최적화 알고리즘으로 변환하여 최적화 문제를 해결하는 데 효율적입니다.

뮤직비디오(결정알고리즘)
지니레코드에서는 불세출의 가수 조영필의 라이브 동영상을 DVD로 만들어 판매하려 한다.
DVD에는 총 N개의 곡이 들어가는데, DVD에 녹화할 때에는 라이브에서의 순서가 그대로 유지
되어야 한다. 순서가 바뀌는 것을 우리의 가수 조영필씨가 매우 싫어한다. 즉, 1번 노래와 5번
노래를 같은 DVD에 녹화하기 위해서는 1번과 5번 사이의 모든 노래도 같은 DVD에 녹화해야
한다. 또한 한 노래를 쪼개서 두 개의 DVD에 녹화하면 안된다.
지니레코드 입장에서는 이 DVD가 팔릴 것인지 확신할 수 없기 때문에 이 사업에 낭비되는
DVD를 가급적 줄이려고 한다. 고민 끝에 지니레코드는 M개의 DVD에 모든 동영상을 녹화하기
로 하였다. 이 때 DVD의 크기(녹화 가능한 길이)를 최소로 하려고 한다. 그리고 M개의 DVD는
모두 같은 크기여야 제조원가가 적게 들기 때문에 꼭 같은 크기로 해야 한다.

▣ 입력설명
첫째 줄에 자연수 N(1≤N≤1,000), M(1≤M≤N)이 주어진다. 다음 줄에는 조영필이 라이브에서
부른 순서대로 부른 곡의 길이가 분 단위로(자연수) 주어진다. 부른 곡의 길이는 10,000분을
넘지 않는다고 가정하자.

▣ 출력설명
첫 번째 줄부터 DVD의 최소 용량 크기를 출력하세요.
▣ 입력예제 1
9 3
1 2 3 4 5 6 7 8 9
▣ 출력예제 1
17
설명 : 3개의 DVD용량이 17분짜리이면 (1, 2, 3, 4, 5) (6, 7), (8, 9) 이렇게 3개의 DVD로 녹음을 할
수 있다. 17분 용량보다 작은 용량으로는 3개의 DVD에 모든 영상을 녹화할 수 없다.
 */
public class 뮤직비디오 {
	static int n;
	static int m;
	static int[] songs;
	
	static class Decision {
		private final int songCnt;
		private final int albumCnt;
		private final int[] songs;
		
		public Decision(int albumCnt, int[] songs) {
			this(songs.length, albumCnt, songs);
		}
		
		public Decision(int songCnt, int albumCnt, int[] songs) {
			this.songCnt = songCnt;
			this.albumCnt = albumCnt;
			this.songs = songs;
		}
		
		public int search() {
			IntSummaryStatistics statistics = Arrays.stream(songs).summaryStatistics();
			
			int result = -1;
			//가장 적은 곡 수, 음반 하나에 가장 긴 곡 한 개
			int lt = statistics.getMax();
			//가장 많은 곡 수, 음반에 모든 음악이 들어감
			int rt = (int) statistics.getSum();
			
			while (lt <= rt) {
				int mid = lt + rt >> 1;
				//조건에 충족하냐
				if (count(mid) <= albumCnt) {
					//일단 충족만 하면 답으로 넣고, 더 정교한 답을 찾아간다.
					result = mid;
					rt = mid - 1;
				} else {
					lt = mid + 1;
				}
			}
			
			return result;
		}

		private int count(int mid) {
			int cnt = 1; //일단 앨범 한 개 사용
			int sum = 0;
			for (int i = 0; i < songCnt; i++) {
				if (sum + songs[i] > mid) {
					sum = songs[i];
					cnt++;
				} else {
					sum += songs[i];
				}
			}
			return cnt;
		}
	}
	
	
	public static void main(String[] args) {
		input();
		System.out.println(new Decision(n, m, songs).search());
	}
	
	private static void input() {
		Scanner sc = new Scanner(System.in);
		n = sc.nextInt();
		m = sc.nextInt();
		
		songs = new int[n];
		for(int i = 0; i < n; i++) {
			songs[i] = sc.nextInt();
		}
		sc.close();
	}
}
