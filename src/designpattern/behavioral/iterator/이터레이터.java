package designpattern.behavioral.iterator;

/**
 * 기본 표현을 노출하지 않고 집계 개체의 요소에 순차적으로 액세스하는 방법을 제공합니다.
 * 
 * GoF에 따르면 반복자 패턴은 "기본 구현을 노출하지 않고 집계 개체의 요소에 순차적으로 액세스하는 데" 사용됩니다.
반복자 패턴은 커서라고도 합니다.
컬렉션 프레임워크에서 우리는 이제 Enumeration보다 선호되는 Iterator를 사용하고 있습니다.
 */
public class 이터레이터 {
	
	static interface Iterator<E>{
		boolean hasNext();
		E next();
	}
	static interface Iterable<E>{
		Iterator<E> iterator(); 
	}
	
	static class Box<E> implements Iterable<E>{
		E[] data;
		
		public Box(E[] data) {
			this.data = data;
		}

		public Iterator<E> iterator() {
			return new Iterator<E>() {
				int cursor = 0;
				@Override
				public boolean hasNext() {
					return cursor<data.length;
				}
				@Override
				public E next() {
					return data[cursor++];
				}
				
			};
		}
	}
	public static void main(String[] args) {
		Box<String> box = new Box<>(new String[] {"하나","둘","셋"});
		for (Iterator<String> iterator = box.iterator(); iterator.hasNext();) {
			String data = iterator.next();
			System.out.println(data);
		}
		
	}
	
	
	
}
