package udemyjavascript;

import java.util.StringJoiner;

public class LinkedListEx02 {
	static class DoublyLinkedList<T>{
		private static class Node<T>{
			T val;
			Node<T> prev;
			Node<T> next;
			
			Node(T val) {
				this.val = val;
			}
			public String toString() {
				String prevVal = prev == null ? "null" : prev.val.toString();
				String nextVal = next == null ? "null" : next.val.toString();
				return "{"+prevVal+", "+val.toString()+", "+nextVal+"}";
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
			} else {
				tail.next = newNode;
				newNode.prev = tail;
			}
			length++;
			tail = newNode;
			
			return tail;
		}
		
		Node<T> pop(){
			Node<T> result;
			if(length == 0) 
				return null;
			
			result = tail;
			
			tail = tail.prev;
			
			if(length == 1) 
				head = null;
			else {
				tail.next = null;
				//노드가 아닌 값을 리턴할 거면 필요없는 코드
				result.prev = null;
			}
			length--;
			
			return result;
		}
		
		
		public String toString() {
			return "length : "+length+"\n"+toString(head);
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
		System.out.println(list.pop());
		System.out.println(list.pop());
		System.out.println(list.pop());
		System.out.println(list);
		System.out.println(list.pop());
		System.out.println(list.pop());
		System.out.println(list);
		
	}
}
