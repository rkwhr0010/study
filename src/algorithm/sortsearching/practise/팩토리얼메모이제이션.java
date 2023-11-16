package algorithm.sortsearching.practise;

import java.util.Arrays;

public class 팩토리얼메모이제이션 {
	static int len;
	static int[] chkArr;
	
	public static void main(String[] args) {
		len = 40;
		chkArr = new int[len+1];
		DFS(40);
		System.out.println(Arrays.toString(chkArr));
	}
	static int DFS(int i) {
		if(chkArr[i]!=0) return chkArr[i];
		if(i==1||i==2) return chkArr[i]=1;
		else {
			return chkArr[i] = DFS(i-1) + DFS(i-2);
		}
	}
}
