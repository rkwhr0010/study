package udemyjavascript;

import java.util.StringJoiner;

public class LinkedListEx02 {
	static class DoublyLinkedList<T>{
		private static class Node<T>{
			T val;
			Node<T> next;
			Node<T> prev;
			
			Node(T val) {
				this.val = val;
			}
			public String toString() {
				return val.toString();
			}
		}
		
		Node<T> head;
		Node<T> tail;
		int length;
		
		Node<T> push(T val){
			Node<T> newNode = new Node<>(val);
			if(length == 0) {
				head = newNode;
				tail = newNode;
				length++;
			} else {
				tail.next = newNode;
				newNode.prev = tail;
			}
			tail = newNode;
			
			return tail;
		}
		
		public String toString() {
			// TODO Auto-generated method stub
			return toString(head);
		}
		private String toString(Node<T> node) {
			StringJoiner sb = new StringJoiner(", ", "[", "]");
			Node<T> cur = node;
			while(cur != null) {
				sb.add(cur.toString());
				cur = cur.next;
			}
			return sb.toString();
		}
		
	}
	
	public static void main(String[] args) {
		DoublyLinkedList<Integer> list = new DoublyLinkedList<>();
		list.push(1);
		list.push(2);
		list.push(3);
		list.push(4);
		System.out.println(list);
		
	}
}
