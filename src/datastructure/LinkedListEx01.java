package datastructure;

import java.util.Objects;

public class LinkedListEx01 {
	static class Node<T>{
		T data;
		Node next;
		public Node(T data) {
			this.data = data;
		}
		@Override
		public String toString() {
			return this.data.toString();
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
		list.push("하나");
		list.push("둘");
		list.push("셋");
		list.push("넷");
		list.push("다섯");
		System.out.println(list);
		list.shift();
		list.shift();
		list.shift();
		list.shift();
		System.out.println(list);
		list.shift();
		list.shift();
		System.out.println(list);
		
		
	}
}
