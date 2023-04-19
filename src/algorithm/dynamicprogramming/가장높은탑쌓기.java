package algorithm.dynamicprogramming;
import java.util.ArrayList;
import java.util.Collections;
class 가장높은탑쌓기{
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
}
