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
		@Override
		public String toString() {
			if(this.head == null) return "";
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
		
		System.out.println(list.toString());
		
		
	}
}
