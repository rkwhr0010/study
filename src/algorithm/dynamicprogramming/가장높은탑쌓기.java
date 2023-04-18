package algorithm.dynamicprogramming;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Scanner;
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
	public int solution(ArrayList<Brick> arr){
		int answer=0;
		Collections.sort(arr);
		dy[0]=arr.get(0).heigth;
		answer=dy[0];
		for(int i=1; i<arr.size(); i++){
			int max_h=0;
			for(int j=i-1; j>=0; j--){
				if(arr.get(j).weight > arr.get(i).weight && dy[j]>max_h){
					max_h=dy[j];
				}
			}
			dy[i]=max_h+arr.get(i).heigth;
			answer=Math.max(answer, dy[i]);
		}
		return answer;
	}
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
	
	public static void main(String[] args){
		가장높은탑쌓기 T = new 가장높은탑쌓기();
		Scanner kb = new Scanner(System.in);
		int n=kb.nextInt();
		ArrayList<Brick> arr=new ArrayList<>();
		dy=new int[n];
		for(int i=0; i<n; i++){
			int a=kb.nextInt();
			int b=kb.nextInt();
			int c=kb.nextInt();
			arr.add(new Brick(a, b, c));
		}
		kb.close();
		System.out.print(T.sola(arr));
	}
}
/*
 * 
5
25 3 4
4 4 6
9 2 3
16 2 5
1 5 2

10
 * 
 */

