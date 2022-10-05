import java.util.*;
import java.util.stream.IntStream;

class 격자판최대합_ {	
	public static void main(String[] args){
		int max = Integer.MIN_VALUE;
		int n = 10;
		
		int[][] arr = IntStream.range(0, n)
				               .mapToObj(index->{
			    	               return new Random().ints(n, 1, 100)
			    			                          .toArray();
			                   }).toArray(int[][]::new);
		prettyPrint(arr);
		
		int rowSum, colSum, cSum1=0,cSum2=0;
		
		for(int i= 0; i<n; i++) {
			rowSum=0;
			colSum=0;
			//핵심 같은 반복문이라도 구조가 유사하니
			//j , i 위치만 바꾸면 한방에 해결
			//구조 i==j , i+j == length-1 , [고정][순회], [순회],[고정]
			for(int j= 0; j<n ;j++) {
				rowSum += arr[i][j]; //같은 행 합
				colSum += arr[j][i]; //같은 열 합
			}
			cSum1+=arr[i][i];     //좌상우하 대각선 for문 1개만 필요
			cSum2+=arr[i][n-1-i]; //우상좌하 대각선
			
			// 내가 틀린 부분 기존 값이 신규 값보다 더 클 수 있다는 것을 간과
//			max = rowSum<colSum ? colSum : rowSum;
			max = max < rowSum ? rowSum 
			    : max < colSum ? colSum : max ;
		}
		max = max < cSum1 ? cSum1 
			: max < cSum2 ? cSum2 : max;
		
		System.out.println(max);
		
	}
	static void prettyPrint(int[][] arr, boolean isIndex) {
		for(int i=0;i<arr.length;i++) {
			for(int j=0;j<arr[i].length;j++) {
				if(isIndex) System.out.printf("%2d,%2d ", i,j);
				else System.out.printf("%2d" + (j==arr[i].length-1? "":",") ,arr[i][j]);
			}
			System.out.println(isIndex?"\n":"");
		}
	}
	static void prettyPrint(int[][] arr) {
		prettyPrint(arr, false);
	}
}