package algorithm.etc;
//1 가위   2 바위   3 보
public class 가위바위보_ {
	//메인 컨셉 , 한쪽으로 이기는 경우의 수만 나열한다.
	//그러면 나머지는 반대 쪽이 이기는 것이다.
	public static void main(String[] args) {
		StringBuilder sb = new StringBuilder();
		int[] a= {1,1,2,3,2,3};
		int[] b= {3,2,1,1,2,3};
		        //A,B,A,B,D,D
		for(int i=0;i<a.length;i++) {
			//비김
			if(a[i]==b[i]) sb.append("D");
			//A가 이기는 경우의 수 전부 나열
			else if(a[i]==1 && b[i]==3 ) sb.append("A");
			else if(a[i]==2 && b[i]==1 ) sb.append("A");
			else if(a[i]==3 && b[i]==2 ) sb.append("A");
			//이외는 B가 이긴 것
			else sb.append("B"); 
		}
		System.out.println(sb);
		
		sb.delete(0, sb.length());
		
	}
}
