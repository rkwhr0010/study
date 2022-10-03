import java.util.*;
import java.util.stream.IntStream;
/**
 * 같은 반을 비교하기 위한 반복문은 기준 반복문과 비교 반복문과 공유한다.
 */
class 임시반장정하기 {	
	public static void main(String[] args){
		int answer = 0;
		// 편의상 i, j  0 인덱스는 학생, 학년을 의미한다.
		// 즉, 1부터 집계를 시작한다. 경계값 주의
		final int STU_SIZE = 15;
		final int BAN_SIZE = 10;
		final int HAK_SIZE = 5;
		
		int max = Integer.MIN_VALUE;
		
		int[][] arr = IntStream.rangeClosed(0, STU_SIZE)
								.mapToObj(stu->{
									return new Random().ints(HAK_SIZE+1, 1, BAN_SIZE)
					        			            .toArray();
								}).toArray(int[][]::new);
		//0번 인덱스 초기화
		for(int i=0;i<arr.length;i++) arr[i][0] = i;
		for(int i=0;i<arr[0].length;i++) arr[0][i] = i;
		
		prettyPrint(arr);
		//기준 학생 x 축
		for(int i=1;i<=STU_SIZE;i++) {
			int count = -1; //나 자신을 제외함 (안해도 되지만 출력을 위함)
			//비교할 학생 x 축
			for(int j=1;j<=STU_SIZE;j++) {
				//학년 배열 y축 기준학생과 비교할 학생은 y축은 공유한다.
				for(int k=1;k<HAK_SIZE;k++) {
					//나 자신도 포함되지만, 모든 학생이 동등한 조건이므로 상관없다.
					if(arr[i][k]==arr[j][k]) {
						//일단 찾으면 더 이상 같은반 이였는지는 찾을 필요없다.(중복)
						count++;
						break;
					}
				}
			}
			//가장 많이 같은 반이였던 수
			if(max<count) {
				max = count;
				answer = i;
			}
		}
		System.out.println(answer+"번 학생 : 임시반장");
		System.out.println(max+"명과 같은 반 인적 있다. ");
	}
	static void prettyPrint(int[][] arr, boolean isIndex) {
		for(int i=0;i<arr.length;i++) {
			for(int j=0;j<arr[i].length;j++) {
				if(isIndex) System.out.printf("%2d,%2d ", i,j);
				else System.out.printf("%2s%2s" ,j==0&&i==0?"":arr[i][j]
						,(0<j&&i==0?"학년": 0<j&&i!=0 ?"    ": i!=0&&j==0?"번|": i==0&&j==0?"  |":"" ) );
			}
			System.out.println(isIndex?"\n":"");
		}
	}
	static void prettyPrint(int[][] arr) {
		prettyPrint(arr, false);
	}
}