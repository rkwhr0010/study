package designpattern.headfirst.chapter7;

import java.util.Enumeration;
import java.util.Iterator;

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
		/*Enumeration는 원래 없던 기능이라 구현이 불가능하다. 
		 * 이처럼 어댑터는 반드시 일대일 대응되리란 보장이 없다. */
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
}
