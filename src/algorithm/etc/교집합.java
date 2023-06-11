package algorithm.etc;
import java.util.*;
class 교집합 {	

	public static void main(String[] args){
		ArrayList<Integer> answer = new ArrayList<>();
		ArrayList<Integer> answer2 = new ArrayList<>();
		int [] a = new Random().ints(20, 0, 100).sorted().toArray();
		int [] b = new Random().ints(20, 0, 100).sorted().toArray();
		
		int p1=0,p2=0,count1=0,count2=0;
		while(p1<a.length&&p2<b.length) {
			if(a[p1]==b[p2]) {
				answer.add(a[p1]);
				p1++;
				p2++;
			}else if(a[p1]<b[p2]) {
				p1++;
			}else {
				p2++;
			}
			count1++;
		}
		
		
		for(int i=0;i<a.length;i++) {
			for(int j=0;j<b.length;j++) {
				count2++;
				if(a[i]==b[j]) {
					answer2.add(a[i]);
					break;
				}
			}
		}
		
		System.out.println(Arrays.toString(a));
		System.out.println(Arrays.toString(b));
		
		System.out.println(answer);
		System.out.println(answer2);
		
		System.out.println(count1);
		System.out.println(count2);
		
	}
}