package designpattern.headfirst.chapter7;

import java.util.Arrays;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.List;

public class EnumIterAdapter {
	static class EnumerationIterator implements Iterator<Object>{
		Enumeration<?> enumeration;
		public EnumerationIterator(Enumeration<?> enumeration) {
			this.enumeration = enumeration;
		}
		@Override
		public boolean hasNext() {
			return enumeration.hasMoreElements();
		}
		@Override
		public Object next() {
			return enumeration.nextElement();
		}
		public void remove() {
			throw new UnsupportedOperationException();
		}
	}
	static class IteratorEnumeration implements Enumeration<Object>{
		Iterator<?> iterator;
		public IteratorEnumeration(Iterator<?> iterator) {
			this.iterator = iterator;
		}
		@Override
		public boolean hasMoreElements() {
			return iterator.hasNext();
		}
		@Override
		public Object nextElement() {
			return iterator.next();
		}
	}
	
	public static void main(String[] args) {
		List<Integer> list = Arrays.asList(1,2,3,4,5);
		
	}
	
}
