package algorithm.greedy.practise;

import java.util.*;
class Edge implements Comparable<Edge>{
    public int v1;
	public int v2;
	public int cost;
    Edge(int v1, int v2, int cost) {
        this.v1 = v1;
		this.v2 = v2;
        this.cost = cost;
    }
    @Override
    public int compareTo(Edge ob){
        return this.cost-ob.cost;
    }
}

class Main {
	static int[] u;
	public static int Find(int v){
		if(v==u[v]) return v;
		else return u[v]=Find(u[v]);
	}
	public static void Union(int a, int b){
		u[a]=b;
	}
	public static void main(String[] args){
		Scanner kb = new Scanner(System.in);
		int n=kb.nextInt();
		int m=kb.nextInt();
		u=new int[n+1];
		ArrayList<Edge> arr=new ArrayList<>();
		for(int i=1; i<=n; i++) u[i]=i;
		for(int i=0; i<m; i++){
			int a=kb.nextInt();
			int b=kb.nextInt();
			int c=kb.nextInt();
			arr.add(new Edge(a, b, c));
		}
		int answer=0;
		Collections.sort(arr);
		for(Edge ob : arr){
			int fv1=Find(ob.v1);
			int fv2=Find(ob.v2);
			if(fv1!=fv2){
				answer+=ob.cost;
				Union(fv1, fv2);
			}
		}
		System.out.println(answer);
	}
}