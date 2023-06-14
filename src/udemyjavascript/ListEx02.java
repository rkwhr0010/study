package udemyjavascript;
/*
리스트 기반 리스트 특징
1. 구조체와 포인터를 함께 사용
2. 필요할 때마다 메모리 공간 할당
3. 중간 지점에 노드 삽입,삭제에 유리
 */
public class ListEx02 {
	static class ArrayList{
		int[] arr;
		int size = 0;
		
		public ArrayList() {
			this(4);
		}
		public ArrayList(int size) {
			arr = new int[size];
		}
		void addBack(int data) {
			sizeChk();
			arr[size++] = data;
		}
		
		//모든 요소를 한 칸씩 뒤로 미는 비효율 발생
		void addFirst(int data) {
			sizeChk();
			for(int i=size;i>=1;i--) {
				arr[i] = arr[i-1];
			}
			arr[0]=data;
			size++;
		}
		void removeAt(int index) {
			for(int i=index;i<size-1;i++) {
				arr[i] = arr[i+1];
			}
		}
		//크기가 고정으로 할당된다. 따라서 허용량 초과 시 새로운 배열을 만들어야 한다.
		private void sizeChk() {
			if(arr.length == size) sizeUp();
		}
		private void sizeUp() {
			int[] newArr = new int[(size>>1)+size];//1.5배 길이
			System.arraycopy(arr, 0, newArr, 0, size);
			arr = newArr;
		}
		
		@Override
		public String toString() {
			StringBuilder sb = new StringBuilder();
			for(int i=0;i<size-1;i++) {
				sb.append(arr[i]).append(", ");
			}
			return sb.toString();
		}
	}
	
	public static void main(String[] args) {
		ArrayList list = new ArrayList();
		list.addBack(3);
		list.addBack(4);
		list.addBack(5);
		list.addBack(6);
		list.addFirst(2);
		list.addFirst(1);
		
		list.removeAt(3);
		
		System.out.println(list);
	}
	
	
}
