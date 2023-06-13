package datastructure;

import java.util.Objects;
import java.util.stream.StreamSupport;

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
		
		Node<T> shift(){
			if(head == null) return null;
			Node<T> tmp = head;
			head = tmp.next;
			length--;
			if(length == 0) tail = null;
			return tmp;
		}
		
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
		
		Node<T> get(int index){
			//유효범위
			if( !(0<= index && index<length) ) return null;
			Node<T> target = head;
			for(int i=0;i<index;i++) {
				target = target.next;
			}
			return target;
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
		
		for(int i = 0;i<5;i++) {
			list.push(String.valueOf(1+i));
		}
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
		System.out.println(list.get(-1));
		
		
		
	}
}
