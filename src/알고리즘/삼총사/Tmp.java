package 알고리즘.삼총사;
public class Tmp {
	
//	static int [] intArr = new int[] {-2,3,0,2,-5};
	static int [] intArr = new int[] {-3,-2,-1,0,1,2,3};
	static int cnt = 0;
	
	static int[] combi = new int[3]; //삼총사....
	
	public static void main(String[] args) {
		int lv = 0;
		DFS(lv,0);
		System.out.println("총 삼총사 수  = " + cnt );
	}
	
	static void DFS(int lv, int s) {
		if(lv == 3) {
			int sum = 0;
			for(int x : combi) {
				sum+=intArr[x];
				System.out.print(x+" ");
			}
			System.out.println("sum = " + sum + (sum==0?" 삼총사군요!! "+ ++cnt:"") );
		}else {
			for(int i =s; i< intArr.length;i++ ) {
				combi[lv] = i;
				DFS(lv+1, i+1);
			}
		}
	}
}
