package algorithm.dynamicprogramming.practise;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

/**
 * 넓은 벽돌은 바닥으로
 * 두 벽돌이 있다면, 더 가벼운 벽돌이 위로 가야한다.
 */
public class 가장높은탑쌓기연습{
	
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
		public String toString() {
			return String.valueOf(width);
		}
	}
	static int[] dy;
	
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
		System.out.print(sol1(arr));
	}
	
	static int sol2(ArrayList<Brick> arr) {
		Collections.sort(arr);
		//dy 는 따로 초기화하지 않았으므로, 기본형인 0 으로 될 것
		dy[0] = arr.get(0).heigth;
		//메인 순회
		for(int i=1/*1부터*/;i<arr.size();i++) {
			Brick now = arr.get(i);
			int hei = 0;
			//메인 순회 이전부터 탐색해 나아간다.
			for(int j=i-1;j>=0;j--) {
				Brick prev = arr.get(j);
				//현재 무게가, 이전 블록 무게보다 작아야 쌓을 수 있다.
				if(now.weight < prev.weight 
						//첫 순회 hei는 0이므로 dy[j]가 크다면 이미 저장된 값이 존재하는지 체크하는 용도다.
						//이후 순회는 게중 가장큰 것이 존재를 찾는다.
						&& dy[j]>hei) {
					hei = dy[j];
				}
			}
			dy[i] = hei + now.heigth;
		}
		return Arrays.stream(dy).max().getAsInt();
	}
	
	static int sol1(ArrayList<Brick> arr) {
		Collections.sort(arr);
		dy[0] = arr.get(0).heigth;
		for(int i=1/*1부터*/;i<arr.size();i++) {
			Brick now = arr.get(i);
			//i 이전 전부 탐색
			int hei = 0;
			for(int j=i-1;j>=0;j--) {
				Brick prev = arr.get(j);
				if(now.weight<prev.weight && dy[j]>hei) {
					hei = dy[j];
				}
			}
			dy[i] = hei + now.heigth;
		}
		return Arrays.stream(dy).max().getAsInt();
	}
	
	
	
	static int sol(ArrayList<Brick> arr) {
		Collections.sort(arr);
		dy[0] = arr.get(0).heigth;
		
		for(int i=1;i<arr.size();i++) {
			int hei = 0;
			for(int j=i-1;j>=0;j--) {
				if(arr.get(i).weight<arr.get(j).weight&&dy[j]>hei) {
					hei = dy[j];
				}
			}
			dy[i] = hei + arr.get(i).heigth;
		}
		return Arrays.stream(dy)
					.max()
					.getAsInt();
	}
	
	
}

/*  10 */

