package designpattern.structural.composite;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class SimpleComposite {
	
	abstract static class Component{
		
		abstract String getName();
		abstract boolean isLeaf();
		void addComponent(Component component) {
			throw new UnsupportedOperationException();
		}
		List<Component> getComponents(){
			throw new UnsupportedOperationException();
		}
		
		abstract void print();
	}
	
	static class Leaf extends Component{
		String name;
		public String getName() {
			return name;
		}
		boolean isLeaf() {
			return true;
		}
		public Leaf(String name) {
			this.name = name;
		}


		void print() {
			System.out.println("[Leaf]"+name);
		}
	}
	static class Composite extends Component{
		String name;
		List<Component> components = new ArrayList<>();
		
		public Composite(String name) {
			this.name = name;
		}
		public String getName() {
			return name;
		}
		boolean isLeaf() {
			return false;
		}
		void addComponent(Component component) {
			components.add(component);
		}
		public List<Component> getComponents() {
			return components;
		}
		void print() {
			System.out.println("[Composite]"+name);
			for(Component component : components) {
				component.print();
			}
		}
	}
	
	public static void main(String[] args) {
		Component root = new Composite("회장님");
		root.addComponent(new Leaf("홍 비서"));
		root.addComponent(new Leaf("박 비서"));
		
		Component composite1 = new Composite("전무");
		composite1.addComponent(new Leaf("김 비서"));
		Component composite2 = new Composite("이사");
		composite2.addComponent(new Leaf("이 비서"));
		
		root.addComponent(composite1);
		root.addComponent(composite2);
		
		Component composite1_1 = new Composite("영업팀");
		composite1_1.addComponent(new Leaf("임꺽정 팀장"));
		
		Component composite2_1 = new Composite("기획팀");
		composite2_1.addComponent(new Leaf("홍길동 팀장"));
		
		composite1.addComponent(composite1_1);
		composite2.addComponent(composite2_1);
		
		
//		root.print();
		BFS(root);
	}
	
	static void BFS(Component root) {
		Queue<Component> components = new LinkedList<>();
		components.offer(root);
		int lv = 1;
		
		while(!components.isEmpty()) {
			int len = components.size();
			System.out.print(lv++ +" ");
			for(int i=0;i<len;i++) {
				Component compo = components.poll();
				System.out.print(compo.getName()+" ");
				if(!compo.isLeaf()) {
					for(Component com : compo.getComponents()) {
						components.add(com);
					}
				}
			}
			System.out.println();
		}
	}
	
}
