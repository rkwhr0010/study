package 알고리즘.삼총사;
class Solution {
	static int [] chkArr = new int[3];//삼총사
	static int [] intArr;
	static int answer = 0;
	
    public int solution(int[] number) {
        intArr = number;
        DFS(0,0);
        return answer;
    }
    
    void DFS(int lv, int s) {
		if(lv == 3) {
			int sum = 0;
			for(int x : chkArr) sum+=intArr[x];
			if(sum == 0) answer++;
		}else {
			for(int i =s; i< intArr.length;i++ ) {
				chkArr[lv] = i;
				DFS(lv+1, i+1);
			}
		}
    }
    
}