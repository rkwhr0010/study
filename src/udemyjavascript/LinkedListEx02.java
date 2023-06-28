package udemyjavascript;

import java.util.StringJoiner;
import java.util.stream.IntStream;

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
		//맨 뒤 추가
		Node<T> push(T val){
			Node<T> newNode = new Node<>(val);
			if(length == 0) {
				head = newNode;
			} else {
				tail.next = newNode;
				newNode.prev = tail;
			}
			length++;
			tail = newNode;
			
			return tail;
		}
		//맨 뒤 제거/ 리턴
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
		//맨앞 제거/리턴
		Node<T> shift(){
			if(length == 0) return null;
			Node<T> result = head;
			
			head = head.next;
			if(length == 1) {
				tail = null;
			}else {
				head.prev = null;
			}
			
			length--;
			//노드가 아닌 값을 리턴할 거면 필요없는 코드
			result.next = null;
			return result;
		}
		//첫자리에 삽입
		DoublyLinkedList<T> unShift(T val) {
			if(length == 0) {
				push(val);
			} else {
				Node<T> newNode = new Node<>(val);
				//헤더 연결
				newNode.next = head;
				//헤더 이전에 신규 노드 연결
				head.prev = newNode;
				//이전 헤드를 신규 헤드로(신규 노드)
				head = head.prev;
				length++;
			}
			
			return this;
		}
		//데이터 찾기
		Node<T> get(int index){
			Node<T> result = null;
			if( !(0 <= index && index < length) ) {
				return result;
			}
			
			int start = 0;
			int end = length-1 ;
			//tail 시작
			if(end >> 1 <= index) {
				result = tail;
				while(index != end) {
					result = result.prev;
					end--;
				}
			//head 시작
			} else {
				result = head;
				while(index != start) {
					result = head.next;
					start++;
				}
			}
			
			return result;
		}
		
		//데이터 수정
		Boolean set(int index, T val) {
			return true;
		}
		
		boolean insert(int index, T val){
			return true;
		}
		
		Node<T> remove(int index){
			return null;
		}
		
		
		DoublyLinkedList<T> reverse() {
			return this;
		}
		
		public String toString() {
			return "\n head : "+ head + " tail : " + tail + " length : "+length+"\n"+toString(head);
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
		IntStream.range(1, 11).forEach(list::push);
		System.out.println(list);
		System.out.println(list.get(1));
		System.out.println(list.get(3));
		System.out.println(list.get(5));
		System.out.println(list.get(6));
		System.out.println(list.get(7));
//		System.out.println(list.shift());
//		System.out.println(list.shift());
//		System.out.println(list.shift());
//		System.out.println(list);
//		System.out.println(list.shift());
//		System.out.println(list.shift());
//		System.out.println(list.shift());
//		System.out.println(list);
//		System.out.println(list.shift());
//		System.out.println(list.shift());
//		System.out.println(list.shift());
//		System.out.println(list.shift());
//		System.out.println(list.shift());
//		System.out.println(list);
		
	}
}
