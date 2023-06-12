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
		
		void push(T data) {
			//첫 저장
			if(Objects.isNull(head)) {
				head = new Node<T>(data);
				tail = head;
			}
			//이후 저장
			else {
				tail.next = new Node<T>(data);
				tail = tail.next;
			}
		}
		@Override
		public String toString() {
			return toString(head);
		}
		private String toString(Node<T> node) {
			if(node.next != null) return node.toString()+"=>"+toString(node.next);
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
		
		System.out.println(list.toString());
		
		
	}
}
