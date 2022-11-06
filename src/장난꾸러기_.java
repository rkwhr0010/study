import java.util.*;
//원래 키순으로 정렬된 배열에서 한 친구가 자리를 바궜다.
//그래서 배열이 뒤틀어져 있다. 
// 이 상황에서 자리를 바꾼 친구 두 친구 번호를 찾기
class 장난꾸러기_ {	
	public static void main(String[] args){
		int[] arr=new int[] {120 ,125, 152, 130, 135, 135, 143, 127, 160};
		ArrayList<Integer> answer=new ArrayList<>();
		int[] arr2 = new int[arr.length];
		
		for(int i=0;i<arr.length;i++) {
			arr2[i] = arr[i];
		}
		for(int i=0;i<arr.length;i++) {
			for(int j=i+1;j<arr.length;j++) {
				if(arr[i]>arr[j]) {
					int tmp = arr[i];
					arr[i] = arr[j];
					arr[j] = tmp;
				}
			}
		}
		for(int i=0;i<arr.length;i++) {
			if(arr[i]!=arr2[i]) answer.add(i+1);
		}
		
		System.out.println(answer);
	}
}