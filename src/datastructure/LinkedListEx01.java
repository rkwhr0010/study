package datastructure;

import java.util.Objects;

public class LinkedListEx01 {
	static class Node<T>{
		T data;
		Node<T> next;
		public Node(T data) {
			this.data = data;
		}
		@Override
		public String toString() {
			return "[data:"+this.data.toString()+"]";
		}
	}
	
	static class SingleLinkedList<T>{
		Node<T> head;
		Node<T> tail;
		int length;
		
		//맨뒤에 추가
		SingleLinkedList<T> push(T data) {
			final Node<T> newNode = new Node<>(data);
			//첫 저장
			if(Objects.isNull(head)) {
				this.head = newNode;
				this.tail = newNode; // == this.head;
			}
			//이후 저장
			else {
				this.tail.next = newNode;
				this.tail = newNode;
			}
			length++;
			return this;
		}
		//맨뒤 제거/리턴
		Node<T> pop(){
			if(head == null) return null;//편의상 예외 회피

			Node<T> prev = head;
			//요소가 단 하나인 경우
			if(prev.next == null){
				head = null;
				tail = null;
				length--;
				return head;
			}
			
			//tail 직전까지 탐색
			while(prev.next != tail) {
				prev = prev.next;
			}
			tail = prev;      //직전 노드를 꼬리로
			tail.next = null; //tail에 연결된 다음 값 제거
			length--;
			return prev;
		}
		//맨앞 제거/리턴
		Node<T> shift(){
			if(head == null) return null;
			Node<T> tmp = head;
			head = tmp.next;
			length--;
			if(length == 0) tail = null;
			return tmp;
		}
		//첫자리에 삽입
		SingleLinkedList<T> unShift(T data) {
			Node<T> newNode = new Node<T>(data);
			//첫 삽입 시 tail은 head와 같은 값을 넣는다.
			if(head == null) 
				tail = newNode;
			//첫 삽입 시 head는 null일 것 하지만 상관없음
			newNode.next = head;
			//위, 아래 순서는 중요 아래 코드가 먼저오면 안된다.
			head = newNode;
			length++;
			return this;
		}
		//데이터 찾기
		Node<T> get(int index){
			//유효범위
			if( !(0<= index && index<length) ) return null;
			Node<T> target = head;
			for(int i=0;i<index;i++) {
				target = target.next;
			}
			return target;
		}
		
		//데이터 수정
		Boolean set(int index, T data) {
			Node<T> findNode = get(index);
			if(findNode == null) return false;
			findNode.data = data;
			return true;
		}
		
		boolean insert(int index, T data){
			//인덱스가 길이보다 크면 튕긴다. 따라서 첫 입력은 반드시 0만 허가된다.
			if(index>=0&&index > length) return false;
			//맨 처음 값 처리 및 인덱스 0인 경우 처리
			if(head == null||index == 0) {
				unShift(data);
				return true;
			}
			//여기 도달했다는 것은 무조건 인덱스 0은 채워져 있다
			//따라서 index-1이 무리가 없다.
			Node<T> prev = get(index-1);
			Node<T> newNode = new Node<>(data);
			newNode.next = prev.next;
			prev.next = newNode;
			length++;
			return true;
		}
		
		
		@Override
		public String toString() {
			if(this.head == null) return "없습니다.";
			return "["+ toString(head)+"] length :"+ this.length;
		}
		private String toString(Node<T> node) {
			if(node.next != null) return node.toString()+", "+toString(node.next);
			else return node.toString();
		}
	}
	
	public static void main(String[] args) {
		SingleLinkedList<String> list = new SingleLinkedList<>();
		
//		for(int i = 0;i<5;i++) {
//			list.push(String.valueOf(1+i));
//		}
//		System.out.println(list);
//		for(int i = 0;i<1000;i++) {
//			list.pop();
//		}
//		System.out.println(list);
//		for(int i = 0;i<5;i++) {
//			list.unShift(i+"");
//		}
//		System.out.println(list);
//		for(int i = 0;i<5;i++) {
//			list.shift();
//		}
		System.out.println(list);
//		list.push("하나");
//		list.push("둘");
//		list.push("셋");
//		list.push("넷");
//		list.push("다섯");
//		list.insert(2, "셋셋셋");
		list.insert(0, "영");
		list.insert(1, "일");
		list.insert(2, "이");
		list.insert(4, "이");
		System.out.println(list);
		
		
		
	}
}
