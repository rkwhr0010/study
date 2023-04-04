package javabasic.generics;

import java.util.Arrays;

public class GenericsArr {
	//지네릭은 컴파일 시 제거되어 그자리에 Object와 형변환이 클래스파일에 남게 된다.
	static class Arr<E>{
		E[] arr;
		public Arr(int size) {
			arr = (E[])new Object[size];
		}
		
		E get(int index) {
			return arr[index];
		}
		void set(int index, E data) {
			arr[0] = data;
		}
		@Override
		public String toString() {
			return Arrays.toString(arr);
		}
	}
	public static void main(String[] args) {
		Arr<String> arr = new Arr<String>(10);
		arr.set(0, "안뇽");
		System.out.println(arr);
	}
}
