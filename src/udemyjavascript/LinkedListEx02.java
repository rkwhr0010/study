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
				return "{"+prevVal+", "+val+", "+nextVal+"}";
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
			
			//tail 시작
			if(length-1 >> 1 <= index) {
				result = tail;
				for(int end = length-1 ; index != end ; end--) {
					result = result.prev;
					
				}
			//head 시작
			} else {
				result = head;
				for(int start = 0; start != index ; start++) {
					result = result.next;
				}
			}
			
			return result;
		}
		
		//데이터 수정
		Boolean set(int index, T val) {
			Node<T> node = get(index);
			if(node == null) {
				
				return false;
			} else {
				node.val = val;
				
				return true;
			}
		}
		
		boolean insert(int index, T val){
			Node<T> node = get(index);
			if(node == null) return false;
			
			if(index == 0) {
				unShift(val);
				
				return true;
			}
			if(index == length-1) {
				push(val);
				
				return true;
			}
			
			Node<T> newNode = new Node<>(val);
			Node<T> prevNode = node.prev;
			
			newNode.next = node;
			node.prev = newNode;
			
			prevNode.next = newNode;
			newNode.prev = prevNode;
			length++;
			
			return true;
		}
		
		Node<T> remove(int index){
			Node<T> removeNode = get(index);
			if(removeNode == null) return removeNode;
			if(index == 0) return shift();
			if((length - 1) == index) return pop();
			//여기 도달 하려면 최소 length는 반드시 3 이상
			
			Node<T> prevNode = removeNode.prev;
			Node<T> nextNode = removeNode.next;
			prevNode.next = nextNode;
			nextNode.prev = prevNode;
			length--;
			
			return removeNode;
		}
		
		
		DoublyLinkedList<T> reverse() {
			//필요한 참조 변수 3개
			Node<T> curNode = null;
			Node<T> nextNode = null;
			Node<T> prevNode = null;
			
			//헤더, 테일 교환
			curNode = head;
			head = tail;
			tail = curNode;
			
			//시작은 이전 헤더(현재 테일)
			for(int i = 0; i < length; i++) {
				nextNode = curNode.next; //다음 노드 참조 저장
				
				curNode.next = prevNode; //핵심 목표, 내 다음이 이전 노드(리버스)
				curNode.prev = nextNode; //핵심 목표, 내 이전이 다음 노드(리버스)
				
				prevNode = curNode;
				curNode = nextNode;
			}
			
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
		IntStream.rangeClosed(1, 10).forEach(list::push);
		System.out.println(list);
		System.out.println(list.reverse());
//		System.out.println(list);
		
	}
}
