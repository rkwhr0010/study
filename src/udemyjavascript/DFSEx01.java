package udemyjavascript;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DFSEx01 {
	static class Node<T>{
		public T data;
		public Node<T> lt,rt;
		public Node(T data) {
			this.data = data;
		}
		@Override
		public String toString() {
			return data.toString();
		}
	}
	
	public static void main(String[] args) {
		Node<Integer> root = new Node<Integer>(1);
		root.lt = new Node<Integer>(2);
		root.rt = new Node<Integer>(3);
		root.lt.lt = new Node<Integer>(4);
		root.lt.rt = new Node<Integer>(5);
		root.rt.lt = new Node<Integer>(6);
		root.rt.rt = new Node<Integer>(7);
		
		DFS1(root);
		for(List<?> li : map.values()) {
			System.out.println(li);
		}
	}
	static Map<String,List<Object>> map = new HashMap<>();
	static {
		map.put("전위",new ArrayList<>());
		map.put("중위",new ArrayList<>());
		map.put("후위",new ArrayList<>());
	}
	
	static void DFS1(Node<?> node) {
		//리프노드 판별
		if(node == null) return;
		map.compute("전위", (k,v)->{
			v.add(node.data);
			return v;
		});
		DFS1(node.lt);
		map.compute("중위", (k,v)->{
			v.add(node.data);
			return v;
		});
		DFS1(node.rt);
		map.compute("후위", (k,v)->{
			v.add(node.data);
			return v;
		});
	}
}
