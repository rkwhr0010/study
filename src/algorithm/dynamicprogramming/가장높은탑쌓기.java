package algorithm.dynamicprogramming;
import java.util.ArrayList;
import java.util.Collections;
<<<<<<< HEAD

/**
 * 벽돌은 가장 넓은 것이 아래에 위치
 * 그리고 가장 무거운 것일 수록 아래 위치
 */
public class 가장높은탑쌓기{


=======
class 가장높은탑쌓기{
>>>>>>> branch 'master' of https://github.com/rkwhr0010/best_practise.git
	static class Brick implements Comparable<Brick>{
		public final int width, heigth, weight;
		public Brick(int width, int heigth, int weight) {
			this.width = width;
			this.heigth = heigth;
			this.weight = weight;
		}
		public int compareTo(Brick o){
			return o.width-this.width;
		}
	}
	
	static int[] dy;
	static ArrayList<ArrayList<Brick>> list;
	
	public static void main(String[] args){
		int[][] input = 
			{
					{25, 3, 4 }
					,{4 ,4 ,6  }
					,{9 ,2 ,3  }
					,{16, 2, 5 }
					,{1 ,5 ,2  }
			};
		int n=input.length;
		
		ArrayList<Brick> arr=new ArrayList<>();
		
		dy=new int[n];
		for(int i=0; i<n; i++){
			int a= input[i][0] ;
			int b= input[i][1] ;
			int c= input[i][2] ;
			arr.add(new Brick(a, b, c));
		}
		System.out.print(solution(arr));
	}
	
	static int solution(ArrayList<Brick> arr){
		//너비 순으로 내림차순
		Collections.sort(arr);
		dy[0]=arr.get(0).heigth;
		int answer= dy[0];
		
		//높이 비교 1부터
		for(int i=1; i<arr.size(); i++){
			//높이 tmp
			int max_h=0;
			//이전 벽돌 비교
			for(int j=i-1; j>=0; j--){
				//무게는 j가 커야한다. 그리고 그 중에 제일 높은 값을 구한다.
				if(arr.get(j).weight > arr.get(i).weight && dy[j]>max_h){
					max_h=dy[j];
				}
			}
			//제일 높은 값을 구했으니, 그 값에 나의 높이를 더 한다.
			dy[i]=max_h+arr.get(i).heigth;
			//지금까지 높이 중 가장 큰 높이를 구한다.
			answer=Math.max(answer, dy[i]);
		}
		return answer;
	}
<<<<<<< HEAD
	static int sol2(ArrayList<Brick> arr) {
		int ans = 0;
		Collections.sort(arr);
		dy[0] = arr.get(0).heigth;
		ans = dy[0];
		
		for(int i = 1 ; i < arr.size();i++) {
			int max = 0;
			for(int j = i-1; j>= 0;j--) {
				if(arr.get(i).weight>arr.get(j).weight && dy[j] >max)
					max = dy[j];
			}
			dy[i] = max+ arr.get(i).heigth ;
			ans = Math.max(ans, dy[i]);
		}
		return ans;
	}
	
	int sol(ArrayList<Brick> arr) {
		int ans = 0;
		//너비가 커야 그 위에 쌓을 수 있다.
		Collections.sort(arr);
		dy[0] = arr.get(0).heigth;
		ans = dy[0];
		
		for(int i = 1 ; i < arr.size() ; ++i) {
			int max_h= 0 ;
			Brick main = arr.get(i);
			for(int j = i-1; j>=0 ; --j ) {
				Brick other = arr.get(j);
				if(main.weight < other.weight && dy[j] > max_h ) {
					max_h = dy[j];
				}
			}
			dy[i] = arr.get(i).heigth + max_h;
			ans = Math.max(ans, dy[i]);
		}
		
		return ans;
	}
	
	int sola(ArrayList<Brick> arr) {
		Collections.sort(arr);
		dy[0] = arr.get(0).heigth;
		
		for(int i= 1;i<arr.size();i++) {
			int hei = 0;
			for(int j=i-1;j>=0;j--) {
				if(arr.get(i).weight < arr.get(j).weight && dy[j] > hei ) {
					hei = dy[j];
				}
			}
			dy[i] = hei + arr.get(i).heigth;
		}
		return Arrays.stream(dy).max().getAsInt();
	}
	
	int solb(ArrayList<? extends Brick> arr) {
		
		Collections.sort(arr);
		dy[0] = arr.get(0).heigth;
		
		for(int i= 1;i<arr.size();i++) {
			int hei = 0;
			for(int j=i-1;j>=0;j--) {
				if(arr.get(i).weight < arr.get(j).weight && dy[j] > hei ) {
					hei = dy[j];
				}
			}
			dy[i] = hei + arr.get(i).heigth;
		}
		return Arrays.stream(dy).max().getAsInt();
	}
	
=======
>>>>>>> branch 'master' of https://github.com/rkwhr0010/best_practise.git
}
