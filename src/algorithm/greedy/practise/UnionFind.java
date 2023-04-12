package algorithm.greedy.practise;

import java.util.Arrays;
import java.util.Scanner;

public class UnionFind {
	
	static int[] unf; //집합번호를 저장하는 배열 unf[1] = 5 ; 1은 5 그룹에 속한다.
	
	public static int Find(int v){
		if(v==unf[v]) {
			return unf[v]; // v는 
		} else {
			System.out.println(Arrays.toString(unf));
			unf[v]=Find(unf[v]);
			System.out.println(Arrays.toString(unf));
			System.out.println();
			return unf[v];
		}
	}
	
	//두 수를 집합 맺어줌
	public static void Union(int a, int b){
		int fa=Find(a);
		int fb=Find(b);
		if(fa!=fb) unf[fa]=fb;
	}
	
	static void uni(int a, int b) {
		int fa = find(a);
		int fb = find(b);
		if(fa!=fb) unf[fa] = fb;
	}
	
	static int find(int v) {
		if(v==unf[v]) return v;
		else return unf[v] = find(unf[v]);
	}
	
	
	public static void main(String[] args){
		Scanner kb = new Scanner(System.in);
		int n=kb.nextInt(); //반 학생수
		int m=kb.nextInt(); //숫자 쌍 수
		unf=new int[n+1];
		for(int i=1; i<=n; i++) unf[i]=i;
		for(int i=1; i<=m; i++){
			int a=kb.nextInt();
			int b=kb.nextInt();
			Union(a, b);
		}
		int a=kb.nextInt(); 
		int b=kb.nextInt();//a와 b는 친구니?
		int fa=Find(a);
		int fb=Find(b);
		System.out.println("fa "+fa +"  fb "+fb);
		if(fa==fb) System.out.println("YES");
		else System.out.println("NO");		
	}
}
/*
9 7
1 2
2 3
3 4
1 5
6 7
7 8
8 9
3 8



*/