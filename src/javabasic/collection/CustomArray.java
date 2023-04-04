package javabasic.collection;

import java.util.Arrays;

public class CustomArray {
	static class MyArr{
		int[] arr;
		int cursor;
		
		public MyArr() {
			arr = new int[5];
			cursor = 0;
		}
		public int size() {
			return cursor;
 		}
		public int length() {
			return arr.length;
		}
		
		public void add(int value) {
			if(arr.length> cursor) {
				arr[cursor++] = value;
			} else {
				sizeUp();
				arr[cursor++] = value;
			}
		}
		private void sizeUp() {
			int[] newArr = new int[arr.length + (arr.length>>1) ];
			System.arraycopy(arr, 0, newArr, 0, arr.length);
			arr = newArr;
		}
		
		@Override
		public String toString() {
			return Arrays.toString(arr)+" cursor: "+size()+" length:"+length();
		}
	}
	
	
	public static void main(String[] args) {
		MyArr arr = new MyArr();
		arr.add(10);
		arr.add(10);
		arr.add(10);
		arr.add(10);
		arr.add(10);
		System.out.println(arr);
		arr.add(10);
		System.out.println(arr);
		arr.add(10);
		System.out.println(arr);
		arr.add(10);
		System.out.println(arr);
		arr.add(10);
		System.out.println(arr);
		arr.add(10);
		System.out.println(arr);
		arr.add(10);
		System.out.println(arr);
		
	}
}
